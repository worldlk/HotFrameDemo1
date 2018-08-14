package com.lk.hotframedemo.mvp.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.TextView;
import android.widget.Toast;

import com.lk.hotframe.base.BaseActivity;
import com.lk.hotframedemo.R;
import com.lk.hotframedemo.bean.Login;
import com.lk.hotframedemo.di.component.DaggerLoginActivityComponent;
import com.lk.hotframedemo.di.module.LoginActivityModule;
import com.lk.hotframedemo.mvp.contract.LoginContract;
import com.lk.hotframedemo.mvp.presenter.LoginPresenter;

import java.util.List;

/**
 * Created by LiuKai on 2018/8/7
 */
public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.View {

    private TextView tvLoginInfo;

    @Override
    public Activity getActivity() {
        return null;
    }

    @Override
    public void getLoginSuccess(List<Login> list) {

        tvLoginInfo.setText(list.toString());
    }


    @Override
    public void showMessage(@NonNull String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        DaggerLoginActivityComponent.builder().loginActivityModule(new LoginActivityModule(this)).build().inject(this);

        tvLoginInfo = findViewById(R.id.tv_login_info);


        mPresenter.login(1);
    }
}
