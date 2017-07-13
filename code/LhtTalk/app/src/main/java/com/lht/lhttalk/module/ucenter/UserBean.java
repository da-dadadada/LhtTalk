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

import com.lht.lhttalk.module.publ.bean.UserBasicInfo;
import com.lht.lhttalk.util.string.StringUtil;

/**
 * <p><b>Package:</b> com.lht.lhttalk.module.ucenter </p>
 * <p><b>Project:</b> LhtTalk </p>
 * <p><b>Classname:</b> UserBean </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/7/11.
 */

public class UserBean {
    private String username;
    private String token;

    private UserBasicInfo userBasicInfo;

//    private final
//    @InstanceType
//    int INSTANCE_TYPE;
//
//    public static final int TYPE_OF_ACCOUNT = 1;
//    public static final int TYPE_OF_TOKEN = 2;
//
//    @IntDef({TYPE_OF_ACCOUNT, TYPE_OF_TOKEN})
//    @Retention(RetentionPolicy.CLASS)
//    public @interface InstanceType {
//    }


    public UserBean() {
    }

    public UserBean(String username, String token) {
        this.username = username;
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isLogin() {
        return !StringUtil.isEmpty(token);
    }

    public UserBasicInfo getUserBasicInfo() {
        return userBasicInfo;
    }

    public void setUserBasicInfo(UserBasicInfo userBasicInfo) {
        this.userBasicInfo = userBasicInfo;
    }

    public void clear() {
        username = null;
        token = null;
        userBasicInfo = null;
    }

    public void copy(UserBean userBean) {
        if (userBean == null) {
            copy(new UserBean());
            return;
        }
        this.setToken(userBean.getToken());
        this.setUsername(userBean.getUsername());
        this.setUserBasicInfo(userBean.getUserBasicInfo());
    }
}
