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

import android.support.v4.app.Fragment;

import com.lht.lhttalk.base.IVerifyHolder;
import com.lht.lhttalk.module.addFriend.bean.AddFriendResBean;
import com.lht.lhttalk.module.api.ApiClient;

import individual.leobert.retrofitext.RetrofitExt;
import individual.leobert.retrofitext.ext.ApiResponseHandler;
import retrofit2.Call;

/**
 * Created by chhyu on 2017/8/16.
 */

public class AddFriendModel {

    private Api apiInstance = ApiClient.getJsonApiClient().apiInstance(Api.class);
    private String target;
    private String attach;

    public AddFriendModel(String target, String attach) {
        this.target = target;
        this.attach = attach;
    }

    public void doRequestAdd(Fragment fragment, ApiResponseHandler<AddFriendResBean> apiResponseHandler) {
        Call<AddFriendResBean> call = apiInstance.doAdd(target, attach, IVerifyHolder.mUserBean.getUsername(), IVerifyHolder.mUserBean.getToken());
        RetrofitExt.lifeCycledWithFragmentV4(fragment, call, apiResponseHandler);
    }
}
