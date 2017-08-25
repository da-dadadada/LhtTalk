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

import android.util.Log;

import com.lht.lhttalk.module.InvitationList.bean.InvitationListResBean;
import com.lht.lhttalk.util.time.TimeUtil;

import java.util.ArrayList;

import individual.leobert.retrofitext.ext.ApiResponseHandler;
import okhttp3.Headers;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by chhyu on 2017/8/17.
 */

public class InvitationListPresenter implements InvivationListContact.Presenter {

    private InvivationListContact.View view;
    private final InvivationListModel invivationListModel;

    public InvitationListPresenter(InvivationListContact.View view) {
        this.view = view;
        invivationListModel = new InvivationListModel();
    }

    @Override
    public void start() {

    }

    @Override
    public void getInvitationsList() {
        view.showWaitView(true);
        invivationListModel.getInvitationsList(view.instance(), new ApiResponseHandler<ArrayList<InvitationListResBean>>() {
            @Override
            public void onSuccess(int code, Call<ArrayList<InvitationListResBean>> call, Headers headers, ArrayList<InvitationListResBean> res) {
                Log.e("lmsg", "获取好友请求列表成功");
                view.showWaitView(false);
                //展示请求列表
                if (res == null || res.size() == 0) {
                    view.showEmptyInvitations();
                } else {
                    view.showInvitationsList(res);
                }
            }

            @Override
            public void onThrow(Throwable t) {
                Log.e("lmsg", t.getMessage());
                super.onThrow(t);
            }

            @Override
            public void onFailure(int code, Call<ArrayList<InvitationListResBean>> call, Headers headers, ResponseBody res) {
                view.showWaitView(false);
                Log.e("lmsg", "获取好友请求列表失败");
            }

            @Override
            public void onFinish(Call<ArrayList<InvitationListResBean>> call) {
                view.showWaitView(false);
            }
        });
    }

    @Override
    public void handleFriendInvitations(InvitationListResBean bean, String action) {
        view.showWaitView(true);
        long postTime = TimeUtil.formatUtc2TimeStamp(bean.getPostTime());
        invivationListModel.handleFriendInvitation(view.instance(), postTime, bean.getUsername(), action, new ApiResponseHandler<String>() {
            @Override
            public void onSuccess(int code, Call<String> call, Headers headers, String res) {
                view.showWaitView(false);
                Log.e("lmsg", "处理成功");
                //刷新请求列表
                view.refreshInvitationList();
            }

            @Override
            public void onFailure(int code, Call<String> call, Headers headers, ResponseBody res) {
                view.showWaitView(false);
                Log.e("lmsg", "处理失败");
            }

            @Override
            public void onFinish(Call<String> call) {
                view.showWaitView(false);
            }
        });
    }
}
