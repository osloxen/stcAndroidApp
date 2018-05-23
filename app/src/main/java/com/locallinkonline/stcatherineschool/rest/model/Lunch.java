package com.locallinkonline.stcatherineschool.rest.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Created by espaan on 3/5/18.
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Lunch {
    @SerializedName("date")
    private Date date;
    @SerializedName("lunchAvailable")
    private boolean available;
    @SerializedName("lunchDescription")
    private String description;
}
