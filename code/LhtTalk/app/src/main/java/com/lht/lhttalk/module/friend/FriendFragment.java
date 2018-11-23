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

package com.lht.lhttalk.module.friend;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.util.CircularArray;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.gjiazhe.wavesidebar.WaveSideBar;
import com.lht.lhttalk.R;
import com.lht.lhttalk.base.fragment.BaseFragment;
import com.lht.lhttalk.module.InvitationList.InvitationListActivity;
import com.lht.lhttalk.module.chat.ChatActivity;
import com.lht.lhttalk.module.friend.adapter.FriendListAdapter;
import com.lht.lhttalk.module.friend.pojo.FriendInfoResBean;
import com.lht.lhttalk.module.search.SearchActivity;
import com.lht.lhttalk.util.toast.ToastUtils;

import java.util.ArrayList;

/**
 * Created by chhyu on 2017/7/12.
 * 好友列表
 */

public class FriendFragment extends BaseFragment
        implements VPContact.View<BaseFragment>, View.OnClickListener {


    private TitleBar titleBar;
    private TextView tvNewFriend;
    private WaveSideBar waveSideBar;
    private RecyclerView rcvFriendList;
    private FriendListAdapter friendListAdapter;
    private ArrayList<FriendInfoResBean> arrayList;

    public static FriendFragment getInstance() {
        FriendFragment friendFragment = new FriendFragment();
        PresenterImpl presenter = new PresenterImpl(friendFragment);
        friendFragment.setPresenter(presenter);
        return friendFragment;
    }

    private VPContact.Presenter presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fg_friend, null);
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
        titleBar = (TitleBar) contentView.findViewById(R.id.titleBar);
        tvNewFriend = (TextView) contentView.findViewById(R.id.tv_new_friend);
        waveSideBar = (WaveSideBar) contentView.findViewById(R.id.side_bar);
        rcvFriendList = (RecyclerView) contentView.findViewById(R.id.rcv_friend_list);

    }

    @Override
    protected void initVariable() {
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rcvFriendList.setLayoutManager(manager);
    }

    @Override
    protected void initEvent() {
        titleBar.setDefaultOnBackListener(getActivity());
        titleBar.setTvTitle("联系人");
        titleBar.setOnAddOnclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                startActivity(intent);
            }
        });

        tvNewFriend.setOnClickListener(this);
        arrayList = new ArrayList<>();
        friendListAdapter = new FriendListAdapter(getActivity(), listener, rcvFriendList, arrayList);
        rcvFriendList.setAdapter(friendListAdapter);
    }


    @Override
    protected String getPageName() {
        return getClass().getSimpleName();
    }

    @Override
    public void setPresenter(VPContact.Presenter presenter) {
        this.presenter = presenter;
        presenter.start();
    }

    @Override
    public void onRestrictResume() {
        super.onRestrictResume();
        Log.d("DebugHandler", "friend fg resume");
        presenter.refreshFriendList();
    }

    @Override
    public BaseFragment instance() {
        return this;
    }

    @Override
    public void showMsg(String msg) {
        ToastUtils.show(getActivity(), msg, ToastUtils.Duration.s);
    }

    @Override
    public void displayFriendList(final ArrayList<FriendInfoResBean> allFriends) {
        arrayList.clear();
        friendListAdapter.addDatas(allFriends);
        waveSideBar.setOnSelectIndexItemListener(new WaveSideBar.OnSelectIndexItemListener() {
            @Override
            public void onSelectIndexItem(String index) {
                for (int i = 0; i < allFriends.size(); i++) {
                    if (allFriends.get(i).getFristLetter().equals(index)) {
                        ((LinearLayoutManager) rcvFriendList.getLayoutManager()).scrollToPositionWithOffset(i, 0);
                        return;
                    }
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_new_friend:
                Intent intent = new Intent(getActivity(), InvitationListActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    FriendListAdapter.OnItemClickListener listener = new FriendListAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(View view, int postion, FriendInfoResBean bean) {
            Intent intent = new Intent(getActivity(), ChatActivity.class);
            intent.putExtra(ChatActivity.KEY_DATA, JSON.toJSONString(bean));
            getActivity().startActivity(intent);
        }
    };
}
