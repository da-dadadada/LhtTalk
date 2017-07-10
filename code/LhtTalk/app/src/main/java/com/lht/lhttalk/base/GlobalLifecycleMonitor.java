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

package com.lht.lhttalk.base;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.os.Bundle;

import com.lht.lhttalk.util.debug.DLog;

import org.greenrobot.eventbus.EventBus;

/**
 * <p><b>Package</b> com.lht.vsocyy.clazz
 * <p><b>Project</b> VsoCyy
 * <p><b>Classname</b> GlobalLifecycleMonitor
 * <p><b>Description</b>: TODO
 * <p>Created by leobert on 2016/12/28.
 */

public class GlobalLifecycleMonitor implements Application.ActivityLifecycleCallbacks {
    private static GlobalLifecycleMonitor instance;

    private GlobalLifecycleMonitor() {
    }

    public static GlobalLifecycleMonitor getInstance() {
        if (instance == null) {
            instance = new GlobalLifecycleMonitor();
        }
        return instance;
    }

    private static int activeActivitiesCount = 0;

    private synchronized void plus() {
        synchronized (this) {
            activeActivitiesCount++;
        }
    }

    private synchronized void minus() {
        synchronized (this) {
            activeActivitiesCount--;
        }
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        debug("Create", activity);
    }

    @Override
    public void onActivityStarted(Activity activity) {
        debug("Start", activity);
        if (isApplicationStartOrResumeFromHome()) {
            DLog.i(getClass(), "post ApplicationStartOrResumeFromHome");
            EventBus.getDefault().post(new EventApplicationStartOrResumeFromHome());

            Intent intent = new Intent();
            intent.setAction(BROADCAST_ACTION_APPACTIVE);
            Application app = MainApplication.getOurInstance();
            if (app != null)
                app.sendBroadcast(intent);
        }
        plus();
    }

    @Override
    public void onActivityResumed(Activity activity) {
        debug("Resume", activity);
    }

    @Override
    public void onActivityPaused(Activity activity) {
        debug("Pause", activity);
    }

    @Override
    public void onActivityStopped(Activity activity) {
        debug("Stop", activity);
        minus();
        if (isApplicationBackToHomeOrTerminate()) {
            DLog.i(getClass(), "post ApplicationBackToHomeOrTerminate");
            EventBus.getDefault().post(new EventApplicationBackToHomeOrTerminate());

            Intent intent = new Intent();
            intent.setAction(BROADCAST_ACTION_APPSLEEP);
            Application app = MainApplication.getOurInstance();
            if (app != null)
                app.sendBroadcast(intent);
        }
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
        debug("SaveInstanceState", activity);
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        debug("Destroy", activity);
    }

    /**
     * start计数前判断是否应用打开或从后台恢复
     *
     * @return true if start or resume from home
     */
    private boolean isApplicationStartOrResumeFromHome() {
        synchronized (this) {
            return activeActivitiesCount == 0;
        }
    }

    /**
     * stop计数后判断是否处于后台或应用退出
     *
     * @return true if back to home or terminated
     */
    private boolean isApplicationBackToHomeOrTerminate() {
        synchronized (this) {
            return activeActivitiesCount == 0;
        }
    }

    private void debug(String state, Activity activity) {
        String msg = "Activity on" + state + ":   <=>" + activity.getClass().getSimpleName();
        DLog.d(getClass(), msg);
        DLog.d(activity.getClass(), msg);
    }


    /**
     * 应用开启或从后台恢复事件
     */
    public static final class EventApplicationStartOrResumeFromHome {
    }

    /**
     * 应用处于后台或退出事件
     */
    public static final class EventApplicationBackToHomeOrTerminate {
    }

    /**
     * Required  自定义，应用到后台，收到通知需处理重定向
     */
    public static final String BROADCAST_ACTION_APPSLEEP
            = "com.lht.vsocyy.intent.APP_BACK_TO_HOME_OR_TERMINATE";

    /**
     * 应用启动或回到前台，收到通知不能重定向
     */
    public static final String BROADCAST_ACTION_APPACTIVE
            = "com.lht.vsocyy.intent.APP_START_OR_RESUME";

    /**
     * singleInstance启动模式的activity实现之以优化回退栈
     */
    public interface ISingletonActivityFriendlyOptimize {
        /**
         * 重定向回退栈指向的位置，一般重定向到HomeActivity
         */
        void redirectBackStack();

        /**
         * 订阅应用恢复事件的回调，非入口页面收到基本可认为是应用恢复
         *
         * @param event 启动或恢复事件
         */
        void onEventMainThread(EventApplicationStartOrResumeFromHome event);
    }
}
