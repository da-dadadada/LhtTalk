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
//package com.lht.lhttalk.customview.popup;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.PopupWindow;
//import android.widget.ProgressBar;
//import android.widget.TextView;
//
//import com.lht.lhttalk.R;
//
//import java.util.Locale;
//
///**
// * @author leobert.lan
// * @version 1.0
// * @ClassName: CustomDialog
// * @Description: 自定义的进度控件
// * @date 2016年1月6日 上午9:50:10
// */
//public class CustomProgressView extends CustomPopupWindow {
//
//    private TextView tvProgress;
//
//
//    private ProgressBar progressBar;
//
//    private View contentView;
//
//
//    public CustomProgressView(IPopupHolder iPopupHolder) {
//        super(iPopupHolder);
//    }
//
//    protected void init() {
//        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
//        this.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
//        this.setFocusable(false);
//        this.setOutsideTouchable(false);
//        this.setAnimationStyle(R.style.iOSActionSheet);
//        setyOffset(-100);
//
//        LayoutInflater inflater = LayoutInflater.from(mActivity);
//        contentView = inflater.inflate(R.layout.view_custom_progress, null);
//        setContentView(contentView);
//
//        progressBar = (ProgressBar) contentView.findViewById(R.id.vcp_pb);
//        tvProgress = (TextView) contentView.findViewById(R.id.vcp_tv);
//    }
//
//    @Override
//    protected int getMyAnim() {
//        return R.style.customdialogAnim;
//    }
//
//    @Override
//    protected void backgroundAlpha(float alpha) {
//        // ignore background change
//    }
//
//    private static final String DEFAULT_FORMAT = "正在下载(%d%%)";
//
//    public void setProgress(long current, long total) {
//        tvProgress.setText(calcProgress(current, total));
//    }
//
//    /**
//     * for test
//     */
//    public static String calcProgress(long current, long total) {
//        int percent = (int) (current * 100 / total);
//        return String.format(Locale.ENGLISH, DEFAULT_FORMAT, percent);
//    }
//
//    /**
//     * @Title: cancel
//     * @Description: 相当于点击取消
//     * @author: leobert.lan
//     */
//    public void cancel() {
//        dismiss();
//    }
//
//    @Override
//    public void setOnDismissListener(PopupWindow.OnDismissListener onDismissListener) {
//        throw new IllegalAccessError("never use this method");
//    }
//
//}
//
