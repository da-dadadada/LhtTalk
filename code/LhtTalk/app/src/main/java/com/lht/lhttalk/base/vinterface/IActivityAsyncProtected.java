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
import android.widget.ProgressBar;

import com.lht.lhttalk.umeng.IUmengReport;

/**
 * @package com.lht.vsocyy.mvp.viewinterface
 * @project AndroidBase
 * @classname IActivityAsyncProtected
 * @description: 同步等待窗，阻拦屏幕事件等
 * Created by leobert on 2016/4/1.
 */
public interface IActivityAsyncProtected extends IUmengReport{

    /**
     * desc: 提供等待窗
     *
     * @return 一个progressBar实例
     */
    ProgressBar getProgressBar();

    /**
     * desc: 修改屏幕事件消费能力
     *
     * @param ：是否激活
     */
    void setActiveStateOfDispatchOnTouch(boolean b);

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

    void showHeadUpMsg(int type, String msg);
}
