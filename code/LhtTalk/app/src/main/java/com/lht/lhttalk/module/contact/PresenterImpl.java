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

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.lht.lhttalk.module.contact.bean.ContactResBean;

import java.util.ArrayList;

import individual.leobert.retrofitext.ext.ApiResponseHandler;
import okhttp3.Headers;
import okhttp3.ResponseBody;
import retrofit2.Call;

/**
 * Created by chhyu on 2017/7/12.
 */

public class PresenterImpl implements ContactFgContact.Presenter {

    private ContactFgContact.View view;
    private final ContactModel model;

    public PresenterImpl(ContactFgContact.View view) {
        this.view = view;
        model = new ContactModel();
    }

    @Override
    public void start() {

    }

    @Override
    public void refreshContactList() {
        model.getContactList(view.instance(), new ApiResponseHandler<ArrayList<ContactResBean>>() {

            @Override
            public void onSuccess(int code, Call<ArrayList<ContactResBean>> call, Headers headers, ArrayList<ContactResBean> res) {
                Log.e("lmsg", "刷新聊天列表成功");
            }

            @Override
            public void onFailure(int code, Call<ArrayList<ContactResBean>> call, Headers headers, ResponseBody res) {
                Log.e("lmsg", "刷新聊天列表失败");
            }

            @Override
            public void onFinish(Call<ArrayList<ContactResBean>> call) {

            }
        });
    }

    /**
     * 从聊天列表中删除
     */
    @Override
    public void deleteFromContact() {
        model.deleteFromContact(view.instance(), new ApiResponseHandler<String>() {
            @Override
            public void onSuccess(int code, Call<String> call, Headers headers, String res) {

            }

            @Override
            public void onFailure(int code, Call<String> call, Headers headers, ResponseBody res) {

            }

            @Override
            public void onFinish(Call<String> call) {

            }
        });
    }

    @Override
    public void add2ContactList() {
        model.add2ContactList(view.instance(), new ApiResponseHandler<String>() {
            @Override
            public void onSuccess(int code, Call<String> call, Headers headers, String res) {

            }

            @Override
            public void onFailure(int code, Call<String> call, Headers headers, ResponseBody res) {

            }

            @Override
            public void onFinish(Call<String> call) {

            }
        });
    }
}
