package com.example.net;

import com.example.net.callback.CallBack;
import com.example.net.entity.CookClassify;
import com.example.net.entity.CookList;
import com.example.net.entity.CookName;
import com.example.net.entity.CookShow;
import com.example.net.entity.Recommend;
import com.example.net.entity.Result;
import com.example.net.retrofit.RetrofitUtils;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by moment on 16/5/24.
 */
public class GetDataList {

    public static void getShowData(final CallBack<CookShow> callBack) {
        new RetrofitUtils().with().build()
                .getCookShow(10L)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(callBack::onNext,
                        callBack::onError,
                        callBack::onCompleted);
    }

    public static void getNameData(final CallBack<CookName> callBack) {
        new RetrofitUtils().with().build()
                .getCookNames("小炒肉")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(callBack::onNext,
                        callBack::onError,
                        callBack::onCompleted);
    }

    public static void getComicRecommends(CallBack<Result<List<Recommend>>> callBack) {
        new RetrofitUtils().with().build()
                .getComicRecommends()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(callBack::onNext,
                        callBack::onError,
                        callBack::onCompleted);
    }

    public static void getListData(final CallBack<CookList> callBack) {
        new RetrofitUtils().with().build()
                .getCookList()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(callBack::onNext,
                        callBack::onError,
                        callBack::onCompleted);
    }

    public static void getListData(long id, final CallBack<CookList> callBack) {
        new RetrofitUtils().with().build()
                .getCookList(id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(callBack::onNext,
                        callBack::onError,
                        callBack::onCompleted);
    }

    public static void getListData(long id, int page, int rows, final CallBack<CookList> callBack) {
        new RetrofitUtils().with().build()
                .getCookList(id, page, rows)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(callBack::onNext,
                        callBack::onError,
                        callBack::onCompleted);
    }


    public static void getCookClassify(final CallBack<CookClassify> callBack) {
        new RetrofitUtils()
                .with()
                .build()
                .getCookClassify()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(callBack::onNext,
                        callBack::onError,
                        callBack::onCompleted);
    }

}
