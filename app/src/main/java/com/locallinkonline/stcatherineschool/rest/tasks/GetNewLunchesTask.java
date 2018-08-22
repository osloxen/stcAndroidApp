package com.locallinkonline.stcatherineschool.rest.tasks;

import android.os.AsyncTask;

import com.google.gson.GsonBuilder;
import com.locallinkonline.stcatherineschool.rest.api.StCatherineApi;
import com.locallinkonline.stcatherineschool.rest.model.LunchResponseObject;
import com.locallinkonline.stcatherineschool.room.dao.LunchDao;
import com.locallinkonline.stcatherineschool.room.db.StCatherineDatabase;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GetNewLunchesTask extends AsyncTask<Void, Void, Void> {

    private final LunchDao dao;
    private final StCatherineApi lunchApi;

    public GetNewLunchesTask(StCatherineDatabase db, String adsUrl) {
        this.dao = db.lunchDao();
        this.lunchApi = new Retrofit.Builder()
                .baseUrl(adsUrl)
                .addConverterFactory(GsonConverterFactory.create(
                        new GsonBuilder().setLenient().create()))
                .build().create(StCatherineApi.class);
    }

    @Override
    protected Void doInBackground(Void... voids) {
        Call<LunchResponseObject> getLunchesCall = lunchApi.getLunches("");

        try {
            Response<LunchResponseObject> response = getLunchesCall.execute();
            LunchResponseObject lunches = response.body();

            if (lunches != null && lunches.getSchedule() != null && lunches.getSchedule().length > 0) {
                dao.insert(lunches.getSchedule());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}