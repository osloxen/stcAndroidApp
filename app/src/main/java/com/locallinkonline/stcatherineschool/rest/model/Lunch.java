package com.locallinkonline.stcatherineschool.rest.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by espaan on 3/5/18.
 */

public class Lunch {
    @SerializedName("date")
    private Date date;
    @SerializedName("lunchAvailable")
    private boolean available;
    @SerializedName("lunchDescription")
    private String description;

    public Lunch() {}

    public Date getDate() {
        return date;
    }

    public boolean isAvailable() {
        return available;
    }

    public String getDescription() {
        return description;
    }
}
