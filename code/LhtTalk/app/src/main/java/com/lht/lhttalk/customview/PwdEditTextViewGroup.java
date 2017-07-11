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
//package com.lht.lhttalk.customview;
//
//import android.content.Context;
//import android.graphics.drawable.Drawable;
//import android.text.method.HideReturnsTransformationMethod;
//import android.text.method.PasswordTransformationMethod;
//import android.util.AttributeSet;
//import android.view.View;
//import android.widget.CheckBox;
//import android.widget.CompoundButton;
//import android.widget.EditText;
//import android.widget.FrameLayout;
//
//import com.lht.lhttalk.R;
//
///**
// * <p><b>Package</b> com.lht.vsocyy.customview
// * <p><b>Project</b> VsoCyy
// * <p><b>Classname</b> PwdEditTextViewGroup
// * <p><b>Description</b>: TODO
// * <p>Created by leobert on 2017/2/15.
// */
//
//public class PwdEditTextViewGroup extends FrameLayout {
//    public PwdEditTextViewGroup(Context context) {
//        this(context, null);
//
//    }
//
//    public PwdEditTextViewGroup(Context context, AttributeSet attrs) {
//        this(context, attrs, 0);
//    }
//
//    public PwdEditTextViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//        init(attrs, defStyleAttr);
//    }
//
//    private View contentView;
//
//    private EditText etPassword;
//
//    private CheckBox cbTogglePwdVisible;
//
//    private void init(AttributeSet attrs, int defStyleAttr) {
//        contentView = inflate(getContext(), R.layout.view_pwd_etvg, this);
//    }
//
//    @Override
//    protected void onFinishInflate() {
//        super.onFinishInflate();
//        etPassword = (EditText) contentView.findViewById(R.id.et_pwd);
//        cbTogglePwdVisible = (CheckBox) contentView.findViewById(R.id.cb_viewpwd);
//
//        cbTogglePwdVisible.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if (isChecked) {
//                    etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
//                } else {
//                    etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
//                }
//            }
//        });
//    }
//
//    public EditText getPwdEditText() {
//        return etPassword;
//    }
//
//    public void updatePwdEditTextDrawable(int resId) {
//        Drawable drawable = getContext().getDrawable(resId);
//        if (drawable == null) {
//            return;
//        }
//        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
//        etPassword.setCompoundDrawables(drawable, null, null, null);
//    }
//
//    public void updateHint(int stringId) {
//        etPassword.setHint(stringId);
//    }
//
//    public String getInput() {
//        return etPassword.getText().toString();
//    }
//}
