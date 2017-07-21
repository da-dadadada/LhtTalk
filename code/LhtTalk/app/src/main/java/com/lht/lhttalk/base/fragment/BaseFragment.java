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

package com.lht.lhttalk.base.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;

import com.lht.lhttalk.Event.AppEvent;
import com.lht.lhttalk.base.IVerifyHolder;
import com.lht.lhttalk.base.MainApplication;
import com.lht.lhttalk.module.ucenter.UserBean;
import com.lht.lhttalk.util.debug.DLog;

import java.io.File;

public abstract class BaseFragment extends Fragment {


//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
//                             @Nullable Bundle savedInstanceState) {
//        MobclickAgent.onPageStart(getPageName());
//        return super.onCreateView(inflater, container, savedInstanceState);
//    }

    protected abstract void initView(View contentView);

    protected abstract void initVariable();

    protected abstract void initEvent();

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        Log.d("DebugHandler","onHiddenChange");
        if (hidden) {
            onRestrictPause();
        } else {
            onRestrictResume();
        }
    }

    public void onRestrictPause() {
        if (getCurrentChildFragment() != null) {
            if (getCurrentChildFragment() instanceof BaseFragment) {
                BaseFragment currentChild = (BaseFragment) getCurrentChildFragment();
                currentChild.onRestrictPause();
            }
        }
    }

    public void onRestrictResume() {
        if (getCurrentChildFragment() != null) {
            if (getCurrentChildFragment() instanceof BaseFragment) {
                BaseFragment currentChild = (BaseFragment) getCurrentChildFragment();
                currentChild.onRestrictResume();
            }
        }
    }


    protected boolean hasLogin() {
        return IVerifyHolder.mUserBean.isLogin();
    }

    protected void setUi2UnLoginState() {
        //stub
    }

    protected void setUi2LoginState(UserBean loginInfo) {
        //stub
    }

    public void onEventMainThread(AppEvent.LoginSuccessEvent event) {
        setUi2LoginState(event.getUserBean());
    }

    @Override
    public void onDestroy() {
        DLog.d(getClass(), "onDestroy:" + getClass().getSimpleName());
        super.onDestroy();
    }

    protected abstract String getPageName();


    public final void onResume() {
        super.onResume();
        onRestrictResume();
//        MobclickAgent.onPageStart(getPageName());
    }

    public final void onPause() {
        super.onPause();
        onRestrictPause();
//        MobclickAgent.onPageEnd(getPageName());
    }

    private Fragment saveFg;

    protected void switchFragment(int rid, Fragment to) {
        if (to == null) {
            // may throw you a IllegalArgumentException be better
            return;
        }
        if (saveFg != to) {
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            if (!to.isAdded()) {    // 先判断是否被add过
                if (saveFg != null) {
                    ft.hide(saveFg);
                }
                ft.add(rid, to).show(to).commit(); // 隐藏当前的fragment，add下一个到Activity中
            } else {
                if (saveFg != null) {
                    ft.hide(saveFg);
                }
                ft.show(to).commit(); // 隐藏当前的fragment，显示下一个
            }
            saveFg = to;
        }
    }

    protected Fragment getCurrentChildFragment() {
        return saveFg;
    }

    /**
     * 获取系统相册的位置
     */
    public synchronized File getSystemImageDir() {
        return getMainApplication().getSystemImageDir();
    }

    protected MainApplication getMainApplication() {
        return MainApplication.getOurInstance();
    }

    public synchronized File getLocalDownloadCacheDir() {
        return getMainApplication().getLocalDownloadCacheDir();
    }

    public synchronized File getSystemDownloadDir() {
        return getMainApplication().getSystemDownloadDir();
    }

    public File getLocalThumbnailCacheDir() {
        return getMainApplication().getLocalThumbnailCacheDir();
    }

    public File getLocalPreviewCacheDir() {
        return getMainApplication().getLocalPreviewCacheDir();
    }

}
