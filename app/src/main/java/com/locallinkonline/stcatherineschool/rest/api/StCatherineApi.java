package com.locallinkonline.stcatherineschool.rest.api;

import com.locallinkonline.stcatherineschool.rest.model.LunchResponseObject;
import com.locallinkonline.stcatherineschool.rest.model.SchoolSchedule;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface StCatherineApi {

    @GET("school-schedule")
    Call<SchoolSchedule> getSchoolSchedule();

    @GET("lunch")
    Call<LunchResponseObject> getLunches(@Query("lunchDate") String date);
}
