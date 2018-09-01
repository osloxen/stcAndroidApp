package com.locallinkonline.stcatherineschool.rest.model;

import com.google.gson.annotations.SerializedName;
import com.locallinkonline.stcatherineschool.room.entity.SchoolScheduleEntity;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class SchoolSchedule {
    @SerializedName("schedule")
    List<SchoolScheduleEntity> schedule;
}
