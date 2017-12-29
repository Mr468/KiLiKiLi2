package com.mk.kilikili.net.retrofit2;

import com.mk.kilikili.net.retrofit2.error_handle.RxErrorHandlingCallAdapterFactory;
import com.mk.kilikili.net.okhttp.OkHttpClientHelper;
import com.mk.kilikili.net.retrofit2.converfactory.StringConverterFactory;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * retrofit2Client
 */
public enum Retrofit2ClientHelper {

    INSTANCE;

    private final Retrofit.Builder retrofitBuilder;

    Retrofit2ClientHelper() {
        retrofitBuilder = new Retrofit.Builder()
                //设置OKHttpClient
                .client(OkHttpClientHelper.INSTANCE.getOkHttpClient())

                //Rx
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())

                //String转换器
                .addConverterFactory(StringConverterFactory.create())

                //gson转化器
                .addConverterFactory(GsonConverterFactory.create())

                //error 转换器
                .addCallAdapterFactory(RxErrorHandlingCallAdapterFactory.create());

    }

    public Retrofit.Builder getRetrofitBuilder() {
        return retrofitBuilder;
    }

}
