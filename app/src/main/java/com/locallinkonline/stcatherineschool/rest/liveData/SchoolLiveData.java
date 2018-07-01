package com.locallinkonline.stcatherineschool.rest.liveData;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.text.SimpleDateFormat;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public interface SchoolLiveData{
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    String baseUrl = "https://telbelahfa.execute-api.us-east-1.amazonaws.com/prodgreen/stc/";
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(
                    new GsonBuilder()
                            .setLenient()
                            .create()))
            .build();
}
