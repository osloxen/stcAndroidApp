package com.locallinkonline.stcatherineschool.rest.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by espaan on 3/5/18.
 */

public class GradeschoolHomework {
    @SerializedName("date")
    private Date date;
    @SerializedName("general reminder")
    private String reminder;
    @SerializedName("math")
    private String math;
    @SerializedName("reading")
    private String reading;
    @SerializedName("english")
    private String english;
    @SerializedName("socialStudies")
    private String socialStudies;
    @SerializedName("spanish")
    private String spanish;
    @SerializedName("science")
    private String science;
    @SerializedName("religion")
    private String religion;
    @SerializedName("music")
    private String music;
    @SerializedName("spelling")
    private String spelling;
    @SerializedName("next special event")
    private String nextEvent;

    public GradeschoolHomework() {}

    public String[] getAllHomeworkAsArray() {
        String[] resultArray = {"Reminder",
                                getReminder(),
                                "Math",
                                getReading(),
                                "Reading",
                                getReading(),
                                "English",
                                getEnglish(),
                                "Social Studies",
                                getSocialStudies(),
                                "Spanish",
                                getSpanish(),
                                "Science",
                                getScience(),
                                "Religion",
                                getReligion(),
                                "Music",
                                getMusic(),
                                "Spelling",
                                 getSpelling(),
                                "Next Special Event",
                                getNextEvent()
        };

        return resultArray;
    }


    public Date getDate() {
        return date;
    }

    public String getReminder() {
        return reminder;
    }

    public String getReading() {
        return reading;
    }

    public String getReligion() {
        return religion;
    }

    public String getEnglish() {
        return english;
    }

    public String getSpelling() {
        return spelling;
    }

    public String getSocialStudies() {
        return socialStudies;
    }

    public String getScience() { return science; };

    public String getSpanish() {
        return spanish;
    }

    public String getMusic() {
        return music;
    }

    public String getNextEvent() {
        return nextEvent;
    }
}
