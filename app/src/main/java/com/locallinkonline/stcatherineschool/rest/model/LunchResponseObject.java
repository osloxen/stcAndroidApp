package com.locallinkonline.stcatherineschool.rest.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Created by dberge on 3/8/18.
 */

@NoArgsConstructor
@Getter
public class LunchResponseObject {
    @SerializedName("schedule")
    private ArrayList<Lunch> lunchScheduleList;
}
