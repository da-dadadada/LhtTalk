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

package com.lht.lhttalk.util.listview;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.lht.lhttalk.adapter.AbsListAdapter;

/**
 * <p><b>Package</b> com.lht.vsocyy.util.listview
 * <p><b>Project</b> VsoCyy
 * <p><b>Classname</b> ConflictListUtil
 * <p><b>Description</b>: 用来处理嵌套的list
 * Created by leobert on 2016/7/28.
 */
public class ConflictListUtil {

    /**
     * set the height of the listView dynamically,make sure the adapter will be set to the listView
     *
     * @param listView the listView to be adjusted
     * @param adapter  the adapter you will set ,to work for the listView
     */
    public static void setDynamicHeight(ListView listView, AbsListAdapter adapter) {
        if (adapter == null) {
            return;
        }
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        int totalHeight = 0;
        for (int i = 0; i < adapter.getCount(); i++) {
            View listItem = adapter.getView(i, null, listView);
            listItem.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (adapter.getCount() - 1));
//        +listView.getPaddingTop() + listView.getPaddingBottom();
        listView.setLayoutParams(params);
    }
}
