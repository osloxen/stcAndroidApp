package com.locallinkonline.stcatherineschool.classrooms;

import com.google.gson.annotations.SerializedName;

/**
 * Created by dberge on 1/15/18.
 */

public class SchoolClassrooms {

    public String date;

    @SerializedName("general reminder")
    public String genReminder;

    @SerializedName("english")
    public String english;

    @SerializedName("social studies")
    public String socialStudies;

    @SerializedName("spanish")
    public String spanish;

    @SerializedName("science")
    public String science;

    @SerializedName("music")
    public String music;

    @SerializedName("current class project")
    public String classProject;

    @SerializedName("next special event")
    public String specialEvent;
}
