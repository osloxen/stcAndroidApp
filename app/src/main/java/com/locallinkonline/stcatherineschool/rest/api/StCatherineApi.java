package com.locallinkonline.stcatherineschool.rest.api;

import com.locallinkonline.stcatherineschool.rest.model.Activities;
import com.locallinkonline.stcatherineschool.rest.model.EighthGradeHomeworkSchedule;
import com.locallinkonline.stcatherineschool.rest.model.GradeschoolHomeworkSchedule;
import com.locallinkonline.stcatherineschool.rest.model.LunchResponseObject;
import com.locallinkonline.stcatherineschool.rest.model.SeventhGradeHomeworkSchedule;
import com.locallinkonline.stcatherineschool.rest.model.SixthGradeHomeworkSchedule;
import com.locallinkonline.stcatherineschool.rest.model.SportsSchedule;
import com.locallinkonline.stcatherineschool.room.entity.LunchEntity;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface StCatherineApi {

    @GET("activities/{activity}")
    Call<Activities> getActivityNotifications(@Path("activity") String activity);

    /*
    @GET("homework/{grade}")
    Call<List<GradeschoolHomework>> getThirdGradeHomework(@Path("grade") String grade,
                                                           @Query("startDate") String startDate,
                                                             @Query("endDate") String endDate);

    @GET("homework/{grade}")
    Call<List<GradeschoolHomework>> getFourthGradeHomework(@Path("grade") String grade,
                                                           @Query("startDate") String startDate,
                                                             @Query("endDate") String endDate);
*/

    @GET("homework/{grade}")
    Call<GradeschoolHomeworkSchedule> getGradeschoolHomework(@Path("grade") String grade,
                                                             @Query("startDate") String startDate,
                                                             @Query("endDate") String endDate);

    @GET("homework/grade6")
    Call<SixthGradeHomeworkSchedule> getSixthGradeHomework(@Query("startDate") String startDate,
                                                           @Query("endDate") String endDate);


    @GET("homework/grade7")
    Call<SeventhGradeHomeworkSchedule> getSeventhGradeHomework(@Query("startDate") String startDate,
                                                               @Query("endDate") String endDate);

    @GET("homework/grade8")
    Call<EighthGradeHomeworkSchedule> getEighthGradeHomework(@Query("startDate") String startDate,
                                                             @Query("endDate") String endDate);

    @GET("sports/{grade}")
    Call<SportsSchedule> getActivitySchedule(@Path("grade") String grade,
                                             @Query("startDate") String startDate,
                                             @Query("endDate") String endDate,
                                             @Query("sport") String sport,
                                             @Query("gender") String gender,
                                             @Query("eventType") String eventType);

    @GET("lunch")
    Call<LunchResponseObject> getLunches(@Query("lunchDate") String date);
}
