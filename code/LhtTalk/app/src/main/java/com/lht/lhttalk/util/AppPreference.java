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

package com.lht.lhttalk.util;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * Description：SharedPreferences的管理类
 * 存放通用设置
 */
public class AppPreference {

    private SharedPreferences mSharedPreferences = null;
    private static Editor mEditor = null;
    private static AppPreference ourInstance;

    private AppPreference(Context context) {
        if (null == mSharedPreferences) {
            mSharedPreferences = android.preference.PreferenceManager.getDefaultSharedPreferences(context);
        }
    }

    public static AppPreference getInstance(Application app) {
        if (ourInstance == null) {
            ourInstance = new AppPreference(app.getApplicationContext());
        }
        return ourInstance;
    }


    public void removeKey(String key) {
        mEditor = mSharedPreferences.edit();
        mEditor.remove(key);
        mEditor.commit();
    }

    public void removeAll() {
        mEditor = mSharedPreferences.edit();
        mEditor.clear();
        mEditor.commit();
    }

    public void commitString(String key, String value) {
        mEditor = mSharedPreferences.edit();
        mEditor.putString(key, value);
        mEditor.commit();
    }

    public String getString(String key, String defaultValue) {
        return mSharedPreferences.getString(key, defaultValue);
    }

    public void commitInt(String key, int value) {
        mEditor = mSharedPreferences.edit();
        mEditor.putInt(key, value);
        mEditor.commit();
    }

    public int getInt(String key, int failValue) {
        return mSharedPreferences.getInt(key, failValue);
    }

    public void commitLong(String key, long value) {
        mEditor = mSharedPreferences.edit();
        mEditor.putLong(key, value);
        mEditor.commit();
    }

    public long getLong(String key, long failValue) {
        return mSharedPreferences.getLong(key, failValue);
    }

    public void commitBoolean(String key, boolean value) {
        mEditor = mSharedPreferences.edit();
        mEditor.putBoolean(key, value);
        mEditor.commit();
    }

    public Boolean getBoolean(String key, boolean failValue) {
        return mSharedPreferences.getBoolean(key, failValue);
    }
}
