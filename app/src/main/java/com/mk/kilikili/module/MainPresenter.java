package com.mk.kilikili.module;

import android.os.Bundle;

import com.mk.kilikili.module.MainActivity;
import com.mk.kilikili.utils.manager.C;
import com.mk.mvpbase.presenter.Factory;
import com.mk.mvpbase.presenter.RxPresenter;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiConsumer;
import io.reactivex.functions.Function;

/**
 * Created by Mr_468 on 2017/3/16.
 */

public class MainPresenter extends RxPresenter<MainActivity> {
    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);
        initFragment();
    }
    private void initFragment(){

    }
}
