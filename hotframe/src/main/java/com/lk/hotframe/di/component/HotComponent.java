package com.lk.hotframe.di.component;

import android.app.Application;

import com.lk.hotframe.di.module.HotModule;
import com.lk.hotframe.http.HttpManager;
import com.lk.hotframe.http.config.HttpGlobalConfig;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.Module;

/**
 * Created by LiuKai on 2018/8/10
 */
@Singleton
@Component(modules = HotModule.class)
public interface HotComponent {

    HttpGlobalConfig httpGlobalConfig();

    HttpManager httpManager();

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        HotComponent build();
    }

}
