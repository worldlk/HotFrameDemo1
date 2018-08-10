package com.lk.hotframe.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lk.hotframe.lifecycle.ILifecycleFragment;
import com.lk.hotframe.mvp.IPresenter;
import com.trello.rxlifecycle2.android.FragmentEvent;

import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.Subject;

/**
 * Created by LiuKai on 2018/8/7
 */
public class BaseFragment<P extends IPresenter> extends Fragment implements ILifecycleFragment {
    private final BehaviorSubject<FragmentEvent> mLifecycleSubject = BehaviorSubject.create();

    protected P mPresenter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) mPresenter.onDestroy();//释放资源
        this.mPresenter = null;
    }

    @NonNull
    @Override
    public Subject<FragmentEvent> provideLifecycleSubject() {
        return mLifecycleSubject;
    }
}
