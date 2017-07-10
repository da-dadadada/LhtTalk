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

package com.lht.lhttalk.customview.tab;

import android.view.View;
import android.widget.CompoundButton;

import java.util.HashMap;
import java.util.Set;

/**
 * <p><b>Package</b> com.lht.vsocyy.clazz
 * <p><b>Project</b> VsoCyy
 * <p><b>Classname</b> OnCheckedChangeListenerImpl
 * <p><b>Description</b>: 自定义的tabhost控制RadioButton只有一个被选中的监听器类
 * Created by leobert on 2016/7/19.
 */
public class OnCheckedChangeListenerImpl implements View.OnClickListener {

    private HashMap<CompoundButton, CompoundButton.OnCheckedChangeListener> callbacks;

    public OnCheckedChangeListenerImpl(HashMap<CompoundButton, CompoundButton.OnCheckedChangeListener> callbacks) {
        this.callbacks = callbacks;
    }

    @Override
    public void onClick(View v) {
        Set<CompoundButton> keys = callbacks.keySet();
        for (CompoundButton key : keys) {
            if(key == v) {
                callbacks.get(key).onCheckedChanged(key,true);
                key.setChecked(true);
            } else {
                callbacks.get(key).onCheckedChanged(key,false);
                key.setChecked(false);
            }
        }
    }
}
