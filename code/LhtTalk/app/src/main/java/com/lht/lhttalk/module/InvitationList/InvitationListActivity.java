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

package com.lht.lhttalk.module.InvitationList;

import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.lht.lhttalk.R;
import com.lht.lhttalk.base.activity.BaseActivity;
import com.lht.lhttalk.base.activity.asyncprotected.AsyncProtectedActivity;
import com.lht.lhttalk.base.presenter.IApiRequestPresenter;
import com.lht.lhttalk.module.addFriend.GeneralTitleBar;
import com.lht.lhttalk.util.ActivityUtils;

/**
 * 新朋友----处理好友请求
 */
public class InvitationListActivity extends AsyncProtectedActivity {

    private GeneralTitleBar titleBar;
    private FrameLayout flContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general);
        initView();
        initVariable();
        initEvent();
    }

    @Override
    protected void initView() {
        titleBar = (GeneralTitleBar) findViewById(R.id.titleBar);
        flContainer = (FrameLayout) findViewById(R.id.fl_container);
    }

    @Override
    protected void initVariable() {

    }

    @Override
    protected void initEvent() {
        titleBar.setTvTitle("新朋友");
        titleBar.setDefaultOnBackListener(this);

        InvitationListFragment newFriendFragment = (InvitationListFragment) getSupportFragmentManager().findFragmentById(R.id.fl_container);
        if (newFriendFragment == null) {
            newFriendFragment = InvitationListFragment.getInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), newFriendFragment, R.id.fl_container);
        }
    }

    @Override
    public BaseActivity getActivity() {
        return InvitationListActivity.this;
    }

    @Override
    protected IApiRequestPresenter getApiRequestPresenter() {
        return null;
    }

    @Override
    public ProgressBar getProgressBar() {
        return null;
    }
}
