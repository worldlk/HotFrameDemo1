package com.lk.hotframe.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * Created by LiuKai on 2018/8/14
 */
public class DeviceUtil {
    /**
     * 获取手机厂商
     *
     * @return
     */
    public static String getDeviceBrand() {
        return android.os.Build.BRAND;
    }

    /**
     * 获取状态栏的高度宽度
     *
     * @param context
     * @return 状态栏的高度
     */
    public static int getStatusBarHeight(Context context) {
        int statusBarHeight = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            statusBarHeight = context.getResources().getDimensionPixelSize(resourceId);
        }
        return statusBarHeight;
    }

    /**
     * 获取屏幕密度
     *
     * @param context
     * @return
     */
    public static DisplayMetrics getDisplayMetrics(Context context) {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        ((WindowManager) context.getSystemService(
                Context.WINDOW_SERVICE)).getDefaultDisplay().getMetrics(
                displaymetrics);
        return displaymetrics;
    }


    /**
     * px转换为dp
     *
     * @return dp
     */
    public static int px2dp(Context context, float pxValue) {
        float scale = getDisplayMetrics(context).density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * dp转化为px
     *
     * @return px
     */
    public static int dp2px(Context context, float dpValue) {
        float scale = getDisplayMetrics(context).density;
        return (int) (dpValue * scale + 0.5f);
    }




}
