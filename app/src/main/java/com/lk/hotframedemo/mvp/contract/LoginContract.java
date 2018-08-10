package com.lk.hotframedemo.mvp.contract;

import android.app.Activity;

import com.lk.hotframe.mvp.IModel;
import com.lk.hotframe.mvp.IView;
import com.lk.hotframedemo.bean.Login;

import java.util.List;

import io.reactivex.Observable;


/**
 * Created by LiuKai on 2018/8/7
 * 契约类
 */
public interface LoginContract {
    interface View extends IView {
        Activity getActivity();

        void getLoginSuccess(List<Login> list);

    }


    interface Model extends IModel {
        Observable<List<Login>> getLogin(int lastIdQueried);

    }
}
