package com.locallinkonline.stcatherineschool.rest.model;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Activities {
    @SerializedName("alexaResponse")
    private String alexaResponse;
    @SerializedName("cells")
    private String[] notifications;
}
