package com.mk.kilikili.module;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.MenuItem;

import com.mk.kilikili.R;
import com.mk.kilikili.base.BaseActivity;
import com.mk.kilikili.base.FragmentFactory;
import com.mk.kilikili.module.home.HomeFragment;
import com.mk.kilikili.module.partition.PartitionFragment;
import com.mk.kilikili.utils.manager.C;
import com.mk.mvpbase.factory.RequiresPresenter;

import butterknife.BindView;

import static android.os.Build.VERSION.SDK_INT;

@RequiresPresenter(MainPresenter.class)
public class MainActivity extends BaseActivity<MainPresenter> implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawLayout;

    @BindView(R.id.nav_view)
    NavigationView nav_view;

    public Fragment[] fragments;

    private int currentIndex;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {

        initNavigationView();
        initFragment();
        setUpInsets();
    }

    private void setUpInsets() {
        //4.4设置fitSystemWindows为false,以免statusBar显示透明。
        if (SDK_INT == Build.VERSION_CODES.KITKAT) {
            mDrawLayout.setFitsSystemWindows(false);
            getWindow().getDecorView().requestLayout();
        }
    }

    private void initNavigationView() {
        //获取NavigationMenuView,隐藏掉进度条
        nav_view.getChildAt(0).setVerticalScrollBarEnabled(false);
        nav_view.setNavigationItemSelectedListener(this);
    }

    private void initFragment() {
        fragments = new Fragment[C.MAIN_FRAGMENT.size()];
        changeFragment(C.MAIN_FRAGMENT.indexOf(HomeFragment.class));
        nav_view.getMenu().findItem(R.id.item_home).setChecked(true);

    }

    /**
     * 切换fragment
     *
     * @param index fragment索引
     */
    private void changeFragment(int index) {

        if (fragments[index] == null) {
            fragments[index] = FragmentFactory.createFragment(C.MAIN_FRAGMENT.get(index));
        }

        FragmentTransaction fm = getSupportFragmentManager().beginTransaction();

        if (!fragments[index].isAdded())
            fm.add(R.id.content_container, fragments[index], fragments[index].getClass().getSimpleName());

        fm.detach(fragments[currentIndex]);
        fm.attach(fragments[index]);
        fm.commit();

        currentIndex = index;

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        toggleDrawer();
        switch (item.getItemId()) {
            case R.id.item_home:
                changeFragment(C.MAIN_FRAGMENT.indexOf(HomeFragment.class));
                return true;
            case R.id.item_partition:
                changeFragment(C.MAIN_FRAGMENT.indexOf(PartitionFragment.class));
                return true;
//            case R.id.item_trends:
//                changeFragment(Constants.MAIN_FRAGMENT.indexOf(TrendsFragment.class));
//                return true;
//            case R.id.item_discovery:
//                changeFragment(Constants.MAIN_FRAGMENT.indexOf(DiscoveryFragment.class));
//                return true;
//            case R.id.item_mine:
//                changeFragment(Constants.MAIN_FRAGMENT.indexOf(MineFragment.class));
//                return true;
        }
        return false;
    }

    public void toggleDrawer() {
        if (mDrawLayout.isDrawerOpen(Gravity.START))
            mDrawLayout.closeDrawer(Gravity.START);
        else
            mDrawLayout.openDrawer(Gravity.START);
    }
}

