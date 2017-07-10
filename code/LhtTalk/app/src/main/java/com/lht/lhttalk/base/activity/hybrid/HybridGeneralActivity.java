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

package com.lht.lhttalk.base.activity.hybrid;

import android.widget.ProgressBar;

import com.lht.lhttalk.R;
import com.lht.lhttalk.customview.MaskView;
import com.lht.lhttalk.customview.toolBar.navigation.ToolbarTheme1;
import com.lht.lhtwebviewlib.BridgeWebView;
import com.lht.ptrlib.library.PtrBridgeWebView;

/**
 * <p><b>Package:</b> com.lht.lhttalk.activity.hybrid </p>
 * <p><b>Project:</b> czspace </p>
 * <p><b>Classname:</b> HybridGeneralActivity </p>
 * <p><b>Description:</b> 通用的混合activity UI基类 </p>
 * Created by leobert on 2017/3/8.
 */

public abstract class HybridGeneralActivity extends AbsHybridActivityBase {

    private ProgressBar progressBar;
    private PtrBridgeWebView ptrBridgeWebView;
    private MaskView maskView;
    protected ToolbarTheme1 titleBar;

    protected String url;

    public static final String KEY_DATA = "_key_url";

    @Override
    protected final BridgeWebView getBridgeWebView() {
        return ptrBridgeWebView.getRefreshableView();
    }

    @Override
    protected PtrBridgeWebView getPTRBase() {
        return ptrBridgeWebView;
    }

    @Override
    protected final MaskView getWebMask() {
        return maskView;
    }

    @Override
    protected final ProgressBar getPageProtectPbar() {
        return progressBar;
    }

    @Override
    protected final int getContentLayoutRes() {
        return R.layout.activity_hybrid_;
    }

    @Override
    protected String getUrl() {
        return url;
    }


    @Override
    protected final void initView() {
        titleBar = (ToolbarTheme1) findViewById(R.id.titlebar);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        maskView = (MaskView) findViewById(R.id.mask);

        ptrBridgeWebView = (PtrBridgeWebView) findViewById(R.id.ptr_web_view);
    }

    @Override
    protected void initVariable() {
        url = getIntent().getStringExtra(KEY_DATA);
    }

}
