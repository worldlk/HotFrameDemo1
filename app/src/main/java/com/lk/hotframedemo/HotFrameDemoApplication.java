package com.lk.hotframedemo;

import com.lk.hotframe.HotFrame;
import com.lk.hotframe.base.BaseApplication;
import com.lk.hotframe.utils.LogUtils;
import com.lk.hotframedemo.constant.UrlConstants;

/**
 * Created by LiuKai on 2018/8/10
 */
public class HotFrameDemoApplication extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        HotFrame.init(this);

        //网络模块
        HotFrame.httpGlobalConfig()
                .setBaseUrl(UrlConstants.BASE_URL)
                .setConnectTimeout(15)
                .setIsUseLog(true);


        //Log 工具类的初始化,Debug 会开启日志,release 会关闭日志
        LogUtils.getConfig().setConsoleSwitch(BuildConfig.LOG_ENABLED);


    }
}
