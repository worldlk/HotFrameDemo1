package com.lk.hotframe.http.config;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Lazy;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * Created by LiuKai on 2018/8/10
 */
@Singleton
public class HttpGlobalConfig {

    @Inject
    Lazy<OkHttpClient.Builder> mOkHttpClientBuilder;
    @Inject
    Lazy<Retrofit.Builder> mRetrofitBuilder;

    private String mBaseUrl;
    private int mConnectTimeout;

    private boolean mIsUseLog;   //是否需要开启日志

    private Map<String, String> mMapHeader;  //添加Header 请求


    @Inject
    public HttpGlobalConfig() {

    }


    public String getBaseUrl() {
        return mBaseUrl == null ? "" : mBaseUrl;
    }

    //设置BaseUrl
    public HttpGlobalConfig setBaseUrl(String baseUrl) {
        this.mBaseUrl = baseUrl;
        return this;
    }


    public int getConnectTimeout() {
        return mConnectTimeout;
    }

    //设置请求超时时长，单位秒
    public HttpGlobalConfig setConnectTimeout(int connectTimeout) {
        this.mConnectTimeout = connectTimeout;
        return this;
    }

    public boolean isUseLog() {
        return mIsUseLog;
    }

    //设置是否开启Log，默认不开启
    public HttpGlobalConfig setIsUseLog(boolean useLog) {
        mIsUseLog = useLog;
        return this;
    }


    public Map<String, String> getMapHeader() {
        return mMapHeader;
    }

    //设置全局的header信息
    public HttpGlobalConfig setMapHeader(Map<String, String> mapHeader) {
        this.mMapHeader = mapHeader;
        return this;
    }


    //获取builder 自定义
    public OkHttpClient.Builder getOkHttpClientBuilder() {
        return mOkHttpClientBuilder.get();
    }

    //获取builder 自定义
    public Retrofit.Builder getRetrofitBuilder() {
        return mRetrofitBuilder.get();
    }
}
