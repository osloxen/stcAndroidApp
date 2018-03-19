package com.locallinkonline.stcatherineschool.rest.api;

import com.locallinkonline.stcatherineschool.rest.model.Activities;
import com.locallinkonline.stcatherineschool.rest.model.SportsSchedule;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by dberge on 3/17/18.
 */

public interface ActivityApi {

    @GET("activities/{activity}")
    Call<Activities> getActivityNotifications(@Path("activity") String activity);
}
