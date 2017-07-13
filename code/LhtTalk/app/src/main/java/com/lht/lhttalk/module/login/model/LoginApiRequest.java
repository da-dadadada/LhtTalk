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

package com.lht.lhttalk.module.login.model;

import android.content.Context;
import android.support.annotation.NonNull;

import com.alibaba.fastjson.JSON;
import com.lht.lhttalk.base.model.apimodel.AbsApiRequest;
import com.lht.lhttalk.base.model.apimodel.ApiRequestCallback;
import com.lht.lhttalk.base.model.apimodel.BaseBeanContainer;
import com.lht.lhttalk.base.model.apimodel.BaseVsoApiResBean;
import com.lht.lhttalk.module.api.IApiNewCollections;
import com.lht.lhttalk.module.login.pojo.LoginResBean;
import com.lht.lhttalk.module.ucenter.LoginAccount;
import com.lht.lhttalk.util.internet.HttpAction;
import com.lht.lhttalk.util.internet.HttpUtil;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestHandle;
import com.loopj.android.http.RequestParams;

/**
 * Created by chhyu on 2017/7/11.
 */

public class LoginApiRequest
        extends AbsApiRequest<IApiNewCollections.LoginApi,LoginAccount> {

    private ApiRequestCallback<LoginResBean> modelCallback;


    public LoginApiRequest(@NonNull LoginAccount account, ApiRequestCallback<LoginResBean> modelCallback) {
        super(IApiNewCollections.LoginApi.class,account);
        this.modelCallback = modelCallback;
    }

    @Override
    protected void handleSuccess(BaseVsoApiResBean baseVsoApiResBean) {
        LoginResBean data = JSON.parseObject(baseVsoApiResBean.getData(),
                LoginResBean.class);
        modelCallback.onSuccess(new BaseBeanContainer<>(data));
    }

    @Override
    protected void handleFailure(BaseVsoApiResBean baseVsoApiResBean) {
        modelCallback.onFailure(new BaseBeanContainer<>(baseVsoApiResBean));
    }

    @Override
    protected void handleHttpFailure(int httpCode) {
        modelCallback.onHttpFailure(httpCode);
    }

    @Override
    protected RequestHandle handle(HttpUtil httpUtil,
                                   Context context,
                                   String url,
                                   RequestParams params,
                                   AsyncHttpResponseHandler handler) {
        return httpUtil.postWithParams(context, url, params, handler);
    }

    @Override
    protected String formatUrl(IApiNewCollections.LoginApi apiImpl) {
        return apiImpl.formatUrl(null);
    }

    @Override
    protected RequestParams formatParam(IApiNewCollections.LoginApi apiImpl) {
        return apiImpl.newRequestParams(getData());
    }

    @Override
    protected HttpAction getHttpAction() {
        return HttpAction.POST;
    }
}
