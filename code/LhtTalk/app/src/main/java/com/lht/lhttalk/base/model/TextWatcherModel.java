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

package com.lht.lhttalk.base.model;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

/**
 * <p><b>Package</b> com.lht.vsocyy.mvp.model
 * <p><b>Project</b> VsoCyy
 * <p><b>Classname</b> TextWatcherModel
 * <p><b>Description</b>: TODO
 * Created by leobert on 2016/5/11.
 */
public class TextWatcherModel implements IWatcher {

    private final TextWatcherModelCallback modelCallback;

    public TextWatcherModel(TextWatcherModelCallback modelCallback) {
        this.modelCallback = modelCallback;
    }

    @Override
    public void doWatcher(EditText editText, int maxLength) {
        editText.addTextChangedListener(new WatcherImpl(editText,maxLength));
    }


    public interface TextWatcherModelCallback {
        void onOverLength(int edittextId, int maxLength);

        void onChanged(int edittextId, int currentCount, int remains);
    }

    private final class WatcherImpl implements TextWatcher {

        private final EditText editText;

        private final int maxLength;

        WatcherImpl(EditText editText, int maxLength) {
            this.editText = editText;
            this.maxLength = maxLength;
        }

        private CharSequence temp;
        private int editStart;
        private int editEnd;

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            temp = s;
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            editStart = editText.getSelectionStart();
            editEnd = editText.getSelectionEnd();
            if (temp.length() > maxLength) {
                s.delete(editStart - 1, editEnd);
                int tempSelection = editStart;
                editText.setText(s);
                editText.setSelection(tempSelection);
                modelCallback.onOverLength(editText.getId(),maxLength);
            } else {
                modelCallback.onChanged(editText.getId(),temp.length(),maxLength-temp.length());
            }
        }
    }
}

interface IWatcher {
    void doWatcher(EditText editText, int maxLength);
}

