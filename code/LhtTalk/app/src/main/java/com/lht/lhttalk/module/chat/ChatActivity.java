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

import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.alibaba.fastjson.JSON;
import com.lht.lhttalk.R;
import com.lht.lhttalk.base.activity.BaseActivity;
import com.lht.lhttalk.base.activity.asyncprotected.AsyncProtectedActivity;
import com.lht.lhttalk.base.presenter.IApiRequestPresenter;
import com.lht.lhttalk.module.addFriend.GeneralTitleBar;
import com.lht.lhttalk.module.friend.pojo.FriendInfoResBean;
import com.lht.lhttalk.util.ActivityUtils;

/**
 * 聊天界面
 */
public class ChatActivity extends AsyncProtectedActivity {

    public static final String KEY_DATA = "key_data";
    private GeneralTitleBar titleBar;
    private FrameLayout flContainer;
    private FriendInfoResBean friendInfoResBean;
    private ChatFragment chatFragment;
    private ChatPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general);
        String s = getIntent().getStringExtra(KEY_DATA);
        friendInfoResBean = JSON.parseObject(s, FriendInfoResBean.class);
        initView();
        initVariable();
        initEvent();
    }

    @Override
    protected void initView() {
        titleBar = (GeneralTitleBar) findViewById(R.id.titleBar);
        flContainer = (FrameLayout) findViewById(R.id.fl_container);

        chatFragment = (ChatFragment) getSupportFragmentManager().findFragmentById(R.id.fl_container);
        if (chatFragment == null) {
            chatFragment = ChatFragment.getInatance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), chatFragment, R.id.fl_container);
        }

    }

    @Override
    protected void initVariable() {
        presenter = new ChatPresenter(chatFragment);
    }

    @Override
    protected void initEvent() {
        titleBar.setTvTitle(friendInfoResBean.getUsername());
        titleBar.setDefaultOnBackListener(this);

        chatFragment.setData(friendInfoResBean);
    }

    @Override
    public BaseActivity getActivity() {
        return ChatActivity.this;
    }

    @Override
    protected IApiRequestPresenter getApiRequestPresenter() {
        if (presenter instanceof IApiRequestPresenter) {
            return (IApiRequestPresenter) presenter;
        }
        return null;
    }

    @Override
    public ProgressBar getProgressBar() {
        return null;
    }

}
