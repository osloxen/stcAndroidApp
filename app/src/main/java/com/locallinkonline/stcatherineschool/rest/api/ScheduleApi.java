package com.locallinkonline.stcatherineschool.rest.api;

import com.locallinkonline.stcatherineschool.rest.model.SportsSchedule;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by dberge on 3/10/18.
 */

public interface ScheduleApi {

    @GET("sports/{grade}")
    Call<SportsSchedule> getActivitySchedule( @Path("grade") String grade,
                                              @Query("startDate") String startDate,
                                              @Query("endDate") String endDate,
                                              @Query("sport") String sport,
                                              @Query("gender") String gender,
                                              @Query("eventType") String eventType);

}
