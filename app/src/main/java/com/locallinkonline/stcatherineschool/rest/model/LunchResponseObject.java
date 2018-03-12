package com.locallinkonline.stcatherineschool.rest.model;

import com.google.gson.annotations.SerializedName;
import com.locallinkonline.stcatherineschool.StcHomeworkByGrade;

import java.util.List;

/**
 * Created by dberge on 3/8/18.
 */

public class LunchResponseObject {
    @SerializedName("alexaResponse")
    private String alexaResponse;
    @SerializedName("lunchScheduleArray")
    private List<Lunch> lunchScheduleArray;

    public List<Lunch> getLunchScheduleArray() { return lunchScheduleArray; }
}
