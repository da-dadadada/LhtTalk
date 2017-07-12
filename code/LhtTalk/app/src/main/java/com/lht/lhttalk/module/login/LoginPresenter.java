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
import android.util.Log;
import android.widget.Toast;

import com.lht.lhttalk.base.model.apimodel.ApiModelCallback;
import com.lht.lhttalk.base.model.apimodel.BaseBeanContainer;
import com.lht.lhttalk.base.model.apimodel.BaseVsoApiResBean;
import com.lht.lhttalk.module.login.model.pojo.LoginResBean;
import com.lht.lhttalk.module.ucenter.LoginAccount;
import com.lht.lhttalk.module.ucenter.UserBean;
import com.lht.lhttalk.module.ucenter.UserModel;
import com.lht.lhttalk.util.string.StringUtil;

/**
 * Created by chhyu on 2017/7/11.
 */

public class LoginPresenter implements LoginContract.Presenter {
    private Context context;
    private LoginContract.View view;

    private LoginAccount loginAccount;

    public LoginPresenter(Context context, LoginContract.View view) {
        this.context = context;
        this.view = view;

        view.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void doLogin(String username, String pwd) {
        view.showWaitView(true);
        if (StringUtil.isEmpty(username)) {
            Toast.makeText(context, "请输入用户名", Toast.LENGTH_SHORT).show();
            return;
        }
        if (StringUtil.isEmpty(pwd)) {
            Toast.makeText(context, "请输入密码", Toast.LENGTH_SHORT).show();
            return;
        }
        loginAccount = new LoginAccount(username, pwd);
        UserModel userModel = new UserModel(new UserBean(loginAccount));
        userModel.login(context, new LoginRequestCallbask());

    }

    class LoginRequestCallbask implements ApiModelCallback<LoginResBean> {

        @Override
        public void onSuccess(BaseBeanContainer<LoginResBean> beanContainer) {
            view.showWaitView(false);
            Toast.makeText(context, "登录成功", Toast.LENGTH_SHORT).show();
            LoginResBean data = beanContainer.getData();
            view.jump2MainActivity(data);
        }

        @Override
        public void onFailure(BaseBeanContainer<BaseVsoApiResBean> beanContainer) {
            view.showWaitView(false);
            beanContainer.getData();
            Toast.makeText(context, "登录失败", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onHttpFailure(int httpStatus) {
            view.showWaitView(false);
            Toast.makeText(context, "网诺异常", Toast.LENGTH_SHORT).show();
        }
    }
}
