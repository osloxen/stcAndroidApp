package com.locallinkonline.stcatherineschool.rest.controller;

import com.locallinkonline.stcatherineschool.rest.api.StCatherineApi;
import com.locallinkonline.stcatherineschool.rest.model.SportsSchedule;

import java.io.IOException;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by dberge on 3/10/18.
 */

public class ActivityScheduleController extends BaseController {

    public SportsSchedule getSchedule(String grade, Date startDate, Date endDate,
                                      String sport, String gender, String eventType) {

        // TODO FIX EVERYTHING BELOW THIS!!!
        StCatherineApi scheduleApi = retrofit.create(StCatherineApi.class);
        Call<SportsSchedule> call = scheduleApi.getActivitySchedule(grade,
                                                                    sdf.format(startDate),
                                                                    sdf.format(endDate),
                                                                    sport, gender, eventType);

        Response<SportsSchedule> response;

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
