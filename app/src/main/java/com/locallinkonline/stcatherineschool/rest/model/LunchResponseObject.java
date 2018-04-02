package com.locallinkonline.stcatherineschool.rest.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by dberge on 3/8/18.
 */

public class LunchResponseObject {
    @SerializedName("alexaResponse")
    private String alexaResponse;
    @SerializedName("lunchScheduleArray")
    private List<Lunch> lunchScheduleArray;

    public List<Lunch> getLunchScheduleList() { return lunchScheduleArray; }

    public Lunch[] getLunchAsArray() {
        return lunchScheduleArray.toArray(new Lunch[lunchScheduleArray.size()]);
    }
}
