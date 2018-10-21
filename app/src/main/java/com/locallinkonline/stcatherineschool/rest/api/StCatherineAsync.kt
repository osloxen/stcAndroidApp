package com.locallinkonline.stcatherineschool.rest.api

import com.google.gson.GsonBuilder
import com.locallinkonline.stcatherineschool.activities.MainActivity
import com.locallinkonline.stcatherineschool.R
import com.locallinkonline.stcatherineschool.rest.model.Schedule
import com.locallinkonline.stcatherineschool.room.db.StCatherineDatabase
import org.jetbrains.anko.doAsync
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.SimpleDateFormat
import java.util.*

class StCatherineAsync {

    private constructor()

    companion object {

        private val db: StCatherineDatabase? = StCatherineDatabase.getDatabase(MainActivity.instance.applicationContext);
        private val stCatherineApi: StCatherineApi = Retrofit.Builder()
                .baseUrl(MainActivity.instance.applicationContext.getString(R.string.stcBaseUrl))
                .addConverterFactory(GsonConverterFactory.create(
                        GsonBuilder().setLenient().create()))
                .build().create(StCatherineApi::class.java)

        private val sdf = SimpleDateFormat("YYYY-MM-DD")

        fun updateDataResources() {

            doAsync {
                val dataResourceResponseCall = stCatherineApi.dataResources
                val response = dataResourceResponseCall.execute()
                val menuEntities = response.body()?.resourceList ?: Collections.emptyList()

                db?.dataResourcesDao()?.updateMenuItems(menuEntities)
            }
        }

        fun updateLunches() {
            doAsync {
                val getLunchesCall = stCatherineApi.getLunches(sdf.format(Date()));
                val response = getLunchesCall.execute();

                val lunches = response.body()?.schedule?.lunches ?: Collections.emptyList()

                db?.lunchDao()?.insert(lunches)
            }
        }

        fun updateSchedule(identifier: String = "school-schedule", type: String = "SchoolSchedule") {
            doAsync {
                var call: Call<Schedule>

                when(type) {
                    "Homework" -> {
                        call = stCatherineApi.getHomeworkSchedule(identifier)
                    }
                    "Sports" -> {
                        call = stCatherineApi.getActivitySchedule(identifier)
                    }
                    else -> {
                        call = stCatherineApi.schoolSchedule
                    }
                }

                val response = call.execute()

                val scheduleItems = response.body()?.schedule ?: Collections.emptyList()

                for (i in scheduleItems.indices) {
                    scheduleItems[i].scheduleType = identifier
                }

                db?.schoolScheduleDao()?.updateScheduleItems(scheduleItems)
            }
        }
    }
}