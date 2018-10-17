package com.locallinkonline.stcatherineschool.rest.tasks

import android.os.AsyncTask

import com.google.gson.GsonBuilder
import com.locallinkonline.stcatherineschool.rest.api.StCatherineApi
import com.locallinkonline.stcatherineschool.room.db.StCatherineDatabase

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

abstract class StCatherineTask<T>(db: StCatherineDatabase, url: String) : AsyncTask<T, Void, Void>() {

    internal val stCatherineApi: StCatherineApi = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create(
                    GsonBuilder().setLenient().create()))
            .build().create(StCatherineApi::class.java)

}
