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

package com.lht.lhttalk.customview.toolBar.msg;

import android.content.Context;
import android.support.annotation.ColorRes;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.lht.lhttalk.R;
import com.lht.lhttalk.customview.toolBar.AbsMsgToolBar;
import com.lht.lhttalk.listener.ICallback;

import individual.leobert.uilib.numbadge.NumBadge;


/**
 * <p><b>Package</b> com.lht.lhttalk.customview.toolBar.msg </p>
 * <p><b>Project</b> czspace </p>
 * <p><b>Classname</b> ToolbarTheme4 </p>
 * <p><b>Description</b>:样式四："message"+text(title)+image </p>
 * <p>
 * Created by leobert on 2017/4/18.
 */
public class ToolbarTheme4 extends AbsMsgToolBar {

    private View mToolbar;
    private TextView tvTitle;
    private View bottomDivider;
    private FrameLayout mMessage;
    private NumBadge mMessageHint;
    protected ImageView mMore;

    public ToolbarTheme4(Context context) {
        super(context);
        init(context);
    }

    public ToolbarTheme4(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ToolbarTheme4(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        setContentInsetsAbsolute(0, 0);
        mToolbar = View.inflate(context, R.layout.view_toolbar_theme4, this);

        tvTitle = (TextView) mToolbar.findViewById(R.id.tv_title);
        mMore = (ImageView) mToolbar.findViewById(R.id.nav_more);
        bottomDivider = mToolbar.findViewById(R.id.bottom_divider);

        mMessage = (FrameLayout) mToolbar.findViewById(R.id.nav_msg);
        mMessageHint = (NumBadge) mToolbar.findViewById(R.id.nav_msg_hint);


        mMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != onMoreClickListener) {
                    onMoreClickListener.onCallback();
                }
            }
        });
    }

    public void setTitle(String text) {
        tvTitle.setText(text);
    }

    public void setRightImageDrawable(int resDrawable) {
        mMore.setImageResource(resDrawable);
    }

    /**
     * 隐藏标题与标题下面内容的分隔线
     */
    public void hideTitleBottomDividerLine() {
        bottomDivider.setVisibility(GONE);
    }

    /**
     * 设置富文本风格的title
     *
     * @param charSequence title
     */
    public void setTitle(CharSequence charSequence) {
        tvTitle.setText(charSequence);
    }

    public void setTitle(int resId) {
        tvTitle.setText(getResources().getText(resId));
    }

    public void setTitleTextColor(@ColorRes int colorResId) {
        tvTitle.setTextColor(ContextCompat.getColor(getContext(), colorResId));
    }

    /**
     * 设置标题字体大小
     *
     * @param size
     */
    public void setTitleTextSize(int size) {
        tvTitle.setTextSize(size);
    }

    /**
     * 消息的点击事件
     *
     * @param l
     */
    public final void setOnNavMessageClickListener(View.OnClickListener l) {
        mMessage.setOnClickListener(l);
    }

    private ICallback onMoreClickListener;

    /**
     * 加号点击事件
     *
     * @param callback
     */
    public void setOnRightImageClickListener(ICallback callback) {
        this.onMoreClickListener = callback;
    }

}
