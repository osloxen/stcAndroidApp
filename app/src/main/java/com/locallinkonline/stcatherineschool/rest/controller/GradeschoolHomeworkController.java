package com.locallinkonline.stcatherineschool.rest.controller;

import com.locallinkonline.stcatherineschool.rest.api.HomeworkApi;
import com.locallinkonline.stcatherineschool.rest.model.EighthGradeHomeworkSchedule;
import com.locallinkonline.stcatherineschool.rest.model.GradeschoolHomeworkSchedule;
import com.locallinkonline.stcatherineschool.rest.model.HomeworkClassAllGrades;

import java.io.IOException;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by espaan on 3/5/18.
 */

public class GradeschoolHomeworkController extends BaseController {

    /*
    public GradeschoolHomeworkSchedule getHomework(String grade, Date startDate, Date endDate) {

        HomeworkApi homeworkApi = retrofit.create(HomeworkApi.class);
        Call<GradeschoolHomeworkSchedule> call = homeworkApi.getGradeschoolHomework(grade, sdf.format(startDate), sdf.format(endDate));

        Response<GradeschoolHomeworkSchedule> response;

        try {
                response = call.execute();
    */

    public GradeschoolHomeworkSchedule getHomework(String grade, Date startDate, Date endDate) {

        HomeworkApi homeworkApi = retrofit.create(HomeworkApi.class);
        Call<GradeschoolHomeworkSchedule> call = homeworkApi.getGradeschoolHomework(grade, sdf.format(startDate), sdf.format(endDate));

        Response<GradeschoolHomeworkSchedule> response;

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
}
