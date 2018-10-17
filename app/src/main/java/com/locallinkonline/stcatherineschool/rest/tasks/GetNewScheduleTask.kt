package com.locallinkonline.stcatherineschool.rest.tasks

import com.locallinkonline.stcatherineschool.rest.model.Schedule
import com.locallinkonline.stcatherineschool.room.dao.SchoolScheduleDao
import com.locallinkonline.stcatherineschool.room.db.StCatherineDatabase
import com.locallinkonline.stcatherineschool.room.entity.ScheduleEntity

import java.io.IOException

import retrofit2.Call
import retrofit2.Response

class GetNewScheduleTask(db: StCatherineDatabase, url: String) : StCatherineTask<String>(db, url) {

    internal var dao: SchoolScheduleDao = db.schoolScheduleDao()

    override fun doInBackground(vararg args: String): Void? {

        val identifier = if (args.size > 0) args[0] else "school-schedule"
        val type = if (args.size > 1) args[1] else "SchoolSchedule"

        val call: Call<Schedule>

        if ("Homework" == type) {
            call = stCatherineApi.getHomeworkSchedule(identifier)
        } else if ("Sports" == type) {
            call = stCatherineApi.getActivitySchedule(identifier)
        } else {
            call = stCatherineApi.schoolSchedule
        }

        try {
            val response = call.execute()

            val entities = response.body()!!.schedule

            for (i in entities!!.indices) {
                entities[i].scheduleType = identifier
            }

            if (entities.size > 0) {
                dao.updateScheduleItems(entities)
            }

        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return null
    }
}
