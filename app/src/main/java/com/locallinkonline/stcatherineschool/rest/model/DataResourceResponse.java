package com.locallinkonline.stcatherineschool.rest.model;

import com.google.gson.annotations.SerializedName;
import com.locallinkonline.stcatherineschool.room.entity.MenuEntity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DataResourceResponse {
    @SerializedName("resourceList")
    List<MenuEntity> resourceList;
}
