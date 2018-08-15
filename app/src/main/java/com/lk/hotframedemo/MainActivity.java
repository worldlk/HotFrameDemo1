package com.lk.hotframedemo;

import android.Manifest;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.lk.hotframe.HotFrame;
import com.lk.hotframe.permission.OnPermissionCallback;
import com.lk.hotframedemo.mvp.ui.LoginActivity;

/**
 * Created by LiuKai on 2018/8/7
 * 1.权限管理测试,一般都是放到Splsh页面，一次性全部申请完。
 * 但是，相关的逻辑的的地方，还是要申请权限申请，因为不排除，Splash 申请完成之后，用户会关闭的情况
 */
public class MainActivity extends AppCompatActivity {

    private TextView tvLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvLogin = findViewById(R.id.tv_login);

        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(mIntent);
            }
        });

        HotFrame.permissionManager().requestEachCombined(MainActivity.this, new OnPermissionCallback() {
            @Override
            public void onGranted(String permissionName) {
                Toast.makeText(MainActivity.this, permissionName, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDenied(String permissionName) {
                Toast.makeText(MainActivity.this, permissionName, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDeniedWithNeverAsk(String permissionName) {
                Toast.makeText(MainActivity.this, permissionName, Toast.LENGTH_SHORT).show();
            }
        }, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA);


    }
}
