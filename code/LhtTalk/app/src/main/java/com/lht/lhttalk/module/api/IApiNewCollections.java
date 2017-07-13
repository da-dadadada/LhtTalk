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

package com.lht.lhttalk.module.api;

import com.lht.lhttalk.base.IVerifyHolder;
import com.lht.lhttalk.base.model.apimodel.BaseVsoApiResBean;
import com.lht.lhttalk.module.publ.bean.BasicInfoParam;
import com.lht.lhttalk.module.ucenter.LoginAccount;
import com.lht.lhttalk.util.string.StringUtil;
import com.loopj.android.http.RequestParams;

/**
 * <p><b>Package:</b> com.lht.lhttalk.interfaces.net </p>
 * <p><b>Project:</b> czspace </p>
 * <p><b>Classname:</b> IApiNewCollections </p>
 * <p><b>Description:</b> 收集采用了auth的apinew公用接口 </p>
 * Created by leobert on 2017/3/20.
 */

public interface IApiNewCollections {

    /**
     * 新版APINEW的接口
     */
    abstract class ApiNewRestOauthApi extends IRestfulApi.AbsRestApiBase {
        private static final String HOST = "apinew.vsochina.com";

        private static final String KEY_AUTH_USER = "auth_username";

        private static final String KEY_AUTH_TOKEN = "auth_token";

        @Override
        protected String getHost() {
            return HOST;
        }

        @Override
        public RequestParams newRequestParams() {
            RequestParams params = new RequestParams();
            params.add("lang", "zh-CN");

            params.add(KEY_AUTH_USER, StringUtil.nullStrToEmpty(IVerifyHolder.mUserBean
                    .getUsername()));
            params.add(KEY_AUTH_TOKEN, StringUtil.nullStrToEmpty(IVerifyHolder.mUserBean
                    .getToken()));

            return params;
        }
    }


    /**
     * User - 用户基础信息
     * GET<br>
     * user/info/view
     * <p/>
     * 参数
     * appid	String 必须，全局通用appid
     * <p/>
     * token	String 必须，全局通用token
     * <p/>
     * username	String 必须，用户帐号
     * <p/>
     * to see ResBean at{@link BaseVsoApiResBean}
     */
    class BasicInfoApi extends ApiNewRestOauthApi {

        private static final String KEY_USERNAME = "username";

        @Override
        protected String getUnformatedPath() {
            return "user/info/view";
        }

        @Override
        protected String getUnformatedQuerystring() {
            return null;
        }

        public RequestParams newRequestParams(String username) {
            RequestParams params = super.newRequestParams();
            params.add(KEY_USERNAME, username);
            return params;
        }

        public RequestParams newRequestParams(String username, String token) {
            final String KEY_AUTH_USER = "auth_username";
            final String KEY_AUTH_TOKEN = "auth_token";
            RequestParams params = new RequestParams();
            params.add(KEY_USERNAME, username);
            params.add(KEY_AUTH_USER, username);
            params.add(KEY_AUTH_TOKEN, token);

            return params;
        }
    }


    /**
     * 新版-用户登录
     * POST
     * user/login/index
     * 参数：
     * <p/>
     * <li>name	String	必须，账号（用户名/手机号/邮箱）
     * <li> password	String	必须，密码
     * <li>prom_profit	Boolean	可选，推广上线分钱（默认false）
     * <li> ip	String	用户ip地址
     * 参数格式:<br>
     * [
     * "appid" => "XXX",
     * "token" => "XXX",
     * "name" => "XXX",
     * "password" => 'xxx'
     * ]
     */
    class LoginApi extends ApiNewRestOauthApi {

        private static final String PATH = "user/login/index";

        private static final String KEY_NAME = "name";

        private static final String KEY_PASSWORD = "password";

        private static final String KEY_CLIENT_ID = "client_id";
        private static final String VALUE_CLIENT_ID = "flag";

        @Override
        protected String getUnformatedPath() {
            return PATH;
        }

        @Override
        protected String getUnformatedQuerystring() {
            return null;
        }

        public RequestParams newRequestParams(LoginAccount account) {
            RequestParams params = super.newRequestParams();
            params.add(KEY_NAME, account.getAccount());
            params.add(KEY_PASSWORD, account.getPassword());
            params.add(KEY_CLIENT_ID, VALUE_CLIENT_ID);
            params.add("auto_login", "1");
            return params;
        }
    }


    /**
     * /user/login/logout
     * POST
     * 参数
     * vso_uid	String 必须，用户编号（UC）
     * vso_sess	String 必须，session，存在cookie中
     * vso_token	String 必须，用户登录后的token，存在cookie中
     * <p/>
     */
    class LogoutApi extends ApiNewRestOauthApi {

        @Override
        protected String getUnformatedPath() {
            return "user/login/logout";
        }

        /**
         * @return null if do not need to format qs
         */
        @Override
        protected String getUnformatedQuerystring() {
            return null;
        }

    }


//    /**
//     * 检查登录状态
//     * POST
//     * user/login/status
//     * <p/>
//     * to see Model at{@link CheckLoginStateModel}
//     */
//    class CheckLoginState extends ApiNewRestOauthApi {
//        private static final String KEY_USERNAME = "username";
//        private static final String KEY_TOKEN = "vso_token";
//
//        @Override
//        protected String getUnformatedPath() {
//            return "user/login/status";
//        }
//
//        @Override
//        protected String getUnformatedQuerystring() {
//            return null;
//        }
//
//        public RequestParams newRequestParams(CheckLoginStateModel.CheckLoginStateData data) {
//            RequestParams params = super.newRequestParams();
//            params.add(KEY_USERNAME, data.getUsername());
//            params.add(KEY_TOKEN, data.getAccesstoken());
//            return params;
//        }
//    }

    class QueryUserBasicInfoApi extends ApiNewRestOauthApi {
        private static final String KEY_USERNAME = "username";
        private static final String KEY_TOKEN = "vso_token";

        @Override
        protected String getUnformatedPath() {
            return "user/info/view";
        }

        @Override
        protected String getUnformatedQuerystring() {
            return null;
        }

        public RequestParams newRequestParams(BasicInfoParam param) {
            RequestParams params = super.newRequestParams();
            params.add(KEY_TOKEN, param.getVso_token());
            params.add(KEY_USERNAME, param.getUsername());
            return params;
        }
    }

}
