package com.lk.hotframedemo.mvp.presenter;

import com.lk.hotframe.HotFrame;
import com.lk.hotframe.di.scope.ActivityScope;
import com.lk.hotframe.http.exception.ApiException;
import com.lk.hotframe.http.observer.CommonObserver;
import com.lk.hotframe.mvp.BasePresenter;
import com.lk.hotframedemo.bean.Login;
import com.lk.hotframedemo.mvp.contract.LoginContract;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by LiuKai on 2018/8/7
 */
@ActivityScope
public class LoginPresenter extends BasePresenter<LoginContract.Model, LoginContract.View> {

    @Inject
    public LoginPresenter(LoginContract.Model model, LoginContract.View rootView) {

        super(model, rootView);
    }

    public void login(int lastUserId) {
        HotFrame.httpManager().commonRequest(mModel.getLogin(lastUserId), new CommonObserver<List<Login>>() {
            @Override
            public void onResult(List<Login> result) {
                if (mRootView != null) {
                    mRootView.getLoginSuccess(result);
                }
            }

            @Override
            public void onError(ApiException e) {
                if(mRootView!=null){
                    mRootView.showMessage(e.getMessage());
                }
            }
        }, mRootView);
    }


}
