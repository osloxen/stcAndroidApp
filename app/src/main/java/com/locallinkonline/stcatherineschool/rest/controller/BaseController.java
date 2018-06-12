package com.locallinkonline.stcatherineschool.rest.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.text.SimpleDateFormat;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by espaan on 3/5/18.
 */

abstract class BaseController {
    final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private final String baseUrl = "https://telbelahfa.execute-api.us-east-1.amazonaws.com/prodgreen/stc/";
    private final Gson gson = new GsonBuilder().setLenient().create();
    final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();
}
