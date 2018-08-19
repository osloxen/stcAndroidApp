package com.locallinkonline.locallinkschool.room.db;

import android.content.Context;
import android.os.AsyncTask;

import com.google.gson.GsonBuilder;
import com.locallinkonline.locallinkschool.R;
import com.locallinkonline.locallinkschool.rest.api.AdEngineApi;
import com.locallinkonline.locallinkschool.room.entity.AdEntity;
import com.locallinkonline.locallinkschool.room.dao.AdDao;

import java.io.IOException;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Database(entities = {AdEntity.class}, version = 1, exportSchema = false)
public abstract class AdDatabase extends RoomDatabase {

    public abstract AdDao adDao();

    private static AdDatabase INSTANCE;

    public static AdDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AdDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            AdDatabase.class,
                            "ad_database")
                            .addCallback(new RoomDatabase.Callback() {
                                @Override
                                public void onOpen(@NonNull SupportSQLiteDatabase db) {
                                    super.onOpen(db);
                                    new InsertAdTask(INSTANCE,
                                                     context.getString(R.string.adsUrl)).execute();
                                }
                            }).build();
                }
            }
        }
        return INSTANCE;
    }

    private static class InsertAdTask extends AsyncTask<Void, Void, Void> {

        private final AdDao dao;
        private final AdEngineApi adEngineApi;

        private InsertAdTask(AdDatabase db, String adsUrl) {
            this.dao = db.adDao();
            this.adEngineApi = new Retrofit.Builder()
                    .baseUrl(adsUrl)
                    .addConverterFactory(GsonConverterFactory.create(
                            new GsonBuilder().setLenient().create()))
                    .build().create(AdEngineApi.class);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            Call<AdEntity[]> getAdsCall = adEngineApi.getAllAds(
                    "android",
                    "1001",
                    "undefined");

            try {
                Response<AdEntity[]> response = getAdsCall.execute();
                AdEntity[] ads = response.body();

                if (ads != null && ads.length > 0) {
                    dao.insert(ads);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
