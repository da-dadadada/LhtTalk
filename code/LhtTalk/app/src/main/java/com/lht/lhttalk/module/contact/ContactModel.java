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

package com.lht.lhttalk.module.contact;

import android.support.v4.app.Fragment;

import com.lht.lhttalk.base.IVerifyHolder;
import com.lht.lhttalk.module.api.ApiClient;
import com.lht.lhttalk.module.contact.bean.ContactResBean;

import java.util.ArrayList;

import individual.leobert.retrofitext.RetrofitExt;
import individual.leobert.retrofitext.ext.ApiResponseHandler;
import retrofit2.Call;

/**
 * Created by chhyu on 2017/7/24.
 */

public class ContactModel {

    private Api apiInstance = ApiClient.getInstance().apiInstance(Api.class);

    /**
     * 获取聊天列表
     *
     * @param fragment
     * @param responseHandler
     */
    public void getContactList(Fragment fragment, ApiResponseHandler<ArrayList<ContactResBean>> responseHandler) {
        // TODO: 2017/7/24 pagenation
        Call<ArrayList<ContactResBean>> call = apiInstance.contactList(0, 10,
                IVerifyHolder.mUserBean.getUsername(),
                IVerifyHolder.mUserBean.getToken());

        RetrofitExt.lifeCycledWithFragmentV4(fragment, call, responseHandler);
    }

    /**
     * 从聊天列表中删除
     *
     * @param fragment
     * @param responseHandler
     */
    public void deleteFromContact(Fragment fragment, ApiResponseHandler<String> responseHandler) {

        Call<String> call = apiInstance.delateFromContact(IVerifyHolder.mUserBean.getUsername(), IVerifyHolder.mUserBean.getToken());

        RetrofitExt.lifeCycledWithFragmentV4(fragment, call, responseHandler);
    }

    /**
     * 添加到联系人列表
     *
     * @param fragment
     * @param responseHandler
     */
    public void add2ContactList(Fragment fragment, ApiResponseHandler<String> responseHandler) {
        Call<String> call = apiInstance.delateFromContact(IVerifyHolder.mUserBean.getUsername(), IVerifyHolder.mUserBean.getToken());

        RetrofitExt.lifeCycledWithFragmentV4(fragment, call, responseHandler);
    }
}
