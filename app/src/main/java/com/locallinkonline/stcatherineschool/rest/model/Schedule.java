package com.locallinkonline.stcatherineschool.rest.model;

import com.google.gson.annotations.SerializedName;
import com.locallinkonline.stcatherineschool.room.entity.ScheduleEntity;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class Schedule {
    @SerializedName("schedule")
    List<ScheduleEntity> schedule;
}
