package com.mk.kilikili.module.common.view;

import android.os.Bundle;
import android.view.View;

import com.mk.kilikili.R;
import com.mk.kilikili.base.BaseActivity;
import com.mk.kilikili.module.MainActivity;
import com.mk.kilikili.module.common.presenter.SplashPresenter;
import com.mk.kilikili.utils.PreferenceUtil;
import com.mk.kilikili.utils.manager.C;
import com.mk.kilikili.utils.manager.NavigationManager;
import com.mk.mvpbase.factory.RequiresPresenter;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;

/**
 * 启动页面.
 */

@RequiresPresenter(SplashPresenter.class)
public class SplashActivity extends BaseActivity<SplashPresenter> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_FULLSCREEN |
                        View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        );
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {

    }

    public void finishTask() {

        boolean isLogin = PreferenceUtil.getBoolean(C.KEY, false);
        if (isLogin) {
            NavigationManager.overlay(this, MainActivity.class);
        } else {
            NavigationManager.overlay(this, LoginActivity.class);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }
}
