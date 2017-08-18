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

package com.lht.lhttalk.module.api;

import android.app.Activity;
import android.app.Fragment;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;

import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import individual.leobert.retrofitext.RetrofitExt;
import individual.leobert.retrofitext.core.ApiDefCheckUtil;
import individual.leobert.retrofitext.coverter.StringConverterFactory;
import individual.leobert.retrofitext.ext.RequestManager;
import individual.leobert.retrofitext.coverter.FastJsonConverterFactory;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;


/**
 * <p><b>Package:</b> individual.leobert.retrofitext.sample </p>
 * <p><b>Project:</b> PermissionDemo </p>
 * <p><b>Classname:</b> HttpUtil </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/6/16.
 */

public class ApiClient {
    private static boolean isLogEnable = false;
    private static final String TAG = "ApiClient";

    private static final Map<Class, Object> sInterfaceImplementCache =
            new ConcurrentHashMap<>();

    private static final String BASE_URL = "https://www.vsochina.com/";

    ///////////////////////////////////////////////////////////////////////////
    // Api type define
    ///////////////////////////////////////////////////////////////////////////
    private static final int TYPE_JSON = 1;
    private static final int TYPE_STRING = 2;

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({TYPE_JSON, TYPE_STRING})
    public @interface ApiType {
    }

    public static void setLogEnable(boolean isLogEnable) {
        ApiClient.isLogEnable = isLogEnable;
        individual.leobert.retrofitext.ext.Debug.setDebugEnable(isLogEnable);
    }

    private Retrofit retrofit = null;

    private static ApiClient jsonApiClient;
    private static ApiClient stringApiClient;


    private static final String KEY_USER_AGENT = "User-Agent";

    // TODO: custom ua
    private static final String CUSTOM_USER_AGENT = "MY CUSTOM UA";

    private ApiClient(@ApiType int type) {
        switch (type) {
            case TYPE_STRING:
                retrofit = new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .client(newCustomUaClient())
                        .addConverterFactory(StringConverterFactory.create())
                        .build();
                break;
            case TYPE_JSON:
            default:
                retrofit = new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .client(newCustomUaClient())
                        .addConverterFactory(FastJsonConverterFactory.create())
                        .build();
                break;
        }
    }

    public static ApiClient getJsonApiClient() {
        if (jsonApiClient == null) {
            jsonApiClient = new ApiClient(TYPE_JSON);
        }
        return jsonApiClient;
    }

    public static ApiClient getStringApiClient() {
        if (stringApiClient == null) {
            stringApiClient = new ApiClient(TYPE_STRING);
        }
        return stringApiClient;
    }


    public <T> T apiInstance(Class<T> remoteApiClazz) {
        if (!ApiDefCheckUtil.isAllowedApi(remoteApiClazz))
            throw new IllegalArgumentException("must give a interface with @apiDef annotation");
//        T api = retrofit.create(remoteApiClazz);
        return RetrofitExt.getProxyInterface(remoteApiClazz, getImpl(remoteApiClazz));
    }

    private <T> T getImpl(Class<T> remoteApiClazz) {
        T apiImplement;
        Object cacheApiImplement = sInterfaceImplementCache.get(remoteApiClazz);
        if (cacheApiImplement != null) {
            apiImplement = remoteApiClazz.cast(cacheApiImplement);
        } else {
            apiImplement = retrofit.create(remoteApiClazz);
            sInterfaceImplementCache.put(remoteApiClazz, apiImplement);
        }
        return apiImplement;
    }

    private OkHttpClient newCustomUaClient() {
        return new OkHttpClient().newBuilder()
                .connectTimeout(3000, TimeUnit.MILLISECONDS)
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(@NonNull Chain chain) throws IOException {
                        Request request = chain.request()
                                .newBuilder()
                                .addHeader(KEY_USER_AGENT, CUSTOM_USER_AGENT)
                                .build();
                        return chain.proceed(request);
                    }
                }).build();
    }

    public static void shutdown() {
        if (jsonApiClient != null)
            jsonApiClient.retrofit = null;
        if (stringApiClient != null)
            stringApiClient.retrofit = null;
        RequestManager.shutdown();
    }

    public static void cancel(Activity activity) {
        RequestManager.getInstance().cancel(activity);
    }

    public static void cancel(Fragment fragment) {
        RequestManager.getInstance().cancel(fragment);
    }

    public static void cancel(android.support.v4.app.Fragment fragment) {
        RequestManager.getInstance().cancel(fragment);
    }

}
