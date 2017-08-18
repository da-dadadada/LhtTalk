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

package com.lht.lhttalk.module.search.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v4.util.CircularArray;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.lht.lhttalk.R;
import com.lht.lhttalk.base.BaseLoadingAdapter;
import com.lht.lhttalk.module.search.bean.SearchResBean;
import com.lht.lhttalk.util.time.TimeUtil;

import java.text.SimpleDateFormat;

/**
 * Created by chhyu on 2017/8/15.
 */

public class SearchResultAdapter extends BaseLoadingAdapter<SearchResBean> {
    private Context context;
    private OnItemClickListener listener;

    public SearchResultAdapter(Context context, OnItemClickListener l, RecyclerView recyclerView, CircularArray<SearchResBean> ts) {
        super(recyclerView, ts);
        this.context = context;
        this.listener = l;
    }

    @Override
    public RecyclerView.ViewHolder onCreateNormalViewHolder(ViewGroup parent) {
        View view = View.inflate(context, R.layout.search_result_item, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindNormalViewHolder(RecyclerView.ViewHolder holder1, final int position) {
        final ViewHolder holder = (ViewHolder) holder1;
        final SearchResBean searchResBean = mDataSet.get(position);
        Glide.with(context).load(searchResBean.getAvatar()).asBitmap().centerCrop().into(new BitmapImageViewTarget(holder.ivAvatar) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                holder.ivAvatar.setImageDrawable(circularBitmapDrawable);
            }
        });
        holder.tvNickname.setText(searchResBean.getNickname());

        long l = Long.parseLong(searchResBean.getCreate_time());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = TimeUtil.getTime(l, format);
        holder.tvCreateTime.setText(time);
        String sex = searchResBean.getSex();
        if (sex.equals("true")) {
            holder.tvSex.setText("性别：男");
        } else {
            holder.tvSex.setText("性别：女");
        }
        holder.rlSearchItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onItemClick(holder.rlSearchItem, position, searchResBean);
                }
            }
        });
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView ivAvatar;
        private final TextView tvNickname;
        private final TextView tvCreateTime;
        private final TextView tvSex;
        private final RelativeLayout rlSearchItem;

        public ViewHolder(View itemView) {
            super(itemView);
            rlSearchItem = (RelativeLayout) itemView.findViewById(R.id.rl_search_item);
            ivAvatar = (ImageView) itemView.findViewById(R.id.iv_avatar);
            tvNickname = (TextView) itemView.findViewById(R.id.tv_nickname);
            tvCreateTime = (TextView) itemView.findViewById(R.id.tv_create_time);
            tvSex = (TextView) itemView.findViewById(R.id.tv_sex);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int postion, SearchResBean bean);
    }
}
