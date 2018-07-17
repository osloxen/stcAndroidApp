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

    public void checkForNewAd() {
        Call<List<AdEntity>> getAdsCall = adEngineApi.getAllAds(
                "android",
                "1001",
                "undefined");

        getAdsCall.enqueue(new Callback<List<AdEntity>>() {
            @Override
            public void onResponse(Call<List<AdEntity>> call, Response<List<AdEntity>> response) {
                List<AdEntity> ads = response.body();

                if(ads != null && ads.size() > 0) {
                    AdEntity ad = ads.get(0);
                    insert(ad);
                    clearDisabledAds(ads);
                }
            }

            @Override
            public void onFailure(Call<List<AdEntity>> call, Throwable t) {
                System.out.print("TEST");
            }
        });
    }

    private void clearDisabledAds(List<AdEntity> ads) { new clearDisabledAdsTask(mAdDao).execute(ads); }

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

    private class clearDisabledAdsTask extends AsyncTask<List<AdEntity>, Void, Void> {
        private final AdDao adDao;

        clearDisabledAdsTask(AdDao adDao) {
            this.adDao = adDao;
        }

        @Override
        protected Void doInBackground(List<AdEntity>... lists) {
            List<AdEntity> adEntities = lists[0];

            String[] ids = new String[adEntities.size()];

            for(int i = 0; i < adEntities.size(); i++) {
                AdEntity ad = adEntities.get(i);
                ids[i] = ad.getBusinessId();
            }

            if(ids.length > 0) {
                adDao.deleteAds(ids);
            }
            return null;
        }
    }
}
