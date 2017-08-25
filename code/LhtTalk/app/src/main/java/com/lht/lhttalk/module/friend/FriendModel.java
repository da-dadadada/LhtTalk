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

import android.content.Context;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.lht.lhttalk.base.IVerifyHolder;
import com.lht.lhttalk.module.api.ApiClient;
import com.lht.lhttalk.module.friend.pojo.FriendBasicPojo;
import com.lht.lhttalk.module.friend.pojo.FriendList;
import com.lht.lhttalk.util.string.StringUtil;

import individual.leobert.retrofitext.RetrofitExt;
import individual.leobert.retrofitext.ext.ApiResponseHandler;
import retrofit2.Call;

/**
 * <p><b>Package:</b> com.lht.lhttalk.module.friend </p>
 * <p><b>Project:</b> LhtTalk </p>
 * <p><b>Classname:</b> FriendModel </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/7/14.
 */

class FriendModel {

    private Api apiInstance = ApiClient.getJsonApiClient().apiInstance(Api.class);


    public void getFriendList(Fragment fragment, ApiResponseHandler<FriendList> responseHandler) {
        Log.e("lmsg", "username=" + IVerifyHolder.mUserBean.getUsername() + "/r/n" + ",token=" + IVerifyHolder.mUserBean.getToken());
        Call<FriendList> call = apiInstance.listAll(IVerifyHolder.mUserBean.getUsername(),
                IVerifyHolder.mUserBean.getToken());

        RetrofitExt.lifeCycledWithFragmentV4(fragment, call, responseHandler);
    }

    public void deleteFriend(Context context, String target, boolean relieve,
                             ApiResponseHandler<String> responseHandler) {
        if (StringUtil.isEmpty(target))
            throw new IllegalArgumentException("target is null or empty!");

        Call<String> call = apiInstance.delete(target, relieve);

        RetrofitExt.lifeCycledWithContext(context, call, responseHandler);
    }

    public void deleteFriend(Context context, FriendBasicPojo target, boolean relieve,
                             ApiResponseHandler<String> responseHandler) {
        if (target == null)
            throw new IllegalArgumentException("target is null");

        this.deleteFriend(context, target.getUsername(), relieve, responseHandler);
    }
}
