package com.lk.hotframe.http;

import com.lk.hotframe.http.config.HttpGlobalConfig;
import com.lk.hotframe.http.retry.RetryFunction;
import com.lk.hotframe.lifecycle.RxLifecycleManager;
import com.lk.hotframe.mvp.IView;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * Created by LiuKai on 2018/8/7
 */

@Singleton
public class HttpManager {
    @Inject
    Retrofit mRetrofit;
    @Inject
    HttpGlobalConfig httpGlobalConfig;

    @Inject
    public HttpManager() {

    }

    public <T> T getService(Class<T> serviceClass) {
        T service = mRetrofit.create(serviceClass);
        return service;
    }

    /**
     * 普通的网络请求
     *
     * @param observable
     * @param observer
     * @param iView      iView 实在是不知道在哪传递了,其实Kotlin的话，可以直接传递给CommonObserver,后期会换为Kotlin,我真的是受不了了啊啊啊啊啊啊
     */
    public void commonRequest(Observable observable, Observer observer, IView iView) {
        observable
                .subscribeOn(Schedulers.io())
                .retryWhen(new RetryFunction(2, 3))
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        iView.showLoading();
                    }
                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        iView.hideLoading();
                    }
                }).compose(RxLifecycleManager.bindToLifecycle(iView)).subscribe(observer);
    }

}
