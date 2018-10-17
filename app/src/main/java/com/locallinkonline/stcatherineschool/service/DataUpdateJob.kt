package com.locallinkonline.stcatherineschool.service

import android.app.job.JobParameters
import android.app.job.JobService
import android.content.Context

import com.locallinkonline.stcatherineschool.R
import com.locallinkonline.stcatherineschool.rest.tasks.GetNewDataResourcesTask
import com.locallinkonline.stcatherineschool.room.db.StCatherineDatabase

class DataUpdateJob : JobService() {

    private var getNewDataResourcesTask: GetNewDataResourcesTask? = null

    override fun onStartJob(jobParameters: JobParameters): Boolean {

        if (getNewDataResourcesTask == null) {
            val ctx = this.applicationContext
            this.getNewDataResourcesTask = GetNewDataResourcesTask(
                    StCatherineDatabase.getDatabase(ctx)!!,
                    ctx.getString(R.string.stcBaseUrl),
                    this)
        }
        getNewDataResourcesTask!!.execute(jobParameters)
        return true
    }

    override fun onStopJob(jobParameters: JobParameters): Boolean {
        return false
    }
}
