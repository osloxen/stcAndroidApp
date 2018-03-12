package com.locallinkonline.stcatherineschool.rest.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by dberge on 3/10/18.
 */

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

    public SportEvent() {}

    public Date getDate() {
        return date;
    }

    public String getEventType() {
        return eventType;
    }

    public String getNotes() {
        return notes;
    }

    public String getStartTime() { return startTime; }

    public String getLocationName() { return locationName; }

    public String getLocationAddress() { return locationAddress; }
}
