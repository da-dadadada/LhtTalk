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

package com.lht.lhttalk.module.contact;

import com.lht.lhttalk.module.contact.bean.ContactResBean;

import java.util.ArrayList;

import individual.leobert.retrofitext.core.ApiDef;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by chhyu on 2017/7/24.
 */

@ApiDef
interface Api {

    /**
     * 刷新聊天列表
     *
     * @param pageIndex
     * @param pageSize
     * @param username
     * @param token
     * @return
     */
    @GET("imapi/contact/list")
    Call<ArrayList<ContactResBean>> contactList(@Query("pageIndex") int pageIndex,
                                                @Query("pageSize") int pageSize,
                                                @Query("vso_uname") String username,
                                                @Query("vso_token") String token);

    @POST("imapi/contact/del")
    Call<String> delateFromContact(@Query("vso_uname") String username,
                                   @Query("vso_token") String token);

    @GET("imapi/contact/add")
    Call<String> add2ContactList(@Query("vso_uname") String username,
                                 @Query("vso_token") String token);
}
