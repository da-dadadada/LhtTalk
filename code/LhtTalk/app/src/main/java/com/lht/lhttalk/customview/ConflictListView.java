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

package com.lht.lhttalk.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * <p><b>Package</b> com.lht.vsocyy.customview
 * <p><b>Project</b> VsoCyy
 * <p><b>Classname</b> ConflictListview
 * <p><b>Description</b>: TODO
 * Created by leobert on 2016/7/28.
 */
public class ConflictListView extends ListView {

//    private boolean isOnConflictPeriod = false;
//
//    public boolean isOnConflictPeriod() {
//        return isOnConflictPeriod;
//    }
//
//    public void setOnConflictPeriod(boolean isOnConflictPeriod) {
//        this.isOnConflictPeriod = isOnConflictPeriod;
//    }

    public ConflictListView(Context context) {
        super(context);
        setFocusable(false);
    }

    public ConflictListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setFocusable(false);
    }

    public ConflictListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setFocusable(false);
    }

//    private OnConflictListLayoutListener onConflictListLayoutListener;
//
//    public OnConflictListLayoutListener getOnConflictListLayoutListener() {
//        return onConflictListLayoutListener;
//    }
//
//    public void setOnConflictListLayoutListener(OnConflictListLayoutListener onConflictListLayoutListener) {
//        this.onConflictListLayoutListener = onConflictListLayoutListener;
//    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

//        if (onConflictListLayoutListener != null) {
//            onConflictListLayoutListener.preMeasure(this);
//        }
//        setOnConflictPeriod(true);

        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
//        if (onConflictListLayoutListener != null) {
//            onConflictListLayoutListener.finishLayout(this, changed);
//        }
//        setOnConflictPeriod(false);
    }

//    public interface OnConflictListLayoutListener {
//        void preMeasure(ConflictListView view);
//
//        void finishLayout(ConflictListView view, boolean changed);
//    }
}
