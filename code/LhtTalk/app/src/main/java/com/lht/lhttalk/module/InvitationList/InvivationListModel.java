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

package com.lht.lhttalk.module.InvitationList;

import android.support.v4.app.Fragment;

import com.lht.lhttalk.base.IVerifyHolder;
import com.lht.lhttalk.module.api.ApiClient;
import com.lht.lhttalk.module.InvitationList.bean.InvitationListResBean;

import java.util.ArrayList;

import individual.leobert.retrofitext.RetrofitExt;
import individual.leobert.retrofitext.ext.ApiResponseHandler;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by chhyu on 2017/8/18.
 */

public class InvivationListModel {


    private Api stringTypeApi = ApiClient.getStringApiClient().apiInstance(Api.class);
    private Api jsonTypeApi = ApiClient.getJsonApiClient().apiInstance(Api.class);
    /**
     * @param fragment
     * @param apiResponseHandler
     */
    public void getInvitationsList(Fragment fragment, ApiResponseHandler<ArrayList<InvitationListResBean>> apiResponseHandler) {
        Call<ArrayList<InvitationListResBean>> call = jsonTypeApi.doGetInvitationsList(IVerifyHolder.mUserBean.getUsername(),
                IVerifyHolder.mUserBean.getToken());
        RetrofitExt.lifeCycledWithFragmentV4(fragment, call, apiResponseHandler);
    }

    /**
     * @param fragment
     * @param postTime           该条邀请生成的时间戳
     * @param target             目标username
     * @param action             处理的action ,accept,ignore,refuse
     * @param apiResponseHandler
     */
    public void handleFriendInvitation(Fragment fragment,
                                       long postTime,
                                       String target,
                                       String action,
                                       ApiResponseHandler<String> apiResponseHandler) {
        Call<String> call =
                stringTypeApi.doHandleInvitation(
                        postTime,
                        target,
                        action,
                        IVerifyHolder.mUserBean.getUsername(),
                        IVerifyHolder.mUserBean.getToken());

        RetrofitExt.lifeCycledWithFragmentV4(fragment, call, apiResponseHandler);
    }
}
