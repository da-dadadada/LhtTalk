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

package com.lht.lhttalk.module.login;

import android.content.Context;
import android.support.annotation.StringRes;

import com.lht.lhttalk.base.BasePresenter;
import com.lht.lhttalk.base.BaseView;
import com.lht.lhttalk.module.login.model.pojo.LoginResBean;

/**
 * Created by chhyu on 2017/7/11.
 */

/*public*/ interface LoginContract {

    interface View extends BaseView<Presenter> {

        void showWaitView(boolean isShow);

        void onLoginSuccess();

        void jump2MainActivity(LoginResBean data);

        void showMsg(String msg);

        void showMsg(@StringRes int msgResId);
    }

    interface Presenter extends BasePresenter {

        void doLogin(Context context, String username, String pwd);

        void doXmppConnect(Context context);
    }
}
