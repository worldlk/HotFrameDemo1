package com.lk.hotframe.http.retry;

import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;

/**
 * Created by LiuKai on 2018/8/7
 */
public class RetryFunction implements Function<Observable<Throwable>, ObservableSource<?>> {
    private int retryDelaySeconds;//延迟重试的时间
    private int retryCountMax;//最大重试次数
    private int retryCount;//记录当前重试次数


    public RetryFunction(int retryDelaySeconds, int retryCountMax) {
        this.retryDelaySeconds = retryDelaySeconds;
        this.retryCountMax = retryCountMax;
    }

    @Override
    public ObservableSource<?> apply(Observable<Throwable> throwableObservable) throws Exception {
        return throwableObservable.flatMap(new Function<Throwable, ObservableSource<?>>() {
            @Override
            public ObservableSource<?> apply(Throwable throwable) throws Exception {

                if (throwable instanceof UnknownHostException) {
                    return Observable.error(throwable);
                }

                if (++retryCount <= retryCountMax) {
                    return Observable.timer(retryDelaySeconds, TimeUnit.SECONDS);
                }

                return Observable.error(throwable);
            }
        });
    }
}
