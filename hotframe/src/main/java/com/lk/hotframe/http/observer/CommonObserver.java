package com.lk.hotframe.http.observer;

import com.lk.hotframe.http.exception.ApiException;
import com.lk.hotframe.http.mode.ApiCode;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by LiuKai on 2018/8/7
 * 如果是kotlin,就可以直接在这处理IView 接口
 * 普通的网络请求
 */
public abstract class CommonObserver<T> implements Observer<T> {
    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(T t) {
        onResult(t);
    }

    @Override
    public void onError(Throwable e) {
        if (e instanceof ApiException) {
            onError((ApiException) e);
        } else {
            onError(new ApiException(e, ApiCode.Request.UNKNOWN));
        }
    }

    @Override
    public void onComplete() {

    }

    public abstract void onResult(T result);

    public abstract void onError(ApiException e);
}
