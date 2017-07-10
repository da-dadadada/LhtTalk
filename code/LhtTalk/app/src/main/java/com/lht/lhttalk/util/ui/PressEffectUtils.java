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

package com.lht.lhttalk.util.ui;

import android.view.MotionEvent;
import android.view.View;

import com.daimajia.androidanimations.library.BaseViewAnimator;
import com.daimajia.androidanimations.library.YoYo;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.ObjectAnimator;

/**
 * <p><b>Package</b> com.lht.vsocyy.util.ui
 * <p><b>Project</b> VsoCyy
 * <p><b>Classname</b> HoverEffectUtils
 * <p><b>Description</b>: 悬停效果助手
 * <p>Created by leobert on 2017/2/14.
 */

public class PressEffectUtils {
    public static void bindDefaultPressEffect(View view) {
        view.setOnTouchListener(new DefaultPressEffect());

    }


    private static final class DefaultPressEffect implements View.OnTouchListener {

        YoYo.YoYoString yoYoString;
        boolean resetOnFinish = false;

        private View.OnTouchListener downListener = new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    if (yoYoString != null) {
                        return false;
                    }
                    yoYoString = YoYo.with(new ZoomAnimator()).duration(300)
                            .withListener(new Animator.AnimatorListener() {
                                @Override
                                public void onAnimationStart(Animator animation) {
                                    resetOnFinish = false;
                                }

                                @Override
                                public void onAnimationEnd(Animator animation) {
                                    if (resetOnFinish) {
                                        yoYoString.stop(true);
                                        yoYoString = null;
                                    }
                                    resetOnFinish = false;
                                }

                                @Override
                                public void onAnimationCancel(Animator animation) {
                                    yoYoString = null;
                                    resetOnFinish = false;
                                }

                                @Override
                                public void onAnimationRepeat(Animator animation) {
                                }
                            })
                            .playOn(v);
                }
                return false;
            }
        };

        private View.OnTouchListener upListener = new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (yoYoString != null) {
                        if (yoYoString.isRunning()) {
                            resetOnFinish = true;
                        } else {
                            resetOnFinish = false;
                            yoYoString.stop(true);
                            yoYoString = null;
                        }
                    }
                }
                return false;
            }
        };

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_DOWN)
                downListener.onTouch(v, event);
            if (event.getAction() == MotionEvent.ACTION_UP)
                upListener.onTouch(v, event);
            return false;
        }
    }


    private static class ZoomAnimator extends BaseViewAnimator {
        @Override
        protected void prepare(View target) {
            getAnimatorAgent().playTogether(
                    ObjectAnimator.ofFloat(target, "scaleX", 1, 0.9f, 0.8f),
                    ObjectAnimator.ofFloat(target, "scaleY", 1, 0.9f, 0.8f)
            );
        }
    }
}
