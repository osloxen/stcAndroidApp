package com.locallinkonline.stcatherineschool.room.repository;

import android.app.Application;

import com.locallinkonline.stcatherineschool.R;
import com.locallinkonline.stcatherineschool.rest.tasks.GetNewDataResourcesTask;
import com.locallinkonline.stcatherineschool.rest.tasks.GetNewLunchesTask;
import com.locallinkonline.stcatherineschool.rest.tasks.GetNewScheduleTask;
import com.locallinkonline.stcatherineschool.room.dao.DataResourcesDao;
import com.locallinkonline.stcatherineschool.room.dao.LunchDao;
import com.locallinkonline.stcatherineschool.room.dao.SchoolScheduleDao;
import com.locallinkonline.stcatherineschool.room.db.StCatherineDatabase;
import com.locallinkonline.stcatherineschool.room.entity.LunchEntity;
import com.locallinkonline.stcatherineschool.room.entity.MenuEntity;
import com.locallinkonline.stcatherineschool.room.entity.ScheduleEntity;

import java.util.Date;
import java.util.List;

import androidx.lifecycle.LiveData;

public class StCatherineRepository {
    private final LunchDao lunchDao;
    private final StCatherineDatabase db;
    private final String url;
    private final SchoolScheduleDao schoolScheduleDao;
    private final DataResourcesDao dataResourcesDao;

    public StCatherineRepository(Application application) {
        this.db = StCatherineDatabase.getDatabase(application);
        this.lunchDao = db.lunchDao();
        this.schoolScheduleDao = db.schoolScheduleDao();
        this.dataResourcesDao = db.dataResourcesDao();
        this.url = application.getString(R.string.stcBaseUrl);
    }

    public LiveData<List<LunchEntity>> getLunches() {
        return lunchDao.getAllLunches();
    }

    public LiveData<List<ScheduleEntity>> getSchoolSchedule() { return  schoolScheduleDao.getSchedule(new Date()); }

    public List<String> getMenuGroups() { return dataResourcesDao.getGroups(); }

    public List<MenuEntity> getMenuItemsForGroup(String groupName) { return dataResourcesDao.getItemsForMenu(groupName); }

    public void checkForNewLunches() {
        new GetNewLunchesTask(db, url).execute();
    }

    public void updateSchedule(String[] args) { new GetNewScheduleTask(db, url).execute(args); }

    public void getNewMenuItems() { new GetNewDataResourcesTask(db, url).execute(); }

    public LiveData<List<ScheduleEntity>> getSchedule(String identifier) {
        return schoolScheduleDao.getSchedule(identifier, new Date());
    }
}
