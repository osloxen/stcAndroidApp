package com.locallinkonline.stcatherineschool.rest.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Created by dberge on 3/10/18.
 */

@Getter
@NoArgsConstructor
public class SportEvent {

    @SerializedName("date")
    private Date date;
    @SerializedName("eventType")
    private String eventType;
    @SerializedName("notes")
    private String notes;
    @SerializedName("startTime")
    private String startTime;
    @SerializedName("locationName")
    private String locationName;
    @SerializedName("locationAddress")
    private String locationAddress;

    public SportEvent getLoadingSportEvent() {
        SportEvent returnThisEvent = new SportEvent();
        returnThisEvent.notes = "loading...";
        returnThisEvent.date = new Date();
        returnThisEvent.startTime = "loading...";

        return returnThisEvent;
    }
}
