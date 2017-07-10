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

package com.lht.lhttalk.base.launcher;

import android.content.Context;
import android.content.Intent;

import com.lht.lhttalk.module.user.login.ui.LoginActivity;

/**
 * <p><b>Package</b> com.lht.vsocyy.clazz
 * <p><b>Project</b> VsoCyy
 * <p><b>Classname</b> LoginIntentFactory
 * <p><b>Description</b>: TODO
 * Created by leobert on 2016/5/6.
 */
public class LoginIntentFactory {

    public static Intent create(Context context, ITriggerCompare iTriggerCompare) {
//        Intent intent = new Intent(context, LoginActivity.class);
//        intent.putExtra(LoginActivity.TRIGGERKEY,iTriggerCompare.getSerializable());
//        return intent;
        return new LoginIntent(context, iTriggerCompare);
    }

    public static class LoginIntent extends Intent {
        public LoginIntent(Context context,ITriggerCompare iTriggerCompare) {
            super(context, LoginActivity.class);
            putExtra(LoginActivity.TRIGGERKEY,iTriggerCompare.getSerializable());
        }
    }
}
