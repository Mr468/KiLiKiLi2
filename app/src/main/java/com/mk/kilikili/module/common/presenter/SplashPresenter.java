package com.mk.kilikili.module.common.presenter;

import com.mk.kilikili.App;
import com.mk.kilikili.module.common.view.SplashActivity;
import com.mk.kilikili.utils.LogUtil;
import com.mk.kilikili.utils.manager.C;
import com.mk.mvpbase.presenter.Factory;
import com.mk.mvpbase.presenter.RxPresenter;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiConsumer;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;


/**
 * Created by Mr_468 on 2017/3/16.
 */

public class SplashPresenter extends RxPresenter<SplashActivity> {

    @Override
    protected void onTakeView(SplashActivity activity) {
        super.onTakeView(activity);
        setUpSplash();
    }

    private void setUpSplash() {

        restartableFirst(C.RESTART1, new Factory<Observable<Boolean>>() {
            @Override
            public Observable<Boolean> create() {
                return Observable.timer(2000, TimeUnit.MILLISECONDS)
                        .map(new Function<Long, Boolean>() {
                            @Override
                            public Boolean apply(@NonNull Long aLong) throws Exception {
                                return App.getInstance().initLater();
                            }
                        });
            }
        }, new BiConsumer<SplashActivity, Boolean>() {
            @Override
            public void accept(@NonNull SplashActivity activity, @NonNull Boolean t) throws Exception {
                if (t)
                    activity.finishTask();
            }
        });

        start(C.RESTART1);
    }

}
