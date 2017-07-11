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
//package com.lht.lhttalk.customview.toast;
//
//import android.app.Activity;
//import android.app.Application;
//import android.content.Context;
//import android.support.annotation.ColorRes;
//import android.support.v4.content.ContextCompat;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.widget.ImageView;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.lht.lhttalk.R;
//
///**
// * Created by chhyu on 2017/4/27.
// * 预览图片--保存--保存状态的toast（保存成功/保存失败）
// */
//
//public class SaveImageStateToast extends Toast {
//    private Context context;
//    private View contentView;
//    private ImageView ivToastImage;
//    private TextView tvToastContent;
//
//    /**
//     * Construct an empty Toast object.  You must call {@link #setView} before you
//     * can call {@link #show}.
//     *
//     * @param context The context to use.  Usually your {@link Application}
//     *                or {@link Activity} object.
//     */
//    public SaveImageStateToast(Context context) {
//        super(context);
//        this.context = context;
//        init(context);
//    }
//
//    private void init(Context context) {
//        contentView = LayoutInflater.from(context).inflate(R.layout.save_state_toast, null);
//        ivToastImage = (ImageView) contentView.findViewById(R.id.iv_toast_image);
//        tvToastContent = (TextView) contentView.findViewById(R.id.tv_toast_content);
//
//        setView(contentView);
//    }
//
//
//    public void setToastImage(int resDrawable) {
//        ivToastImage.setImageResource(resDrawable);
//    }
//
//    public void setToastContent(String content) {
//        tvToastContent.setText(content);
//    }
//
//    public void setToastContent(int resContent) {
//        tvToastContent.setText(resContent);
//    }
//
//    public void setToastContentColor(@ColorRes int resColor) {
//        tvToastContent.setTextColor(ContextCompat.getColor(context, resColor));
//    }
//
//}
