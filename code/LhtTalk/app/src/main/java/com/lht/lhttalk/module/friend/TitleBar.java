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

package com.lht.lhttalk.module.friend;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lht.lhttalk.R;
import com.lht.lhttalk.customview.toolBar.AbsMsgToolBar;

import java.lang.ref.WeakReference;


public class TitleBar extends AbsMsgToolBar {

    private View titleBar;
    private ImageView ibBack;
    private TextView tvTitle;
    private TextView tvAddFriend;

    public TitleBar(Context context) {
        super(context);
        init(context);
    }

    public TitleBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public TitleBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        setContentInsetsAbsolute(0, 0);
        titleBar = View.inflate(context, R.layout.view_titlebar, this);
        ibBack = (ImageView) titleBar.findViewById(R.id.back);
        tvTitle = (TextView) titleBar.findViewById(R.id.title);
        tvAddFriend = (TextView) titleBar.findViewById(R.id.tv_add_friend);
    }

    /**
     * 设置title
     *
     * @param s
     */
    public void setTvTitle(String s) {
        tvTitle.setText(s);
    }

    /**
     * 设置标题颜色
     *
     * @param color
     */
    public void setTvTitleColor(int color) {
        tvTitle.setTextColor(color);
    }

    /**
     * 设置标题字体大小
     *
     * @param size
     */
    public void setTvTitleTextSize(int size) {
        tvTitle.setTextSize(size);
    }

    /**
     * 添加按钮点击时的监听
     *
     * @param l
     */
    public void setOnAddOnclickListener(OnClickListener l) {
        tvAddFriend.setOnClickListener(l);
    }

    /**
     * 返回
     *
     * @param activity
     */
    public void setDefaultOnBackListener(Activity activity) {
        setOnBackListener(new DefaultOnBackClickListener(activity));
    }

    private void setOnBackListener(final ITitleBackListener listener) {
        ibBack.setVisibility(View.VISIBLE);
        if (listener != null) {
            ibBack.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onTitleBackClick();
                }
            });
        }
    }

    public interface ITitleBackListener {
        void onTitleBackClick();
    }

    public static class DefaultOnBackClickListener implements ITitleBackListener {
        private WeakReference<Activity> mActivity;

        public DefaultOnBackClickListener(Activity activity) {
            mActivity = new WeakReference<>(activity);
        }

        @Override
        public void onTitleBackClick() {
            if (mActivity.get() != null) {
                mActivity.get().finish();
            }
        }
    }
}
