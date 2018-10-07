package com.locallinkonline.stcatherineschool.rest.api;

import com.locallinkonline.stcatherineschool.rest.model.DataResourceResponse;
import com.locallinkonline.stcatherineschool.rest.model.LunchResponseObject;
import com.locallinkonline.stcatherineschool.rest.model.Schedule;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface StCatherineApi {

    @GET("school-schedule")
    Call<Schedule> getSchoolSchedule();

    @GET("activity")
    Call<Schedule> getActivitySchedule(@Query("activityId") String activityId);

    @GET("activity")
    Call<Schedule> getHomeworkSchedule(@Query("id") String id);

    @GET("lunch")
    Call<LunchResponseObject> getLunches(@Query("lunchDate") String date);

    @GET("data-resources")
    Call<DataResourceResponse> getDataResources();
}
