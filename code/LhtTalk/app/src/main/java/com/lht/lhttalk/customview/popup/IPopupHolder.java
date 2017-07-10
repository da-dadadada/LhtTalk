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

package com.lht.lhttalk.customview.popup;

import com.lht.lhttalk.base.activity.BaseActivity;

/**
 * <p><b>Package</b> com.lht.vsocyy.interfaces.custompopupwins
 * <p><b>Project</b> VsoCyy
 * <p><b>Classname</b> IPopupHolder
 * <p><b>Description</b>: 持有者
 * Created by leobert on 2016/5/11.
 */
public interface IPopupHolder extends IPenetrateController{
    BaseActivity getHolderActivity();

    void setLatestPopupWindow(CustomPopupWindow latestPopupWindow);

    CustomPopupWindow getLatestPopupWindow();

    void onActionSheetDismiss();

    boolean isPopupShowing();

    void closePopupWindow();
}
