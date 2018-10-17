package com.locallinkonline.stcatherineschool.rest.api

import com.locallinkonline.stcatherineschool.rest.model.DataResourceResponse
import com.locallinkonline.stcatherineschool.rest.model.LunchResponseObject
import com.locallinkonline.stcatherineschool.rest.model.Schedule

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface StCatherineApi {

    @get:GET("school-schedule")
    val schoolSchedule: Call<Schedule>

    @get:GET("data-resources")
    val dataResources: Call<DataResourceResponse>

    @GET("activity")
    fun getActivitySchedule(@Query("activityId") activityId: String): Call<Schedule>

    @GET("activity")
    fun getHomeworkSchedule(@Query("id") id: String): Call<Schedule>

    @GET("lunch")
    fun getLunches(@Query("lunchDate") date: String): Call<LunchResponseObject>
}
