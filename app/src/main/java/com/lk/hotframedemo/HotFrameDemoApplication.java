package com.lk.hotframedemo;

import com.lk.hotframe.HotFrame;
import com.lk.hotframe.base.BaseApplication;
import com.lk.hotframedemo.constant.UrlConstants;

/**
 * Created by LiuKai on 2018/8/10
 */
public class HotFrameDemoApplication extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        HotFrame.init(this);

        HotFrame.httpGlobalConfig()
                .setBaseUrl(UrlConstants.BASE_URL)
                .setConnectTimeout(15)
                .setIsUseLog(true);


    }
}
