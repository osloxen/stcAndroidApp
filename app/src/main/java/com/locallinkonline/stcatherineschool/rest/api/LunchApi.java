package com.locallinkonline.stcatherineschool.rest.api;

import com.locallinkonline.stcatherineschool.rest.model.Lunch;
import com.locallinkonline.stcatherineschool.rest.model.LunchResponseObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by espaan on 3/5/18.
 */

public interface LunchApi {

    @GET("lunch")
    Call<LunchResponseObject> getLunches(@Query("startDate") String startDate, @Query("endDate") String endDate);
}
