package com.locallinkonline.stcatherineschool.rest.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Created by espaan on 3/5/18.
 */

@Getter
@NoArgsConstructor
class GradeschoolHomework {
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

    public String[] getAllHomeworkAsArray() {

        return new String[]{"Reminder",
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
    }
}
