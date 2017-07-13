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

package com.lht.lhttalk.module.login.pojo;

import com.lht.lhttalk.base.model.apimodel.BaseVsoApiResBean;
import com.lht.lhttalk.util.string.StringUtil;

/**
 * <p><b>Package:</b> com.lht.lhttalk.module.login.pojo </p>
 * <p><b>Project:</b> LhtTalk </p>
 * <p><b>Classname:</b> LoginResBean </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/7/11.
 */

public class LoginResBean {
    public static final int RET_UNEXIST = 13003;

    public static final int RET_ERROR_PWD = 13004;

    private String username;

    private String nickname;

    private String mobile;

    /**
     * 头像url
     */
    private String avatar;

    /**
     * redis中存储用户信息的key
     */
    private String vso_token;//

    private int sex;

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getVso_token() {
        return vso_token;
    }

    public void setVso_token(String vso_token) {
        this.vso_token = vso_token;
    }

    public static String parseMsgByRet(int ret, BaseVsoApiResBean bean) {
        String msg = null;
        switch (ret) {
            case RET_ERROR_PWD:
                msg = "密码错误，请重新输入";
                break;
            case RET_UNEXIST:
                msg = "账号不存在"; //实际上优先检查该情况进行注册引导
                break;
            default:
                if (bean != null)
                    msg = bean.getMessage();
                if (StringUtil.isEmpty(msg))
                    msg = "登录失败，请重新登录";
                break;
        }
        return msg;
    }
}
