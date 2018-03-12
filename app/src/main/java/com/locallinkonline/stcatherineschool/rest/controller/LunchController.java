package com.locallinkonline.stcatherineschool.rest.controller;

import com.locallinkonline.stcatherineschool.rest.api.LunchApi;
import com.locallinkonline.stcatherineschool.rest.model.Lunch;
import com.locallinkonline.stcatherineschool.rest.model.LunchResponseObject;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by espaan on 3/5/18.
 */

public class LunchController extends BaseController {

    public LunchResponseObject getLunches(Date startDate, Date endDate) {

        LunchApi lunchApi = retrofit.create(LunchApi.class);
        Call<LunchResponseObject> call = lunchApi.getLunches(sdf.format(startDate), sdf.format(endDate));

        Response<LunchResponseObject> response;

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
