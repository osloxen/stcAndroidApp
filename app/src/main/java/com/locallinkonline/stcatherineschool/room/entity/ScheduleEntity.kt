package com.locallinkonline.stcatherineschool.room.entity

import com.google.gson.annotations.SerializedName
import com.locallinkonline.locallinkschool.model.ScheduleModel

import java.util.Date
import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "schedule_table", primaryKeys = arrayOf("id", "event_date"))
data class ScheduleEntity(
        @ColumnInfo(name = "id")
        @SerializedName("id")
        var id: String,

        @ColumnInfo(name = "event_date")
        @SerializedName("eventDate")
        override var date: Date,

        @ColumnInfo(name = "schedule_type_id")
        var scheduleType: String,

        @ColumnInfo(name = "start_time")
        @SerializedName("startTime")
        override var startTime: String,

        @ColumnInfo(name = "end_time")
        @SerializedName("endTime")
        override var endTime: String,

        @ColumnInfo(name = "summary")
        @SerializedName("summary")
        override var summary: String,

        @ColumnInfo(name = "description")
        @SerializedName("description")
        override var longDescription: String
) : ScheduleModel {


}
