package com.mk.kilikili.module.home;

import com.mk.kilikili.utils.ToastUtil;
import com.mk.mvpbase.presenter.RxPresenter;

/**
 * Created by Mr_468 on 2017/3/26.
 */

public class HomePresenter extends RxPresenter<HomeFragment> {
    @Override
    protected void onLazyLoad() {
        ToastUtil.ShortToast(HomePresenter.class.getSimpleName());
    }

    private void loadData() {

    }

}
