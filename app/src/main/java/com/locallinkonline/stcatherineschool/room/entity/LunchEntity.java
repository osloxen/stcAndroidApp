package com.locallinkonline.stcatherineschool.room.entity;

import com.google.gson.annotations.SerializedName;
import com.locallinkonline.locallinkschool.room.entity.AdEntity;

import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(tableName = "lunch_table")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class LunchEntity {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "event_date")
    @SerializedName("eventDate")
    private Date eventDate;

    @NonNull
    @ColumnInfo(name ="summary")
    @SerializedName("summary")
    private String summary;
}
