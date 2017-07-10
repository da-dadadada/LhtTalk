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

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.SystemClock;
import android.support.v4.content.FileProvider;

import com.alibaba.fastjson.JSON;
import com.lht.lhttalk.BuildConfig;
import com.lht.lhttalk.R;
import com.lht.lhttalk.base.MainApplication;
import com.lht.lhttalk.base.model.apimodel.ApiModelCallback;
import com.lht.lhttalk.base.model.apimodel.BaseBeanContainer;
import com.lht.lhttalk.base.model.apimodel.BaseVsoApiResBean;
import com.lht.lhttalk.module.setting.model.CheckVersionUpdateModel;
import com.lht.lhttalk.service.NotificationCancelService;
import com.lht.lhttalk.service.VersionUpdateService;
import com.lht.lhttalk.util.notification.NotificationUtil;

import java.io.File;
import java.lang.ref.WeakReference;

/**
 * @author leobert.lan
 * @version 1.0
 */
public class VersionUtil {

    /**
     * @param mContext not null
     * @return version
     */
    public static String getVersion(Context mContext) {
        try {
            PackageManager manager = mContext.getPackageManager();
            PackageInfo info = manager.getPackageInfo(mContext.getPackageName(), 0);
            return info.versionName;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static class VersionCheckCallback implements ApiModelCallback<CheckVersionUpdateModel.VersionResBean> {

        private WeakReference<Context> contextRef;

        public VersionCheckCallback(Context context) {
            contextRef = new WeakReference<>(context);
        }

        @Override
        public void onSuccess(BaseBeanContainer<CheckVersionUpdateModel.VersionResBean> beanContainer) {
            CheckVersionUpdateModel.VersionResBean versionResBean = beanContainer.getData();
            CheckVersionUpdateModel.VersionResBean.VersionInfoData data = versionResBean.getData();
            if (data == null) {
                return;
            }
            //发出通知，提示用户更新
            sendUpdateNotification(data);
        }

        @Override
        public void onFailure(BaseBeanContainer<BaseVsoApiResBean> beanContainer) {
            //ignore
        }

        @Override
        public void onHttpFailure(int httpStatus) {
            //ignore
        }

        private void sendUpdateNotification(CheckVersionUpdateModel.VersionResBean.VersionInfoData data) {
            int requestCode = (int) SystemClock.uptimeMillis();
            if (contextRef == null) {
                return;
            }
            Context context = contextRef.get();
            if (context == null) {
                return;
            }
            NotificationUtil notificationUtil = new NotificationUtil(context);
//            PendingIntent leftIntent = PendingIntent.getActivity(context, requestCode,
//                    new Intent(), PendingIntent.FLAG_UPDATE_CURRENT);
            Intent cancelIntent = NotificationCancelService.newStartIntent(notificationUtil);
            PendingIntent leftIntent = PendingIntent.getService(context, requestCode,
                    cancelIntent, PendingIntent.FLAG_UPDATE_CURRENT);

            Intent startUpdateServiceIntent = new Intent(context, VersionUpdateService.class);
            startUpdateServiceIntent.putExtra(VersionUpdateService.VERSION_INFO, JSON.toJSONString(data));
            startUpdateServiceIntent.putExtra(VersionUpdateService.KEY_CANCEL_ID, notificationUtil.getNotificationId());
            PendingIntent rightIntent = PendingIntent.getService(context, requestCode,
                    startUpdateServiceIntent, PendingIntent.FLAG_UPDATE_CURRENT);

            notificationUtil.notifyWithButton(R.drawable.icon_small,
                    R.drawable.ic_action_ignore,
                    context.getString(R.string.v1020_versionupdate_dialog_btn_noupdate),
                    leftIntent, R.drawable.ic_action_update,
                    context.getString(R.string.v1020_versionupdate_dialog_btn_update),
                    rightIntent,
                    context.getString(R.string.v1020_versionupdate_notification_ticker),
                    context.getString(R.string.v1020_versionupdate_dialog_notification_title),
                    context.getString(R.string.v1020_versionupdate_dialog_notification_description),
                    true, true, true);
        }
    }

    public static void updateVersion(Context context,File apkFile) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        //判断是否是AndroidN以及更高的版本
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            Uri contentUri = FileProvider.getUriForFile(context,
                    BuildConfig.APPLICATION_ID + ".provider", apkFile);
            intent.setDataAndType(contentUri, "application/vnd.android.package-archive");
        } else {
            intent.setDataAndType(Uri.fromFile(apkFile), "application/vnd.android.package-archive");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        context.startActivity(intent);
        MainApplication.getOurInstance().finishAll();
    }

}
