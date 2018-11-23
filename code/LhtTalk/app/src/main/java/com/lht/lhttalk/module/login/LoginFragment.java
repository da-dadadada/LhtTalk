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

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.alibaba.fastjson.JSON;
import com.lht.lhttalk.R;
import com.lht.lhttalk.base.fragment.BaseFragment;
import com.lht.lhttalk.module.login.pojo.LoginResBean;
import com.lht.lhttalk.module.main.HomeActivity;
import com.lht.lhttalk.util.toast.ToastUtils;

/**
 * Created by chhyu on 2017/7/11.
 */

public class LoginFragment extends BaseFragment implements LoginContract.View {
    public static final String PAGENAME = "LoginFragment";
    private AutoCompleteTextView tvUsername;
    private EditText etPassword;
    private Button btnLogin;
    private ProgressBar progressBar;
    private LoginContract.Presenter presenter;

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fg_login, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initVariable();
        initEvent();
    }

    @Override
    protected void initView(View view) {
        progressBar = (ProgressBar) view.findViewById(R.id.login_progress);
        tvUsername = (AutoCompleteTextView) view.findViewById(R.id.tv_username);
        etPassword = (EditText) view.findViewById(R.id.et_password);
        btnLogin = (Button) view.findViewById(R.id.btn_login);

    }

    @Override
    protected void initVariable() {

    }

    @Override
    protected void initEvent() {
        tvUsername.setText("13404298601");
        etPassword.setText("123456");
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = tvUsername.getText().toString();
                String password = etPassword.getText().toString();

                presenter.doLogin(getActivity(),username, password);
            }
        });
    }

    @Override
    protected String getPageName() {
        return LoginFragment.PAGENAME;
    }

    @Override
    public void setPresenter(@NonNull LoginContract.Presenter presenter1) {
        presenter = presenter1;
    }


    @Override
    public void showWaitView(boolean isShow) {
        if (isShow) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onLoginSuccess() {
        presenter.doXmppConnect(getActivity());
    }

    @Override
    public void jump2MainActivity(LoginResBean data) {
        Intent intent = new Intent(getActivity(), HomeActivity.class);
//        intent.putExtra(HomeActivity.USER_LOGIN_INFO, JSON.toJSONString(data));
        startActivity(intent);
        getActivity().finish();
    }

    @Override
    public void showMsg(String msg) {
        ToastUtils.show(getActivity(),msg, ToastUtils.Duration.s);
    }

    @Override
    public void showMsg(@StringRes int msgResId) {
        ToastUtils.show(getActivity(),msgResId, ToastUtils.Duration.s);
    }

}
