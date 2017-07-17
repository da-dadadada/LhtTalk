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

package com.lht.lhttalk.module.main;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.widget.ProgressBar;

import com.lht.lhttalk.R;
import com.lht.lhttalk.base.activity.BaseActivity;
import com.lht.lhttalk.base.activity.asyncprotected.AsyncProtectedActivity;
import com.lht.lhttalk.base.fragment.BaseFragment;
import com.lht.lhttalk.base.presenter.IApiRequestPresenter;
import com.lht.lhttalk.module.contact.ContactFragment;
import com.lht.lhttalk.module.friend.FriendFragment;
import com.lht.lhttalk.module.mine.MineFragment;
import com.lht.lhttalk.module.smack.service.SmackService;

import java.util.ArrayList;
import java.util.List;

import devlight.io.library.ntb.NavigationTabBar;

public class HomeActivity extends AsyncProtectedActivity {

    public static String USER_LOGIN_INFO = "user_login_info";
    private NavigationTabBar tabBar;
    private ViewPager vpContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
        initVariable();
        initEvent();
    }

    @Override
    protected int getCustomStatusBarColor() {
        return 0;
    }

    @Override
    protected void initView() {
        tabBar = (NavigationTabBar) findViewById(R.id.home_tab);
        vpContainer = (ViewPager) findViewById(R.id.vp_horizontal_ntb);
    }

    List<BaseFragment> fragments = new ArrayList<>();
    private HomeFragmentPagerAdapter fragmentPagerAdapter;
    @Override
    protected void initVariable() {
        ContactFragment contactFragment = new ContactFragment();
        FriendFragment friendFragment = new FriendFragment();
        MineFragment mineFragment = new MineFragment();

        fragments.add(contactFragment);
        fragments.add(friendFragment);
        fragments.add(mineFragment);

        fragmentPagerAdapter = new HomeFragmentPagerAdapter(getSupportFragmentManager(),fragments);
    }

    int[] colors = {Color.RED, Color.GREEN, Color.YELLOW};

    @Override
    protected void initEvent() {
        final ArrayList<NavigationTabBar.Model> models = new ArrayList<>();
        vpContainer.setAdapter(fragmentPagerAdapter);
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.mipmap.ic_launcher), colors[0])
                        .title("Heart")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.mipmap.ic_launcher), colors[1])
                        .title("Cup")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.mipmap.ic_launcher), colors[2])
                        .title("Diploma")
                        .build()
        );
        tabBar.setModels(models);
        tabBar.setViewPager(vpContainer, 0);
        tabBar.setBehaviorEnabled(false);

        tabBar.setOnTabBarSelectedIndexListener(new NavigationTabBar.OnTabBarSelectedIndexListener() {
            @Override
            public void onStartTabSelected(final NavigationTabBar.Model model, final int index) {
            }

            @Override
            public void onEndTabSelected(final NavigationTabBar.Model model, final int index) {
                model.hideBadge();
            }
        });
        tabBar.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(final int position, final float positionOffset, final int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(final int position) {

            }

            @Override
            public void onPageScrollStateChanged(final int state) {

            }
        });

    }

    @Override
    public BaseActivity getActivity() {
        return HomeActivity.this;
    }

    @Override
    protected IApiRequestPresenter getApiRequestPresenter() {
        return null;
    }

    @Override
    public ProgressBar getProgressBar() {
        return null;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Intent intent = new Intent(this, SmackService.class);
        stopService(intent);
    }
}
