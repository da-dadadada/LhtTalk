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
import android.os.Handler;
import android.widget.ProgressBar;

import com.alibaba.fastjson.JSON;
import com.lht.lhttalk.R;
import com.lht.lhttalk.base.IVerifyHolder;
import com.lht.lhttalk.base.MainApplication;
import com.lht.lhttalk.base.activity.BaseActivity;
import com.lht.lhttalk.base.activity.asyncprotected.AsyncProtectedActivity;
import com.lht.lhttalk.base.model.apimodel.ApiRequestCallback;
import com.lht.lhttalk.base.model.apimodel.BaseBeanContainer;
import com.lht.lhttalk.base.model.apimodel.BaseVsoApiResBean;
import com.lht.lhttalk.base.presenter.IApiRequestPresenter;
import com.lht.lhttalk.module.login.LoginActivity;
import com.lht.lhttalk.module.main.HomeActivity;
import com.lht.lhttalk.module.publ.bean.BasicInfoParam;
import com.lht.lhttalk.module.publ.bean.UserBasicInfo;
import com.lht.lhttalk.module.ucenter.UserModel;
import com.lht.lhttalk.util.string.StringUtil;

import static com.lht.lhttalk.cfg.SPConstants.Token.KEY_ACCESS_TOKEN;
import static com.lht.lhttalk.cfg.SPConstants.Token.KEY_USERNAME;

public class SplashActivity extends AsyncProtectedActivity {
//    public static final String PAGENAME = "SplashActivity";
//    private RelativeLayout rlSplash;

    private boolean authSuccess = false;

    private AuthRequest authRequest;

    private UserBasicInfo userBasicInfo;

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
//        rlSplash = (RelativeLayout) findViewById(R.id.rl_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (authSuccess) {
                    authRequest.cancelRequest();
                    jump2MainActivity(userBasicInfo);
                } else
                    jump2LoginActivity();
            }
        }, 2000);
        autoLogin();
    }

    private void autoLogin() {
        SharedPreferences sp = MainApplication.getOurInstance().getTokenSp();
        String token = sp.getString(KEY_ACCESS_TOKEN, "");
        String username = sp.getString(KEY_USERNAME, "");
        if (StringUtil.isEmpty(token)) {
            authSuccess = false;
            return;
        }
        if (StringUtil.isEmpty(username)) {
            authSuccess = false;
            return;
        }
        //验证token
        BasicInfoParam basicInfoParam = new BasicInfoParam();
        basicInfoParam.setUsername(username);
        basicInfoParam.setAuth_name(username);
        basicInfoParam.setVso_token(token);

        authRequest = new AuthRequest(basicInfoParam, new AuthCallback());
        authRequest.doRequest(this);
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

    private class AuthCallback implements ApiRequestCallback<UserBasicInfo> {

        @Override
        public void onSuccess(BaseBeanContainer<UserBasicInfo> beanContainer) {
            userBasicInfo = beanContainer.getData();
            IVerifyHolder.mUserBean.setUserBasicInfo(userBasicInfo);
            new UserModel().startXmpp(getActivity());
            authSuccess = true;
        }

        @Override
        public void onFailure(BaseBeanContainer<BaseVsoApiResBean> beanContainer) {
            authSuccess = false;
        }

        @Override
        public void onHttpFailure(int httpStatus) {
            authSuccess = false;
        }
    }

    private void jump2MainActivity(UserBasicInfo data) {
        //自动登录成功
        Intent intent = new Intent(this, HomeActivity.class);
//        intent.putExtra(HomeActivity.USER_LOGIN_INFO, JSON.toJSONString(data));
        startActivity(intent);
        finishWithoutOverrideAnim();
    }

    @Override
    public ProgressBar getProgressBar() {
        return null;
    }

    public void jump2LoginActivity() {
        Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
        startActivity(intent);
        finishWithoutOverrideAnim();
    }
}
