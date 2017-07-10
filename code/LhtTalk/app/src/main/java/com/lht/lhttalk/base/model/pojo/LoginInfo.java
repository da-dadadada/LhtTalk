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

package com.lht.lhttalk.base.model.pojo;

import com.lht.lhttalk.module.user.login.model.pojo.LoginResBean;
import com.lht.lhttalk.util.string.StringUtil;

/**
 * <p><b>Package</b> com.lht.vsocyy.mvp.model.pojo
 * <p><b>Project</b> VsoCyy
 * <p><b>Classname</b> VerifyInfo
 * <p><b>Description</b>: 身份认证信息
 * Created by leobert on 2016/5/5.
 */
public class LoginInfo {
    //最基本的信息
    private String username;
    private String accessToken;
    private String accessId;
    private String avatar;
    private String nickname;
    private boolean hasChooseRole;


//    private LoginType type;


//    //用户基本但不敏感的信息
//    private BasicInfoResBean basicUserInfo;

    //登录返回data，保留备用
    private LoginResBean loginResBean;


    public LoginInfo() {
    }


    public LoginInfo(String username, String accessToken, String accessId) {
        this.username = username;
        this.accessToken = accessToken;
        this.accessId = accessId;
    }

    public boolean isHasChooseRole() {
        return hasChooseRole;
    }

    public void setHasChooseRole(boolean hasChooseRole) {
        this.hasChooseRole = hasChooseRole;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAccessId() {
        return accessId;
    }

    public void setAccessId(String accessId) {
        this.accessId = accessId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String toString() {
        return "user:" + getUsername() + ",id:" + getAccessId() + ",token:" + getAccessToken();
    }


    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public LoginResBean getLoginResBean() {
        return loginResBean;
    }

    public void setLoginResBean(LoginResBean loginResBean) {
        this.loginResBean = loginResBean;
    }

    public boolean isLogin() {
        //非严格判断
        return !StringUtil.isEmpty(username);
    }

    public boolean isUserInfoCreated() {
        //非严格判断
        boolean b = StringUtil.isEmpty(nickname) || nickname.equals(username);
        return !b;
    }


    public void copy(LoginInfo info) {
        if (info == null) {
            copy(new LoginInfo());
            return;
        }
        this.setUsername(info.getUsername());
        this.setAccessId(info.getAccessId());
        this.setAccessToken(info.getAccessToken());
//        this.setType(info.getType());
        this.setNickname(info.getNickname());
        this.setAvatar(info.getAvatar());
//        this.setBasicUserInfo(info.getBasicUserInfo());
        this.setHasChooseRole(info.isHasChooseRole());
        this.setLoginResBean(info.getLoginResBean());
    }


//    public static String generateSession(LoginInfo info) {
//        String sess = VsoUtil.createVsoSessionCode(info.getUsername(),
//                info.getAccessId());
//        return sess;
//    }
}
