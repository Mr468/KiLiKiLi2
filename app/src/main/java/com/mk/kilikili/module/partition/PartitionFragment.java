package com.mk.kilikili.module.partition;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;

import com.mk.kilikili.R;
import com.mk.kilikili.base.BaseFragment;
import com.mk.kilikili.module.MainActivity;
import com.mk.mvpbase.factory.RequiresPresenter;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Mr_468 on 2017/3/28.
 */

@RequiresPresenter(PartitionPresenter.class)
public class PartitionFragment extends BaseFragment {

    @BindView(R.id.cl)
    CoordinatorLayout cl;

    @BindView(R.id.app_bar)
    AppBarLayout appbar;

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_partition;
    }

    @Override
    public void finishCreateView(Bundle state) {
        setUpInsets(cl, appbar);
    }

    @OnClick(R.id.iv_nav)
    void toggleDrawable() {
        ((MainActivity) getActivity()).toggleDrawer();
    }

}
