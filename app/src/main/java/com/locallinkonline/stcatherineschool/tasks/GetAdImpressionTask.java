package com.locallinkonline.stcatherineschool.tasks;

import android.app.Fragment;
import android.os.AsyncTask;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.locallinkonline.stcatherineschool.R;
import com.locallinkonline.stcatherineschool.rest.api.AdEngineApi;
import com.locallinkonline.stcatherineschool.rest.model.AdUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GetAdImpressionTask extends AsyncTask<String,Void,AdUnit> {

    private final String baseUrl = "https://lndj19ck3k.execute-api.us-east-1.amazonaws.com/march2018green/";
    private final Gson gson = new GsonBuilder().setLenient().create();
    private final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();

    private static AdUnit ad;

    private final Fragment fragment;

    public GetAdImpressionTask(Fragment fragment) {
        this.fragment = fragment;
    }

    @Override
    protected AdUnit doInBackground(String... params) {
        return getAdImpression("android","1001","undefined");
    }

    @Override
    protected void onPostExecute(AdUnit result) {
        TextView adDisplay;
        adDisplay = this.fragment.getView().findViewById(R.id.localLinkAdBusiness);
        String adDisplayString = result != null ? result.getBusiness() + "\n" + result.getAdText() : "";
        adDisplay.setText(adDisplayString);
    }

    private AdUnit getAdImpression(String platform, String schoolId, String impressionLocation) {

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