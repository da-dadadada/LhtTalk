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

package com.lht.lhttalk.umeng.customerror;

/**
 * <p><b>Package</b> com.lht.vsocyy.clazz.customerror
 * <p><b>Project</b> VsoCyy
 * <p><b>Classname</b> PushContentError
 * <p><b>Description</b>: 错误内容的推送
 * <p>Created by leobert on 2016/12/14.
 */

public class PushContentError extends BaseUmengError {
    private static final String ERROR_TYPE = IllegalArgumentException.class.getSimpleName();

    private static final String ERROR_INFO = "Android Jpush error content";

    public PushContentError(String errorData) {
        setErrorType(ERROR_TYPE);
        setErrorInfo(ERROR_INFO);
        this.errorData = errorData;
    }

}
