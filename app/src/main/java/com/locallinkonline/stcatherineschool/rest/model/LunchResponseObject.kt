package com.locallinkonline.stcatherineschool.rest.model

import com.google.gson.annotations.SerializedName

data class LunchResponseObject(
        @SerializedName("schedule")
        val schedule: LunchSchedule?
)
