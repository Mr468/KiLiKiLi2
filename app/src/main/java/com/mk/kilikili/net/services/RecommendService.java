package com.mk.kilikili.net.services;

import com.mk.kilikili.module.common.entity.video.VideoDetailsInfo;
import com.mk.kilikili.module.home.recommend.entity.RecommendBannerInfo;
import com.mk.kilikili.module.home.recommend.entity.RecommendInfo;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Mr_468 on 2017/4/19.
 */

public interface RecommendService {
    /**
     * 首页推荐数据
     */
    @GET("x/show/old?platform=android&device=&build=412001")
    Observable<RecommendInfo> getRecommendedInfo();

    /**
     * 首页推荐banner
     */
    @GET("x/banner?plat=4&build=411007&channel=bilih5")
    Observable<RecommendBannerInfo> getRecommendedBannerInfo();

    /**
     * 视频详情数据
     */
    @GET(
            "x/view?access_key=19946e1ef3b5cad1a756c475a67185bb&actionKey=appkey&appkey=27eb53fc9058f8c3&build=3940&device=phone&mobi_app=iphone&platform=ios&sign=1206255541e2648c1badb87812458046&ts=1478349831")
    Observable<VideoDetailsInfo> getVideoDetails(@Query("aid") int aid);
}
