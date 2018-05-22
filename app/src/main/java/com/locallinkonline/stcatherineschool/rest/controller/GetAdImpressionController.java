package com.locallinkonline.stcatherineschool.rest.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.locallinkonline.stcatherineschool.rest.api.AdEngineApi;
import com.locallinkonline.stcatherineschool.rest.model.AdUnit;
import java.io.IOException;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GetAdImpressionController {

    private final String baseUrl = "https://lndj19ck3k.execute-api.us-east-1.amazonaws.com/march2018green/";
    private final Gson gson = new GsonBuilder().setLenient().create();
    private final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();

    private static AdUnit ad;

    public AdUnit getAdImpression(String platform, String schoolId, String impressionLocation) {

        AdEngineApi adEngineApi = retrofit.create(AdEngineApi.class);

        Call<AdUnit> call = adEngineApi.getAd(platform,  schoolId,  impressionLocation);

        call.enqueue(new Callback<AdUnit>() {
            @Override
            public void onResponse(Call<AdUnit> call, Response<AdUnit> response) {
                ad = response.body();
            }

            @Override
            public void onFailure(Call<AdUnit> call, Throwable t) {
                //TODO: Do nothing on failure for now. Perhaps we should have a
            }
        });

        if(ad != null) {
            logImpression(ad);
        }

        return ad;
    }

    private void logImpression(AdUnit ad) {
        //TODO: Implement true impression logging
    }
}
