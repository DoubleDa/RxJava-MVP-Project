package com.dyx.rmp.service;

import com.dyx.rmp.bean.json.NewsBean;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * project name：RxJava-MVP-Project
 * class describe：请求news接口
 * create person：dayongxin
 * create time：16/8/3 下午5:41
 * alter person：dayongxin
 * alter time：16/8/3 下午5:41
 * alter remark：
 */
public interface NewsService {
    @GET("news")
    Observable<NewsBean> news(
            @Query("rows") Integer rows
    );
}
