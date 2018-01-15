package com.example.net.service;

import com.example.net.entity.CookClassify;
import com.example.net.entity.CookList;
import com.example.net.entity.CookName;
import com.example.net.entity.CookShow;
import com.example.net.entity.Recommend;
import com.example.net.entity.Result;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by moment on 16/5/24.
 */
public interface Service {

    /**
     * 天狗健康菜谱
     */

    //菜谱名称
    @GET("cook/name")
    Observable<CookName> getCookNames(
            @Query("name") String name
    );

    //菜谱详情
    @GET("cook/show")
    Observable<CookShow> getCookShow(
            @Query("id") long id
    );

    //菜谱列表
    @GET("cook/list")
    Observable<CookList> getCookList(
            @Query("id") long id,
            @Query("page") int page,
            @Query("rows") int rows
    );

    //菜谱列表
    @GET("cook/list")
    Observable<CookList> getCookList(
            @Query("id") long id,
            @Query("page") int page
    );

    //菜谱列表
    @GET("cook/list")
    Observable<CookList> getCookList(
            @Query("id") long id
    );

    //菜谱列表
    @GET("cook/list")
    Observable<CookList> getCookList(
    );

    //菜谱分类
    @GET("cook/classify")
    Observable<CookClassify> getCookClassify(
    );

    @GET("/comic/home")
    Observable<Result<List<Recommend>>> getComicRecommends();

}
