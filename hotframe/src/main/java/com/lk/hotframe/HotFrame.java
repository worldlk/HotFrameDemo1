package com.lk.hotframe;

import android.app.Application;

import com.lk.hotframe.di.component.DaggerHotComponent;
import com.lk.hotframe.di.component.HotComponent;
import com.lk.hotframe.http.HttpGlobalConfig;
import com.lk.hotframe.http.HttpManager;
import com.lk.hotframe.permission.PermissionManager;

/**
 * Created by LiuKai on 2018/8/6
 */
public class HotFrame {
    private static HotComponent mHotComponent;

    /**
     * 初始化操作,必须优先执行
     */
    public static void init(Application application) {
        if (application != null) {
            mHotComponent = DaggerHotComponent.builder().application(application).build();
        }

    }

    /**
     * 获取Application
     */
    public static Application application() {
        return mHotComponent.application();
    }

    /**
     * Http全局配置
     *
     * @return HttpGlobalConfig 实例
     */
    public static HttpGlobalConfig httpGlobalConfig() {
        return mHotComponent.httpGlobalConfig();
    }

    /**
     * 网络请求管理者
     */
    public static HttpManager httpManager() {
        return mHotComponent.httpManager();
    }

    /**
     * 权限管理
     *
     * @return PermissionManager 实例
     */
    public static PermissionManager permissionManager() {
        return mHotComponent.permissionManager();
    }


}
