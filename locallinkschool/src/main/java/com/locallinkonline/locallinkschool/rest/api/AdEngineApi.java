package com.locallinkonline.locallinkschool.rest.api;


import com.locallinkonline.locallinkschool.room.entity.AdEntity;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;



public interface AdEngineApi {

    @GET("local-link-ad")
    Call<AdEntity> getAd(@Query("platform") String platform,
                         @Query("schoolId") String schoolId,
                         @Query("impressionLocation") String impressionLocation);

    @GET("get-all-ads")
    Call<AdEntity[]> getAllAds(@Query("platform") String platform,
                               @Query("schoolId") String schoolId,
                               @Query("impressionLocation") String impressionLocation);
}
