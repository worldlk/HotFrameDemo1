package com.lk.hotframe.permission;

/**
 * 权限回掉接口
 * Created by LiuKai on 2018/8/15
 */
public interface OnPermissionCallback {
    //允许授权
    void onGranted(String permissionName);

    //拒绝授权
    void onDenied(String permissionName);

    //被拒绝,并且勾选了不在询问
    void onDeniedWithNeverAsk(String permissionName);
}
