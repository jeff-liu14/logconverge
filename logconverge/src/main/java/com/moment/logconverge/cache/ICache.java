package com.moment.logconverge.cache;

import java.util.List;

/**
 * Created by moment on 2018/1/12.
 */

public interface ICache<R, T> {

    R init();

    R save(String log);

    R delete(String key);

    R delete(List<String> key);

    R delete(String... key);

    List<T> getAll();


}
