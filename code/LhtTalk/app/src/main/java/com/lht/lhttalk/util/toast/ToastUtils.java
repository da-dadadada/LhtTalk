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

package com.lht.lhttalk.util.toast;

import android.content.Context;
import android.os.Handler;
import android.widget.Toast;

import com.lht.lhttalk.customview.toast.HeadUpToast;

import java.util.ArrayList;

/**
 * @package com.lht.vsocyy.util.toast
 * @project AndroidBase
 * @classname ToastUtils
 * @description: TODO
 * Created by leobert on 2016/4/5.
 */
public class ToastUtils {
    private static String preMsg = "";
    private static final int shortTime = 2000;
    private static final int longTime = 3500;
    private static boolean onShowing = false;
    private static final Object syncLock = new Object();
    private static ArrayList<ToastJob> ToastList = new ArrayList<>();

    public static void show(Context c, int sRid, Duration d) {
        int duration = 0;
        long time = 0;
        switch (d) {
            case l:
                duration = Toast.LENGTH_LONG;
                time = longTime;
                break;
            case s:
                duration = Toast.LENGTH_SHORT;
                time = shortTime;
                break;
            default:
                break;
        }
        String msg = c.getResources().getString(sRid);
//        if (msg == null)
//            return;
        if (msg.equals(preMsg) && onShowing)
            return;
        preMsg = msg;
        ToastJob job = new ToastJob();
        job.setToast(Toast.makeText(c, msg, duration));
        job.setTime(time);
        synchronized (syncLock) {
            ToastList.add(job);
            callShow();
        }
    }

    public static void show(Context c, String msg, Duration d) {
        int duration = 0;
        long time = 0;
        switch (d) {
            case l:
                duration = Toast.LENGTH_LONG;
                time = longTime;
                break;
            case s:
                duration = Toast.LENGTH_SHORT;
                time = shortTime;
                break;
            default:
                break;
        }
        if (msg == null)
            return;
        if (msg.equals(preMsg) && onShowing)
            return;
        preMsg = msg;
        ToastJob job = new ToastJob();
        job.setToast(Toast.makeText(c, msg, duration));
        job.setTime(time);
        synchronized (syncLock) {
            ToastList.add(job);
            callShow();
        }
    }

    public static void showHeadUp(Context c, String msg, int type) {
        if (msg == null)
            return;
        if (msg.equals(preMsg) && onShowing)
            return;
        preMsg = msg;
        ToastJob job = new ToastJob();
        HeadUpToast toast = new HeadUpToast(c);
        toast.setContent(type, msg);
        job.setToast(toast.getToast());
        job.setTime(shortTime);
        synchronized (syncLock) {
            ToastList.add(job);
            callShow();
        }
    }


    private static synchronized void callShow() {
        synchronized (syncLock) {
            if (!onShowing && ToastList.size() > 0) {
                ToastList.get(0).getToast().show();
                onShowing = true;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        onShowing = false;
                        callShow();
                    }

                }, ToastList.get(0).getTime());
                ToastList.remove(0);
            }
        }
    }


    public enum Duration {
        l, s;
    }


    static class ToastJob {
        private Toast Toast;
        private long time;

        public Toast getToast() {
            return Toast;
        }

        public void setToast(Toast Toast) {
            this.Toast = Toast;
        }

        public long getTime() {
            return time;
        }

        public void setTime(long time) {
            this.time = time;
        }
    }
}