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

package com.lht.lhttalk.umeng.customerror;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lht.lhttalk.base.MainApplication;
import com.umeng.analytics.MobclickAgent;

/**
 * <p><b>Package</b> com.lht.vsocyy.clazz
 * <p><b>Project</b> VsoCyy
 * <p><b>Classname</b> BaseUmengError
 * <p><b>Description</b>: TODO
 * <p>Created by leobert on 2016/12/14.
 */

public abstract class BaseUmengError implements IUmengCustomError {

    protected String errorType;

    protected String errorInfo;

    protected String errorData;

    @Override
    public void setErrorType(String type) {
        this.errorType = type;
    }

    @Override
    public void setErrorInfo(String info) {
        this.errorInfo = info;
    }

    @Override
    public void setErrorData(String data) {
        this.errorData = data;
    }

    protected String getErrorType() {
        return errorType;
    }

    protected String getErrorInfo() {
        return errorInfo;
    }

    protected String getErrorData() {
        return errorData;
    }

    protected final String formatErrorString() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("errorType",getErrorType());
        jsonObject.put("errorInfo",getErrorInfo());
        jsonObject.put("errorData",getErrorData());
        return JSON.toJSONString(jsonObject);
    }

    @Override
    public final void report() {
        report(MainApplication.getOurInstance());
    }

    @Override
    public final void report(Context context) {
        MobclickAgent.reportError(context,formatErrorString());
    }
}
