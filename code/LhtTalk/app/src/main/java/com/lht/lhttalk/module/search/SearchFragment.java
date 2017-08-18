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

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.util.CircularArray;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.lht.lhttalk.R;
import com.lht.lhttalk.base.fragment.BaseFragment;
import com.lht.lhttalk.module.addFriend.AddFriendActivity;
import com.lht.lhttalk.module.search.adapter.SearchResultAdapter;
import com.lht.lhttalk.module.search.bean.SearchResBean;
import com.lht.lhttalk.util.toast.ToastUtils;

import java.util.ArrayList;

/**
 * Created by chhyu on 2017/7/25.
 */

public class SearchFragment extends BaseFragment implements SearchContract.View {
    private static final String PAGENAME = "SearchFragment";
    private SearchContract.Presenter presenter;
    private EditText etSearchContent;
    private Button btnSearch;
    private ProgressBar progressBar;
    private RecyclerView rcvSearchResult;
    private SearchResultAdapter searchResultAdapter;
    private CircularArray<SearchResBean> searchResBeanCircularArray;

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
        rcvSearchResult = (RecyclerView) contentView.findViewById(R.id.rcv_secrch_result);

    }

    @Override
    protected void initVariable() {
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rcvSearchResult.setLayoutManager(manager);
        searchResBeanCircularArray = new CircularArray<>();

        searchResultAdapter = new SearchResultAdapter(getActivity(), itemClickListener, rcvSearchResult, searchResBeanCircularArray);
        rcvSearchResult.setAdapter(searchResultAdapter);
    }

    @Override
    protected void initEvent() {
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (searchResBeanCircularArray != null) {
                    searchResBeanCircularArray.clear();
                    presenter.doSearch(etSearchContent.getText().toString());
                }
            }
        });
        etSearchContent.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (searchResBeanCircularArray != null) {
                    searchResBeanCircularArray.clear();
                    presenter.doSearch(etSearchContent.getText().toString());
                }
                return false;
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

    @Override
    public void addSearchDatas(ArrayList<SearchResBean> res) {
        searchResultAdapter.addDatas(res);
    }

    private SearchResultAdapter.OnItemClickListener itemClickListener = new SearchResultAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(View view, int postion, SearchResBean bean) {
            //跳转到添加朋友界面
            Intent intent = new Intent(getActivity(), AddFriendActivity.class);
            intent.putExtra(AddFriendActivity.KEY_USER_INFO, JSON.toJSONString(bean));
            getActivity().startActivity(intent);
        }
    };
}
