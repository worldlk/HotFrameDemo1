package com.lk.hotframe.permission;

import android.app.Activity;
import android.os.Build;

import io.reactivex.functions.Consumer;

/**
 * 权限管理
 */
public class PermissionManager {
    private static PermissionManager permissionManager;

    private PermissionManager() {

    }

    public static PermissionManager instance() {
        if (permissionManager == null) {
            synchronized (PermissionManager.class) {
                if (permissionManager == null) {
                    permissionManager = new PermissionManager();
                }
            }
        }
        return permissionManager;
    }

    public void request(Activity activity, final OnPermissionCallback permissionCallback, final String... permissions) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && activity != null && permissionCallback != null) {
            RxPermissions rxPermissions = new RxPermissions(activity);
            rxPermissions.requestEach(permissions).subscribe(new Consumer<Permission>() {
                @Override
                public void accept(Permission permission) throws Exception {
                    if (permission.granted) {
                        permissionCallback.onRequestAllow(permission.name);
                    } else if (permission.shouldShowRequestPermissionRationale) {
                        permissionCallback.onRequestRefuse(permission.name);
                    } else {
                        permissionCallback.onRequestNoAsk(permission.name);
                    }
                }
            });
        }
    }
}
