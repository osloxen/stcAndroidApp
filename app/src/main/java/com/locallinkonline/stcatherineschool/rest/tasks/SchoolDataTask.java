package com.locallinkonline.stcatherineschool.rest.tasks;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public abstract class SchoolDataTask<I, P, R> extends AsyncTask<I,P,R> {

    final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private final String baseUrl = "https://103hytduv6.execute-api.us-west-2.amazonaws.com/dev";
    private final Gson gson = new GsonBuilder().setLenient().create();
    protected final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();
}
