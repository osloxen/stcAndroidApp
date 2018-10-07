package com.locallinkonline.stcatherineschool.room.entity;

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

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity(tableName = "menu_table")
public class MenuEntity {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name ="identifier")
    @SerializedName("identifier")
    private String identifier;

    @ColumnInfo(name ="menu_item")
    @SerializedName("menuItem")
    private String menuItem;

    @ColumnInfo(name ="display_name")
    @SerializedName("displayName")
    private String displayName;
}