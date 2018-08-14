package com.lk.hotframe.image;

/**
 * Created by LiuKai on 2018/8/14
 */
public class ImageConfig {
    public int placeholderResId;  //占位图
    public int errorResId;        //错误图
    //... 我想不起来设置什么了^_^,可以设置使用OkHttp

    public ImageConfig setPlaceholderResId(int placeholderResId) {
        this.placeholderResId = placeholderResId;
        return this;
    }

    public int getPlaceholderResId() {
        return placeholderResId;
    }

    public int getErrorResId() {
        return errorResId;
    }

    public ImageConfig setErrorResId(int errorResId) {
        this.errorResId = errorResId;
        return this;
    }
}
