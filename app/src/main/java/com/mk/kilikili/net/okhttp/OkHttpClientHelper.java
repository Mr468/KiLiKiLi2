package com.mk.kilikili.net.okhttp;

import com.mk.kilikili.App;
import com.mk.kilikili.net.NetConstants;
import com.mk.kilikili.net.okhttp.interceptor.OfflineCacheControlInterceptor;
import com.mk.kilikili.net.okhttp.interceptor.UserAgentInterceptor;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * httpClient
 */
public enum OkHttpClientHelper {

    INSTANCE;

    private final OkHttpClient okHttpClient;

    private static final int TIMEOUT_READ = 15;
    private static final int TIMEOUT_CONNECTION = 15;

    OkHttpClientHelper() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        Cache cache = new Cache(new File(App.getInstance()
                .getCacheDir(), "HttpCache"), 1024 * 1024 * 10);

        Interceptor cacheInterceptor = new OfflineCacheControlInterceptor();

        okHttpClient = new okhttp3.OkHttpClient.Builder()
                //打印日志
                .addInterceptor(interceptor)
                //设置Cache目录
                .cache(cache)
                //设置缓存
                .addInterceptor(cacheInterceptor)
                .addNetworkInterceptor(cacheInterceptor)
                //失败重连
                .retryOnConnectionFailure(true)
                //time out
                .readTimeout(TIMEOUT_READ, TimeUnit.SECONDS)
                .connectTimeout(TIMEOUT_CONNECTION, TimeUnit.SECONDS)
                .addInterceptor(new UserAgentInterceptor(NetConstants.COMMON_UA_STR))
                .build();
    }

    public okhttp3.OkHttpClient getOkHttpClient() {
        return okHttpClient;
    }

}
