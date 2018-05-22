package com.locallinkonline.stcatherineschool.rest.controller;

import com.locallinkonline.stcatherineschool.rest.api.ActivityApi;
import com.locallinkonline.stcatherineschool.rest.model.Activities;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by dberge on 3/17/18.
 */

public class ActivityController extends BaseController {

    public Activities getActivityNotifications(String activity) {

        // TODO FIX EVERYTHING BELOW THIS!!!
        ActivityApi activityApi = retrofit.create(ActivityApi.class);
        Call<Activities> call = activityApi.getActivityNotifications(activity);

        Response<Activities> response;

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
