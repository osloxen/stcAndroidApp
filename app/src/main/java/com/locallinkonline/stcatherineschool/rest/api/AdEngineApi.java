package com.locallinkonline.stcatherineschool.rest.api;

import com.locallinkonline.stcatherineschool.rest.model.AdUnit;
import com.locallinkonline.stcatherineschool.rest.model.LunchResponseObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;



public interface AdEngineApi {

    @GET("local-link-ad")
    Call<AdUnit> getAd(@Query("platform") String platform,
                       @Query("schoolId") String schoolId,
                       @Query("impressionLocation") String impressionLocation);
}
