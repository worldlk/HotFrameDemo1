package com.lk.hotframe.permission;

import android.app.Activity;

import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import io.reactivex.functions.Consumer;

/**
 * 权限管理
 * Created by LiuKai on 2018/8/15
 */
public class PermissionManager {
    /**
     * 申请多个权限，成功，失败权限名称会一个个返回
     *
     * @param activity    在哪个Activity进行授权
     * @param callback    权限申请回掉
     * @param permissions 权限名称
     */
    public void requestEach(Activity activity, final OnPermissionCallback callback, String... permissions) {
        if (activity != null) {
            RxPermissions rxPermissions = new RxPermissions(activity);
            rxPermissions.requestEach(permissions).subscribe(new Consumer<Permission>() {
                @Override
                public void accept(Permission permission) throws Exception {
                    if (permission.granted) {
                        if (callback != null) {
                            callback.onGranted(permission.name);
                        }
                    } else if (permission.shouldShowRequestPermissionRationale) {
                        if (callback != null) {
                            callback.onDenied(permission.name);
                        }
                    } else {
                        if (callback != null) {
                            callback.onDeniedWithNeverAsk(permission.name);
                        }
                    }
                }
            });
        }
    }

    /**
     * 申请多个权限，成功或者失败，权限名称会全部返回
     *
     * @param activity    在哪个Activity进行授权
     * @param callback    权限申请回掉
     * @param permissions 多个权限名称 可变参数用"," 分隔
     */
    public void requestEachCombined(Activity activity, final OnPermissionCallback callback, String... permissions) {
        if (activity != null) {
            RxPermissions rxPermissions = new RxPermissions(activity);
            rxPermissions.requestEachCombined(permissions).subscribe(new Consumer<Permission>() {
                @Override
                public void accept(Permission permission) throws Exception {
                    if (permission.granted) {
                        if (callback != null) {
                            callback.onGranted(permission.name);
                        }
                    } else if (permission.shouldShowRequestPermissionRationale) {
                        if (callback != null) {
                            callback.onDenied(permission.name);
                        }
                    } else {
                        if (callback != null) {
                            callback.onDeniedWithNeverAsk(permission.name);
                        }
                    }
                }
            });
        }
    }


}
