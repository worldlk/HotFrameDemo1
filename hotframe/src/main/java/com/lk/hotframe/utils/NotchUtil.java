package com.lk.hotframe.utils;

import android.content.Context;
import android.os.SystemProperties;
import android.util.Log;

import java.lang.reflect.Method;

/**
 * Created by LiuKai on 2018/8/14
 * 刘海(不是刘凯^_^)屏幕的适配工具类
 */
public class NotchUtil {


    //------------------------------华为 刘海屏Start-------------------------------------------------

    /**
     * 必须在AndroidManifest.xml添加
     * <meta-data android:name="android.notch_support" android:value="true"/>
     */

    /**
     * 判断华为是否有刘海屏
     *
     * @param context 上下文对象
     * @return 是华为刘海屏返回true 不是华为刘海屏返回false
     */
    public static boolean isHuaweiNotch(Context context) {
        boolean ret = false;
        try {
            ClassLoader classLoader = context.getClassLoader();
            Class HwNotchSizeUtil = classLoader.loadClass("com.huawei.android.util.HwNotchSizeUtil");
            Method get = HwNotchSizeUtil.getMethod("hasNotchInScreen");
            ret = (boolean) get.invoke(HwNotchSizeUtil);
        } catch (ClassNotFoundException e) {
            Log.e("HuaWei", "isHuaweiNotch ClassNotFoundException");
        } catch (NoSuchMethodException e) {
            Log.e("HuaWei", "isHuaweiNotch NoSuchMethodException");
        } catch (Exception e) {
            Log.e("HuaWei", "isHuaweiNotch Exception");
        } finally {
            return ret;
        }
    }


    /**
     * 获取华为刘海的宽高
     *
     * @param context
     * @return int[0]为刘海的宽  int[1]为刘海的高
     */
    public static int[] getHuaweiNotchWH(Context context) {
        int[] ret = new int[]{0, 0};
        try {
            ClassLoader cl = context.getClassLoader();
            Class HwNotchSizeUtil = cl.loadClass("com.huawei.android.util.HwNotchSizeUtil");
            Method get = HwNotchSizeUtil.getMethod("getNotchSize");
            ret = (int[]) get.invoke(HwNotchSizeUtil);
        } catch (ClassNotFoundException e) {
            Log.e("HuaWei", "getHuaweiNotchWH ClassNotFoundException");
        } catch (NoSuchMethodException e) {
            Log.e("HuaWei", "getHuaweiNotchWH NoSuchMethodException");
        } catch (Exception e) {
            Log.e("HuaWei", "getHuaweiNotchWH Exception");
        } finally {
            return ret;
        }
    }


    //------------------------------华为 刘海屏End---------------------------------------------------


    //------------------------------Vivo 刘海屏Start-------------------------------------------------
    public static final int VIVO_NOTCH = 0x00000020;//是否有刘海
    public static final int VIVO_FILLET = 0x00000008;//是否有圆角

    /**
     * 判断Vivo 是否是刘海屏
     *
     * @param context 上下文对象
     * @return 是Vivo刘海屏返回true 不是Vivo刘海屏返回false
     */
    public static boolean isVivoNotch(Context context) {
        boolean ret = false;
        try {
            ClassLoader classLoader = context.getClassLoader();
            Class FtFeature = classLoader.loadClass("android.util.FtFeature");
            Method method = FtFeature.getMethod("isFeatureSupport", int.class);
            ret = (boolean) method.invoke(FtFeature, VIVO_NOTCH);
        } catch (ClassNotFoundException e) {
            Log.e("Vivo", "isVivoNotch ClassNotFoundException");
        } catch (NoSuchMethodException e) {
            Log.e("Vivo", "isVivoNotch NoSuchMethodException");
        } catch (Exception e) {
            Log.e("Vivo", "isVivoNotch Exception");
        } finally {
            return ret;
        }
    }


    /**
     * Vivo 没有提供获取刘海宽高,Vivo 的宽为100dp,高为27dp
     */

    //------------------------------Vivo 刘海屏End---------------------------------------------------


    //------------------------------OPPO刘海屏Start---------------------------------------------------

    /**
     * 获取Oppo是否是刘海屏
     *
     * @param context 上下文对象
     * @return 设置 – 显示 – 应用全屏显示 – 凹形区域显示控制，里面有关闭凹形区域开关
     */
    public static boolean isOppoNotch(Context context) {
        return context.getPackageManager().hasSystemFeature("com.oppo.feature.screen.heteromorphism");
    }

    /**
     * OPPO 没有提供获取刘海屏的宽高，其中1080*2280分辨率刘海的宽为324px 高为80px
     */

    //------------------------------OPPO 刘海屏End---------------------------------------------------


    //------------------------------小米 刘海屏Start---------------------------------------------------


    /**
     * 必须在AndroidManifest.xml添加：
     *
     * <meta-data android:name="notch.config" android:value="portrait|landscape"/>
     *
     * 其中android:value的取值为4种：
     *                    1."none" 横竖屏都不绘制耳朵区
     *                    2."portrait" 竖屏绘制到耳朵区
     *                    3."landscape" 横屏绘制到耳朵区
     *                    4."portrait|landscape" 横竖屏都绘制到耳朵区
     */


    /**
     * 获取小米是否是刘海屏
     *
     * @return
     */
    public static boolean isMiuiNotch() {
        return SystemProperties.getInt("ro.miui.notch", 0) == 1;
    }

    /**
     * 获取小米刘海屏的高
     *
     * @param context
     * @return 小米刘海屏的高
     */
    public static int getMiuiNotchH(Context context) {
        int resultH = 0;
        int resourceId = context.getResources().getIdentifier("notch_height", "dimen", "android");
        if (resourceId > 0) {
            resultH = context.getResources().getDimensionPixelSize(resourceId);
        }
        return resultH;
    }

    /**
     * 获取小米刘海屏的宽
     *
     * @param context
     * @return 获取小米刘海屏的宽
     */
    public static int getMiuiNotchW(Context context) {
        int resultW = 0;

        int resourceId = context.getResources().getIdentifier("notch_width", "dimen", "android");
        if (resourceId > 0) {
            resultW = context.getResources().getDimensionPixelSize(resourceId);
        }

        return resultW;
    }


    //------------------------------小米 刘海屏End---------------------------------------------------


    //-------------------------------通用 刘海屏 Start-----------------------------------------------
    /**
     * 获取其他手机刘海屏的高
     * 一般情况下，状态栏的高度要比刘海屏的高度高,变态设备，请骂他，或者提交个pr,谢谢！
     * {@link DeviceUtil} 的getStatusBarHeight
     */
    //-------------------------------通用 刘海屏 End-------------------------------------------------





}
