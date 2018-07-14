package com.locallinkonline.stcatherineschool.room.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(tableName = "ad_table")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AdEntity {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "business_id")
    private String businessId;

    @NonNull
    @ColumnInfo(name = "business")
    private String business;

    @NonNull
    @ColumnInfo(name = "ad_title")
    private String adTitle;

    @NonNull
    @ColumnInfo(name = "ad_text")
    private String adText;
}
