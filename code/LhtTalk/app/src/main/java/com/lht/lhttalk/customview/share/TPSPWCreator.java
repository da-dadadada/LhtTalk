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

package com.lht.lhttalk.customview.share;

import android.view.Gravity;

import com.lht.lhttalk.R;
import com.lht.lhttalk.customview.popup.IPopupHolder;

import java.util.ArrayList;


/**
 * <p><b>Package</b> com.lht.lhttalk.customview
 * <p><b>Project</b> czspace
 * <p><b>Classname</b> TPSPWCreator
 * <p><b>Description</b>: 三方分享弹窗创建器
 * <p>
 * Created by leobert on 2017/3/8.
 */
public class TPSPWCreator {

    public static ThirdPartySharePopWins create(IPopupHolder iPopupHolder,
                                                ThirdPartySharePopWins.ShareData shareData) {
        ThirdPartySharePopWins wins = new ThirdPartySharePopWins(iPopupHolder,shareData);

        ArrayList<ThirdPartyShareViewItem> items = new ArrayList<>();
        String[] mTextIds = {"新浪微博", "QQ", "微信", "QQ空间", "朋友圈", "复制链接"};
        Integer[] mThumbIds = {R.drawable.share_plateform_sina, R.drawable.share_plateform_qq,
                R.drawable.share_plateform_wechat, R.drawable.share_plateform_qqzone,
                R.drawable.share_plateform_wechat_fc, R.drawable.share_plateform_copy};
        for (int i = 0; i < mTextIds.length; i++) {
            ThirdPartyShareViewItem item = new ThirdPartyShareViewItem();
            item.setName(mTextIds[i]);
            item.setDrawableRes(mThumbIds[i]);
            items.add(item);
        }

        wins.setItems(items);

        wins.setGravity(Gravity.BOTTOM);

        wins.setOnThirdPartyShareItemClickListener(new ThirdPartyShareHandler(
                iPopupHolder.getHolderActivity()));
        wins.setOutsideClickDismiss();

        return wins;
    }


}
