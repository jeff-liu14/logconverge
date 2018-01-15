package com.moment.logconverge.test.main;

import android.util.Log;

import com.example.net.GetDataList;
import com.example.net.callback.CallBack;
import com.example.net.entity.Recommend;
import com.example.net.entity.Result;

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
        Log.d("testsss", "-------------start-------------------");
    }

    @Override
    public void getData() {
        GetDataList.getComicRecommends(new CallBack<Result<List<Recommend>>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Log.d("error", e.getMessage());
            }

            @Override
            public void onNext(Result<List<Recommend>> listResult) {
                mMainView.refreshViews(listResult.getData().get(0).getIntroduction() + "-size");
            }
        });

    }
}
