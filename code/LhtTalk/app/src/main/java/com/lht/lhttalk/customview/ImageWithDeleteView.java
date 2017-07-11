///*
// * MIT License
// *
// * Copyright (c) 2017 leobert-lan
// *
// * Permission is hereby granted, free of charge, to any person obtaining a copy
// * of this software and associated documentation files (the "Software"), to deal
// * in the Software without restriction, including without limitation the rights
// * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
// * copies of the Software, and to permit persons to whom the Software is
// * furnished to do so, subject to the following conditions:
// *
// * The above copyright notice and this permission notice shall be included in all
// * copies or substantial portions of the Software.
// *
// * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
// * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
// * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
// * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
// * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
// * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
// * SOFTWARE.
// *
// */
//
//package com.lht.lhttalk.customview;
//
//import android.content.Context;
//import android.util.AttributeSet;
//import android.view.View;
//import android.widget.FrameLayout;
//import android.widget.ImageButton;
//import android.widget.ImageView;
//
//import com.lht.lhttalk.R;
//
///**
// * <p><b>Package</b> com.lht.vsocyy.customview
// * <p><b>Project</b> VsoCyy
// * <p><b>Classname</b> ImageWithDeleteView
// * <p><b>Description</b>: TODO
// * Created by leobert on 2016/7/8.
// */
//public class ImageWithDeleteView extends FrameLayout {
//    private ImageView content;
//
//    private ImageButton delete;
//
//    public ImageWithDeleteView(Context context) {
//        this(context, null);
//    }
//
//    public ImageWithDeleteView(Context context, AttributeSet attrs) {
//        this(context, attrs, 0);
//    }
//
//    public ImageWithDeleteView(Context context, AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//        init();
//    }
//
//    private void init() {
//        inflate(getContext(), R.layout.view_image_with_delete, this);
//        content = (ImageView) findViewById(R.id.image_content);
//        delete = (ImageButton) findViewById(R.id.image_delete);
//
//        delete.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (onImageDeleteListener != null) {
//                    onImageDeleteListener.onDelete(ImageWithDeleteView.this, getTag());
//                }
//            }
//        });
//    }
//
//
//    public ImageView getActualImageView() {
//        return content;
//    }
//
//    private OnImageDeleteListener onImageDeleteListener;
//
//    public void setOnImageDeleteListener(OnImageDeleteListener listener) {
//        onImageDeleteListener = listener;
//    }
//
//    public interface OnImageDeleteListener {
//        /**
//         * @param view
//         * @param tag
//         */
//        void onDelete(ImageWithDeleteView view, Object tag);
//    }
//}
