package com.locallinkonline.stcatherineschool.rest.controller;

import com.locallinkonline.stcatherineschool.rest.api.LunchApi;
import com.locallinkonline.stcatherineschool.rest.model.Lunch;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by espaan on 3/5/18.
 */

public class LunchController extends BaseController {

    public List<Lunch> getLunches(Date startDate, Date endDate) {
        LunchApi lunchApi = retrofit.create(LunchApi.class);
        Call<List<Lunch>> call = lunchApi.getLunches(sdf.format(startDate), sdf.format(endDate));

        Response<List<Lunch>> response;

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
