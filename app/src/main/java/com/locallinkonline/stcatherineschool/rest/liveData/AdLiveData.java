package com.locallinkonline.stcatherineschool.rest.liveData;

import com.google.gson.GsonBuilder;
import com.locallinkonline.stcatherineschool.rest.api.AdEngineApi;
import com.locallinkonline.stcatherineschool.rest.model.AdUnit;

import androidx.lifecycle.LiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AdLiveData extends LiveData<AdUnit> {

    private final String adsBaseUrl = "https://lndj19ck3k.execute-api.us-east-1.amazonaws.com/march2018green/";
    private final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(adsBaseUrl)
            .addConverterFactory(GsonConverterFactory.create(
                    new GsonBuilder()
                            .setLenient()
                            .create()))
            .build();
    private final AdEngineApi adEngineApi = retrofit.create(AdEngineApi.class);

    public AdLiveData() {
        loadData();
    }

    private void loadData() {
        Call<AdUnit> call = adEngineApi.getAd("android", "1001", "undefined");

        call.enqueue(new Callback<AdUnit>() {
            @Override
            public void onResponse(Call<AdUnit> call, Response<AdUnit> response) {
                setValue(response.body());
            }

            @Override
            public void onFailure(Call<AdUnit> call, Throwable t) {

            }
        });
    }

    @Override
    protected void onActive() {
        loadData();
    }
}
