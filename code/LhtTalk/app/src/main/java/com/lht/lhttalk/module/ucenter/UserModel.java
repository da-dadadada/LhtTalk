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

package com.lht.lhttalk.module.ucenter;

import android.content.Context;

import com.lht.lhttalk.base.model.apimodel.ApiRequestCallback;
import com.lht.lhttalk.module.login.model.LoginApiRequest;
import com.lht.lhttalk.module.login.pojo.LoginResBean;

/**
 * <p><b>Package:</b> com.lht.lhttalk.module.ucenter </p>
 * <p><b>Project:</b> LhtTalk </p>
 * <p><b>Classname:</b> UserModel </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/7/11.
 */

public class UserModel {
    private final UserBean userBean;


    public UserModel(UserBean userBean) {
        this.userBean = userBean;
    }

    public void logout() {

    }

    public void login(Context context, ApiRequestCallback<LoginResBean> modelCallback) {
        LoginApiRequest request = new LoginApiRequest(userBean.getLoginAccount(), modelCallback);
        request.doRequest(context);
    }

    public boolean isLogin() {
        return false;
    }

    public void getBasicInfo() {

    }


}
