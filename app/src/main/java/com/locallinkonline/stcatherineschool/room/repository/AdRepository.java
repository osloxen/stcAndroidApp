package com.locallinkonline.stcatherineschool.room.repository;

import android.app.Application;
import android.os.AsyncTask;

import com.google.gson.GsonBuilder;
import com.locallinkonline.stcatherineschool.rest.api.AdEngineApi;
import com.locallinkonline.stcatherineschool.room.dao.AdDao;
import com.locallinkonline.stcatherineschool.room.db.AdDatabase;
import com.locallinkonline.stcatherineschool.room.entity.AdEntity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AdRepository {

    private AdDao mAdDao;

    private final AdEngineApi adEngineApi = new Retrofit.Builder()
            .baseUrl("https://6qft2bh965.execute-api.us-west-2.amazonaws.com/dev/")
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

    public void checkForNewAds() {
        Call<AdEntity[]> getAdsCall = adEngineApi.getAllAds(
                "android",
                "1001",
                "undefined");

        getAdsCall.enqueue(new Callback<AdEntity[]>() {
            @Override
            public void onResponse(Call<AdEntity[]> call, Response<AdEntity[]> response) {
                AdEntity[] ads = response.body();

                if(ads != null && ads.length > 0) {
                    insert(ads);
                    clearDisabledAds(ads);
                }
            }

            @Override
            public void onFailure(Call<AdEntity[]> call, Throwable t) {
                System.out.print("TEST");
            }
        });
    }

    private void clearDisabledAds(AdEntity[] ads) { new clearDisabledAdsTask(mAdDao).execute(ads); }

    private void insert(AdEntity[] ads) {
        new insertAdTask(mAdDao).execute(ads);
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

    private static class clearDisabledAdsTask extends AsyncTask<AdEntity, Void, Void> {
        private final AdDao adDao;

        clearDisabledAdsTask(AdDao adDao) {
            this.adDao = adDao;
        }

        @Override
        protected Void doInBackground(AdEntity... ads) {

            String[] ids = new String[ads.length];

            for(int i = 0; i < ads.length; i++) {
                ids[i] = ads[i].getBusinessId();
            }

            if(ids.length > 0) {
                adDao.deleteAds(ids);
            }
            return null;
        }
    }
}
