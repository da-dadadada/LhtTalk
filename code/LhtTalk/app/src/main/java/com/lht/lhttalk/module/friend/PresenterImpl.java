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

import com.lht.lhttalk.base.fragment.BaseFragment;
import com.lht.lhttalk.module.friend.pojo.FriendBasicPojo;
import com.lht.lhttalk.module.friend.pojo.FriendList;

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

class PresenterImpl implements VPContact.Presenter{

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

            }

            @Override
            public void onFailure(int i, Call<FriendList> call, Headers headers, ResponseBody responseBody) {

            }

            @Override
            public void onFinish(Call<FriendList> call) {

            }
        });
    }

}
