package com.mk.kilikili;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.mk.kilikili.utils.CommonUtil;
import com.mk.skinloader.manager.SkinManager;
import com.squareup.leakcanary.LeakCanary;

/**
 * Created by Mr_468 on 2017/3/16.
 */

public class App extends Application {

    public static App mInstance;


    @Override
    public void onCreate() {

        super.onCreate();

        mInstance = this;
        init();
    }

    public static App getInstance() {
        return mInstance;
    }

    private void init() {

    }

    public boolean initLater() {
        // 初始化换肤框架
        SkinManager.getInstance().init(getApplicationContext());
        SkinManager.getInstance().load();
        //安装Leak内存泄露检测工具
        if (CommonUtil.isApkDebugable(mInstance))
            LeakCanary.install(this);
        //初始化Stetho调试工具
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                        .build());
        return true;
    }
}
