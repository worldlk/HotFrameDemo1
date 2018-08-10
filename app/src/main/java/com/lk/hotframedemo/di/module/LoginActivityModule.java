package com.lk.hotframedemo.di.module;

import com.lk.hotframe.di.scope.ActivityScope;
import com.lk.hotframedemo.mvp.contract.LoginContract;
import com.lk.hotframedemo.mvp.model.LoginModel;

import dagger.Module;
import dagger.Provides;

/**
 * Created by LiuKai on 2018/8/10
 */
@Module
public class LoginActivityModule {
    private LoginContract.View view;

    public LoginActivityModule(LoginContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    LoginContract.View provideUserView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    LoginContract.Model provideUserModel(LoginModel model) {
        return model;
    }
}
