package com.mk.kilikili.module.home;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.mk.kilikili.R;
import com.mk.kilikili.base.BaseFragment;
import com.mk.kilikili.module.MainActivity;
import com.mk.mvpbase.factory.RequiresPresenter;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Mr_468 on 2017/3/25.
 */


@RequiresPresenter(HomePresenter.class)
public class HomeFragment extends BaseFragment<HomePresenter> {

    @BindView(R.id.cl)
    CoordinatorLayout cl;

    @BindView(R.id.app_bar)
    AppBarLayout appbar;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.tab)
    TabLayout tab;

    @BindView(R.id.view_pager)
    ViewPager viewPager;

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_home;
    }

    @Override
    public void finishCreateView(Bundle state) {
        initViewpager();
        setUpInsets(cl, appbar);
    }

    private void initViewpager() {
        HomePagerAdapter adapter = new HomePagerAdapter(getChildFragmentManager(), getApplicationContext());
        viewPager.setAdapter(adapter);
        tab.setupWithViewPager(viewPager);
    }

    @OnClick(R.id.iv_nav)
    void toggleDrawable() {
        ((MainActivity) getActivity()).toggleDrawer();
    }

}
