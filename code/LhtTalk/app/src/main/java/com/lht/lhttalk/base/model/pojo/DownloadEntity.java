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

package com.lht.lhttalk.base.model.pojo;

import com.lht.lhttalk.hybrid.native4js.expandreqbean.NF_DownloadReqBean;
import com.lht.lhttalk.module.setting.model.CheckVersionUpdateModel;

/**
 * <p><b>Package</b> com.lht.vsocyy.mvp.model.pojo
 * <p><b>Project</b> VsoCyy
 * <p><b>Classname</b> DownloadEntity
 * <p><b>Description</b>: TODO
 * <p>Created by leobert on 2016/11/10.
 */

public class DownloadEntity {

    /**
     * the real name of the file
     */
    private String fileName;

    /**
     * bytes of the attachment
     */
    private long fileSize;

    /**
     * url of the attachment
     */
    private String fileUrl;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public static DownloadEntity copyFromPreviewImage(PreviewImage previewImage) {
        DownloadEntity entity = new DownloadEntity();
        entity.setFileName(previewImage.getName());
        entity.setFileUrl(previewImage.getResUrl());
        entity.setFileSize(previewImage.getFileSize());
        return entity;
    }

    public static DownloadEntity copyFromVersionResBean(CheckVersionUpdateModel.VersionResBean.VersionInfoData versionInfoData) {
        DownloadEntity entity = new DownloadEntity();
        int index = versionInfoData.getUrl().lastIndexOf("/");
        versionInfoData.setFileName(versionInfoData.getUrl().substring(index + 1));
        entity.setFileName(versionInfoData.getFileName());
//        entity.setFileSize(versionResBean.getFileSize());
        entity.setFileUrl(versionInfoData.getUrl());
        return entity;
    }

    public static DownloadEntity copyFromDownloadBean(NF_DownloadReqBean bean) {
        DownloadEntity entity = new DownloadEntity();
        entity.setFileUrl(bean.getUrl_download());
        entity.setFileName(bean.getFile_name());
        entity.setFileSize(bean.getFile_size());
        return entity;
    }
}
