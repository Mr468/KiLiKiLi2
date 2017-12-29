package com.mk.kilikili.module.home.bangumi.view;

import android.os.Bundle;

import com.mk.kilikili.R;
import com.mk.kilikili.base.BaseFragment;
import com.mk.kilikili.module.home.bangumi.presenter.BangumiPresenter;
import com.mk.mvpbase.factory.RequiresPresenter;

/**
 * Created by Mr_468 on 2017/3/28.
 */

@RequiresPresenter(BangumiPresenter.class)
public class BangumiFragment extends BaseFragment<BangumiPresenter> {
    @Override
    public int getLayoutResId() {
        return R.layout.fragment_bangumi;
    }

    @Override
    public void finishCreateView(Bundle state) {

    }

    public static BangumiFragment newInstance(){
        return new BangumiFragment();
    }
}
