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


import com.lht.lhttalk.util.debug.DLog;
import com.loopj.android.http.RequestParams;

/**
 * <p><b>Package</b> com.lht.vsocyy.interfaces.net
 * <p><b>Project</b> VsoCyy
 * <p><b>Classname</b> IRestfulApi
 * <p><b>Description</b>: Restful API 管理</br>
 * Created by leobert on 2016/6/30.
 */
public interface IRestfulApi {


    enum Protocol {
        HTTP("http://"),

        HTTPS("https://");

        private final String protocol;

        Protocol(String s) {
            protocol = s;
        }

        @Override
        public String toString() {
            return protocol;
        }
    }

    Protocol DEFAULT_PROTOCOL = Protocol.HTTPS;

    String HOST = "maker.vsochina.com";

    String SEPARATOR = "/";

    String QUERY_SYMBOL = "?";

    String AND_SYMBOL = "&";

    /**
     * default schema is https
     *
     * @param pathParams params in path
     * @return request url
     */
    String formatUrl(String[] pathParams);

    /**
     * @param pathParams        params in path
     * @param queryStringParams
     * @return
     */
    String formatUrl(String[] pathParams, String[] queryStringParams);

    /**
     * @param protocol
     * @param pathParams params in path
     * @return
     */
    String formatUrl(Protocol protocol, String[] pathParams);

    /**
     * @param protocol          协议
     * @param pathParams        params in path
     * @param queryStringParams params in querystrings
     * @return
     */
    String formatUrl(Protocol protocol, String[] pathParams, String[] queryStringParams);

    abstract class AbsRestApiBase implements IRestfulApi {
        protected abstract String getUnformattedPath();

        /**
         * @return null if do not need to format qs
         */
        protected abstract String getUnformattedQueryString();

        protected Protocol getProtocol() {
            return DEFAULT_PROTOCOL;
        }

        @Override
        public String formatUrl(String[] pathParams) {
            return this.formatUrl(pathParams, null);
        }

        @Override
        public String formatUrl(String[] pathParams, String[] queryStringParams) {
            return this.formatUrl(getProtocol(), pathParams, queryStringParams);
        }

        @Override
        public String formatUrl(Protocol protocol, String[] pathParams) {
            return this.formatUrl(protocol, pathParams, null);
        }

        @Override
        public String formatUrl(Protocol protocol, String[] pathParams, String[]
                queryStringParams) {
            StringBuilder builder = new StringBuilder(protocol.toString());
            builder.append(getHost()).append(SEPARATOR);

            Object[] p1 = pathParams;
            builder.append(String.format(getUnformattedPath(), p1));

            if (queryStringParams != null) {
                Object[] p2 = queryStringParams;
                builder.append(QUERY_SYMBOL).append(String.format(getUnformattedQueryString(), p2));
            }

            String ret = trim(builder);
            log(ret);
            return ret;
        }

        protected String getHost() {
            return HOST;
        }


        protected void log(String s) {
            DLog.d(getClass(), "format url:" + s);
        }

        String trim(StringBuilder builder) {
            return builder.toString().trim();
        }

        public RequestParams newRequestParams() {
            RequestParams params = new RequestParams();
            params.add("lang", "zh-CN");
            return params;
        }
    }
}
