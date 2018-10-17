package com.locallinkonline.stcatherineschool.rest.model

import com.google.gson.annotations.SerializedName
import com.locallinkonline.stcatherineschool.room.entity.MenuEntity

data class DataResourceResponse (@SerializedName("resourceList")
                                 var resourceList: List<MenuEntity>?)
