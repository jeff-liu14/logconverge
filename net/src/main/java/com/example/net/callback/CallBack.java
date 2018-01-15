package com.example.net.callback;

/**
 * Created by moment on 16/5/24.
 */

public interface CallBack<T> {
    void onCompleted();

    void onError(Throwable e);

    void onNext(T t);
}
