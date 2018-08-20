package com.locallinkonline.stcatherineschool.rest.liveData;

import android.text.format.DateUtils;
import android.util.Log;

import com.locallinkonline.stcatherineschool.rest.api.LunchApi;
import com.locallinkonline.stcatherineschool.rest.model.LunchResponseObject;

import java.util.Date;

import androidx.lifecycle.LiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LunchLiveData extends LiveData<LunchResponseObject> implements SchoolLiveData {

    private final LunchApi lunchApi = retrofit.create(LunchApi.class);

    public LunchLiveData() {
        loadData();
    }

    @Override
    protected void onActive() {
        loadData();
    }

    private void loadData() {
        String startDate = sdf.format(new Date());

        Call<LunchResponseObject> call = lunchApi.getLunches(startDate);

        call.enqueue(new Callback<LunchResponseObject>() {
            @Override
            public void onResponse(Call<LunchResponseObject> call, Response<LunchResponseObject> response) {
                setValue(response.body());
            }

            @Override
            public void onFailure(Call<LunchResponseObject> call, Throwable t) {
                Log.e("Error", t.getLocalizedMessage());
            }
        });
    }
}
