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

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;


import com.lht.lhttalk.base.MainApplication;

import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPException;

import java.io.IOException;

import static com.lht.lhttalk.cfg.SPConstants.Token.KEY_ACCESS_TOKEN;
import static com.lht.lhttalk.cfg.SPConstants.Token.KEY_USERNAME;

public class SmackService extends Service {

    private static final String APP_ID = "com.lht.lhttalk";

    public static final String NEW_MESSAGE = APP_ID + ".newmessage";
    public static final String SEND_MESSAGE = APP_ID + ".sendmessage";
    public static final String NEW_ROSTER = APP_ID + ".newroster";

    public static final String BUNDLE_FROM_JID = "b_from";
    public static final String BUNDLE_MESSAGE_BODY = "b_body";
    public static final String BUNDLE_ROSTER = "b_body";
    public static final String BUNDLE_TO = "b_to";

    public static SmackConnection.ConnectionState sConnectionState;

    private String username;
    private String token;


    public static SmackConnection.ConnectionState getState() {
        if (sConnectionState == null) {
            return SmackConnection.ConnectionState.DISCONNECTED;
        }
        return sConnectionState;
    }

    private boolean mActive;
    private Thread mThread;
    private Handler mTHandler;
    private SmackConnection mConnection;

    public SmackService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        start();
        return Service.START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stop();
    }

    public void start() {
        if (!mActive) {
            mActive = true;

            // Create ConnectionThread Loop
            if (mThread == null || !mThread.isAlive()) {
                mThread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Looper.prepare();
                        mTHandler = new Handler();
                        initConnection();
                        Looper.loop();
                    }

                });
                mThread.start();
            }

        }
    }

    public void stop() {
        mActive = false;
        mTHandler.post(new Runnable() {

            @Override
            public void run() {
                if (mConnection != null) {
                    mConnection.disconnect();
                }
            }
        });

    }

    private void initConnection() {
        if (mConnection == null) {
            SharedPreferences spToken = MainApplication.getOurInstance().getTokenSp();
            username = spToken.getString(KEY_USERNAME, "");
            token = spToken.getString(KEY_ACCESS_TOKEN, "");


            mConnection = new SmackConnection(this, username, token);
        }
        try {
            mConnection.connect();
        } catch (IOException | SmackException | XMPPException e) {
            e.printStackTrace();
        }
    }
}
