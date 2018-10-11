package com.locallinkonline.stcatherineschool.room.entity;

import com.google.gson.annotations.SerializedName;
import com.locallinkonline.locallinkschool.model.ScheduleModel;

import java.util.Date;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(tableName = "schedule_table", primaryKeys = {"id", "event_date"})
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class ScheduleEntity implements ScheduleModel {
    @NonNull
    @ColumnInfo(name = "id")
    @SerializedName("id")
    String id;

    @NonNull
    @ColumnInfo(name = "event_date")
    @SerializedName("eventDate")
    Date date;

    @NonNull
    @ColumnInfo(name = "schedule_type_id")
    String scheduleType;

    @NonNull
    @ColumnInfo(name ="start_time")
    @SerializedName("startTime")
    String startTime;

    @NonNull
    @ColumnInfo(name ="end_time")
    @SerializedName("endTime")
    String endTime;

    @NonNull
    @ColumnInfo(name ="summary")
    @SerializedName("summary")
    String summary;

    @ColumnInfo(name="description")
    @SerializedName("description")
    String longDescription;
}
