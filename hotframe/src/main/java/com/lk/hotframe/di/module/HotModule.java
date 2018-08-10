package com.lk.hotframe.di.module;

import android.app.Application;
import android.text.TextUtils;

import com.lk.hotframe.http.config.HttpGlobalConfig;
import com.lk.hotframe.http.interceptor.HttpHeaderInterceptor;
import com.lk.hotframe.http.interceptor.HttpLogInterceptor;
import com.lk.hotframe.utils.CollectionUtil;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by LiuKai on 2018/8/10
 */
@Module
public class HotModule {
    //HTTP START
    @Singleton
    @Provides
    Retrofit retrofit(Retrofit.Builder builder, OkHttpClient okHttpClient, HttpGlobalConfig httpGlobalConfig) {
        //多个BaseUrl切换，后期会考虑添加。
        if (!TextUtils.isEmpty(httpGlobalConfig.getBaseUrl())) {
            builder.baseUrl(httpGlobalConfig.getBaseUrl());
        }

        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        builder.client(okHttpClient);

        return builder.build();
    }

    @Singleton
    @Provides
    Retrofit.Builder retrofitBuilder() {
        return new Retrofit.Builder();
    }

    @Singleton
    @Provides
    OkHttpClient okHttpClient(Application application, OkHttpClient.Builder builder, HttpGlobalConfig httpGlobalConfig) {
        if (httpGlobalConfig.getConnectTimeout() > 0) {
            builder.connectTimeout(httpGlobalConfig.getConnectTimeout(), TimeUnit.SECONDS);
        }
        if (httpGlobalConfig.isUseLog()) {
            HttpLogInterceptor loggingInterceptor = new HttpLogInterceptor();
            loggingInterceptor.setLevel(HttpLogInterceptor.Level.BODY);
            builder.addInterceptor(loggingInterceptor);
        }

        if (!CollectionUtil.isEmpty(httpGlobalConfig.getMapHeader())) {  //判断Header 是否为null
            HttpHeaderInterceptor headerInterceptor = new HttpHeaderInterceptor(httpGlobalConfig.getMapHeader());
            builder.addInterceptor(headerInterceptor);
        }

        return builder.build();
    }

    @Singleton
    @Provides
    OkHttpClient.Builder okClientBuilder() {
        return new OkHttpClient.Builder();
    }

    //HTTP END
}
