package com.lk.hotframe.di.module;

import com.lk.hotframe.permission.PermissionManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by LiuKai on 2018/8/15
 */
@Module
public class OtherModule {
    @Singleton
    @Provides
    PermissionManager permissionManager() {
        return new PermissionManager();
    }
}
