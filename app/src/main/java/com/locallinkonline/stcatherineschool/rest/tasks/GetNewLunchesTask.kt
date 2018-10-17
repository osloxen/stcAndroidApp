package com.locallinkonline.stcatherineschool.rest.tasks

import com.locallinkonline.stcatherineschool.rest.model.LunchResponseObject
import com.locallinkonline.stcatherineschool.room.dao.LunchDao
import com.locallinkonline.stcatherineschool.room.db.StCatherineDatabase

import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date

import retrofit2.Call
import retrofit2.Response

class GetNewLunchesTask(db: StCatherineDatabase, url: String) : StCatherineTask<Void>(db, url) {

    private val dao: LunchDao = db.lunchDao()

    override fun doInBackground(vararg args: Void): Void? {
        val sdf = SimpleDateFormat("YYYY-MM-DD")
        val getLunchesCall = stCatherineApi.getLunches(sdf.format(Date()))

        try {
            val response = getLunchesCall.execute()
            val lunches = response.body()

            if (lunches?.schedule?.lunches != null &&
                    lunches.schedule.lunches!!.isNotEmpty()) {
                dao.insert(*lunches.schedule.lunches!!)
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return null
    }
}