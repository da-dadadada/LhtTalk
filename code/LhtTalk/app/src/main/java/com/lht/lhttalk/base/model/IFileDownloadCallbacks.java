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

package com.lht.lhttalk.base.model;


import com.lht.lhttalk.base.model.pojo.DownloadEntity;

import java.io.File;

/**
 * <p><b>Package</b> com.lht.vsocyy.mvp.model.interfaces
 * <p><b>Project</b> VsoCyy
 * <p><b>Classname</b> IFileDownloadCallbacks
 * <p><b>Description</b>: TODO
 * <p>Created by leobert on 2016/11/10.
 */

public interface IFileDownloadCallbacks {
    /**
     * 无网络、下载中断线
     */
    void onNoInternet();

    /**
     * 数据流量连接
     */
    void onMobileNet();

    /**
     * 服务端文件不存在
     */
    void onFileNotFoundOnServer();

    /**
     * 开始下载
     */
    void onDownloadStart(DownloadEntity entity);

    /**
     * 取消了下载
     */
    void onDownloadCancel();

    /**
     * 下载成功
     */
    void onDownloadSuccess(DownloadEntity entity, File file);

    /**
     * 下载过程中、进度更新
     *
     * @param entity  预览文件实体
     * @param current 当前量 byte
     * @param total   总量 byte
     */
    void onDownloading(DownloadEntity entity, long current, long total);

    /**
     * 没有足够空间下载
     */
    void onNoEnoughSpace();

    /**
     * 文件下载失败
     */
    void downloadFailure();
}
