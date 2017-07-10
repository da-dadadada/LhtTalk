///*
// * MIT License
// *
// * Copyright (c) 2017 leobert-lan
// *
// * Permission is hereby granted, free of charge, to any person obtaining a copy
// * of this software and associated documentation files (the "Software"), to deal
// * in the Software without restriction, including without limitation the rights
// * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
// * copies of the Software, and to permit persons to whom the Software is
// * furnished to do so, subject to the following conditions:
// *
// * The above copyright notice and this permission notice shall be included in all
// * copies or substantial portions of the Software.
// *
// * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
// * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
// * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
// * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
// * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
// * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
// * SOFTWARE.
// *
// */
//
//package com.lht.lhttalk.util.permission;
//
//import com.lht.lhttalk.R;
//import com.lht.lhttalk.customview.popup.CustomPopupWindow;
//import com.lht.lhttalk.customview.popup.IPopupHolder;
//import com.lht.lhttalk.customview.popup.dialog.CustomDialog;
//import com.lht.lhttalk.util.phonebasic.SettingUtil;
//
///**
// * <p><b>Package</b> com.lht.vsocyy.util.permission
// * <p><b>Project</b> VsoCyy
// * <p><b>Classname</b> Permissions
// * <p><b>Description</b>: TODO
// * <p>Created by leobert on 2016/11/24.
// */
//
//public enum Permissions implements IAlertCreator {
//    CAMERA(R.string.v1022_dialog_permission_camera),
//
//    STORAGE(R.string.v1022_dialog_permission_sd);
//
//    private final int alertContentResId;
//
//    Permissions(int alertContentResId) {
//        this.alertContentResId = alertContentResId;
//    }
//
//    public CustomDialog newPermissionGrantReqAlert(final IPopupHolder iPopupHolder) {
//        CustomDialog dialog = new CustomDialog(iPopupHolder);
//        dialog.setContent(alertContentResId);
//        dialog.setPositiveButton(R.string.v1022_dialog_permission_pbtn);
//        dialog.setNegativeButton(R.string.v1022_dialog_permission_nbtn);
//
//
//        dialog.setPositiveClickListener(new CustomPopupWindow.OnPositiveClickListener() {
//            @Override
//            public void onPositiveClick() {
//                SettingUtil.startAppSettings(iPopupHolder.getHolderActivity());
//            }
//        });
//
//        return dialog;
//    }
//}
//
//interface IAlertCreator {
//    CustomDialog newPermissionGrantReqAlert(IPopupHolder iPopupHolder);
//}
