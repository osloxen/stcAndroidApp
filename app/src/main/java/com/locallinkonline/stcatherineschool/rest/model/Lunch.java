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
    @SerializedName("eventDate")
    private Date date;
    @SerializedName("summary")
    private String description;
}
