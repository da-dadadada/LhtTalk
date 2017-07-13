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

<<<<<<< HEAD
import android.content.Context;
import android.content.Intent;
=======
>>>>>>> 35ddafcdc3e642cc72e16bdd4ac7a666c81e7709
import android.content.SharedPreferences;

import com.lht.lhttalk.base.model.apimodel.ApiRequestCallback;
import com.lht.lhttalk.base.model.apimodel.BaseBeanContainer;
import com.lht.lhttalk.base.model.apimodel.BaseVsoApiResBean;
<<<<<<< HEAD
import com.lht.lhttalk.base.presenter.IApiRequestPresenter;
import com.lht.lhttalk.module.login.model.pojo.LoginResBean;
import com.lht.lhttalk.module.smack.service.SmackService;
=======
import com.lht.lhttalk.cfg.SPConstants;
import com.lht.lhttalk.module.login.pojo.LoginResBean;
>>>>>>> 35ddafcdc3e642cc72e16bdd4ac7a666c81e7709
import com.lht.lhttalk.module.ucenter.LoginAccount;
import com.lht.lhttalk.module.ucenter.UserBean;
import com.lht.lhttalk.module.ucenter.UserModel;
import com.lht.lhttalk.util.SPUtil;
import com.lht.lhttalk.util.string.StringUtil;

import static com.lht.lhttalk.cfg.SPConstants.Token.KEY_ACCESS_TOKEN;
import static com.lht.lhttalk.cfg.SPConstants.Token.KEY_USERNAME;

/**
 * Created by chhyu on 2017/7/11.
 */

public class LoginPresenter implements LoginContract.Presenter, IApiRequestPresenter {

<<<<<<< HEAD
    private SharedPreferences spToken;
=======
>>>>>>> 35ddafcdc3e642cc72e16bdd4ac7a666c81e7709
    private LoginContract.View view;
    private SharedPreferences sp;
    private LoginAccount loginAccount;

<<<<<<< HEAD

    public LoginPresenter(SharedPreferences spToken, LoginContract.View view) {
        this.spToken = spToken;
=======
    public LoginPresenter(SharedPreferences sp, LoginContract.View view) {
        this.sp = sp;
>>>>>>> 35ddafcdc3e642cc72e16bdd4ac7a666c81e7709
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
<<<<<<< HEAD
    public void doLogin(Context context, String username, String pwd) {
//   for quick ui test     Intent intent = new Intent(context, MainActivity.class);
//        context.startActivity(intent);
        if (StringUtil.isEmpty(username)) {
            view.showMsg("请输入用户名");
            return;
        }
        if (StringUtil.isEmpty(pwd)) {
            view.showMsg("请输入密码");
=======
    public void doLogin(String username, String pwd) {
        if (StringUtil.isEmpty(username)) {
            view.showInfoEmptyToast("请输入用户名");
            return;
        }
        if (StringUtil.isEmpty(pwd)) {
            view.showInfoEmptyToast("请输入密码");
>>>>>>> 35ddafcdc3e642cc72e16bdd4ac7a666c81e7709
            return;
        }
        view.showWaitView(true);
        loginAccount = new LoginAccount(username, pwd);
        UserModel userModel = new UserModel(new UserBean(loginAccount));
<<<<<<< HEAD
        userModel.login(context, new LoginRequestCallback());
=======
        userModel.login(view.getmContext(), new LoginRequestCallbask());
>>>>>>> 35ddafcdc3e642cc72e16bdd4ac7a666c81e7709

    }

    @Override
    public void doXmppConnect(Context context) {
        Intent intent = new Intent(context,SmackService.class);
        context.startService(intent);
    }

<<<<<<< HEAD
    @Override
    public void cancelRequestOnFinish(Context context) {

    }

    private class LoginRequestCallback implements ApiRequestCallback<LoginResBean> {

//        private String username;
//        private String password;
//
//        public LoginRequestCallback(String username, String pwd) {
//            this.username = username;
//            this.password = pwd;
//        }

        @Override
        public void onSuccess(BaseBeanContainer<LoginResBean> beanContainer) {
            view.showWaitView(false);
            view.showMsg("登录成功");
=======
        @Override
        public void onSuccess(BaseBeanContainer<LoginResBean> beanContainer) {
            view.showWaitView(false);
            view.showLoginStateToast("登录成功");
>>>>>>> 35ddafcdc3e642cc72e16bdd4ac7a666c81e7709
            LoginResBean data = beanContainer.getData();

<<<<<<< HEAD
            SPUtil.modifyString(spToken, KEY_USERNAME, data.getUsername());
            SPUtil.modifyString(spToken, KEY_ACCESS_TOKEN, data.getVso_token());

            view.onLoginSuccess();
            view.jump2MainActivity(data);
=======
            SPUtil.modifyString(sp, SPConstants.Token.SP_TOKEN, data.getVso_token());
            SPUtil.modifyString(sp, SPConstants.Token.KEY_USERNAME, data.getUsername());
>>>>>>> 35ddafcdc3e642cc72e16bdd4ac7a666c81e7709
        }

        @Override
        public void onFailure(BaseBeanContainer<BaseVsoApiResBean> beanContainer) {
            view.showWaitView(false);
<<<<<<< HEAD
            beanContainer.getData();
            view.showMsg("登录失败");
=======
            view.showLoginStateToast("登录失败");
>>>>>>> 35ddafcdc3e642cc72e16bdd4ac7a666c81e7709
        }

        @Override
        public void onHttpFailure(int httpStatus) {
            view.showWaitView(false);
<<<<<<< HEAD
//            handle on debug
=======
            view.showLoginStateToast("网络异常");
>>>>>>> 35ddafcdc3e642cc72e16bdd4ac7a666c81e7709
        }
    }
}
