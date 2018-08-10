package com.lk.hotframe.lifecycle;

import com.lk.hotframe.mvp.IView;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.android.FragmentEvent;
import com.trello.rxlifecycle2.android.RxLifecycleAndroid;

import io.reactivex.annotations.NonNull;

/**
 * Created by LiuKai on 2018/8/7
 * 独创RxJava的生命周期管理，不需要继承
 */
public class RxLifecycleManager {
    private RxLifecycleManager() {
        throw new IllegalStateException("you can't instantiate me!");
    }

    /**
     * 绑定 Activity 的指定生命周期
     *
     * @param view
     * @param event
     * @param <T>
     * @return
     */
    public static <T> LifecycleTransformer<T> bindUntilEvent(@NonNull final IView view,
                                                             final ActivityEvent event) {
        if (view instanceof ILifecycleActivity) {
            return bindUntilEvent((ILifecycleActivity) view, event);
        } else {
            throw new IllegalArgumentException("view isn't ActivityLifecycleable");
        }
    }

    /**
     * 绑定 Fragment 的指定生命周期
     *
     * @param view
     * @param event
     * @param <T>
     * @return
     */
    public static <T> LifecycleTransformer<T> bindUntilEvent(@NonNull final IView view,
                                                             final FragmentEvent event) {
        if (view instanceof ILifecycleFragment) {
            return bindUntilEvent((ILifecycleFragment) view, event);
        } else {
            throw new IllegalArgumentException("view isn't FragmentLifecycleable");
        }
    }

    public static <T, R> LifecycleTransformer<T> bindUntilEvent(@NonNull final ILifecycle<R> ILifecycle,
                                                                final R event) {

        return com.trello.rxlifecycle2.RxLifecycle.bindUntilEvent(ILifecycle.provideLifecycleSubject(), event);
    }


    /**
     * 绑定 Activity/Fragment 的生命周期
     *
     * @param view
     * @param <T>
     * @return
     */
    public static <T> LifecycleTransformer<T> bindToLifecycle(@NonNull IView view) {

        if (view instanceof ILifecycle) {
            return bindToLifecycle((ILifecycle) view);
        } else {
            throw new IllegalArgumentException("view isn't ILifecycle");
        }
    }

    public static <T> LifecycleTransformer<T> bindToLifecycle(@NonNull ILifecycle ILifecycle) {

        if (ILifecycle instanceof ILifecycleActivity) {
            return RxLifecycleAndroid.bindActivity(((ILifecycleActivity) ILifecycle).provideLifecycleSubject());
        } else if (ILifecycle instanceof ILifecycleFragment) {
            return RxLifecycleAndroid.bindFragment(((ILifecycleFragment) ILifecycle).provideLifecycleSubject());
        } else {
            throw new IllegalArgumentException("ILifecycle not match");
        }
    }


}
