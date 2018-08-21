package com.locallinkonline.stcatherineschool.room.db;

import android.content.Context;
import android.os.AsyncTask;

import com.google.gson.GsonBuilder;
import com.locallinkonline.stcatherineschool.R;
import com.locallinkonline.stcatherineschool.rest.api.StCatherineApi;
import com.locallinkonline.stcatherineschool.rest.model.LunchResponseObject;
import com.locallinkonline.stcatherineschool.room.converter.DateConverter;
import com.locallinkonline.stcatherineschool.room.dao.LunchDao;
import com.locallinkonline.stcatherineschool.room.entity.LunchEntity;

import java.io.IOException;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Database(entities = {LunchEntity.class}, version = 1, exportSchema = false)
@TypeConverters({DateConverter.class})
public abstract class StCatherineDatabase extends RoomDatabase {
    public abstract LunchDao lunchDao();

    private static StCatherineDatabase INSTANCE;

    public static StCatherineDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (StCatherineDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            StCatherineDatabase.class,
                            "lunch_database")
                            .addCallback(new RoomDatabase.Callback() {
                                @Override
                                public void onOpen(@NonNull SupportSQLiteDatabase db) {
                                    super.onOpen(db);
                                    new StCatherineDatabase.InsertLunchTask(INSTANCE,
                                            context.getString(R.string.stcBaseUrl)).execute();
                                }
                            }).build();
                }
            }
        }
        return INSTANCE;
    }

    private static class InsertLunchTask extends AsyncTask<Void, Void, Void> {

        private final LunchDao dao;
        private final StCatherineApi lunchApi;

        private InsertLunchTask(StCatherineDatabase db, String adsUrl) {
            this.dao = db.lunchDao();
            this.lunchApi = new Retrofit.Builder()
                    .baseUrl(adsUrl)
                    .addConverterFactory(GsonConverterFactory.create(
                            new GsonBuilder().setLenient().create()))
                    .build().create(StCatherineApi.class);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            Call<LunchResponseObject> getLunchesCall = lunchApi.getLunches("");

            try {
                Response<LunchResponseObject> response = getLunchesCall.execute();
                LunchResponseObject lunches = response.body();

                if (lunches != null && lunches.getSchedule() != null && lunches.getSchedule().length > 0) {
                    dao.insert(lunches.getSchedule());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
