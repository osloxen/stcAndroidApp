package com.locallinkonline.stcatherineschool.service;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Context;

import com.locallinkonline.stcatherineschool.R;
import com.locallinkonline.stcatherineschool.rest.tasks.GetNewDataResourcesTask;
import com.locallinkonline.stcatherineschool.room.db.StCatherineDatabase;

public class DataUpdateJob extends JobService {

    private GetNewDataResourcesTask getNewDataResourcesTask;

    public DataUpdateJob() {
    }

    @Override
    public boolean onStartJob(JobParameters jobParameters) {

        if(getNewDataResourcesTask == null) {
            Context ctx = this.getApplicationContext();
            this.getNewDataResourcesTask =
                    new GetNewDataResourcesTask(
                            StCatherineDatabase.getDatabase(ctx),
                            ctx.getString(R.string.stcBaseUrl),
                            this);
        }
        getNewDataResourcesTask.execute(jobParameters);
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        return false;
    }
}
