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

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.lht.lhttalk.R;
import com.lht.lhttalk.base.activity.BaseActivity;
import com.lht.lhttalk.base.activity.asyncprotected.AsyncProtectedActivity;
import com.lht.lhttalk.base.model.apimodel.ApiRequestCallback;
import com.lht.lhttalk.base.model.apimodel.BaseBeanContainer;
import com.lht.lhttalk.base.model.apimodel.BaseVsoApiResBean;
import com.lht.lhttalk.base.presenter.IApiRequestPresenter;
import com.lht.lhttalk.module.login.LoginActivity;
import com.lht.lhttalk.module.login.LoginPresenter;
import com.lht.lhttalk.module.login.model.pojo.LoginResBean;
import com.lht.lhttalk.module.main.MainActivity;
import com.lht.lhttalk.module.ucenter.LoginAccount;
import com.lht.lhttalk.module.ucenter.UserBean;
import com.lht.lhttalk.module.ucenter.UserModel;
import com.lht.lhttalk.util.SPUtil;
import com.lht.lhttalk.util.string.StringUtil;
import com.lht.lhttalk.util.toast.ToastUtils;

public class SplashActivity extends AsyncProtectedActivity {
    public static final String PAGENAME = "SplashActivity";
    private RelativeLayout rlSplash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initView();
        initVariable();
        initEvent();
    }

    @Override
    public BaseActivity getActivity() {
        return SplashActivity.this;
    }

    @Override
    protected void initView() {
        rlSplash = (RelativeLayout) findViewById(R.id.rl_splash);
        rlSplash.postDelayed(new Runnable() {
            @Override
            public void run() {
                //// TODO: 2017/7/13
                jump2LoginActivity();
            }
        }, 2000);
    }

    private void autoLogin() {
//        SharedPreferences sp = getSharedPreferences(LoginPresenter.SP_NAME, MODE_PRIVATE);
//        String username = sp.getString(LoginPresenter.AUTO_LOGIN_USERNAME, "");
//        String password = sp.getString(LoginPresenter.AUTO_LOGIN_PASSWORD, "");
//        Log.e("lmsg", "userInfo=" + username + "+" + password);
//        if (StringUtil.isEmpty(username)) {
//            jump2LoginActivity();
//            return;
//        }
//        if (StringUtil.isEmpty(password)) {
//            jump2LoginActivity();
//            return;
//        }
//        LoginAccount loginAccount = new LoginAccount(username, password);
//        UserModel userModel = new UserModel(new UserBean(loginAccount));
//        userModel.login(this, new LoginRequestCallback());
    }

    @Override
    protected IApiRequestPresenter getApiRequestPresenter() {
        return null;
    }


    @Override
    protected void initVariable() {

    }

    @Override
    protected void initEvent() {

    }

    class LoginRequestCallback implements ApiRequestCallback<LoginResBean> {
        @Override
        public void onSuccess(BaseBeanContainer<LoginResBean> beanContainer) {
            Log.e("lmsg", "自动登录成功");
            LoginResBean data = beanContainer.getData();
            jump2MainActivity(data);
        }

        @Override
        public void onFailure(BaseBeanContainer<BaseVsoApiResBean> beanContainer) {
            Log.e("lmsg", "自动登录失败");
            jump2LoginActivity();
        }

        @Override
        public void onHttpFailure(int httpStatus) {
            jump2LoginActivity();
            ToastUtils.show(SplashActivity.this, "网诺异常", ToastUtils.Duration.s);
        }
    }

    private void jump2MainActivity(LoginResBean data) {
        //自动登录成功
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(MainActivity.USER_LOGIN_INFO, JSON.toJSONString(data));
        startActivity(intent);
    }

    @Override
    public ProgressBar getProgressBar() {
        return null;
    }

    public void jump2LoginActivity() {
        Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
