package com.lk.hotframe.lifecycle;

import com.trello.rxlifecycle2.android.ActivityEvent;

/**
 * Created by LiuKai on 2018/8/7
 * 实现此接口可以实现自动管理RxJava的生命周期
 */
public interface ILifecycleActivity extends ILifecycle<ActivityEvent> {
}
