package com.locallinkonline.stcatherineschool.rest.model

import com.google.gson.annotations.SerializedName
import com.locallinkonline.stcatherineschool.room.entity.LunchEntity

data class LunchSchedule(
        @SerializedName("alexaResponse")
        var alexaResponse: String?,
        @SerializedName("lunchScheduleArray")
        var lunches: List<LunchEntity>? = null
)
