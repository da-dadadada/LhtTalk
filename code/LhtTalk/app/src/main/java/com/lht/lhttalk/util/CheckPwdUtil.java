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

package com.lht.lhttalk.util;

import com.lht.lhttalk.R;
import com.lht.lhttalk.base.vinterface.IActivityAsyncProtected;
import com.lht.lhttalk.util.string.StringUtil;


public class CheckPwdUtil {

    private static final String REGIX = "^[a-zA-Z0-9-`=\\[\\]{};',./~!@#$%^&*()_+|:\"<>?]+$";

    public static boolean checkPwdLengthLegal(String password,IActivityAsyncProtected iActivityAsyncProtected){
        if(StringUtil.isEmpty(password)) {
            iActivityAsyncProtected.showMsg(iActivityAsyncProtected.getAppResource().getString(
                    R.string.v1000_toast_error_null_pwd));
            return false;
        }

        if (password.length() < 6) {
            iActivityAsyncProtected.showMsg(iActivityAsyncProtected.getAppResource().getString(
                    R.string.v1000_toast_register_text_pwd_illegal));
            return false;
        } else if (password.length() > 20) {
            iActivityAsyncProtected.showMsg(iActivityAsyncProtected.getAppResource().getString(
                    R.string.v1000_toast_register_text_pwd_illegal));
            return false;
        } else if (!password.matches(REGIX)){
            iActivityAsyncProtected.showMsg(iActivityAsyncProtected.getAppResource().getString(
                    R.string.v1000_toast_register_text_pwd_illegal));
            return false;
        } else {
            return true;
        }
    }
}
