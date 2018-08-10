package com.lk.hotframedemo.di.component;

import com.lk.hotframe.di.scope.ActivityScope;
import com.lk.hotframedemo.di.module.LoginActivityModule;
import com.lk.hotframedemo.mvp.ui.LoginActivity;

import dagger.Component;

/**
 * Created by LiuKai on 2018/8/10
 */
@ActivityScope
@Component(modules = LoginActivityModule.class)
public interface LoginActivityComponent {
    void inject(LoginActivity loginActivity);
}
