package com.locallinkonline.stcatherineschool.rest.model;

import com.google.gson.annotations.SerializedName;


/**
 * Created by dberge on 3/17/18.
 */

public class Drama {

    @SerializedName("alexaResponse")
    private String alexaResponse;
    @SerializedName("cells")
    private String[] notifications;


    public Drama() {}

    public String[] getNotifications() {
        return notifications;
    }
}
