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

package com.lht.lhttalk.customview.popup;

import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.lht.lhttalk.R;
import com.lht.lhttalk.base.launcher.ITriggerCompare;

import java.io.Serializable;

/**
 * <p><b>Package</b> com.lht.lhttalk.customview.popup
 * <p><b>Project</b> czspace
 * <p><b>Classname</b> PupupPublishTypeChooseWin
 * <p><b>Description</b>: TODO
 * <p>Created by leobert on 2017/2/28.
 */

public class PopupPublishTypeChooseWin extends CustomPopupWindow implements View.OnClickListener {

    private LinearLayout llPublishArticle;
    private LinearLayout llPublishProject;
    private ImageView ivClose;

    public PopupPublishTypeChooseWin(IPopupHolder iPopupHolder) {
        super(iPopupHolder);
    }

    private View contentView;

    private RelativeLayout animContent;

    @Override
    protected void init() {
        super.doDefaultSetting();
        setyOffset(100);
        LayoutInflater inflater = LayoutInflater.from(mActivity);
        contentView = inflater.inflate(R.layout.popup_publish_type_choose, null);
        animContent = (RelativeLayout) contentView.findViewById(R.id.rl_anim_content);
        llPublishArticle = (LinearLayout) contentView.findViewById(R.id.ll_publish_artical);
        llPublishProject = (LinearLayout) contentView.findViewById(R.id.ll_publish_project);
        ivClose = (ImageView) contentView.findViewById(R.id.view_close);
        setClickEvent();
        setContentView(contentView);
        ColorDrawable cd = new ColorDrawable(0x000000);
        this.setBackgroundDrawable(cd);
    }

    private void setClickEvent() {
        llPublishProject.setOnClickListener(this);
        llPublishArticle.setOnClickListener(this);
        ivClose.setOnClickListener(this);
        contentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    @Override
    protected int getMyAnim() {
        return R.style.CustomIn_SlideOut;
    }

    @Override
    protected int getMyWidth() {
        return mActivity.getWindowManager().getDefaultDisplay().getWidth();
    }

    @Override
    protected int getMyHeight() {
        return ViewGroup.LayoutParams.MATCH_PARENT;
    }

    @Override
    public void showBelow(View target) {
        super.showBelow(target);
    }

    @Override
    protected void onShow() {
        super.onShow();
        animContent.startAnimation(dropdownAnimation(300));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_publish_project:
                if (listener != null) {
                    listener.onProjectPublish();
                }
                break;
            case R.id.ll_publish_artical:
                if (listener != null) {
                    listener.onArticlePublish();
                }
                break;
            case R.id.view_close:
                dismiss();
                break;
            default:
                break;
        }
    }

    public interface OnPublishClickListener {
        void onProjectPublish();

        void onArticlePublish();
    }

    private OnPublishClickListener listener;

    public void setOnProjectPublish(OnPublishClickListener listener) {
        this.listener = listener;
    }

    public enum LoginTrigger implements ITriggerCompare {
        ProjectPublish(1), ArticlePublish(2);

        private final int tag;


        LoginTrigger(int i) {
            tag = i;
        }

        @Override
        public boolean equals(ITriggerCompare compare) {
            if (compare == null) {
                return false;
            }
            boolean b1 = compare.getClass().getName().equals(getClass().getName());
            boolean b2 = compare.getTag().equals(getTag());
            return b1 & b2;
        }

        @Override
        public Object getTag() {
            return tag;
        }

        @Override
        public Serializable getSerializable() {
            return this;
        }

    }
}
