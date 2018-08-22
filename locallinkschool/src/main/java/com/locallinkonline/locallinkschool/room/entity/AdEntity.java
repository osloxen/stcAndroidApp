package com.locallinkonline.locallinkschool.room.entity;

import com.google.gson.annotations.SerializedName;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(tableName = "ad_table")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class AdEntity {

    @SerializedName("businessId")
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "business_id")
    private String businessId;

    @SerializedName("business")
    @NonNull
    @ColumnInfo(name = "business")
    private String business;

    @SerializedName("adTitle")
    @NonNull
    @ColumnInfo(name = "ad_title")
    private String adTitle;

    @SerializedName("adText")
    @NonNull
    @ColumnInfo(name = "ad_text")
    private String adText;
}
