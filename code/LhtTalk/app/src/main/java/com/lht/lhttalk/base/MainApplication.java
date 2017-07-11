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
import android.content.Context;
import android.content.SharedPreferences;

import com.alibaba.fastjson.JSON;
import com.lht.lhttalk.BuildConfig;
import com.lht.lhttalk.Event.AppEvent;
import com.lht.lhttalk.base.activity.BaseActivity;
import com.lht.lhttalk.base.model.pojo.LoginInfo;
import com.lht.lhttalk.cfg.SPConstants;
import com.lht.lhttalk.module.cache.CacheController;
import com.lht.lhttalk.module.cache.ICacheController;
import com.lht.lhttalk.module.publ.SplashActivity;
import com.lht.lhttalk.structure.SingletonStack;
import com.lht.lhttalk.util.AppPreference;
import com.lht.lhttalk.util.debug.DLog;
//import com.umeng.analytics.MobclickAgent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.File;
import java.util.ArrayList;


/**
 * MainApplication
 * Created by leobert on 2016/4/7.
 */
public class MainApplication extends Application {
    private static MainApplication ourInstance;


    private CacheController cacheController;


    private static final SingletonStack<BaseActivity> mainActivityStack
            = new SingletonStack<>();

    public static final ArrayList<BaseActivity> activityList = new ArrayList<>();


    /**
     * 获取系统相册的位置
     */
    public synchronized File getSystemImageDir() {
        return cacheController.getSystemImageDir();
    }

    public synchronized File getLocalDownloadCacheDir() {
        return cacheController.getLocalDownloadCacheDir();
    }

    public synchronized File getSystemDownloadDir() {
        return cacheController.getSystemDownloadDir();
    }

    public File getLocalThumbnailCacheDir() {
        return cacheController.getLocalThumbnailCacheDir();
    }

    public File getLocalPreviewCacheDir() {
        return cacheController.getLocalThumbnailCacheDir();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        DLog.i(getClass(), "Debug-Mode:" + BuildConfig.DEBUG);
        if (ourInstance == null) {
            ourInstance = this;
        }
        activeLifecycleMonitor();
        //角标模块激活
//        BadgeNumberManager.initOnApplicationStart();

        //debug模式检测内存泄漏
//        if (BuildConfig.DEBUG) {
//            if (LeakCanary.isInAnalyzerProcess(this)) {
//                // This process is dedicated to LeakCanary for heap analysis.
//                // You should not init your app in this process.
//                return;
//            }
//            LeakCanary.install(this);
//        }

        initUserInfo();
        cacheController = new CacheController(getLocalStorageRoot());
        cacheController.registerCacheChangedListener(
                new ICacheController.OnCacheChangedListener() {
                    @Override
                    public void onCacheChanged(ICacheController cacheController) {
                    }
                });



        EventBus.getDefault().register(this);

        JSON.setArrayStrictMode(false);

    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }


    private void activeLifecycleMonitor() {
        getOurInstance().registerActivityLifecycleCallbacks(GlobalLifecycleMonitor.getInstance());
    }


    public void initUserInfo() {
        SharedPreferences sp = getTokenSp();
        IVerifyHolder.mLoginInfo.setUsername(sp.getString(SPConstants.Token.KEY_USERNAME, ""));
        IVerifyHolder.mLoginInfo.setAccessToken(sp.getString(SPConstants.Token.KEY_ACCESS_TOKEN,
                ""));
    }

    public SharedPreferences getTokenSp() {
        return getSharedPreferences(SPConstants.Token.SP_TOKEN, MODE_PRIVATE);
    }

    @Subscribe
    public void onEventMainThread(AppEvent.LoginSuccessEvent event) {
        cacheController.notifyUserChanged(event.getLoginInfo().getUsername());
    }

    @Subscribe
    public void onEventMainThread(AppEvent.LogoutEvent event) {
        IVerifyHolder.mLoginInfo.copy(new LoginInfo());
        EventBus.getDefault().removeStickyEvent(AppEvent.LoginSuccessEvent.class);
        cacheController.notifyUserChanged(null);
        finishAll();
    }

    public void bindDevice() {
        // TODO: 2017/4/6 极光集成后使用
        DLog.i(getClass(), "处理完极光之后再使用");
//        String username = IVerifyHolder.mLoginInfo.getUsername();
//        String registrationId = JPushInterface.getRegistrationID(getOurInstance());
//        DeviceBindModel model = new DeviceBindModel(username, registrationId);
//        model.doRequest(getOurInstance());
    }

    public synchronized void finishAll() {
        DLog.d(getClass(), "finishAll");
        for (BaseActivity activity : activityList) {
            if (activity != null) {
                activity.finish();
            }
//            activityList.remove(activity);
        }
        mainActivityStack.clear();
        activityList.clear();
    }

//    /**
//     * 某些特殊情况下，页面栈被打乱，难以按照原定的逻辑走，如“打补丁”会很臃肿，
//     * 在必要的情况下打开主页，避免页面被全部关闭即可
//     */
//    public synchronized void startHomeIfNecessary() {
//        for (BaseActivity activity : activityList) {
//            if (activity instanceof HomeActivity) {
//                return;
//            }
//        }
//
//        //不存在HomeActivity实例，需要打开
//        startHome();
//    }

//    private void startHome() {
//        Intent intent = new Intent(getOurInstance(), HomeActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
////        intent.putExtra(HomeActivity.KEY_ISLOGIN, IVerifyHolder.mLoginInfo.isLogin());
////        intent.putExtra(HomeActivity.KEY_DATA, JSON.toJSONString(IVerifyHolder.mLoginInfo));
//        startActivity(intent);
//    }

    @Override
    public void onTerminate() {
        //never called on device,only simulator works
//        EventBus.getDefault().unregister(this);
        super.onTerminate();
    }

    @Override
    protected void finalize() throws Throwable {
        EventBus.getDefault().unregister(this);
        super.finalize();
    }

    public AppPreference getAppPreference() {
        return AppPreference.getInstance(this);
    }

    public static MainApplication getOurInstance() {
        return ourInstance;
    }


    public static void addActivity(BaseActivity activity) {
        activityList.add(activity);
        if (isMainStackActivity(activity)) {
            mainActivityStack.add(activity);
        }
    }

    public static void removeActivity(BaseActivity activity) {
        activityList.remove(activity);
        if (isMainStackActivity(activity)) {
            mainActivityStack.remove(activity);
        }
    }

    public String getMainStackTopActivityPath() {
        if (mainActivityStack.isEmpty()) {
            // TODO: 2017/7/11
            return SplashActivity.class.getName();
        } else {
            return mainActivityStack.lastElement().getClass().getName();
        }
    }

    private Activity currentTopActivity;

    public void updateCurrentTopActivity(Activity activity) {
        this.currentTopActivity = activity;
    }

    public Activity getCurrentTopActivity() {
        return currentTopActivity;
    }


    public void reloadCache() {
        cacheController.reloadCache();
    }


    private static boolean isMainStackActivity(Activity activity) {
//        if (activity instanceof BannerInfoActivity)
//            return false;
//        if (activity instanceof MessageInfoActivity)
//            return false;
        return true;
    }

    private String getLocalStorageRoot() {
        return getCacheDir().getPath();
    }
}
