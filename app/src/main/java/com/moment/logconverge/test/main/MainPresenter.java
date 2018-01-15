package com.moment.logconverge.test.main;

import android.util.Log;

import java.util.List;

/**
 * Created by moment on 2017/11/23.
 */

public class MainPresenter implements MainContract.MainPresenter {
    MainContract.MainView mMainView;

    public MainPresenter(MainContract.MainView mainView) {
        mMainView = mainView;
        mMainView.setPresenter(this);
    }

    @Override
    public void start() {
        Log.d("TAG", "-------------start-------------------");
    }

    @Override
    public void getData() {

    }
}
