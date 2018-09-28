package com.locallinkonline.stcatherineschool.rest.tasks;

import android.app.job.JobParameters;
import android.app.job.JobService;

import com.locallinkonline.stcatherineschool.rest.model.DataResourceResponse;
import com.locallinkonline.stcatherineschool.room.dao.DataResourcesDao;
import com.locallinkonline.stcatherineschool.room.db.StCatherineDatabase;
import com.locallinkonline.stcatherineschool.room.entity.MenuEntity;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class GetNewDataResourcesTask extends StCatherineTask<JobParameters> {

    private DataResourcesDao dataResourcesDao;

    private final JobService job;

    public GetNewDataResourcesTask(StCatherineDatabase db, String url, JobService job) {
        super(db, url);
        this.dataResourcesDao = db.dataResourcesDao();
        this.job = job;
    }

    @Override
    protected Void doInBackground(JobParameters... args) {
        Call<DataResourceResponse> dataResourceResponseCall = stCatherineApi.getDataResources();

        boolean success;

        try
        {
            Response<DataResourceResponse> response = dataResourceResponseCall.execute();
            List<MenuEntity> menuEntities = response.body() != null ? response.body().getResourceList() : Collections.EMPTY_LIST;

            if(menuEntities != null && menuEntities.size() > 0) {
                dataResourcesDao.updateMenuItems(menuEntities);
            }

            success = true;
        } catch (IOException e) {
            success = false;
        }

        if(args != null && args.length > 0) {
            job.jobFinished(args[0], !success);
        }

        return null;
    }
}
