package com.locallinkonline.stcatherineschool.rest.tasks;

import android.os.AsyncTask;

import com.google.gson.GsonBuilder;
import com.locallinkonline.stcatherineschool.rest.api.StCatherineApi;
import com.locallinkonline.stcatherineschool.room.db.StCatherineDatabase;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public abstract class StCatherineTask extends AsyncTask<Void, Void, Void> {

    final StCatherineApi stCatherineApi;

    public StCatherineTask(StCatherineDatabase db, String url) {
        this.stCatherineApi = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create(
                        new GsonBuilder().setLenient().create()))
                .build().create(StCatherineApi.class);
    }
}
