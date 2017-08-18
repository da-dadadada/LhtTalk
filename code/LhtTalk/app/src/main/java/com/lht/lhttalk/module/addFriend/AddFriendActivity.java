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

package com.lht.lhttalk.module.addFriend;

import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.alibaba.fastjson.JSON;
import com.lht.lhttalk.R;
import com.lht.lhttalk.base.activity.BaseActivity;
import com.lht.lhttalk.base.activity.asyncprotected.AsyncProtectedActivity;
import com.lht.lhttalk.base.presenter.IApiRequestPresenter;
import com.lht.lhttalk.module.search.bean.SearchResBean;
import com.lht.lhttalk.util.ActivityUtils;

/**
 * 添加好友
 */
public class AddFriendActivity extends AsyncProtectedActivity {

    private static final String PAGENAME = "AddFriendActivity";
    public static final String KEY_USER_INFO = "userInfo";
    private GeneralTitleBar titleBar;
    private FrameLayout flContainer;
    private AddFriendFragment addFriendFragment;
    private AddFriendPresenter presenter;
    private SearchResBean searchResBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general);
        String s = getIntent().getStringExtra(KEY_USER_INFO);
        searchResBean = JSON.parseObject(s, SearchResBean.class);
        initView();
        initVariable();
        initEvent();
    }

    @Override
    public BaseActivity getActivity() {
        return AddFriendActivity.this;
    }

    @Override
    protected void initView() {
        titleBar = (GeneralTitleBar) findViewById(R.id.titleBar);
        flContainer = (FrameLayout) findViewById(R.id.fl_container);

        addFriendFragment = (AddFriendFragment) getSupportFragmentManager().findFragmentById(R.id.fl_container);
        if (addFriendFragment == null) {
            addFriendFragment = AddFriendFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), addFriendFragment, R.id.fl_container);
        }
        addFriendFragment.setDatas(searchResBean);
    }

    @Override
    protected void initVariable() {
        presenter = new AddFriendPresenter(addFriendFragment);
    }

    @Override
    protected void initEvent() {
        titleBar.setTvTitle("添加好友");
        titleBar.setDefaultOnBackListener(this);
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
