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

package com.lht.lhttalk.base.model.apimodel;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.lht.lhttalk.util.internet.AsyncResponseHandlerComposite;
import com.lht.lhttalk.util.internet.HttpAction;
import com.lht.lhttalk.util.internet.HttpUtil;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestHandle;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * <p><b>Package:</b> com.lht.lhttalk.mvp.model </p>
 * <p><b>Project:</b> czspace </p>
 * <p><b>Classname:</b> AbsRestfulApiModel </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/3/7.
 */

public abstract class AbsApiRequest<API, T> implements IApiRequest {
    private final T data;
    private final HttpUtil httpUtil;
    private RequestParams params;
    private RequestHandle handle;
    private API apiImpl;

    public AbsApiRequest(Class<API> apiClass, T data) {
        this.data = data;
        httpUtil = HttpUtil.getInstance();
        try {
            Constructor<API> constructor = apiClass.getConstructor();
            apiImpl = constructor.newInstance();
        } catch (NoSuchMethodException
                | SecurityException
                | InstantiationException
                | IllegalAccessException
                | IllegalArgumentException
                | InvocationTargetException e) {
            e.printStackTrace();

            throw new IllegalArgumentException(
                    apiClass.getSimpleName()
                            + "must have a public none param constructor");

        }
    }

    protected abstract String formatUrl(API apiImpl);
    protected abstract RequestParams formatParam(API apiImpl);
    protected abstract HttpAction getHttpAction();

    protected final T getData() {
        return data;
    }


    @Override
    public final void cancelRequestByContext(Context context) {
        if (handle != null) {
            handle.cancel(true);
        }
    }

    @Override
    public final void doRequest(Context context) {
        String url = formatUrl(apiImpl);
        params = formatParam(apiImpl);
        AsyncResponseHandlerComposite composite =
                new AsyncResponseHandlerComposite(getHttpAction(), url, params);
        composite.addHandler(new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                String res = new String(bytes);
                BaseVsoApiResBean bean = JSON.parseObject(res, BaseVsoApiResBean.class);
                if (bean.isSuccess()) {
                   handleSuccess(bean);
                } else {
                   handleFailure(bean);
                }
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                handleHttpFailure(i);
            }
        });
        handle = handle(httpUtil,context,url,params,composite);
    }

    protected abstract void handleSuccess(BaseVsoApiResBean baseVsoApiResBean);

    protected abstract void handleFailure(BaseVsoApiResBean baseVsoApiResBean);

    protected abstract void handleHttpFailure(int httpCode);

    protected abstract RequestHandle handle(HttpUtil httpUtil,Context context,
                                            String url,RequestParams params,
                                            AsyncHttpResponseHandler handler);
}
