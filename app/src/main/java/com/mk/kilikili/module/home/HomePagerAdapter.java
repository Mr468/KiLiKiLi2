package com.mk.kilikili.module.home;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.mk.kilikili.R;
import com.mk.kilikili.base.FragmentFactory;
import com.mk.kilikili.module.home.bangumi.view.BangumiFragment;
import com.mk.kilikili.module.home.live.view.LiveFragment;
import com.mk.kilikili.module.home.recommend.view.RecommendFragment;

/**
 * 主界面Fragment模块Adapter
 */
public class HomePagerAdapter extends FragmentPagerAdapter {

    private final String[] TITLES;

    private Fragment[] fragments;


    public HomePagerAdapter(FragmentManager fm, Context context) {

        super(fm);
        TITLES = context.getResources().getStringArray(R.array.sections);
        fragments = new Fragment[TITLES.length];
    }


    @Override
    public Fragment getItem(int position) {

        if (fragments[position] == null) {
            switch (position) {
                case 0:
                    fragments[position] = FragmentFactory.createFragment(LiveFragment.class);
                    break;
                case 1:
                    fragments[position] = FragmentFactory.createFragment(RecommendFragment.class);
                    break;
                case 2:
                    fragments[position] = FragmentFactory.createFragment(BangumiFragment.class);
                    break;
                default:
                    break;
            }
        }
        return fragments[position];
    }


    @Override
    public int getCount() {

        return TITLES.length;
    }


    @Override
    public CharSequence getPageTitle(int position) {

        return TITLES[position];
    }
}
