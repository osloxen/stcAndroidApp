package com.locallinkonline.stcatherineschool.rest.api;

import com.locallinkonline.stcatherineschool.rest.model.EighthGradeHomework;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by espaan on 3/5/18.
 */

public interface HomeworkApi {

    @GET("homework/grade7")
    Call<List<EighthGradeHomework>> getSeventhGradeHomework(@Query("startDate") String startDate,
                                                            @Query("endDate") String endDate);

    @GET("homework/grade8")
    Call<List<EighthGradeHomework>> getEighthGradeHomework(@Query("startDate") String startDate,
                                                           @Query("endDate") String endDate);
}
