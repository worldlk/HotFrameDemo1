package com.lk.hotframedemo.mvp.model;

import com.lk.hotframe.HotFrame;
import com.lk.hotframe.di.scope.ActivityScope;
import com.lk.hotframe.mvp.BaseModel;
import com.lk.hotframedemo.apiservice.LoginService;
import com.lk.hotframedemo.bean.Login;
import com.lk.hotframedemo.mvp.contract.LoginContract;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by LiuKai on 2018/8/10
 */
@ActivityScope
public class LoginModel extends BaseModel implements LoginContract.Model {

    @Inject
    public LoginModel() {
    }

    @Override
    public Observable<List<Login>> getLogin(int id) {
        return HotFrame.httpManager().getService(LoginService.class).getLogin(id);
    }
}
