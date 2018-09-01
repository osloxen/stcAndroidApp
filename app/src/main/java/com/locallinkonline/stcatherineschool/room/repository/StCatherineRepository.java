package com.locallinkonline.stcatherineschool.room.repository;

import android.app.Application;

import com.locallinkonline.stcatherineschool.R;
import com.locallinkonline.stcatherineschool.rest.tasks.GetNewLunchesTask;
import com.locallinkonline.stcatherineschool.rest.tasks.GetNewScheduleTask;
import com.locallinkonline.stcatherineschool.room.dao.LunchDao;
import com.locallinkonline.stcatherineschool.room.dao.SchoolScheduleDao;
import com.locallinkonline.stcatherineschool.room.db.StCatherineDatabase;
import com.locallinkonline.stcatherineschool.room.entity.LunchEntity;
import com.locallinkonline.stcatherineschool.room.entity.SchoolScheduleEntity;

import java.util.Date;
import java.util.List;

import androidx.lifecycle.LiveData;

public class StCatherineRepository {
    private final LunchDao lunchDao;
    private final StCatherineDatabase db;
    private final String url;
    private final SchoolScheduleDao schoolScheduleDao;

    public StCatherineRepository(Application application) {
        this.db = StCatherineDatabase.getDatabase(application);
        this.lunchDao = db.lunchDao();
        this.schoolScheduleDao = db.schoolScheduleDao();
        this.url = application.getString(R.string.stcBaseUrl);
    }

    public LiveData<List<LunchEntity>> getLunches() {
        return lunchDao.getAllLunches();
    }

    public LiveData<List<SchoolScheduleEntity>> getSchoolSchedule() { return  schoolScheduleDao.getSchedule(new Date()); }

    public void checkForNewLunches() {
        new GetNewLunchesTask(db, url).execute();
    }

    public void updateSchedule() { new GetNewScheduleTask(db, url).execute(); }
}
