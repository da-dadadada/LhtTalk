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

package com.lht.lhttalk.base.activity.asyncprotected;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import com.lht.lhttalk.base.activity.BaseActivity;
import com.lht.lhttalk.base.activity.ExclusivePopupWinsActivity;
import com.lht.lhttalk.base.presenter.IApiRequestPresenter;
import com.lht.lhttalk.base.vinterface.IActivityAsyncProtected;
import com.lht.lhttalk.util.debug.DLog;
import com.lht.lhttalk.util.toast.ToastUtils;

public abstract class AsyncProtectedActivity extends ExclusivePopupWinsActivity
        implements IActivityAsyncProtected {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private boolean needDispatch = true;

    // 子类实现 public abstract ProgressBar getProgressBar();

    public void setActiveStateOfDispatchOnTouch(boolean b) {
        needDispatch = b;
    }

    public void showWaitView(boolean isProtectNeed) {
        if (getProgressBar() != null) {
            getProgressBar().setVisibility(View.VISIBLE);
            getProgressBar().bringToFront();
        } else {
            DLog.i(getClass(), "progressbar is null");
        }
        if (isProtectNeed)
            setActiveStateOfDispatchOnTouch(false);
    }

    /**
     * desc: TODO: 描述方法
     * 注意：如果仅progressBar显示需要拦截屏幕，可以这样做，否则需要进行判断或者设计多个flag共同作用
     */
    public void cancelWaitView() {
        if (getProgressBar() != null) {
            getProgressBar().setVisibility(View.GONE);
        } else {
            DLog.i(getClass(), "progressbar is null");
        }
        setActiveStateOfDispatchOnTouch(true);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (needDispatch)
            return super.dispatchTouchEvent(ev);
        else
            return false;
    }

    @Override
    public BaseActivity getHolderActivity() {
        return getActivity();
    }

    @Override
    public void setPenetrable(Activity activity, boolean isProtectNeed) {
        if (activity instanceof AsyncProtectedActivity)
            ((AsyncProtectedActivity) activity)
                    .setActiveStateOfDispatchOnTouch(!isProtectNeed);
    }

    @Override
    public Resources getAppResource() {
        return getActivity().getResources();
    }

    @Override
    protected void onDestroy() {
        IApiRequestPresenter presenter = getApiRequestPresenter();
        if (presenter != null) {
            presenter.cancelRequestOnFinish(getActivity());
        }
        super.onDestroy();
    }

    protected abstract IApiRequestPresenter getApiRequestPresenter();

    public void showMsg(String msg) {
        ToastUtils.show(getActivity(), msg, ToastUtils.Duration.s);
    }

    @Override
    public void showHeadUpMsg(int type, String msg) {
        ToastUtils.showHeadUp(getActivity(),msg,type);
    }
}
