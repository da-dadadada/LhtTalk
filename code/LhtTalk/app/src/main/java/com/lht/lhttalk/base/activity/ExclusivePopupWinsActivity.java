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

package com.lht.lhttalk.base.activity;

import android.app.Activity;

import com.lht.lhttalk.base.activity.asyncprotected.AsyncProtectedActivity;
import com.lht.lhttalk.base.keyback.PopupWinsCloseHandler;
import com.lht.lhttalk.customview.popup.CustomPopupWindow;
import com.lht.lhttalk.customview.popup.IPopupHolder;

/**
 * <p><b>Package</b> com.lht.vsocyy.activity
 * <p><b>Project</b> VsoCyy
 * <p><b>Classname</b> ExclusivePopupWinsActivity
 * <p><b>Description</b>: TODO
 * <p>Created by leobert on 2017/1/20.
 */

public abstract class ExclusivePopupWinsActivity extends BaseActivity
        implements IPopupHolder {

    protected CustomPopupWindow exclusivePopupWins;

    @Override
    public BaseActivity getHolderActivity() {
        return getActivity();
    }

    @Override
    protected void equipKeyBackHandlerChain() {
        getIKeyBackHandlerChain().next(new PopupWinsCloseHandler(this));
        super.equipKeyBackHandlerChain();
    }

    @Override
    public void onActionSheetDismiss() {
    }

    @Override
    public void setLatestPopupWindow(CustomPopupWindow latestPopupWindow) {
        this.exclusivePopupWins = latestPopupWindow;
    }

    @Override
    public CustomPopupWindow getLatestPopupWindow() {
        return exclusivePopupWins;
    }

    @Override
    public void setPenetrable(Activity activity, boolean isProtectNeed) {
        if (activity instanceof AsyncProtectedActivity) {
            ((AsyncProtectedActivity) activity).setActiveStateOfDispatchOnTouch(!isProtectNeed);
        }
    }

    @Override
    public boolean isPopupShowing() {
        if (exclusivePopupWins == null)
            return false;
        return exclusivePopupWins.isShowing();
    }

    @Override
    public void closePopupWindow() {
        if (exclusivePopupWins != null) {
            exclusivePopupWins.dismiss();
            exclusivePopupWins = null;
        }
    }
}
