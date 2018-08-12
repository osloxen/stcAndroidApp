package com.locallinkonline.stcatherineschool.rest.api;

import com.locallinkonline.stcatherineschool.room.entity.AdEntity;

import java.util.List;

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
