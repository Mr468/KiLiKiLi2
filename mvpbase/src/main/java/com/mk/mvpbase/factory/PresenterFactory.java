package com.mk.mvpbase.factory;


import com.mk.mvpbase.presenter.Presenter;

public interface PresenterFactory<P extends Presenter> {
    P createPresenter();
}
