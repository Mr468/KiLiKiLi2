package com.mk.kilikili.base;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mk.mvpbase.presenter.Presenter;
import com.mk.mvpbase.view.MvpFragment;
import com.mk.skinloader.utils.L;

import butterknife.ButterKnife;
import butterknife.Unbinder;

import static android.os.Build.VERSION.SDK_INT;
import static com.mk.kilikili.R.id.cl;

/**
 * Created by Mr_468 on 2017/3/25.
 */

public abstract class BaseFragment<P extends Presenter> extends MvpFragment<P> {

    private Activity activity;

    private Unbinder bind;

    private View rootView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle state) {

        if (rootView == null) {
            rootView = inflater.inflate(getLayoutResId(), container, false);
            activity = getActivity();
        } else if (rootView.getParent() != null)
            ((ViewGroup) rootView.getParent()).removeView(rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        bind = ButterKnife.bind(this, view);
        finishCreateView(savedInstanceState);
    }

    public abstract int getLayoutResId();

    public abstract void finishCreateView(Bundle state);

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        bind.unbind();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.activity = null;
    }

    public Context getApplicationContext() {

        return this.activity == null
                ? (getActivity() == null ? null :
                getActivity().getApplicationContext())
                : this.activity.getApplicationContext();
    }

    public <T> void showDataView(T data) {
    }

    public void showEmptyView() {
    }

    protected void showProgressBar() {
    }

    protected void hideProgressBar() {
    }


    protected void initRecyclerView() {
    }


    protected void initRefreshLayout() {
    }

    public void setUpInsets(CoordinatorLayout cl, AppBarLayout appbar) {
        if (SDK_INT == Build.VERSION_CODES.KITKAT) {
            if (cl != null) {
                if (cl.getFitsSystemWindows())
                    cl.setFitsSystemWindows(false);
            }
            if (appbar != null) {
//                int statusBarHeight = 0;
//                int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
//                if (resourceId > 0) {
//                    //根据资源ID获取响应的尺寸值
//                    statusBarHeight = getResources().getDimensionPixelSize(resourceId);
//                }
                appbar.setFitsSystemWindows(true);
            }
        }
    }
}
