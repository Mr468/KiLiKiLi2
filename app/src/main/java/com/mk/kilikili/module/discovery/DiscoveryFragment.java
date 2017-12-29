package com.mk.kilikili.module.discovery;

import android.os.Bundle;

import com.mk.kilikili.base.BaseFragment;
import com.mk.mvpbase.factory.RequiresPresenter;

/**
 * Created by Mr_468 on 2017/3/28.
 */
@RequiresPresenter(DiscoveryPresenter.class)
public class DiscoveryFragment extends BaseFragment<DiscoveryPresenter> {
    @Override
    public int getLayoutResId() {
        return 0;
    }

    @Override
    public void finishCreateView(Bundle state) {

    }
}
