package com.locallinkonline.stcatherineschool.tasks;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.locallinkonline.stcatherineschool.rest.model.LunchResponseObject;

import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public abstract class SchoolDataTask<I, P, R> extends AsyncTask<I,P,R> {

    protected final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    protected final String baseUrl = "https://telbelahfa.execute-api.us-east-1.amazonaws.com/prodgreen/stc/";
    protected final Gson gson = new GsonBuilder().setLenient().create();
    protected final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();
}
