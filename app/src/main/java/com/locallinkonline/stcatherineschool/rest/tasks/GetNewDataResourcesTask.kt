package com.locallinkonline.stcatherineschool.rest.tasks

import android.app.job.JobParameters
import android.app.job.JobService

import com.locallinkonline.stcatherineschool.rest.model.DataResourceResponse
import com.locallinkonline.stcatherineschool.room.dao.DataResourcesDao
import com.locallinkonline.stcatherineschool.room.db.StCatherineDatabase
import com.locallinkonline.stcatherineschool.room.entity.MenuEntity

import java.io.IOException
import java.util.Collections

import retrofit2.Call
import retrofit2.Response

class GetNewDataResourcesTask(db: StCatherineDatabase, url: String, private val job: JobService?) : StCatherineTask<JobParameters>(db, url) {

    private val dataResourcesDao: DataResourcesDao = db.dataResourcesDao()

    override fun doInBackground(vararg args: JobParameters): Void? {
        val dataResourceResponseCall = stCatherineApi.dataResources

        var success: Boolean

        try {
            val response = dataResourceResponseCall.execute()
            val menuEntities = response.body()!!.resourceList

            if (menuEntities != null && menuEntities.isNotEmpty()) {
                dataResourcesDao.updateMenuItems(menuEntities)
            }

            success = true
        } catch (e: IOException) {
            success = false
        }

        if (args.size > 0 &&job != null) {
            job.jobFinished(args[0], !success)
        }

        return null
    }
}
