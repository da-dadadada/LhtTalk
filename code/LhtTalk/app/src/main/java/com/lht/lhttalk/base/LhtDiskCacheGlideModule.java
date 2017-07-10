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

package com.lht.lhttalk.base;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.cache.DiskCache;
import com.bumptech.glide.load.engine.cache.DiskLruCacheWrapper;
import com.bumptech.glide.module.GlideModule;

import java.io.File;

/**
 * <p><b>Package:</b> com.lht.lhttalk.clazz </p>
 * <p><b>Project:</b> czspace </p>
 * <p><b>Classname:</b> LhtDiskCacheGlideModule </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/4/20.
 */

public class LhtDiskCacheGlideModule implements GlideModule {

    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        builder.setDecodeFormat(DecodeFormat.PREFER_ARGB_8888);
        builder.setDiskCache(new DiskCache.Factory() {
            @Override
            public DiskCache build() {
                final int MAX_SIZE_100M = 100 * 1000 * 1000;
                File cacheFolder = MainApplication.getOurInstance().getLocalThumbnailCacheDir();

                return DiskLruCacheWrapper.get(cacheFolder, MAX_SIZE_100M);
            }
        });
    }

    @Override
    public void registerComponents(Context context, Glide glide) {

    }
}
