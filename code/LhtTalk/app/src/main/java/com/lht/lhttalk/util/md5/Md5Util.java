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

package com.lht.lhttalk.util.md5;

import android.text.TextUtils;

import java.io.FileInputStream;
import java.security.MessageDigest;

/**
 * <p><b>Package</b> com.lht.vsocyy.util.md5
 * <p><b>Project</b> VsoCyy
 * <p><b>Classname</b> Md5Util
 * <p><b>Description</b>: TODO
 * <p> Create by Leobert on 2016/9/7
 */
public class Md5Util {
    private static int bufferSize = 1024 * 1024;

    public static String getFileMd5String(String fileName) {
        byte[] buf = new byte[bufferSize];
        MessageDigest md;
        boolean fileIsNull = true;

        try {
            FileInputStream fis = new FileInputStream(fileName);
            int len = 0;
            md = MessageDigest.getInstance("MD5");

            len = fis.read(buf);
            if (len > 0) {
                fileIsNull = false;
                while (len > 0) {
                    md.update(buf, 0, len);
                    len = fis.read(buf);
                }
            }
        } catch (Exception e) {
            return null;
        }

        if (fileIsNull)
            return null;
        else
            return bufferToHex(md.digest());
    }

    public static String getStringMd5(String res) {
        MessageDigest md;
        if (TextUtils.isEmpty(res))
            return null;

        try {
            md = MessageDigest.getInstance("MD5");
            byte[] buf = res.getBytes();
            md.update(buf);
        } catch (Exception e) {
            return null;
        }

        return bufferToHex(md.digest());
    }

    private static String bufferToHex(byte bytes[]) {
        return bufferToHex(bytes, 0, bytes.length);
    }

    private static String bufferToHex(byte bytes[], int m, int n) {
        StringBuffer stringbuffer = new StringBuffer(2 * n);
        int k = m + n;
        for (int l = m; l < k; l++) {
            appendHexPair(bytes[l], stringbuffer);
        }
        return stringbuffer.toString();
    }

    private static void appendHexPair(byte bt, StringBuffer stringbuffer) {
        char c0 = hexDigits[(bt & 0xf0) >> 4];
        char c1 = hexDigits[bt & 0xf];
        stringbuffer.append(c0);
        stringbuffer.append(c1);
    }

    protected static char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6',
            '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
}
