package com.locallinkonline.stcatherineschool.room.entity

import com.google.gson.annotations.SerializedName
import com.locallinkonline.locallinkschool.model.ScheduleModel

import java.util.Date
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "lunch_table")
data class LunchEntity(@PrimaryKey
                       @ColumnInfo(name = "event_date")
                       @SerializedName("date")
                       override val date: Date,

                       @ColumnInfo(name = "summary")
                       @SerializedName("lunchDescription")
                       override var summary: String,

                       override val endTime: String,
                       override val longDescription: String,
                       override val startTime: String) : ScheduleModel
