package com.mk.kilikili.base;

import android.os.Bundle;
import android.view.View;

import com.mk.kilikili.R;
import com.mk.mvpbase.presenter.Presenter;
import com.mk.mvpbase.view.MvpAppCompatActivity;
import com.mk.skinloader.i.ISkinUpdate;
import com.mk.skinloader.manager.SkinInflaterFactory;
import com.mk.skinloader.manager.SkinManager;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Mr_468 on 2017/3/16.
 */

public abstract class BaseActivity<P extends Presenter> extends MvpAppCompatActivity<P> implements ISkinUpdate {

    private Unbinder bind;

    /**
     * 皮肤变化时候是否跟着一起变
     */
    private boolean changeWithSkin = true;

    private SkinInflaterFactory mSkinFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        if (savedInstanceState == null) {
            //mSkinFactory = new SkinInflaterFactory();
            //getLayoutInflater().setFactory(mSkinFactory);
            //SkinManager.getInstance().attach(this);
            super.onCreate(savedInstanceState);
            //设置布局内容
            setContentView(getLayoutId());
            //代码添加布局
            addViewsInCode();
            //初始化黄油刀控件绑定框架
            bind = ButterKnife.bind(this);
            //初始化控件
            initViews(savedInstanceState);
        }
    }


    public abstract int getLayoutId();

    public abstract void initViews(Bundle savedInstanceState);

    public void addViewsInCode() {

    }

    public void loadData() {
    }


    public void showProgressBar() {
    }


    public void hideProgressBar() {
    }


    public void initRecyclerView() {
    }


    public void initRefreshLayout() {
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SkinManager.getInstance().detach(this);
        if (mSkinFactory != null)
            mSkinFactory.clean();
        bind.unbind();
    }

    /**
     * 设置是否随皮肤变化改变页面主题
     *
     * @param enable true改变，false不变
     */
    final protected void changeWithSkin(boolean enable) {
        changeWithSkin = enable;
    }

    /**
     * 换肤
     */
    public void onThemeUpdate() {
        if (!changeWithSkin) {
            return;
        }
        mSkinFactory.applySkin();
    }

}
