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
import android.support.annotation.Nullable;
import android.support.v4.util.CircularArray;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.lht.lhttalk.R;
import com.lht.lhttalk.base.fragment.BaseFragment;
import com.lht.lhttalk.module.InvitationList.adapter.InvitationListAdapter;
import com.lht.lhttalk.module.InvitationList.bean.InvitationListResBean;

import java.util.ArrayList;

/**
 * Created by chhyu on 2017/8/17.
 */

public class InvitationListFragment extends BaseFragment implements InvivationListContact.View {

    private static final String PAGENAME = "InvitationListFragment";
    private InvivationListContact.Presenter presenter;
    private RecyclerView rcvRequestList;
    private TextView tvNoRequest;
    private ProgressBar progressBar;
    private InvitationListAdapter adapter;

    public static InvitationListFragment getInstance() {
        InvitationListFragment newFriendFragment = new InvitationListFragment();
        InvitationListPresenter newFriendPresenter = new InvitationListPresenter(newFriendFragment);
        newFriendFragment.setPresenter(newFriendPresenter);
        return newFriendFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fg_newfriend, null);
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
        rcvRequestList = (RecyclerView) contentView.findViewById(R.id.rcv_request_list);
        tvNoRequest = (TextView) contentView.findViewById(R.id.tv_no_request);
        progressBar = (ProgressBar) contentView.findViewById(R.id.progressBar);
    }

    @Override
    protected void initVariable() {
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rcvRequestList.setLayoutManager(manager);
    }

    @Override
    protected void initEvent() {
        //获取好友请求
        presenter.getInvitationsList();

        adapter = new InvitationListAdapter(getActivity(), listener, rcvRequestList, new CircularArray<InvitationListResBean>());
        rcvRequestList.setAdapter(adapter);
    }

    @Override
    protected String getPageName() {
        return InvitationListFragment.PAGENAME;
    }

    @Override
    public void setPresenter(InvivationListContact.Presenter presenter) {
        this.presenter = presenter;
        presenter.start();
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
    public void showInvitationsList(ArrayList<InvitationListResBean> res) {
        adapter.addDatas(res);
    }

    @Override
    public void showEmptyInvitations() {
        rcvRequestList.setVisibility(View.GONE);
        tvNoRequest.setVisibility(View.VISIBLE);
    }

    @Override
    public void refreshInvitationList() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public InvitationListFragment instance() {
        return InvitationListFragment.this;
    }

    InvitationListAdapter.OnHandleButtonClickListener listener = new InvitationListAdapter.OnHandleButtonClickListener() {
        @Override
        public void onHandleButtonClick(View view, InvitationListAdapter.MyViewHolder holder, int position, InvitationListResBean bean) {
            String action = "";
            if (view == holder.getBtnAccept()) {
                action = String.valueOf(InvitationListResBean.HandleState.ACCEPT);
                Log.e("lmsg", "接受");
            }
            if (view == holder.getBtnIgnore()) {
                Log.e("lmsg", "忽略");
                action = String.valueOf(InvitationListResBean.HandleState.IGNORE);
            }
            if (view == holder.getBtnRefuse()) {
                Log.e("lmsg", "拒绝");
                action = String.valueOf(InvitationListResBean.HandleState.REFUSE);
            }
            presenter.handleFriendInvitations(bean, action);
        }
    };
}
