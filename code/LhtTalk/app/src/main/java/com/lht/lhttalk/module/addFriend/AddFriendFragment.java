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

package com.lht.lhttalk.module.addFriend;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.lht.lhttalk.R;
import com.lht.lhttalk.base.fragment.BaseFragment;
import com.lht.lhttalk.module.search.bean.SearchResBean;
import com.lht.lhttalk.util.time.TimeUtil;

import java.text.SimpleDateFormat;

/**
 * Created by chhyu on 2017/8/16.
 */

public class AddFriendFragment extends BaseFragment implements AddFriendContract.View {

    private SearchResBean searchResBean;
    private ImageView ivAvatar;
    private TextView tvAccount;
    private TextView tvUsername;
    private TextView tvNickname;
    private TextView tvCreateTime;
    private TextView tvEmail;
    private TextView tvSex;
    private Button btnAddFriend;
    private AddFriendContract.Presenter presenter;

    public static AddFriendFragment newInstance() {
        AddFriendFragment addFriendFragment = new AddFriendFragment();
        AddFriendPresenter presenter = new AddFriendPresenter(addFriendFragment);
        addFriendFragment.setPresenter(presenter);
        return addFriendFragment;
    }

    public void setDatas(SearchResBean searchResBean) {
        this.searchResBean = searchResBean;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fg_add_friend, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initVariable();
        initEvent();
    }

    @Override
    protected void initView(View contentView) {
        ivAvatar = (ImageView) contentView.findViewById(R.id.iv_avatar);
        tvNickname = (TextView) contentView.findViewById(R.id.tv_nickname);
        tvUsername = (TextView) contentView.findViewById(R.id.tv_username);
        tvAccount = (TextView) contentView.findViewById(R.id.tv_account);
        tvSex = (TextView) contentView.findViewById(R.id.tv_sex);
        tvEmail = (TextView) contentView.findViewById(R.id.tv_email);
        tvCreateTime = (TextView) contentView.findViewById(R.id.tv_create_time);

        btnAddFriend = (Button) contentView.findViewById(R.id.btn_add_friend);
    }

    @Override
    protected void initVariable() {

    }

    @Override
    protected void initEvent() {
        Glide.with(getActivity()).load(searchResBean.getAvatar()).asBitmap().centerCrop().into(new BitmapImageViewTarget(ivAvatar) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                ivAvatar.setImageDrawable(circularBitmapDrawable);
            }
        });
        tvNickname.setText(searchResBean.getNickname());
        tvAccount.setText("账号： " + searchResBean.getMix_name());
        tvUsername.setText("用户名：" + searchResBean.getUsername());
        tvEmail.setText("Email: " + searchResBean.getEmail());
        long l = Long.parseLong(searchResBean.getCreate_time());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = TimeUtil.getTime(l, format);
        tvCreateTime.setText("账号创建时间： " + time);
        String sex = searchResBean.getSex();
        if (sex.equals("true")) {
            tvSex.setText("性别：男");
        } else {
            tvSex.setText("性别：女");
        }

        btnAddFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.doAddFriend(searchResBean);
            }
        });
    }


    @Override
    public void setPresenter(AddFriendContract.Presenter presenter) {
        this.presenter = presenter;
        presenter.start();
    }

    @Override
    protected String getPageName() {
        return null;
    }

    @Override
    public AddFriendFragment instance() {
        return AddFriendFragment.this;
    }
}
