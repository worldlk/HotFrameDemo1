package com.lk.hotframe.lifecycle;

import android.support.annotation.NonNull;

import io.reactivex.subjects.Subject;

public interface ILifecycle<E> {
    @NonNull
    Subject<E> provideLifecycleSubject();
}