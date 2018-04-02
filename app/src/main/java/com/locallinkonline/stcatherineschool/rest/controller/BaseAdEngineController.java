package com.locallinkonline.stcatherineschool.rest.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.text.SimpleDateFormat;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



public abstract class BaseAdEngineController {
//    protected final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    protected final String baseUrl = "https://lndj19ck3k.execute-api.us-east-1.amazonaws.com/march2018green/";
    protected final Gson gson = new GsonBuilder().setLenient().create();
    protected final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();
}
