package com.mk.mvpbase.view;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.mk.mvpbase.factory.PresenterFactory;
import com.mk.mvpbase.factory.PresenterLifecycleDelegate;
import com.mk.mvpbase.factory.ReflectionPresenterFactory;
import com.mk.mvpbase.factory.RequiresPresenter;
import com.mk.mvpbase.presenter.Presenter;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

/**
 * Created by Mr_468 on 2017/3/15.
 */
@RequiresPresenter(Presenter.class)
public class MvpAppCompatActivity<P extends Presenter> extends RxAppCompatActivity implements ViewWithPresenter<P> {

    private static final String PRESENTER_STATE_KEY = "activity_presenter_state";

    private PresenterLifecycleDelegate<P> presenterDelegate =
            new PresenterLifecycleDelegate<>(ReflectionPresenterFactory.<P>fromViewClass(getClass()));

    /**
     * Returns a current presenter factory.
     */
    @Override
    public PresenterFactory<P> getPresenterFactory() {
        return presenterDelegate.getPresenterFactory();
    }

    /**
     * Sets a presenter factory.
     * Call this method before onCreate/onFinishInflate to override default {@link ReflectionPresenterFactory} presenter factory.
     * Use this method for presenter dependency injection.
     */
    @Override
    public void setPresenterFactory(PresenterFactory<P> presenterFactory) {
        presenterDelegate.setPresenterFactory(presenterFactory);
    }

    /**
     * Returns a current attached presenter.
     * This method is guaranteed to return a non-null value between
     * onResume/onPause and onAttachedToWindow/onDetachedFromWindow calls
     * if the presenter factory returns a non-null value.
     *
     * @return a currently attached presenter or null.
     */
    @Override
    public P getPresenter() {
        return presenterDelegate.getPresenter();
    }


    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        presenterDelegate.onRestoreInstanceState(savedInstanceState.getBundle(PRESENTER_STATE_KEY));
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBundle(PRESENTER_STATE_KEY, presenterDelegate.onSaveInstanceState());
    }

    @Override
    protected void onResume() {
        super.onResume();
        //如果在之前生命周期方法中没有创建presenter，这里会创建presenter并绑定view到presenter
        presenterDelegate.onResume(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenterDelegate.onDestroy(true);
    }

}
