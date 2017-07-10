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

package com.lht.lhttalk.util.file;

import com.lht.lhttalk.util.string.StringUtil;

import java.util.HashSet;

/**
 * <p><b>Package</b> com.lht.vsocyy.util.file
 * <p><b>Project</b> VsoCyy
 * <p><b>Classname</b> PreviewUtils
 * <p><b>Description</b>: TODO
 * <p>Created by leobert on 2016/11/8.
 */

public class PreviewUtils {

    private static HashSet<String> mimes = new HashSet<>();

    static {
        mimes.add("application/vnd.ms-excel");
        mimes.add("application/pdf");
        mimes.add("application/msword");
        mimes.add("text/plain");
        mimes.add("application/vnd.ms-powerpoint");
//        excel  application/vnd.ms-excel
//        pdf application/pdf
//        word application/msword
//        ppt application/vnd.ms-powerpoint
//        txt text/plain
    }

    public static boolean isSupportByVsoPreviewRules(String mime) {
        if (mimes.contains(mime)) {
            return true;
        }

        if (isImageButGif(mime)) {
            return true;
        }
        return false;
    }

//    public static ArrayList<AttachmentExt> filterImageTypedWithoutGif(ArrayList<AttachmentExt> exts) {
//        if (exts == null || exts.isEmpty()) {
//            return new ArrayList<>();
//        }
//        ArrayList<AttachmentExt> rets = new ArrayList<>();
//
//        for (AttachmentExt ext : exts) {
//            if (isImageButGif(ext.getMime())) {
//                rets.add(ext);
//            }
//        }
//
//        return rets;
//    }
//
//    public static ArrayList<AttachmentExt> filterFileTyped(ArrayList<AttachmentExt> exts) {
//        if (exts == null || exts.isEmpty()) {
//            return new ArrayList<>();
//        }
//        ArrayList<AttachmentExt> rets = new ArrayList<>();
//
//        for (AttachmentExt ext : exts) {
//            if (!isImageButGif(ext.getMime())) {
//                rets.add(ext);
//            }
//        }
//
//        return rets;
//    }

    private static boolean isImageButGif(String mime) {
        String _mime = StringUtil.nullStrToEmpty(mime).toLowerCase();
        final String IMAGE = "image";
        final String GIF = "gif";
        if (!_mime.contains(IMAGE)) {
            return false;
        } else if (_mime.contains(GIF)) {
            return false;
        }
        return true;
    }
}
