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

import com.lht.lhttalk.util.debug.DLog;

import java.util.HashMap;

/**
 * <p><b>Package</b> com.lht.vsocyy.clazz
 * <p><b>Project</b> VsoCyy
 * <p><b>Classname</b> TabManager
 * <p><b>Description</b>: TODO
 * <p>Created by leobert on 2017/2/9.
 */

public class TabManager {

    public static void init(OnTabSelectedListener onTabSelectedListener, CompoundButton... compoundButtons) {
        HashMap<CompoundButton, CompoundButton.OnCheckedChangeListener> map = new HashMap<>();

        Listener listener = new Listener(onTabSelectedListener);

        for (CompoundButton rb : compoundButtons) {
            map.put(rb, listener);
        }
        View.OnClickListener listener2 = new OnCheckedChangeListenerImpl(map);
        for (CompoundButton rb : compoundButtons) {
            rb.setOnClickListener(listener2);
        }
    }

    private static final class Listener implements CompoundButton.OnCheckedChangeListener {

        private final OnTabSelectedListener onTabSelectedListener;

        Listener(OnTabSelectedListener onTabSelectedListener) {
            this.onTabSelectedListener = onTabSelectedListener;
            if (onTabSelectedListener == null)
                DLog.e(TabManager.class,"onTabSelectedListener is null");
        }

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (!isChecked) {
                return;
            }
            if (onTabSelectedListener == null) {
                DLog.e(TabManager.class,"onTabSelectedListener is null");
                return;
            }
            onTabSelectedListener.onTabSelect(buttonView);
        }
    }

    public interface OnTabSelectedListener {
        void onTabSelect(CompoundButton selectedTab);
    }
}
