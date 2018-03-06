package com.locallinkonline.stcatherineschool.rest.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by espaan on 3/5/18.
 */

public class EighthGradeHomework {
    @SerializedName("date")
    private Date date;
    @SerializedName("general reminder")
    private String reminder;
    @SerializedName("algebra")
    private String algebra;
    @SerializedName("algebra fundamentals")
    private String algebraFundamentals;
    @SerializedName("english")
    private String english;
    @SerializedName("middle school math")
    private String middleSchoolMath;
    @SerializedName("social studies")
    private String socialStudies;
    @SerializedName("spanish")
    private String spanish;
    @SerializedName("music")
    private String music;
    @SerializedName("current class project")
    private String currentProject;
    @SerializedName("next special event")
    private String nextEvent;

    public EighthGradeHomework() {}


    public Date getDate() {
        return date;
    }

    public String getReminder() {
        return reminder;
    }

    public String getAlgebra() {
        return algebra;
    }

    public String getAlgebraFundamentals() {
        return algebraFundamentals;
    }

    public String getEnglish() {
        return english;
    }

    public String getMiddleSchoolMath() {
        return middleSchoolMath;
    }

    public String getSocialStudies() {
        return socialStudies;
    }

    public String getSpanish() {
        return spanish;
    }

    public String getMusic() {
        return music;
    }

    public String getCurrentProject() {
        return currentProject;
    }

    public String getNextEvent() {
        return nextEvent;
    }
}
