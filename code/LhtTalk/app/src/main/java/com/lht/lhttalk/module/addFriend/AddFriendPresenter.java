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

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.lht.lhttalk.R;
import com.lht.lhttalk.module.addFriend.bean.AddFriendResBean;
import com.lht.lhttalk.module.search.bean.SearchResBean;

import individual.leobert.retrofitext.ext.ApiResponseHandler;
import okhttp3.Headers;
import okhttp3.ResponseBody;
import retrofit2.Call;

/**
 * Created by chhyu on 2017/8/16.
 */

public class AddFriendPresenter implements AddFriendContract.Presenter {

    private AddFriendContract.View view;

    public AddFriendPresenter(AddFriendContract.View view) {
        this.view = view;
    }

    @Override
    public void start() {

    }

    @Override
    public void doAddFriend(final SearchResBean searchResBean) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(((AddFriendFragment) view).getActivity());
        View view1 = View.inflate(((AddFriendFragment) view).getActivity(), R.layout.add_friend_dialog, null);
        final EditText etAttachMsg = (EditText) view1.findViewById(R.id.et_attach_msg);
        builder.setView(view1);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                AddFriendModel model = new AddFriendModel(searchResBean.getUsername(), etAttachMsg.getText().toString());
                model.doRequestAdd(view.instance(), apiResponseHandler);
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create();
        builder.show();
    }

    ApiResponseHandler<AddFriendResBean> apiResponseHandler = new ApiResponseHandler<AddFriendResBean>() {
        @Override
        public void onSuccess(int code, Call<AddFriendResBean> call, Headers headers, AddFriendResBean res) {
            view.showMsg("请求成功，等待好友通过");
            Log.e("lmsg", "请求成功");
        }

        @Override
        public void onFailure(int code, Call<AddFriendResBean> call, Headers headers, ResponseBody res) {
            Log.e("lmsg", "请求失败");
        }

        @Override
        public void onFinish(Call<AddFriendResBean> call) {

        }
    };
}
