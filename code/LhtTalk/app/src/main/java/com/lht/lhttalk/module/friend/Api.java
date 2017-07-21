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

package com.lht.lhttalk.module.friend;

import com.lht.lhttalk.module.friend.pojo.FriendBasicPojo;
import com.lht.lhttalk.module.friend.pojo.FriendList;

import java.util.Map;

import individual.leobert.retrofitext.core.ApiDef;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * <p><b>Package:</b> com.lht.lhttalk.module.friend </p>
 * <p><b>Project:</b> LhtTalk </p>
 * <p><b>Classname:</b> Api </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/7/21.
 */

@ApiDef
interface Api {
    @GET("imapi/friend")
    Call<FriendList> listAll(@Query("vso_uname") String name,
                             @Query("vso_token") String token);

    @POST("imapi/friend/del")
    Call<String> delete(@Field("target") String target,
                        @Field("relieve") boolean relieve);
}
