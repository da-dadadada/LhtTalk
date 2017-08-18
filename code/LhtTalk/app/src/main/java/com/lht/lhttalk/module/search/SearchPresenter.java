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

package com.lht.lhttalk.module.search;

import android.util.Log;

import com.lht.lhttalk.module.search.bean.SearchResBean;
import com.lht.lhttalk.util.string.StringUtil;

import java.util.ArrayList;

import individual.leobert.retrofitext.ext.ApiResponseHandler;
import okhttp3.Headers;
import okhttp3.ResponseBody;
import retrofit2.Call;

/**
 * Created by chhyu on 2017/7/25.
 */

public class SearchPresenter implements SearchContract.Presenter {
    private SearchContract.View view;

    public SearchPresenter(SearchContract.View view) {
        this.view = view;
    }

    @Override
    public void start() {

    }

    @Override
    public void doSearch(String text) {
        if (StringUtil.isEmpty(text)) {
            view.showMsg("搜索内容为空");
            return;
        }
        view.showWaitView(true);
        SearchModel searchModel = new SearchModel(text);
        searchModel.doSearch(view.instance(), new ApiResponseHandler<ArrayList<SearchResBean>>() {

            @Override
            public void onSuccess(int code, Call<ArrayList<SearchResBean>> call, Headers headers, ArrayList<SearchResBean> res) {
                view.showWaitView(false);
                Log.e("lmsg", "搜索成功");
                view.addSearchDatas(res);
            }

            @Override
            public void onFailure(int code, Call<ArrayList<SearchResBean>> call, Headers headers, ResponseBody res) {
                view.showWaitView(false);
                Log.e("lmsg", "搜索失败");
            }

            @Override
            public void onFinish(Call<ArrayList<SearchResBean>> call) {
                view.showWaitView(false);
            }
        });
    }
}
