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
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.lht.lhttalk.R;
import com.lht.lhttalk.base.fragment.BaseFragment;
import com.lht.lhttalk.util.toast.ToastUtils;

/**
 * Created by chhyu on 2017/7/25.
 */

public class SearchFragment extends BaseFragment implements SearchContract.View {
    private static final String PAGENAME = "SearchFragment";
    private SearchContract.Presenter presenter;
    private EditText etSearchContent;
    private Button btnSearch;
    private ProgressBar progressBar;

    public static SearchFragment newInstance() {
        SearchFragment searchFragment = new SearchFragment();
        SearchPresenter searchPresenter = new SearchPresenter(searchFragment);
        searchFragment.setPresenter(searchPresenter);
        return searchFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fg_search, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initVariable();
        initEvent();
    }

    @Override
    protected void initView(View contentView) {
        etSearchContent = (EditText) contentView.findViewById(R.id.et_search_content);
        btnSearch = (Button) contentView.findViewById(R.id.btn_search);
        progressBar = (ProgressBar) contentView.findViewById(R.id.progressBar);

    }

    @Override
    protected void initVariable() {
    }

    @Override
    protected void initEvent() {
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("lmsg", "搜索");
                presenter.doSearch(etSearchContent.getText().toString());
            }
        });
    }

    @Override
    protected String getPageName() {
        return SearchFragment.PAGENAME;
    }

    @Override
    public void setPresenter(SearchContract.Presenter presenter) {
        this.presenter = presenter;
        presenter.start();
    }

    @Override
    public void showMsg(String text) {
        ToastUtils.show(getActivity(), text, ToastUtils.Duration.s);
    }

    @Override
    public SearchFragment instance() {
        return SearchFragment.this;
    }

    @Override
    public void showWaitView(boolean b) {
        if (b) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.INVISIBLE);
        }
    }

}
