package com.locallinkonline.stcatherineschool.room.entity

import com.google.gson.annotations.SerializedName
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "menu_table")
class MenuEntity(@PrimaryKey
                 @ColumnInfo(name = "identifier")
                 @SerializedName("identifier")
                 var identifier: String,
                 @ColumnInfo(name = "menu_item")
                 @SerializedName("menuItem")
                 var menuItem: String,

                 @ColumnInfo(name = "display_name")
                 @SerializedName("displayName")
                 var displayName: String


)
