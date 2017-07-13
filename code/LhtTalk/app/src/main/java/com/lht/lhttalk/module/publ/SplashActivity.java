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

import com.alibaba.fastjson.JSON;
import com.lht.lhttalk.R;
import com.lht.lhttalk.base.activity.BaseActivity;
import com.lht.lhttalk.base.activity.asyncprotected.AsyncProtectedActivity;
import com.lht.lhttalk.base.model.apimodel.ApiRequestCallback;
import com.lht.lhttalk.base.model.apimodel.BaseBeanContainer;
import com.lht.lhttalk.base.model.apimodel.BaseVsoApiResBean;
import com.lht.lhttalk.base.presenter.IApiRequestPresenter;
import com.lht.lhttalk.cfg.SPConstants;
import com.lht.lhttalk.module.login.LoginActivity;
import com.lht.lhttalk.module.login.pojo.LoginResBean;
import com.lht.lhttalk.module.main.HomeActivity;
import com.lht.lhttalk.module.publ.bean.BasicInfoParam;
import com.lht.lhttalk.module.publ.bean.UserBasicInfo;
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
                autoLogin();
            }
        }, 2000);
    }

    private void autoLogin() {
        SharedPreferences sp = getSharedPreferences(SPConstants.Basic.SP_NAME, MODE_PRIVATE);
        String sp_token = sp.getString(SPConstants.Token.SP_TOKEN, "");
        String username = sp.getString(SPConstants.Token.KEY_USERNAME, "");
        if (StringUtil.isEmpty(sp_token)) {
            jump2LoginActivity();
            return;
        }
        if (StringUtil.isEmpty(username)) {
            jump2LoginActivity();
            return;
        }
        //验证token
        Log.e("lmsg", "token:" + sp_token);
        Log.e("lmsg", "username:" + username);

        QueryUserBasicInfoRequest request = new QueryUserBasicInfoRequest(new BasicInfoParam(sp_token, username), new QueryBasicInfoRequestCallback());
        request.doRequest(this);
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

    class QueryBasicInfoRequestCallback implements ApiRequestCallback<UserBasicInfo> {

        @Override
        public void onSuccess(BaseBeanContainer<UserBasicInfo> beanContainer) {
            UserBasicInfo data = beanContainer.getData();
            Log.e("lmsg", "查询用户基本信息成功" + "\r\n" + "info=" + JSON.toJSONString(data));
            jump2MainActivity(data);
        }

        @Override
        public void onFailure(BaseBeanContainer<BaseVsoApiResBean> beanContainer) {

            Log.e("lmsg", "查询用户基本信息失败" + "\r\n" + "info=" + JSON.toJSONString(beanContainer.getData()));
            jump2LoginActivity();
        }

        @Override
        public void onHttpFailure(int httpStatus) {

        }
    }

    private void jump2MainActivity(UserBasicInfo data) {
        //自动登录成功
        Intent intent = new Intent(this, HomeActivity.class);
        intent.putExtra(HomeActivity.USER_LOGIN_INFO, JSON.toJSONString(data));
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
