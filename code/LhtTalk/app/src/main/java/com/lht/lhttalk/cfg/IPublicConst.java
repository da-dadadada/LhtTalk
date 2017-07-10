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

package com.lht.lhttalk.cfg;

/**
 * <p><b>Package</b> com.lht.vsocyy.interfaces
 * <p><b>Project</b> VsoC
 * yy
 * <p><b>Classname</b> IPublicConst
 * <p><b>Description</b>: TODO
 * <p> Create by Leobert on 2016/9/6
 */
public interface IPublicConst {
    String TEL = "400-164-7979";

    String VSO_SESSION_PREFIX = "v-s-o-c*h*i*n*a";

    /**
     * 蓝海创意云用户协议
     */
    String SIMPLIFIED_AGREEMENT = "https://m.vsochina.com/maker/module/protocol.html";


    /**
     * 入驻申请协议 useless
     */
    String SIMPLIFIED_AGREEMENT_JOININ = "http://m.vsochina.com/protocol/user-agreement";

    String SHARE_APP_LINK = "https://m.vsochina.com/maker/module/loadpage.html";

    String MSGINFO_URL_FORMAT = "http://m.vsochina.com/message/detail/%s?auth_token=%s&auth_username=%s";

//    class MsgInfoUrlHelpler {
//       public static String formatUrl(String msgId) {
//           return  String.format(MSGINFO_URL_FORMAT, msgId, IVerifyHolder.mLoginInfo.getAccessToken(),
//                   IVerifyHolder.mLoginInfo.getUsername());
//       }
//    }
//
//    String APP_SID = "FLAG";
//
//    String APP_SYS = "android";
//
//
//    String USERAGENT_MODEL = "VSO-lhttalk/%s Android/%s Language/%s";
//
//    String USER_AGENT = String.format(USERAGENT_MODEL,
//            VersionUtil.getVersion(MainApplication.getOurInstance()), Build.VERSION.SDK_INT,
//            Locale.getDefault().getLanguage());
}
