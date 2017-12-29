package com.mk.kilikili.utils.manager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 管理页面跳转类.
 */

public class NavigationManager {

    private static final String PARCELABLE_EXTRA_KEY = "parcel_extra_key";

    private static final ArrayList<Activity> tobeFinishAct = new ArrayList<>();

    //==========逻辑方法==========
    public static <T> T getParcelableExtra(Activity activity) {
        Parcelable parcelable = activity.getIntent().getParcelableExtra(NavigationManager.PARCELABLE_EXTRA_KEY);
        activity = null;
        return (T)parcelable;
    }

    public static void overlay(Context context, Class<? extends Activity> targetClazz, int flags, Parcelable parcelable) {
        Intent intent = new Intent(context, targetClazz);
        setFlags(intent, flags);
        putParcelableExtra(intent, parcelable);
        context.startActivity(intent);
    }

    public static void overlay(Context context, Class<? extends Activity> targetClazz, Parcelable parcelable) {
        Intent intent = new Intent(context, targetClazz);
        putParcelableExtra(intent, parcelable);
        context.startActivity(intent);
    }

    public static void overlay(Context context, Class<? extends Activity> targetClazz, Serializable serializable) {
        Intent intent = new Intent(context, targetClazz);
        putSerializableExtra(intent, serializable);
        context.startActivity(intent);
    }

    public static void overlay(Context context, Class<? extends Activity> targetClazz) {
        Intent intent = new Intent(context, targetClazz);
        context.startActivity(intent);
    }

    public static void startForResult(Context context, Class<? extends Activity> targetClazz, int flags) {
        Intent intent = new Intent(context, targetClazz);
        if (notActivity(context)) return;
        ((Activity)context).startActivityForResult(intent, flags);
    }

    public static void startForResult(Context context, Class<? extends Activity> targetClazz, int flags, Parcelable parcelable) {
        Intent intent = new Intent(context, targetClazz);
        if (notActivity(context)) return;
        putParcelableExtra(intent, parcelable);
        ((Activity)context).startActivityForResult(intent, flags);
    }

    public static void setResult(Context context, Class<? extends Activity> targetClazz, int flags, Parcelable parcelable) {
        Intent intent = new Intent(context, targetClazz);
        setFlags(intent, flags);
        putParcelableExtra(intent, parcelable);
        if (notActivity(context)) return;
        ((Activity)context).setResult(flags, intent);
        ((Activity)context).finish();
    }

    private static boolean notActivity(Context context) {
        if (!(context instanceof Activity)) return true;
        return false;
    }

    private static void setFlags(Intent intent, int flags) {
        if (flags < 0) return;
        intent.setFlags(flags);
    }

    private static void putParcelableExtra(Intent intent, Parcelable parcelable) {
        if (parcelable == null) return;
        intent.putExtra(PARCELABLE_EXTRA_KEY, parcelable);
    }

    private static void putSerializableExtra(Intent intent, Serializable serializable) {
        if (serializable == null) return;
        intent.putExtra(PARCELABLE_EXTRA_KEY, serializable);
    }
}
