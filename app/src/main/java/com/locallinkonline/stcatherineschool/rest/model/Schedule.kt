package com.locallinkonline.stcatherineschool.rest.model

import com.google.gson.annotations.SerializedName
import com.locallinkonline.stcatherineschool.room.entity.ScheduleEntity

class Schedule(
        @SerializedName("schedule")
        var schedule: List<ScheduleEntity>? = null
)
