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
import com.lht.lhttalk.util.string.StringUtil;
import com.loopj.android.http.RequestParams;

/**
 * <p><b>Package:</b> com.lht.lhttalk.module.api </p>
 * <p><b>Project:</b> LhtTalk </p>
 * <p><b>Classname:</b> ImApiCollections </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/7/14.
 */

public interface ImApiCollections {

    abstract class ImApi extends IRestfulApi.AbsRestApiBase {
        private static final String HOST = "www.vsochina.com";

        private static final String KEY_AUTH_USER = "auth_uname";

        private static final String KEY_AUTH_TOKEN = "auth_token";

        @Override
        protected String getHost() {
            return HOST;
        }

        @Override
        protected Protocol getProtocol() {
            return Protocol.HTTP;
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
     * get
     */
    class FriendListApi extends ImApi {

        @Override
        protected String getUnformattedPath() {
            return "imapi/friend";
        }

        @Override
        protected String getUnformattedQueryString() {
            return null;
        }
    }

    /**
     * get
     */
    class ContactListApi extends ImApi {

        @Override
        protected String getUnformattedPath() {
            return "imapi/contact/list";
        }

        @Override
        protected String getUnformattedQueryString() {
            return null;
        }
    }

    /**
     * get
     * username, nickname, email
     */
    class UserSearchApi extends ImApi {
        private static final String KEY_NICKNAME = "nickname";

        @Override
        protected String getUnformattedPath() {
            return "imapi/user/search";
        }

        @Override
        protected String getUnformattedQueryString() {
            return null;
        }
    }



}
