package com.mk.mvpbase.view;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;

import com.mk.mvpbase.factory.PresenterFactory;
import com.mk.mvpbase.factory.PresenterLifecycleDelegate;
import com.mk.mvpbase.factory.ReflectionPresenterFactory;
import com.mk.mvpbase.presenter.Presenter;
import com.trello.rxlifecycle2.components.support.RxFragment;

/**
 * This view is an example of how a view should control it's presenter.
 * You can inherit from this class or copy/paste this class's code to
 * create your own view implementation.
 *
 * @param <P> a type of presenter to return with {@link #getPresenter}.
 */
public abstract class MvpFragment<P extends Presenter> extends RxFragment implements ViewWithPresenter<P> {

    private static final String PRESENTER_STATE_KEY = "fragment_presenter_state";

    //标志位 fragment是否可见
    private boolean isVisible;

    private boolean isViewCreated;

    private boolean hasDataLoaded;

    // 在ViewPager中,虽然Fragment被destroy了,再是实例似乎并没有被销毁,重新重新创建的时候并不会初始化这里的参数,而是
    // 仍然保留成员变量的值
    private PresenterLifecycleDelegate<P> presenterDelegate =
            new PresenterLifecycleDelegate<>(ReflectionPresenterFactory.<P>fromViewClass(getClass()));

    /**
     * Returns a current presenter factory.
     */
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
    public P getPresenter() {
        return presenterDelegate.getPresenter();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null && getPresenter() == null)
            presenterDelegate.onRestoreInstanceState(savedInstanceState.getBundle(PRESENTER_STATE_KEY));
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBundle(PRESENTER_STATE_KEY, presenterDelegate.onSaveInstanceState());
    }

    @Override
    public void onResume() {
        super.onResume();
        presenterDelegate.onResume(this);
        isViewCreated = true;
        if (!hasDataLoaded && isVisible && isViewCreated) {
            lazyLoad();
        }
    }

    @CallSuper
    public <T> void onDataLoaded(T data) {
        hasDataLoaded = true;
    }

    /**
     * Viewpager + fragment 懒加载
     */
    private void lazyLoad() {
        onPreLazyLoad();
        getPresenter().lazyLoad();
    }

    protected void onPreLazyLoad() {
    }

    /**
     * Viewpager + Fragment数据的懒加载,普通Fragment不会走这个方法
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        isVisible = getUserVisibleHint();
        if (!hasDataLoaded && isVisible && isViewCreated)
            lazyLoad();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenterDelegate.onDestroy(false);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenterDelegate.onDestroy(getActivity().isFinishing());
    }
}
