package com.locallinkonline.stcatherineschool.room.db;

import android.content.Context;
import android.os.AsyncTask;

import com.google.gson.GsonBuilder;
import com.locallinkonline.stcatherineschool.rest.api.AdEngineApi;
import com.locallinkonline.stcatherineschool.rest.model.AdUnit;
import com.locallinkonline.stcatherineschool.room.dao.AdDao;
import com.locallinkonline.stcatherineschool.room.entity.AdEntity;

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

    private static final AdEngineApi adEngineApi = new Retrofit.Builder()
            .baseUrl("https://lndj19ck3k.execute-api.us-east-1.amazonaws.com/march2018green/")
            .addConverterFactory(GsonConverterFactory.create(
                    new GsonBuilder().setLenient().create()))
            .build().create(AdEngineApi.class);

    public abstract AdDao adDao();

    private static AdDatabase INSTANCE;

    public static AdDatabase getDatabase(final Context context) {
        if(INSTANCE == null) {
            synchronized (AdDatabase.class) {
                if(INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            AdDatabase.class,
                            "ad_database")
                            .addCallback(new RoomDatabase.Callback() {
                                @Override
                                public void onOpen(@NonNull SupportSQLiteDatabase db) {
                                    super.onOpen(db);
                                    new InsertAdTask(INSTANCE).execute();
                                }
                            }).build();
                }
            }
        }
        return INSTANCE;
    }

    private static class InsertAdTask extends AsyncTask<Void, Void, Void> {

        private final AdDao dao;

        private InsertAdTask(AdDatabase db) {
            this.dao = db.adDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            Call<AdUnit> getAdsCall = adEngineApi.getAd(
                    "android",
                    "1001",
                    "undefined");

            try {
                Response<AdUnit> response = getAdsCall.execute();
                AdUnit ad = response.body();
                if(ad != null) {
                    dao.insert(new AdEntity(ad.getBusinessId(), ad.getBusiness(), ad.getAdTitle(), ad.getAdText()));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
