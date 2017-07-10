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

package com.lht.lhttalk.base.vinterface;

import android.content.res.Resources;

/**
 * <p><b>Package</b> com.lht.vsocyy.mvp.viewinterface
 * <p><b>Project</b> VsoCyy
 * <p><b>Classname</b> IAsyncProtectedFragent
 * <p><b>Description</b>: TODO
 * <p> Create by Leobert on 2016/8/23
 */
public interface IAsyncProtectedFragment {
    /**
     * desc: 显示等待窗
     *
     * @param isProtectNeed 是否需要屏幕防击穿保护
     */
    void showWaitView(boolean isProtectNeed);

    /**
     * desc: 取消等待窗
     */
    void cancelWaitView();

    Resources getAppResource();

    void showMsg(String msg);

    void showErrorMsg(String msg);
}
