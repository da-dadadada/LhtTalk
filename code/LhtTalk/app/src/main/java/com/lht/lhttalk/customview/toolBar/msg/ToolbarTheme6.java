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
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.lht.lhttalk.R;
import com.lht.lhttalk.customview.toolBar.AbsMsgToolBar;

import individual.leobert.uilib.numbadge.NumBadge;

/**
 * <p><b>Package</b> com.lht.lhttalk.customview.toolBar.msg </p>
 * <p><b>Project</b> czspace </p>
 * <p><b>Classname</b> ToolbarTheme6 </p>
 * <p><b>Description</b>:样式6："message"+cb(title&op)+image </p>
 * <p>
 * Created by leobert on 2017/4/18.
 */
public class ToolbarTheme6 extends AbsMsgToolBar {

    private View mToolbar;
    private CheckBox cbRightView;
    private LinearLayout llAllProject;
    private CheckBox cbAllProject;

    protected FrameLayout mMessage;
    protected NumBadge mMessageHint;

    public ToolbarTheme6(Context context) {
        super(context);
        init(context);
    }

    public ToolbarTheme6(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ToolbarTheme6(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        setContentInsetsAbsolute(0, 0);
        mToolbar = View.inflate(context, R.layout.view_toolbar_theme6, this);

        cbRightView = (CheckBox) mToolbar.findViewById(R.id.cb_right_state);

        cbAllProject = (CheckBox) mToolbar.findViewById(R.id.cb_all_project);
        llAllProject = (LinearLayout) mToolbar.findViewById(R.id.ll_all);

        mMessage = (FrameLayout) mToolbar.findViewById(R.id.nav_msg);
        mMessageHint = (NumBadge) mToolbar.findViewById(R.id.nav_msg_hint);

        cbAllProject.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (getiFilterDisplay() == null) {
                    return;
                }
                if (isChecked)
                    getiFilterDisplay().showFilter();
                else
                    getiFilterDisplay().hideFilter();
            }
        });

    }

    /**
     * 设置title
     *
     * @param text
     */
    public void setTitle(String text) {
        cbAllProject.setText(text);
    }

    /**
     * @param resId 设置title
     */
    public void setTitle(int resId) {
        cbAllProject.setText(getResources().getText(resId));
    }

    /**
     * 设置title 字体颜色
     *
     * @param colorResid
     */
    public void setTitleTextColor(@ColorRes int colorResid) {
        cbAllProject.setTextColor(ContextCompat.getColor(getContext(), colorResid));
    }

    /**
     * 设置右边的图片
     *
     * @param srcDrawable
     */
    public void setRightImageDrawable(int srcDrawable) {
        cbRightView.setButtonDrawable(srcDrawable);
    }


    /**
     * 手动关闭filter时外部调用刷新UI
     */
    public void refreshUiOnManualHideFilter() {
        cbAllProject.setChecked(false);
    }


    /**
     * 右边加号点击时的监听
     *
     * @param l
     */
    public void setRightOpClickListener(View.OnClickListener l) {
        cbRightView.setOnClickListener(l);
    }

    public final void setOnNavMessageClickListener(View.OnClickListener l) {
        mMessage.setOnClickListener(l);
    }

    private IFilterDisplay iFilterDisplay;

    public IFilterDisplay getiFilterDisplay() {
        return iFilterDisplay;
    }

    public void setiFilterDisplay(IFilterDisplay iFilterDisplay) {
        this.iFilterDisplay = iFilterDisplay;
    }

    public interface IFilterDisplay {
        void showFilter();

        void hideFilter();
    }
}
