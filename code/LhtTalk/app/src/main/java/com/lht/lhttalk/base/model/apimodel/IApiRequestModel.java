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

package com.lht.lhttalk.base.model.apimodel;

import android.content.Context;

/**
 * <p><b>Package</b> com.lht.vsocyy.interfaces.net
 * <p><b>Project</b> VsoCyy
 * <p><b>Classname</b> IApiRequestModel
 * <p><b>Description</b>: 所有的网络请求model实现该接口
 *
 * added at 2017年06月27日: api&apinew 的接口是非restful的，而且面向"行为"设计的，
 * 大体变现为：url是表达了一个操作，qs或form包含了业务数据。
 * 所以，用DataModel or DataRepository implement IOperations to operate Data
 * 的形式去设计model层会显得很恶心（需要花费相当多的心思去将操作同一资源的不同接口进行整理，
 * 不同的原始数据进行整理，不同的返回数据进行整理...）。
 * 故而，model层仅仅对这些接口请求行为进行了封装和业务实现。
 *
 * 而创意空间的WebServer，大体上是RestFul-Api，所以可以不再沿用这一低劣的设计
 *
 * Created by leobert on 2016/7/7.
 */
@Deprecated
public interface IApiRequestModel extends ICancelRequest {
    /**
     * 执行请求
     *
     * 当前的接口出现非200都是网络状态问题，没有赋予特殊含义。
     * 有三层可以处理失败情况：
     * 1.model层内部处理，实质是在Composite中的default实现中处理
     * 2.回调接口的缺省适配器，暂未实现之
     * 3.回调接口实现类单独处理。
     * @param context    发起请求的对象的上下文
     */
    void doRequest(Context context);
}
