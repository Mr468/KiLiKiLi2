package com.mk.kilikili.module.home.recommend.presenter;

import android.os.Bundle;

import com.mk.kilikili.module.home.live.entity.LiveAppIndexInfo;
import com.mk.kilikili.module.home.live.view.LiveFragment;
import com.mk.kilikili.module.home.recommend.entity.RecommendInfo;
import com.mk.kilikili.module.home.recommend.view.RecommendFragment;
import com.mk.kilikili.net.CoreService;
import com.mk.kilikili.utils.manager.C;
import com.mk.mvpbase.presenter.Factory;
import com.mk.mvpbase.presenter.RxPresenter;
import com.mk.skinloader.utils.L;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.BiConsumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Mr_468 on 2017/3/28.
 */

public class RecommendPresenter extends RxPresenter<RecommendFragment> {

    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);
        initTask();
    }

    @Override
    protected void onLazyLoad() {
        start(C.RESTART1);
    }

    @Override
    protected void onRefresh() {
        start(C.RESTART2);
    }

    private void initTask() {
        restartableFirst(C.RESTART1, new Factory<Observable<List<RecommendInfo.ResultBean>>>() {
            @Override
            public Observable<List<RecommendInfo.ResultBean>> create() {
                return CoreService.getBiliAppAPI()
                        .getRecommendedInfo()
                        .subscribeOn(Schedulers.io())
                        .map(new Function<RecommendInfo, List<RecommendInfo.ResultBean>>() {
                            @Override
                            public List<RecommendInfo.ResultBean> apply(@NonNull RecommendInfo info) throws Exception {
                                return info.getResult();
                            }
                        })
                        .observeOn(AndroidSchedulers.mainThread());
            }
        }, new BiConsumer<RecommendFragment, List<RecommendInfo.ResultBean>>() {
            @Override
            public void accept(@NonNull RecommendFragment recommendFragment, @NonNull List<RecommendInfo.ResultBean> info) throws Exception {
                recommendFragment.onDataLoaded(info);
            }
        }, new BiConsumer<RecommendFragment, Throwable>() {
            @Override
            public void accept(@NonNull RecommendFragment recommendFragment, @NonNull Throwable throwable) throws Exception {
                recommendFragment.showEmptyView();
            }
        });

        restartableFirst(C.RESTART2, new Factory<Observable<List<RecommendInfo.ResultBean>>>() {
            @Override
            public Observable<List<RecommendInfo.ResultBean>> create() {
                return CoreService.getBiliAppAPI()
                        .getRecommendedInfo()
                        .subscribeOn(Schedulers.io())
                        .map(new Function<RecommendInfo, List<RecommendInfo.ResultBean>>() {
                            @Override
                            public List<RecommendInfo.ResultBean> apply(@NonNull RecommendInfo info) throws Exception {
                               return info.getResult();
                            }
                        })
                        .observeOn(AndroidSchedulers.mainThread());
            }
        }, new BiConsumer<RecommendFragment, List<RecommendInfo.ResultBean>>() {
            @Override
            public void accept(@NonNull RecommendFragment recommendFragment, @NonNull List<RecommendInfo.ResultBean> info) throws Exception {
                recommendFragment.onDataLoaded(info);
            }
        }, new BiConsumer<RecommendFragment, Throwable>() {
            @Override
            public void accept(@NonNull RecommendFragment recommendFragment, @NonNull Throwable throwable) throws Exception {
                recommendFragment.showEmptyView();
            }
        });
    }
}
