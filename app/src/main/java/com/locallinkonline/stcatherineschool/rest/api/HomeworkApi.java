package com.locallinkonline.stcatherineschool.rest.api;


import com.locallinkonline.stcatherineschool.rest.model.EighthGradeHomeworkSchedule;
import com.locallinkonline.stcatherineschool.rest.model.GradeschoolHomeworkSchedule;
import com.locallinkonline.stcatherineschool.rest.model.SeventhGradeHomeworkSchedule;
import com.locallinkonline.stcatherineschool.rest.model.SixthGradeHomeworkSchedule;



import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by espaan on 3/5/18.
 */

public interface HomeworkApi {

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
}
