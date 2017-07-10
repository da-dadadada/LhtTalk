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

package com.lht.lhttalk.util.ui;

import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.widget.EditText;

import com.lht.lhttalk.util.string.StringUtil;

/**
 * <p><b>Package:</b> com.lht.lhttalk.util.ui </p>
 * <p><b>Project:</b> czspace </p>
 * <p><b>Classname:</b> EditTextUtils </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/3/21.
 */

public class EditTextUtils {

    /**
     * move the input index to the end of the target
     * @param target the input EditText widget
     */
    public static final void moveIndex2End(EditText target) {
        if (target == null)
            return;
        String input = target.getText().toString();
        int end = StringUtil.nullStrToEmpty(input).length();
        target.setSelection(end);
    }

    public static final void togglePwdDisplay(boolean display, EditText et) {
        if (display) {
            et.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        } else {
            et.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }
        EditTextUtils.moveIndex2End(et);
    }
}
