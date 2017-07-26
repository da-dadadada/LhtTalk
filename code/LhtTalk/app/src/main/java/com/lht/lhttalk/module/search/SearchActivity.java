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

package com.lht.lhttalk.module.search;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.lht.lhttalk.R;
import com.lht.lhttalk.base.activity.BaseActivity;
import com.lht.lhttalk.base.activity.asyncprotected.AsyncProtectedActivity;
import com.lht.lhttalk.base.presenter.IApiRequestPresenter;
import com.lht.lhttalk.util.ActivityUtils;

public class SearchActivity extends AsyncProtectedActivity {

    private FrameLayout flContainer;
    private SearchFragment searchFragment;
    private SearchPresenter presenter;
    private TitleBar2 titleBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_friend);
        initView();
        initVariable();
        initEvent();
    }

    @Override
    public BaseActivity getActivity() {
        return this;
    }

    @Override
    protected void initView() {
        titleBar = (TitleBar2) findViewById(R.id.titleBar);
        flContainer = (FrameLayout) findViewById(R.id.fl_container);

        searchFragment = (SearchFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fl_container);
        if (searchFragment == null) {
            searchFragment = SearchFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), searchFragment, R.id.fl_container);
        }
    }

    @Override
    protected void initVariable() {
        presenter = new SearchPresenter(searchFragment);
    }

    @Override
    protected void initEvent() {
        titleBar.setTvTitle("添加");
        titleBar.setDefaultOnBackListener(this);
    }

    @Override
    protected IApiRequestPresenter getApiRequestPresenter() {
        if (presenter instanceof IApiRequestPresenter)
            return (IApiRequestPresenter) presenter;
        return null;
    }

    @Override
    public ProgressBar getProgressBar() {
        return null;
    }
}
