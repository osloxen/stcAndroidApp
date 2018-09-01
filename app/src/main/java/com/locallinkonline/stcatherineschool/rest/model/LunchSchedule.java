package com.locallinkonline.stcatherineschool.rest.model;

import com.google.gson.annotations.SerializedName;
import com.locallinkonline.stcatherineschool.room.entity.LunchEntity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@EqualsAndHashCode
@NoArgsConstructor
@Getter
public class LunchSchedule {
    @SerializedName("alexaResponse")
    String alexaResponse;
    @SerializedName("lunchScheduleArray")
    LunchEntity[] lunches;
}
