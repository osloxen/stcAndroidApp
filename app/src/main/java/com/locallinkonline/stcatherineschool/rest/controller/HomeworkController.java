package com.locallinkonline.stcatherineschool.rest.controller;

import com.locallinkonline.stcatherineschool.rest.api.HomeworkApi;
import com.locallinkonline.stcatherineschool.rest.model.EighthGradeHomeworkSchedule;
import com.locallinkonline.stcatherineschool.rest.model.SeventhGradeHomeworkSchedule;
import com.locallinkonline.stcatherineschool.rest.model.SixthGradeHomeworkSchedule;

import java.io.IOException;
import java.util.Date;


import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by espaan on 3/5/18.
 */

public class HomeworkController extends BaseController {

    public EighthGradeHomeworkSchedule getHomeworkEigthGrade(Date startDate, Date endDate) {

        HomeworkApi homeworkApi = retrofit.create(HomeworkApi.class);
        Call<EighthGradeHomeworkSchedule> call = homeworkApi.getEighthGradeHomework(sdf.format(startDate), sdf.format(endDate));

        Response<EighthGradeHomeworkSchedule> response;

        try {
                response = call.execute();

            /*
            response = call.enqueue(new Callback<List<Lunch>>() {
                @Override
                public void onResponse(Call<MyItem> call, Response<MyItem> response) {
                    MyItem myItem=response.body();
                }

                @Override
                public void onFailure(Call<MyItem> call, Throwable t) {
                    //Handle failure
                }
            });
            */

        } catch (IOException e) {
            response = null;
        }

        if(response != null && response.isSuccessful()) {
            return response.body();
        }

        return null;
    }


    public SeventhGradeHomeworkSchedule getHomeworkSeventhGrade(Date startDate, Date endDate) {

        HomeworkApi homeworkApi = retrofit.create(HomeworkApi.class);
        Call<SeventhGradeHomeworkSchedule> call = homeworkApi.getSeventhGradeHomework(sdf.format(startDate), sdf.format(endDate));

        Response<SeventhGradeHomeworkSchedule> response;

        try {
            response = call.execute();


        } catch (IOException e) {
            response = null;
        }

        if(response != null && response.isSuccessful()) {
            return response.body();
        }

        return null;
    }


    public SixthGradeHomeworkSchedule getHomeworkSixthGrade(Date startDate, Date endDate) {

        HomeworkApi homeworkApi = retrofit.create(HomeworkApi.class);
        Call<SixthGradeHomeworkSchedule> call = homeworkApi.getSixthGradeHomework(sdf.format(startDate), sdf.format(endDate));

        Response<SixthGradeHomeworkSchedule> response;

        try {
            response = call.execute();


        } catch (IOException e) {
            response = null;
        }

        if(response != null && response.isSuccessful()) {
            return response.body();
        }

        return null;
    }


}
