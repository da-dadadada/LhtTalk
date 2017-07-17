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

package com.lht.lhttalk.module.friend.Requests;

import android.content.Context;

import com.lht.lhttalk.base.model.apimodel.AbsApiRequest;
import com.lht.lhttalk.base.model.apimodel.BaseVsoApiResBean;
import com.lht.lhttalk.module.api.ImApiCollections;
import com.lht.lhttalk.util.internet.HttpAction;
import com.lht.lhttalk.util.internet.HttpUtil;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestHandle;
import com.loopj.android.http.RequestParams;

/**
 * <p><b>Package:</b> com.lht.lhttalk.module.friend.Requests </p>
 * <p><b>Project:</b> LhtTalk </p>
 * <p><b>Classname:</b> GetAllFriendsRequest </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/7/14.
 */

public class GetAllFriendsRequest
        extends AbsApiRequest<ImApiCollections.FriendListApi,
        GetAllFriendsRequest.RequestData> {

    public GetAllFriendsRequest(Class<ImApiCollections.FriendListApi> friendListApiClass,
                                RequestData data) {
        super(friendListApiClass, data);
    }

    @Override
    protected String formatUrl(ImApiCollections.FriendListApi apiImpl) {
        return null;
    }

    @Override
    protected RequestParams formatParam(ImApiCollections.FriendListApi apiImpl) {
        return null;
    }

    @Override
    protected HttpAction getHttpAction() {
        return null;
    }

    @Override
    protected void handleSuccess(BaseVsoApiResBean baseVsoApiResBean) {

    }

    @Override
    protected void handleFailure(BaseVsoApiResBean baseVsoApiResBean) {

    }

    @Override
    protected void handleHttpFailure(int httpCode) {

    }

    @Override
    protected RequestHandle handle(HttpUtil httpUtil, Context context, String url, RequestParams params, AsyncHttpResponseHandler handler) {
        return null;
    }

    public static final class RequestData {

    }

}
