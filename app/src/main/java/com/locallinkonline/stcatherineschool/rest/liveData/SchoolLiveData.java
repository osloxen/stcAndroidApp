package com.locallinkonline.stcatherineschool.rest.liveData;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.text.SimpleDateFormat;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public interface SchoolLiveData{
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    String baseUrl = "https://103hytduv6.execute-api.us-west-2.amazonaws.com/dev/";
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(
                    new GsonBuilder()
                            .setLenient()
                            .create()))
            .build();
}
