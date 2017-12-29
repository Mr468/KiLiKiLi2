package com.mk.kilikili.module.home.live.presenter;

import android.os.Bundle;

import com.mk.kilikili.module.home.live.entity.LiveAppIndexInfo;
import com.mk.kilikili.module.home.live.view.LiveFragment;
import com.mk.kilikili.net.CoreService;
import com.mk.kilikili.utils.manager.C;
import com.mk.mvpbase.presenter.Factory;
import com.mk.mvpbase.presenter.RxPresenter;

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

public class LivePresenter extends RxPresenter<LiveFragment> {

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
        restartableFirst(C.RESTART1, new Factory<Observable<List<Object>>>() {
            @Override
            public Observable<List<Object>> create() {
                return CoreService.getLiveAPI()
                        .getLiveAppIndex()
                        .subscribeOn(Schedulers.io())
                        .map(new Function<LiveAppIndexInfo, List<Object>>() {
                            @Override
                            public List<Object> apply(@NonNull LiveAppIndexInfo info) throws Exception {
                                List<Object> datas = new ArrayList<>();
                                if (info.getData().getBanner() != null)
                                    datas.add(info.getData().getBanner());
                                if (info.getData().getRecommend_data() != null)
                                    datas.add(info.getData().getRecommend_data());
                                if (info.getData().getPartitions() != null)
                                    datas.addAll(info.getData().getPartitions());
                                return datas;
                            }
                        })
                        .observeOn(AndroidSchedulers.mainThread());
            }
        }, new BiConsumer<LiveFragment, List<Object>>() {
            @Override
            public void accept(@NonNull LiveFragment liveFragment, @NonNull List<Object> info) throws Exception {
                liveFragment.onDataLoaded(info);
            }
        }, new BiConsumer<LiveFragment, Throwable>() {
            @Override
            public void accept(@NonNull LiveFragment liveFragment, @NonNull Throwable throwable) throws Exception {
                liveFragment.showEmptyView();
            }
        });

        restartableFirst(C.RESTART2, new Factory<Observable<List<Object>>>() {
            @Override
            public Observable<List<Object>> create() {
                return CoreService.getLiveAPI()
                        .getLiveAppIndex()
                        .subscribeOn(Schedulers.io())
                        .map(new Function<LiveAppIndexInfo, List<Object>>() {
                            @Override
                            public List<Object> apply(@NonNull LiveAppIndexInfo info) throws Exception {
                                List<Object> datas = new ArrayList<>();
                                if (info.getData().getBanner() != null)
                                    datas.add(info.getData().getBanner());
                                if (info.getData().getRecommend_data() != null)
                                    datas.add(info.getData().getRecommend_data());
                                if (info.getData().getPartitions() != null)
                                    datas.addAll(info.getData().getPartitions());
                                return datas;
                            }
                        })
                        .observeOn(AndroidSchedulers.mainThread());
            }
        }, new BiConsumer<LiveFragment, List<Object>>() {
            @Override
            public void accept(@NonNull LiveFragment liveFragment, @NonNull List<Object> info) throws Exception {
                liveFragment.onDataLoaded(info);
            }
        }, new BiConsumer<LiveFragment, Throwable>() {
            @Override
            public void accept(@NonNull LiveFragment liveFragment, @NonNull Throwable throwable) throws Exception {
                liveFragment.showEmptyView();
            }
        });
    }
}
