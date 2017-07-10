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

package com.lht.lhttalk.customview.toolBar;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

/**
 * <p><b>Package:</b> com.lht.lhttalk.customview.toolBar </p>
 * <p><b>Project:</b> czspace </p>
 * <p><b>Classname:</b> AbsMsgToolBar </p>
 * <p><b>Description:</b> 抽象包含通知的toolbar，包含4、6、8 todo 丰富doc </p>
 * Created by leobert on 2017/4/18.
 */
public abstract class AbsMsgToolBar extends AbsLhtToolBar {
    public AbsMsgToolBar(Context context) {
        super(context);
    }

    public AbsMsgToolBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public AbsMsgToolBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
