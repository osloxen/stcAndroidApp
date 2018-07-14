package com.locallinkonline.stcatherineschool.room.repository;

import android.app.Application;
import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.locallinkonline.stcatherineschool.rest.api.AdEngineApi;
import com.locallinkonline.stcatherineschool.rest.model.AdUnit;
import com.locallinkonline.stcatherineschool.room.dao.AdDao;
import com.locallinkonline.stcatherineschool.room.db.AdDatabase;
import com.locallinkonline.stcatherineschool.room.entity.AdEntity;

import java.util.List;

import androidx.lifecycle.LiveData;
import lombok.Getter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AdRepository {

    private AdDao mAdDao;

    private final AdEngineApi adEngineApi = new Retrofit.Builder()
            .baseUrl("https://lndj19ck3k.execute-api.us-east-1.amazonaws.com/march2018green/")
            .addConverterFactory(GsonConverterFactory.create(
                    new GsonBuilder().setLenient().create()))
            .build().create(AdEngineApi.class);

    public AdRepository(Application application) {
        AdDatabase db = AdDatabase.getDatabase(application);
        mAdDao = db.adDao();
    }

    public List<AdEntity> getAds() {
        return mAdDao.getAllAds();
    }

    public void checkForNewAd() {
        Call<AdUnit> getAdsCall = adEngineApi.getAd(
                "android",
                "1001",
                "undefined");

        getAdsCall.enqueue(new Callback<AdUnit>() {
            @Override
            public void onResponse(Call<AdUnit> call, Response<AdUnit> response) {
                AdUnit ad = response.body();

                insert(new AdEntity(ad.getBusinessId(), ad.getBusiness(), ad.getAdTitle(), ad.getAdText()));
            }

            @Override
            public void onFailure(Call<AdUnit> call, Throwable t) {

            }
        });
    }

    private void insert(AdEntity ad) {
        new insertAdTask(mAdDao).execute(ad);
    }

    private static class insertAdTask extends AsyncTask<AdEntity, Void, Void> {

        private AdDao mAdDao;

        insertAdTask(AdDao dao) {
            mAdDao = dao;
        }

        @Override
        protected Void doInBackground(AdEntity... adEntities) {
            mAdDao.insert(adEntities[0]);
            return null;
        }
    }
}
