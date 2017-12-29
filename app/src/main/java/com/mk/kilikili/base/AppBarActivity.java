package com.mk.kilikili.base;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;


import com.mk.kilikili.R;
import com.mk.mvpbase.presenter.Presenter;

/**
 * Created by Mr_468 on 2017/3/18.
 */


public abstract class AppBarActivity<P extends Presenter> extends BaseActivity<P> {

    public FrameLayout appbar;

    public FrameLayout content;

    /**
     * 为了省略代码,包含appBar布局的都必须包含toolBar，且toolBar的id都是toolbar。
     */
    public Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @CallSuper
    public void initToolBar() {
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public final int getLayoutId() {
        return R.layout.activity_app_bar;
    }

    @Override
    @CallSuper
    public void addViewsInCode() {
        appbar = (FrameLayout) findViewById(R.id.app_bar);
        content = (FrameLayout) findViewById(R.id.content);
        if (appbar.getChildCount() == 0 && getBarLayoutId() != 0) {
            LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
            View barView = inflater.inflate(getBarLayoutId(), null);
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
            appbar.addView(barView, params);

            mToolbar = (Toolbar) findViewById(R.id.toolbar);
            //初始化ToolBar
            initToolBar();
        }
        if (content.getChildCount() == 0) {
            LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
            View contentView = inflater.inflate(getContentLayoutId(), null);
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
            content.addView(contentView, params);
        }
    }

    abstract protected int getBarLayoutId();

    abstract protected int getContentLayoutId();

}
