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

package com.lht.lhttalk.module.chat;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.lht.lhttalk.R;
import com.lht.lhttalk.base.fragment.BaseFragment;
import com.lht.lhttalk.module.chat.adapter.ChatMsgItemAdapter;
import com.lht.lhttalk.module.chat.bean.MsgEntity;
import com.lht.lhttalk.module.friend.pojo.FriendInfoResBean;
import com.lht.lhttalk.module.smack.service.SmackService;

import java.util.ArrayList;

/**
 * Created by chhyu on 2017/8/22.
 */

public class ChatFragment extends BaseFragment implements ChatContact.View {

    private FriendInfoResBean friendInfoResBean;
    private RecyclerView rcvChatMsg;
    private EditText etMsg;
    private Button btnSend;
    private ChatContact.Presenter presenter;
    private ChatMsgItemAdapter adapter;

    public static ChatFragment getInatance() {
        ChatFragment chatFragment = new ChatFragment();
        ChatPresenter presenter = new ChatPresenter(chatFragment);
        chatFragment.setPresenter(presenter);
        return chatFragment;
    }

    public void setData(FriendInfoResBean friendInfoResBean) {
        this.friendInfoResBean = friendInfoResBean;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fg_chat, null);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initVariable();
        initEvent();

        registerReceiveMsgBroadCastReceiver();
    }

    @Override
    protected void initView(View contentView) {
        rcvChatMsg = (RecyclerView) contentView.findViewById(R.id.rcv_chat_msg);
        etMsg = (EditText) contentView.findViewById(R.id.et_msg);
        btnSend = (Button) contentView.findViewById(R.id.btn_send);
    }

    private String friend = "http://img5.imgtn.bdimg.com/it/u=3719690304,2175364311&fm=26&gp=0.jpg";
    private String self = "http://img5.imgtn.bdimg.com/it/u=2053745272,3162331373&fm=26&gp=0.jpg";
    ArrayList<MsgEntity> arrayList = new ArrayList<>();

    @Override
    protected void initVariable() {
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rcvChatMsg.setLayoutManager(manager);

        arrayList.add(new MsgEntity(MsgEntity.TYPE_FRIEND, friend, "你好"));
        arrayList.add(new MsgEntity(MsgEntity.TYPE_SELF, self, "你好"));
        arrayList.add(new MsgEntity(MsgEntity.TYPE_FRIEND, friend, "吃饭了吗"));
        arrayList.add(new MsgEntity(MsgEntity.TYPE_SELF, self, "没有 ， 你呢？"));
        adapter = new ChatMsgItemAdapter(getActivity(), arrayList);
        rcvChatMsg.setAdapter(adapter);
    }

    @Override
    protected void initEvent() {
        etMsg.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (count == 0) {
                    btnSend.setEnabled(false);
                } else {
                    btnSend.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.toSendMsg(etMsg.getText().toString());
                etMsg.setText(null);
            }
        });
    }

    @Override
    protected String getPageName() {
        return null;
    }

    @Override
    public void setPresenter(ChatContact.Presenter presenter) {
        this.presenter = presenter;
        presenter.start();
    }


    private void registerReceiveMsgBroadCastReceiver() {
        ReceiveMsgReceiver receiveMsgReceiver = new ReceiveMsgReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(SmackService.NEW_MESSAGE);
        getActivity().registerReceiver(receiveMsgReceiver, filter);
    }

    class ReceiveMsgReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.e("lmsg", "收到消息");
            String message = intent.getStringExtra(SmackService.BUNDLE_MESSAGE_BODY);
            String s = intent.getStringExtra(SmackService.BUNDLE_FROM_JID);

        }
    }
}
