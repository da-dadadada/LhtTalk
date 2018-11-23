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

package com.lht.lhttalk.module.InvitationList.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v4.util.CircularArray;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.lht.lhttalk.R;
import com.lht.lhttalk.base.BaseLoadingAdapter;
import com.lht.lhttalk.module.InvitationList.bean.InvitationListResBean;

import java.util.ArrayList;

/**
 * Created by chhyu on 2017/8/17.
 */

public class InvitationListAdapter extends RecyclerView.Adapter {

    private static final int TYPE_IN = 1;
    private static final int TYPE_OUT = 2;
    private Context context;
    private ArrayList<InvitationListResBean> datas;
    private OnHandleButtonClickListener listener;

    public InvitationListAdapter(Context context, OnHandleButtonClickListener listener, ArrayList<InvitationListResBean> datas) {
        this.context = context;
        this.datas = datas;
        this.listener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        RecyclerView.ViewHolder holder = null;
        switch (viewType) {
            case TYPE_IN:
                view = View.inflate(context, R.layout.friend_request_in_item, null);
                holder = new InViewHolder(view);
                break;
            case TYPE_OUT:
                view = View.inflate(context, R.layout.friend_request_out_item, null);
                holder = new OutViewHolder(view);
                break;
            default:
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final InvitationListResBean bean = datas.get(position);
        int itemViewType = getItemViewType(position);
        Log.e("lmsg", "state=" + bean.getState());
        switch (itemViewType) {
            case TYPE_IN:
                handleTypeInRequest(holder, bean, position);
                break;
            case TYPE_OUT:
                handleTypeOutRequest(holder, bean);
                break;
            default:
                break;
        }

    }

    /**
     * 请求添加别人为好友
     *
     * @param holder1
     * @param bean
     */
    private void handleTypeOutRequest(final RecyclerView.ViewHolder holder1, InvitationListResBean bean) {
        final OutViewHolder holder = (OutViewHolder) holder1;
        Glide.with(context).load(bean.getAvatar()).asBitmap().centerCrop().into(new BitmapImageViewTarget(holder.ivAvatar) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable = RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                holder.ivAvatar.setImageDrawable(circularBitmapDrawable);
            }
        });
        holder.tvUsername.setText(bean.getUsername());
        holder.tvAttachMsg.setText(bean.getAttach());
    }

    /**
     * 别人请求添加为好友
     *
     * @param holder1
     * @param bean
     * @param position
     */
    private void handleTypeInRequest(RecyclerView.ViewHolder holder1, InvitationListResBean bean, int position) {
        final InViewHolder holder = (InViewHolder) holder1;
        Glide.with(context).load(bean.getAvatar()).asBitmap().centerCrop().into(new BitmapImageViewTarget(holder.ivAvatar) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable = RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                holder.ivAvatar.setImageDrawable(circularBitmapDrawable);
            }
        });
        holder.tvUsername.setText(bean.getUsername());
        holder.tvAttachMsg.setText(bean.getAttach());

        if (bean.getState().equals(InvitationListResBean.ACTION_ACCEPT)) {
            holder.btnAccept.setEnabled(false);
            holder.btnAccept.setText("已同意");
            holder.btnIgnore.setEnabled(false);
            holder.btnRefuse.setEnabled(false);
        }
        if (bean.getState().equals(InvitationListResBean.ACTION_IGNORE)) {
            holder.btnAccept.setEnabled(false);
            holder.btnIgnore.setText("已忽略");
            holder.btnIgnore.setEnabled(false);
            holder.btnRefuse.setEnabled(false);
        }
        if (bean.getState().equals(InvitationListResBean.ACTION_REFUSE)) {
            holder.btnAccept.setEnabled(false);
            holder.btnRefuse.setText("已拒绝");
            holder.btnIgnore.setEnabled(false);
            holder.btnRefuse.setEnabled(false);
        }
        handleClickResult(holder, bean, position);
    }

    @Override
    public int getItemViewType(int position) {
        String type = datas.get(position).getType();
        if (type.equals("in")) {
            return TYPE_IN;
        } else {
            return TYPE_OUT;
        }
    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    public void addDatas(ArrayList<InvitationListResBean> _datas) {
        if (datas == null)
            datas = new ArrayList<>();
        if (_datas == null)
            return;
        for (InvitationListResBean bean : _datas) {
            datas.add(bean);
        }
        notifyDataSetChanged();
    }

    private void handleClickResult(final InViewHolder holder, final InvitationListResBean bean, final int position) {
        holder.btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onHandleButtonClick(holder.btnAccept, holder, position, bean);
                }
            }
        });
        holder.btnIgnore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onHandleButtonClick(holder.btnIgnore, holder, position, bean);
                }
            }
        });
        holder.btnRefuse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onHandleButtonClick(holder.btnRefuse, holder, position, bean);
                }
            }
        });
    }

    /**
     * 别人请求加好友
     */
    public class InViewHolder extends RecyclerView.ViewHolder {

        private final ImageView ivAvatar;
        private final TextView tvUsername;
        private final TextView tvAttachMsg;
        private final Button btnAccept;
        private final Button btnIgnore;
        private final Button btnRefuse;

        public InViewHolder(View itemView) {
            super(itemView);
            ivAvatar = (ImageView) itemView.findViewById(R.id.iv_avatar);
            tvUsername = (TextView) itemView.findViewById(R.id.tv_username);
            tvAttachMsg = (TextView) itemView.findViewById(R.id.tv_attach_msg);
            btnAccept = (Button) itemView.findViewById(R.id.btn_accept);
            btnIgnore = (Button) itemView.findViewById(R.id.btn_ignore);
            btnRefuse = (Button) itemView.findViewById(R.id.btn_refuse);

        }

        public Button getBtnAccept() {
            return btnAccept;
        }

        public Button getBtnIgnore() {
            return btnIgnore;
        }

        public Button getBtnRefuse() {
            return btnRefuse;
        }
    }

    /**
     * 请求添加别人为好友
     */
    public class OutViewHolder extends RecyclerView.ViewHolder {

        private final ImageView ivAvatar;
        private final TextView tvUsername;
        private final TextView tvAttachMsg;

        public OutViewHolder(View itemView) {
            super(itemView);
            ivAvatar = (ImageView) itemView.findViewById(R.id.iv_avatar);
            tvUsername = (TextView) itemView.findViewById(R.id.tv_username);
            tvAttachMsg = (TextView) itemView.findViewById(R.id.tv_attach_msg);

        }
    }

    public interface OnHandleButtonClickListener {
        void onHandleButtonClick(View view, InViewHolder holder, int position, InvitationListResBean bean);
    }
}
