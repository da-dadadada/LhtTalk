

package com.lht.lhttalk.module.friend.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v4.util.CircularArray;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.lht.lhttalk.R;
import com.lht.lhttalk.base.BaseLoadingAdapter;
import com.lht.lhttalk.module.friend.pojo.FriendInfoResBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gjz on 9/4/16.
 */
public class FriendListAdapter extends RecyclerView.Adapter<FriendListAdapter.MyViewHolder> {

    private Context context;
    private OnItemClickListener listener;
    private ArrayList<FriendInfoResBean> datas;

    public FriendListAdapter(Context context, OnItemClickListener listener, RecyclerView recyclerView, ArrayList<FriendInfoResBean> datas) {
        this.context = context;
        this.datas = datas;
        this.listener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.friendlist_item, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final FriendInfoResBean bean = datas.get(position);

        if (position == 0 || !datas.get(position - 1).getFristLetter().equals(bean.getFristLetter())) {
            holder.tvIndex.setVisibility(View.VISIBLE);
            holder.tvIndex.setText(bean.getFristLetter());
        } else {
            holder.tvIndex.setVisibility(View.GONE);
        }
        holder.tvUsername.setText(bean.getUsername());
        holder.tvNickname.setText(bean.getNickname());
        Glide.with(context).load(bean.getAvatar()).asBitmap().centerCrop().into(new BitmapImageViewTarget(holder.ivAvatar) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                holder.ivAvatar.setImageDrawable(circularBitmapDrawable);
            }
        });

        holder.rlRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onItemClick(holder.rlRoot, position, bean);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    public void addDatas(ArrayList<FriendInfoResBean> _datas) {
        if (datas == null)
            datas = new ArrayList<>();
        if (_datas == null)
            return;
        for (FriendInfoResBean bean : _datas) {
            datas.add(bean);
        }
        notifyDataSetChanged();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvIndex;
        public TextView tvUsername;
        public ImageView ivAvatar;
        public TextView tvNickname;
        private final RelativeLayout rlRoot;

        public MyViewHolder(View itemView) {
            super(itemView);
            rlRoot = (RelativeLayout) itemView.findViewById(R.id.rl_root);
            tvIndex = (TextView) itemView.findViewById(R.id.tv_index);
            tvUsername = (TextView) itemView.findViewById(R.id.tv_username);
            ivAvatar = (ImageView) itemView.findViewById(R.id.iv_avatar);
            tvNickname = (TextView) itemView.findViewById(R.id.tv_nickname);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int postion, FriendInfoResBean bean);
    }
}