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

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.lht.lhttalk.R;
import com.lht.lhttalk.adapter.AbsListAdapter;
import com.lht.lhttalk.adapter.ListAdapter;
import com.lht.lhttalk.adapter.viewprovider.ProjectTypeProviderImpl;
import com.lht.lhttalk.module.proj.model.pojo.ProjectTypeResBean;
import com.lht.lhttalk.util.debug.DLog;

import java.util.ArrayList;

/**
 * 筛选弹出窗
 * Created by chhyu on 2017/3/2.
 */

public class PWForProjectTypeChoose extends CustomPopupWindow implements
        AbsListAdapter.ICustomizeListItem<ProjectTypeResBean,
                ProjectTypeProviderImpl.ViewHolder> {

    private View contentView;
    private ListView lvAllType;

    public PWForProjectTypeChoose(IPopupHolder iPopupHolder) {
        super(iPopupHolder);
    }

    private ListAdapter<ProjectTypeResBean> adapter;

    private ProjectTypeProviderImpl itemViewProvider;

    @Override
    protected void init() {
        super.doDefaultSetting();
        setyOffset(100);
        LayoutInflater inflater = LayoutInflater.from(mActivity);
        contentView = inflater.inflate(R.layout.view_project_type, null);
        setContentView(contentView);

        lvAllType = (ListView) contentView.findViewById(R.id.lv_all_type);
        itemViewProvider = new ProjectTypeProviderImpl(inflater, this);
        adapter = new ListAdapter<>(new ArrayList<ProjectTypeResBean>(), itemViewProvider);
        lvAllType.setAdapter(adapter);

        contentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        ColorDrawable dw = new ColorDrawable(getCustomBackgroundDrawableColor());
        this.setBackgroundDrawable(dw);
    }

    @Override
    protected int getCustomBackgroundDrawableColor() {
        return 0x000000;
    }

    public void updateData(ArrayList<ProjectTypeResBean> datas) {
        if (datas == null) {
            DLog.e(getClass(), new DLog.LogLocation(), "data is null");
        }
        adapter.setLiData(datas);
    }

    @Override
    protected void onShow() {
        super.onShow();
        contentView.setBackgroundColor(Color.argb(90, 0, 0, 0));
        lvAllType.startAnimation(dropdownAnimation(300));
    }

    @Override
    public void dismiss() {
        contentView.setBackgroundColor(Color.argb(0, 0, 0, 0));
        super.dismiss();
    }

//    private final void superDismiss() {
//        super.dismiss();
//    }

    @Override
    protected int getMyAnim() {
        return 0;
//        return R.style.CustomIn_SlideOut;
    }

    @Override
    protected int getMyWidth() {
        return mActivity.getWindowManager().getDefaultDisplay()
                .getWidth();
    }

    @Override
    protected int getMyHeight() {
        return ViewGroup.LayoutParams.MATCH_PARENT;
    }


    @Override
    public void customize(final int position, final ProjectTypeResBean data, View convertView,
                          ProjectTypeProviderImpl.ViewHolder viewHolder) {

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemViewProvider.notifySelected(position);
                adapter.notifyDataSetChanged();
                if (onListItemClickListener != null)
                    onListItemClickListener.onListItemClick(position, data);
            }
        });
    }

    private OnListItemClickListener onListItemClickListener;

    public void setOnListItemClickListener(OnListItemClickListener onListItemClickListener) {
        this.onListItemClickListener = onListItemClickListener;
    }

    public interface OnListItemClickListener {
        void onListItemClick(int position, ProjectTypeResBean data);
    }
}
