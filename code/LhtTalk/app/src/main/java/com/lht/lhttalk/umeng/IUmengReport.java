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

package com.lht.lhttalk.umeng;

import com.lht.lhttalk.base.activity.BaseActivity;

import java.util.HashMap;

/**
 * @package com.lht.vsocyy.interfaces
 * @project AndroidBase
 * @classname IUmengReport
 * @description: 进行友盟统计的接口，实现于activity、fragment，调用于presenter，presenter提供model需要的回调
 * Created by leobert on 2016/4/1.
 */
public interface IUmengReport extends IUmengEventKey {
    /**
     * @param pageName
     */
    void reportPageStart(String pageName);

    /**
     * @param pageName
     */
    void reportPageEnd(String pageName);

    /**
     * desc: 友盟统计-报告计数事件
     *
     * @param eventKey
     */
    void reportCountEvent(String eventKey);

    /**
     * desc:友盟统计-报告计数事件
     *
     * @param eventKey 事件key
     * @param attrMap  属性集<br>
     *                 <String Key,String Value>: key:属性key，value：属性集
     */
    void reportCountEvent(String eventKey,
                          HashMap<String, String> attrMap);


    /**
     * @param eventKey
     * @param attrMap
     * @param du
     */
    void reportCalcEvent(String eventKey,
                         HashMap<String, String> attrMap, int du);

    BaseActivity getActivity();
}
