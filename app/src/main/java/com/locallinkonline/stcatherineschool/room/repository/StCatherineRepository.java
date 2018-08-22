package com.locallinkonline.stcatherineschool.room.repository;

import android.app.Application;

import com.google.gson.GsonBuilder;
import com.locallinkonline.stcatherineschool.R;
import com.locallinkonline.stcatherineschool.rest.api.StCatherineApi;
import com.locallinkonline.stcatherineschool.rest.tasks.GetNewLunchesTask;
import com.locallinkonline.stcatherineschool.room.dao.LunchDao;
import com.locallinkonline.stcatherineschool.room.db.StCatherineDatabase;
import com.locallinkonline.stcatherineschool.room.entity.LunchEntity;

import androidx.lifecycle.LiveData;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class StCatherineRepository {
    private final LunchDao lunchDao;
    private final StCatherineDatabase db;
    private final String url;

    public StCatherineRepository(Application application) {
        this.db = StCatherineDatabase.getDatabase(application);
        this.lunchDao = db.lunchDao();
        this.url = application.getString(R.string.stcBaseUrl);
    }

    public LiveData<LunchEntity[]> getLunches() {
        return lunchDao.getAllLunches();
    }

    public void checkForNewLunches() {
        new GetNewLunchesTask(db, url).execute();
    }
}
