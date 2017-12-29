package com.mk.kilikili.net;

import com.mk.kilikili.net.retrofit2.Retrofit2ClientHelper;
import com.mk.kilikili.net.services.LiveService;
import com.mk.kilikili.net.services.RecommendService;

import retrofit2.Retrofit;

/**
 * Service管理类
 */
public class CoreService {

    public static LiveService getLiveAPI() {

        return createApi(LiveService.class, NetConstants.LIVE_BASE_URL);
    }


    public static RecommendService getBiliAppAPI() {

        return createApi(RecommendService.class, NetConstants.APP_BASE_URL);
    }

//
//  public static BiliApiService getBiliAPI() {
//
//    return createApi(BiliApiService.class, ApiConstants.API_BASE_URL);
//  }
//
//
//  public static BiliGoService getBiliGoAPI() {
//
//    return createApi(BiliGoService.class, ApiConstants.BILI_GO_BASE_URL);
//  }
//
//
//  public static RankService getRankAPI() {
//
//    return createApi(RankService.class, ApiConstants.RANK_BASE_URL);
//  }
//
//
//  public static UserService getUserAPI() {
//
//    return createApi(UserService.class, ApiConstants.USER_BASE_URL);
//  }
//
//
//  public static VipService getVipAPI() {
//
//    return createApi(VipService.class, ApiConstants.VIP_BASE_URL);
//  }
//
//
//  public static BangumiService getBangumiAPI() {
//
//    return createApi(BangumiService.class, ApiConstants.BANGUMI_BASE_URL);
//  }
//
//
//  public static SearchService getSearchAPI() {
//
//    return createApi(SearchService.class, ApiConstants.SEARCH_BASE_URL);
//  }
//
//
//  public static AccountService getAccountAPI() {
//
//    return createApi(AccountService.class, ApiConstants.ACCOUNT_BASE_URL);
//  }
//
//
//  public static Im9Service getIm9API() {
//
//    return createApi(Im9Service.class, ApiConstants.IM9_BASE_URL);
//  }


    /**
     * 根据传入的baseUrl，和api创建retrofit
     */
    private static <T> T createApi(Class<T> clazz, String baseUrl) {

        Retrofit retrofit = Retrofit2ClientHelper.INSTANCE
                .getRetrofitBuilder()
                .baseUrl(baseUrl)
                .build();

        return retrofit.create(clazz);
    }
}
