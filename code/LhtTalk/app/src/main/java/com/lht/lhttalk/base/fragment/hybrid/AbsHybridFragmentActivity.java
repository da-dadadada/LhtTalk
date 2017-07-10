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

package com.lht.lhttalk.base.fragment.hybrid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.widget.ProgressBar;

import com.lht.lhttalk.base.activity.UMengActivity;
import com.lht.lhttalk.base.activity.asyncprotected.AsyncProtectedActivity;
import com.lht.lhttalk.base.fragment.BaseFragment;
import com.lht.lhttalk.base.presenter.IApiRequestPresenter;
import com.lht.lhtwebviewlib.base.Interface.IFileChooseSupport;

/**
 * <p><b>Package</b> com.lht.vsocyy.fragment.hybrid
 * <p><b>Project</b> VsoCyy
 * <p><b>Classname</b> AbsHybridFragmentActivity
 * <p><b>Description</b>: 抽象activity类-承载桥接fragment的activity
 * <p>Created by leobert on 2017/2/13.
 */

public abstract class AbsHybridFragmentActivity extends AsyncProtectedActivity {

    public final int REQ_IMAGE_PREVIEW = 1000;

    public abstract ProgressBar getPageProtectPbar();

    protected IFileChooseSupport.DefaultFileChooseSupportImpl defaultFileChooseSupport;

    IFileChooseSupport.DefaultFileChooseSupportImpl getDefaultFileChooseSupport() {
        initFileChooseSupport();
        return defaultFileChooseSupport;
    }

    private void initFileChooseSupport() {
        if (defaultFileChooseSupport == null) {
            defaultFileChooseSupport = new IFileChooseSupport.DefaultFileChooseSupportImpl(this);
        }
    }

    @Override
    protected final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentLayoutRes());
        onContentViewBeenSet();
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (getCurrentChildFragment() != null) {
            if (getCurrentChildFragment() instanceof BaseFragment) {
                BaseFragment currentChild = (BaseFragment) getCurrentChildFragment();
                currentChild.onRestrictResume();
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (getCurrentChildFragment() != null) {
            if (getCurrentChildFragment() instanceof BaseFragment) {
                BaseFragment currentChild = (BaseFragment) getCurrentChildFragment();
                currentChild.onRestrictPause();
            }
        }
    }


    protected abstract int getContentLayoutRes();

    protected final void onContentViewBeenSet() {
        initView();
        initVariable();
        initFileChooseSupport();
        initEvent(); // eventbus regist in initEvent
    }

    @Override
    protected void onNewIntent(Intent intent) {
        if (intent.getBooleanExtra(UMengActivity.KEY_IS_RESTRICT_INTENT, false)) {
            super.onNewIntent(intent);
            return;
        }
        setIntent(intent);
        super.onNewIntent(intent);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        defaultFileChooseSupport.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    protected IApiRequestPresenter getApiRequestPresenter() {
        return null;
    }

    @Override
    public ProgressBar getProgressBar() {
        return getPageProtectPbar();
    }

    private Fragment saveFg;

    protected void switchFragment(final int rid, Fragment to) {
        if (to == null) {
            // may throw you a IllegalArgumentException be better
            return;
        }
        if (saveFg != to) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
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


}
