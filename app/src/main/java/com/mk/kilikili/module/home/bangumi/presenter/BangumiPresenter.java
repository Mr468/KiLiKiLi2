package com.mk.kilikili.module.home.bangumi.presenter;

import com.mk.kilikili.module.home.bangumi.view.BangumiFragment;
import com.mk.mvpbase.presenter.RxPresenter;
import com.mk.skinloader.utils.L;

/**
 * Created by Mr_468 on 2017/3/28.
 */

public class BangumiPresenter extends RxPresenter<BangumiFragment> {
    @Override
    protected void onLazyLoad() {
        L.d(BangumiPresenter.class.getSimpleName());
    }
}

