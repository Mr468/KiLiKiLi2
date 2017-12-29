package com.mk.kilikili.module.mine;

import android.os.Bundle;

import com.mk.kilikili.R;
import com.mk.kilikili.base.AppBarActivity;
import com.mk.kilikili.base.BaseActivity;

import butterknife.OnClick;

/**
 * Created by Mr_468 on 2017/3/23.
 */

public class SkinLoaderActivity extends BaseActivity {
    @Override
    public int getLayoutId() {
        return R.layout.activity_skin_loader;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {

    }
//    @Override
//    protected int getContentLayoutId() {
//        return R.layout.activity_skin_loader;
//    }
//
//    @Override
//    protected int getBarLayoutId() {
//        return R.layout.layout_appbar;
//    }
//
//    @Override
//    public void initViews(Bundle savedInstanceState) {
//        mToolbar.setTitle("换肤");
//    }
//
//    @Override
//    public void initToolBar() {
//        super.initToolBar();
//    }
//
//    @OnClick(R.id.changeSkin)
//    void changeSkin() {
//
//    }
}
