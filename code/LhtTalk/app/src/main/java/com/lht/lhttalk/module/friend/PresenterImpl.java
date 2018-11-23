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

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.lht.lhttalk.base.fragment.BaseFragment;
import com.lht.lhttalk.module.friend.pojo.FriendBasicPojo;
import com.lht.lhttalk.module.friend.pojo.FriendInfoResBean;
import com.lht.lhttalk.module.friend.pojo.FriendList;

import java.util.ArrayList;

import individual.leobert.retrofitext.ext.ApiResponseHandler;
import okhttp3.Headers;
import okhttp3.ResponseBody;
import retrofit2.Call;

/**
 * <p><b>Package:</b> com.lht.lhttalk.module.friend </p>
 * <p><b>Project:</b> LhtTalk </p>
 * <p><b>Classname:</b> PresenterImpl </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/7/14.
 */

class PresenterImpl implements VPContact.Presenter {

    private VPContact.View<BaseFragment> view;

    private FriendModel friendModel;

    public PresenterImpl(VPContact.View<BaseFragment> view) {
        this.view = view;
        friendModel = new FriendModel();
    }

    @Override
    public void start() {

    }

    @Override
    public void refreshFriendList() {
        friendModel.getFriendList(view.instance(), new ApiResponseHandler<FriendList>() {
            @Override
            public void onSuccess(int i, Call<FriendList> call, Headers headers, FriendList friendList) {
                if (friendList == null) {
                    view.showMsg("暂时没有好友");
                    return;
                }
                Log.e("lmsg", "friendList=" + JSON.toJSONString(friendList));
                ArrayList<FriendInfoResBean> allFriends = FriendInfoResBean.getAllFriendInfo(friendList);
                Log.e("lmsg", "allFriends=" + JSON.toJSONString(allFriends));
                view.displayFriendList(allFriends);
            }

            @Override
            public void onFailure(int i, Call<FriendList> call, Headers headers, ResponseBody responseBody) {
                view.showMsg("搜索好友失败，请稍后重试");
            }

            @Override
            public void onFinish(Call<FriendList> call) {

            }
        });
    }

}
