package com.locallinkonline.stcatherineschool.rest.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by dberge on 3/10/18.
 */

public class SportsSchedule {

    @SerializedName("alexaResponse")
    private String alexaResponse;
    @SerializedName("sportScheduleArray")
    private List<SportEvent> sportScheduleArray;

    public List<SportEvent> getSportScheduleArray() { return sportScheduleArray; }

    public SportEvent[] getActivityScheduleAsArray() {

        return sportScheduleArray.toArray(new SportEvent[sportScheduleArray.size()]);
    }

}
