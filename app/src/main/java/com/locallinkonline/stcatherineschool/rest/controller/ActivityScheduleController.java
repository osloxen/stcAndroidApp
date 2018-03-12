package com.locallinkonline.stcatherineschool.rest.controller;

import com.locallinkonline.stcatherineschool.rest.api.LunchApi;
import com.locallinkonline.stcatherineschool.rest.api.ScheduleApi;
import com.locallinkonline.stcatherineschool.rest.model.LunchResponseObject;
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
        ScheduleApi scheduleApi = retrofit.create(ScheduleApi.class);
        Call<SportsSchedule> call = scheduleApi.getActivitySchedule(grade,
                                                                    sdf.format(startDate),
                                                                    sdf.format(endDate),
                                                                    sport, gender, eventType);

        Response<SportsSchedule> response;

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
