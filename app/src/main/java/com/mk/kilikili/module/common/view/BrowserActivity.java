package com.mk.kilikili.module.common.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.mk.kilikili.base.BaseActivity;
import com.mk.kilikili.utils.manager.C;

import butterknife.BindView;

/**
 * 浏览器界面
 */
public class BrowserActivity extends BaseActivity {

    public static void launch(Activity activity, String url, String title) {

        Intent intent = new Intent(activity, BrowserActivity.class);
        intent.putExtra(C.EXTRA_URL, url);
        intent.putExtra(C.EXTRA_TITLE, title);
        activity.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return 0;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {

    }
}
