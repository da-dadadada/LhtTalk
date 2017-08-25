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
//package com.lht.lhttalk.module.chat.adapter;
//
//import android.content.Context;
//import android.graphics.Bitmap;
//import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
//import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
//import android.support.v4.util.CircularArray;
//import android.support.v7.widget.RecyclerView;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.bumptech.glide.Glide;
//import com.bumptech.glide.request.target.BitmapImageViewTarget;
//import com.lht.lhttalk.R;
//import com.lht.lhttalk.base.BaseLoadingAdapter;
//import com.lht.lhttalk.module.chat.bean.MsgEntity;
//
///**
// * Created by chhyu on 2017/8/22.
// */
//
//public class ChatMsgItemAdapter extends BaseLoadingAdapter {
//    private Context context;
//
//    public ChatMsgItemAdapter(Context context, RecyclerView recyclerView, CircularArray ts) {
//        super(recyclerView, ts);
//        this.context = context;
//    }
//
//    @Override
//    public RecyclerView.ViewHolder onCreateNormalViewHolder(ViewGroup parent) {
//        View view = null;
//        RecyclerView.ViewHolder holder = null;
//        if (mDataSet != null || mDataSet.size() != 0) {
//            for (int i = 0; i < mDataSet.size(); i++) {
//                MsgEntity msgEntity = (MsgEntity) mDataSet.get(i);
//                switch (msgEntity.getType()) {
//                    case MsgEntity.TYPE_FRIEND:
//                        view = View.inflate(context, R.layout.chat_msg_item_friend, null);
//                        holder = new FriendMsgViewHolder(view);
//                        break;
//                    case MsgEntity.TYPE_SELF:
//                        view = View.inflate(context, R.layout.chat_msg_item_self, null);
//                        holder = new SelfMsgViewHolder(view);
//                        break;
//                    default:
//                        break;
//                }
//            }
//        }
//        return holder;
//    }
//
//    @Override
//    public void onBindNormalViewHolder(RecyclerView.ViewHolder holder1, int position) {
//        MsgEntity msgEntity = (MsgEntity) mDataSet.get(position);
//        switch (msgEntity.getType()) {
//                case MsgEntity.TYPE_FRIEND:
//                   diplayFriendChatInfo(msgEntity,holder1);
//                    break;
//                case MsgEntity.TYPE_SELF:
//
//                    displaySelfMsgInfo(msgEntity,holder1);
//
//                    break;
//                default:
//                    break;
//            }
//        }
//
//    class FriendMsgViewHolder extends RecyclerView.ViewHolder {
//
//        private final ImageView ivAvatar;
//        private final TextView tvMsg;
//
//        public FriendMsgViewHolder(View itemView) {
//            super(itemView);
//            ivAvatar = (ImageView) itemView.findViewById(R.id.iv_avatar);
//            tvMsg = (TextView) itemView.findViewById(R.id.tv_msg);
//        }
//    }
//
//    class SelfMsgViewHolder extends RecyclerView.ViewHolder {
//
//        private final ImageView ivAvatar;
//        private final TextView tvMsg;
//
//        public SelfMsgViewHolder(View itemView) {
//            super(itemView);
//            ivAvatar = (ImageView) itemView.findViewById(R.id.iv_avatar);
//            tvMsg = (TextView) itemView.findViewById(R.id.tv_msg);
//        }
//    }
//
//    private void displaySelfMsgInfo(MsgEntity msgEntity, RecyclerView.ViewHolder holder) {
//        final SelfMsgViewHolder selfMsgViewHolder = (SelfMsgViewHolder) holder;
//        Glide.with(context).load(msgEntity.getAvatar()).asBitmap().centerCrop().into(new BitmapImageViewTarget(selfMsgViewHolder.ivAvatar) {
//            @Override
//            protected void setResource(Bitmap resource) {
//                RoundedBitmapDrawable circularBitmapDrawable =
//                        RoundedBitmapDrawableFactory.create(context.getResources(), resource);
//                circularBitmapDrawable.setCircular(true);
//                selfMsgViewHolder.ivAvatar.setImageDrawable(circularBitmapDrawable);
//            }
//        });
//        selfMsgViewHolder.tvMsg.setText(msgEntity.getMessage());
//    }
//
//    private void diplayFriendChatInfo(MsgEntity msgEntity, RecyclerView.ViewHolder holder) {
//        final FriendMsgViewHolder friendMsgViewHolder = (FriendMsgViewHolder) holder;
//        Glide.with(context).load(msgEntity.getAvatar()).asBitmap().centerCrop().into(new BitmapImageViewTarget(friendMsgViewHolder.ivAvatar) {
//            @Override
//            protected void setResource(Bitmap resource) {
//                RoundedBitmapDrawable circularBitmapDrawable =
//                        RoundedBitmapDrawableFactory.create(context.getResources(), resource);
//                circularBitmapDrawable.setCircular(true);
//                friendMsgViewHolder.ivAvatar.setImageDrawable(circularBitmapDrawable);
//            }
//        });
//        friendMsgViewHolder.tvMsg.setText(msgEntity.getMessage());
//    }
//}
