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

package com.lht.lhttalk.module.publ;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.lht.lhttalk.base.model.apimodel.AbsApiRequest;
import com.lht.lhttalk.base.model.apimodel.ApiRequestCallback;
import com.lht.lhttalk.base.model.apimodel.BaseBeanContainer;
import com.lht.lhttalk.base.model.apimodel.BaseVsoApiResBean;
import com.lht.lhttalk.module.api.IApiNewCollections;
import com.lht.lhttalk.module.publ.bean.BasicInfoParam;
import com.lht.lhttalk.module.publ.bean.UserBasicInfo;
import com.lht.lhttalk.util.internet.HttpAction;
import com.lht.lhttalk.util.internet.HttpUtil;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestHandle;
import com.loopj.android.http.RequestParams;

/**
 * Created by chhyu on 2017/7/13.
 */

public class AuthRequest extends AbsApiRequest<IApiNewCollections.QueryUserBasicInfoApi, BasicInfoParam> {

    private ApiRequestCallback<UserBasicInfo> modelCallback;

    public AuthRequest(BasicInfoParam param, ApiRequestCallback<UserBasicInfo> modelCallback) {
        super(IApiNewCollections.QueryUserBasicInfoApi.class, param);
        this.modelCallback = modelCallback;
    }

    @Override
    protected String formatUrl(IApiNewCollections.QueryUserBasicInfoApi apiImpl) {
        return apiImpl.formatUrl(null);
    }

    @Override
    protected RequestParams formatParam(IApiNewCollections.QueryUserBasicInfoApi apiImpl) {
        return apiImpl.newRequestParams(getData());
    }

    @Override
    protected HttpAction getHttpAction() {
        return HttpAction.GET;
    }

    @Override
    protected void handleSuccess(BaseVsoApiResBean baseVsoApiResBean) {
        UserBasicInfo basicUserInfo = JSON.parseObject(baseVsoApiResBean.getData(), UserBasicInfo.class);
        modelCallback.onSuccess(new BaseBeanContainer<UserBasicInfo>(basicUserInfo));
    }

    @Override
    protected void handleFailure(BaseVsoApiResBean baseVsoApiResBean) {
        modelCallback.onFailure(new BaseBeanContainer<BaseVsoApiResBean>(baseVsoApiResBean));
    }

    @Override
    protected void handleHttpFailure(int httpCode) {
        modelCallback.onHttpFailure(httpCode);
    }

    @Override
    protected RequestHandle handle(HttpUtil httpUtil, Context context, String url, RequestParams params, AsyncHttpResponseHandler handler) {
        return httpUtil.getWithParams(context, url, params, handler);
    }

}
