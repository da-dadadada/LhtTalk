/*
 * MIT License
 *
 * Copyright (c) 2017 leobert-lan
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */

package com.lht.lhttalk.module.smack.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.ConnectionListener;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.chat2.Chat;
import org.jivesoftware.smack.chat2.ChatManager;
import org.jivesoftware.smack.chat2.IncomingChatMessageListener;
import org.jivesoftware.smack.chat2.OutgoingChatMessageListener;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.roster.Roster;
import org.jivesoftware.smack.roster.RosterEntry;
import org.jivesoftware.smack.roster.RosterListener;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;
import org.jivesoftware.smackx.ping.PingFailedListener;
import org.jivesoftware.smackx.ping.PingManager;
import org.jxmpp.jid.EntityBareJid;
import org.jxmpp.jid.Jid;
import org.jxmpp.jid.impl.JidCreate;
import org.jxmpp.stringprep.XmppStringprepException;

import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Collection;

//import org.jivesoftware.smack.chat.Chat; deprecate


public class SmackConnection implements ConnectionListener,
        //ChatManagerListener,
        RosterListener,
        //ChatMessageListener, use incoming and outgoing
        IncomingChatMessageListener,
        OutgoingChatMessageListener,
        PingFailedListener {


    public enum ConnectionState {
        CONNECTED, CONNECTING, RECONNECTING, DISCONNECTED;
    }

    private static final String TAG = "SMACK";
    private final Context mApplicationContext;
    private final String mToken;
    private final String mUsername;
    private final String mServiceName = "vsochina.com";

    private XMPPTCPConnection mConnection;
    private ArrayList<String> mRoster;
    private BroadcastReceiver mReceiver;

    private String jabberId;

    public SmackConnection(Context pContext, String username, String token) {
        Log.i(TAG, "ChatConnection()");

        mApplicationContext = pContext.getApplicationContext();
        mToken = token;
        mUsername = username;
        refreshJabberId(mUsername);
    }

    private void refreshJabberId(String username) {
        jabberId = username + "@" + mServiceName;

    }

    public void connect() throws IOException, XMPPException, SmackException {
        Log.i(TAG, "connect()");

        XMPPTCPConnectionConfiguration.Builder builder = XMPPTCPConnectionConfiguration.builder();
        builder.setXmppDomain(mServiceName);
        builder.setHostAddress(InetAddress.getByName("im.vsochina.com"));
        //default port 5222
        builder.setResource("test android");
        builder.setDebuggerEnabled(true);
        builder.setCompressionEnabled(true);
        builder.setSendPresence(true);
        builder.setSecurityMode(ConnectionConfiguration.SecurityMode.disabled);
        builder.setUsernameAndPassword(mUsername, mToken);


//        DomainBareJid serviceName = JidCreate.domainBareFrom("example.org");
//        config.setServiceName(serviceName);


        mConnection = new XMPPTCPConnection(builder.build());

        try {
            if (!mConnection.isConnected()) {
                //Set ConnectionListener here to catch initial connect();
                mConnection.addConnectionListener(this);
                mConnection.connect();
            } else {
                Log.d(TAG, "Already connected");
            }

            mConnection.login();
        } catch (InterruptedException
                | XMPPException
                | SmackException
                | IOException e) {
            e.printStackTrace();
        }

        PingManager.setDefaultPingInterval(600); //Ping every 10 minutes
        PingManager pingManager = PingManager.getInstanceFor(mConnection);
        pingManager.registerPingFailedListener(this);

        setupSendMessageReceiver();

        ChatManager.getInstanceFor(mConnection).addIncomingListener(this);
        Roster.getInstanceFor(mConnection).addRosterListener(this);

    }

    public void disconnect() {
        Log.i(TAG, "disconnect()");
        if (mConnection != null) {
            mConnection.disconnect();
        }
        SmackService.sConnectionState = ConnectionState.DISCONNECTED;

        mConnection = null;
        if (mReceiver != null) {
            mApplicationContext.unregisterReceiver(mReceiver);
            mReceiver = null;
        }
    }


    private void rebuildRoster() {
        mRoster = new ArrayList<>();
        String status;

        Roster xmppRoster = Roster.getInstanceFor(mConnection);

        for (RosterEntry entry : xmppRoster.getEntries()) {
            if (xmppRoster.getPresence(entry.getJid()).isAvailable()) {
                status = "Online";
            } else {
                status = "Offline";
            }
            mRoster.add(entry.getJid().toString() + ": " + status);
        }

        Intent intent = new Intent(SmackService.NEW_ROSTER);
        intent.setPackage(mApplicationContext.getPackageName());
        intent.putStringArrayListExtra(SmackService.BUNDLE_ROSTER, mRoster);

//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
//        } current  min Android5.0
        intent.addFlags(Intent.FLAG_RECEIVER_FOREGROUND);

        mApplicationContext.sendBroadcast(intent);
    }

    /*
    * register a broadCastReceiver to listen the broadCast of sendMessageRequest
    * chat room will send the broadCast
    * */
    private void setupSendMessageReceiver() {
        mReceiver = new BroadcastReceiver() {

            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if (action.equals(SmackService.SEND_MESSAGE)) {
                    String toUsername = intent.getStringExtra(SmackService.BUNDLE_TO);
                    try {
                        EntityBareJid entityBareJid = JidCreate.entityBareFrom(toUsername + "@" + mServiceName);
                        sendMessage(intent.getStringExtra(SmackService.BUNDLE_MESSAGE_BODY),
                                entityBareJid);
                    } catch (XmppStringprepException e) {
                        e.printStackTrace();
                    }
                }
            }

        };

        IntentFilter filter = new IntentFilter();
        filter.addAction(SmackService.SEND_MESSAGE);
        mApplicationContext.registerReceiver(mReceiver, filter);
    }

    private void sendMessage(String body, EntityBareJid toJid) {
        Log.i(TAG, "sendMessage()");
        Chat chat = ChatManager.getInstanceFor(mConnection).chatWith(toJid);
        try {
            chat.send(body);
        } catch (SmackException.NotConnectedException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    //ChatListener

//    @Override
//    public void chatCreated(Chat chat, boolean createdLocally) {
//        Log.i(TAG, "chatCreated()");
//        chat.addMessageListener(this);
//    }

    @Override
    public void newIncomingMessage(EntityBareJid from, Message message, org.jivesoftware.smack.chat2.Chat chat) {
        Log.i(TAG, "processMessage()");
        if (message.getType().equals(Message.Type.chat) || message.getType().equals(Message.Type.normal)) {
            if (message.getBody() != null) {
                Intent intent = new Intent(SmackService.NEW_MESSAGE);
                intent.setPackage(mApplicationContext.getPackageName());
                intent.putExtra(SmackService.BUNDLE_MESSAGE_BODY, message.getBody());
                intent.putExtra(SmackService.BUNDLE_FROM_JID, from.toString());

                intent.addFlags(Intent.FLAG_RECEIVER_FOREGROUND);

                mApplicationContext.sendBroadcast(intent);
                Log.i(TAG, "processMessage() BroadCast send");
            }
        }
    }

    @Override
    public void newOutgoingMessage(EntityBareJid to, Message message, org.jivesoftware.smack.chat2.Chat chat) {

    }


    //MessageListener

//    @Override
//    public void processMessage(Chat chat, Message message) {
//        Log.i(TAG, "processMessage()");
//        if (message.getType().equals(Message.Type.chat) || message.getType().equals(Message.Type.normal)) {
//            if (message.getBody() != null) {
//                Intent intent = new Intent(SmackService.NEW_MESSAGE);
//                intent.setPackage(mApplicationContext.getPackageName());
//                intent.putExtra(SmackService.BUNDLE_MESSAGE_BODY, message.getBody());
//                intent.putExtra(SmackService.BUNDLE_FROM_JID, message.getFrom());
//
//
//                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
//                    intent.addFlags(Intent.FLAG_RECEIVER_FOREGROUND);
//                }
//                mApplicationContext.sendBroadcast(intent);
//                Log.i(TAG, "processMessage() BroadCast send");
//            }
//        }
//    }

    //ConnectionListener

    @Override
    public void connected(XMPPConnection connection) {
        SmackService.sConnectionState = ConnectionState.CONNECTED;
        Log.i(TAG, "connected()");
    }

    @Override
    public void authenticated(XMPPConnection connection, boolean resumed) {
        SmackService.sConnectionState = ConnectionState.CONNECTED;
        Log.i(TAG, "authenticated() is resume:" + resumed);
    }


    @Override
    public void connectionClosed() {
        SmackService.sConnectionState = ConnectionState.DISCONNECTED;
        Log.i(TAG, "connectionClosed()");
    }

    @Override
    public void connectionClosedOnError(Exception e) {
        SmackService.sConnectionState = ConnectionState.DISCONNECTED;
        Log.i(TAG, "connectionClosedOnError()");
    }

    @Override
    public void reconnectingIn(int seconds) {
        SmackService.sConnectionState = ConnectionState.RECONNECTING;
        Log.i(TAG, "reconnectingIn()");
    }

    @Override
    public void reconnectionSuccessful() {
        SmackService.sConnectionState = ConnectionState.CONNECTED;
        Log.i(TAG, "reconnectionSuccessful()");
    }

    @Override
    public void reconnectionFailed(Exception e) {
        SmackService.sConnectionState = ConnectionState.DISCONNECTED;
        Log.i(TAG, "reconnectionFailed()");
    }

    //RosterListener
    @Override
    public void entriesAdded(Collection<Jid> addresses) {
        Log.i(TAG, "entriesAdded()");
        rebuildRoster();
    }

    @Override
    public void entriesUpdated(Collection<Jid> addresses) {
        Log.i(TAG, "entriesUpdated()");
        rebuildRoster();
    }

    @Override
    public void entriesDeleted(Collection<Jid> addresses) {
        Log.i(TAG, "entriesDeleted()");
        rebuildRoster();
    }

    @Override
    public void presenceChanged(Presence presence) {
        Log.i(TAG, "presenceChanged()");
        rebuildRoster();
    }

    //PingFailedListener

    @Override
    public void pingFailed() {
        Log.i(TAG, "pingFailed()");
    }
}
