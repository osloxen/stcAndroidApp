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
    private final String baseUrl = "https://telbelahfa.execute-api.us-east-1.amazonaws.com/prodgreen/stc/";
    private final Gson gson = new GsonBuilder().setLenient().create();
    protected final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();
}
