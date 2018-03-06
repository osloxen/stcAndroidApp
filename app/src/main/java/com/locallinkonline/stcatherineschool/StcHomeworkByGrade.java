package com.locallinkonline.stcatherineschool;

import com.google.gson.annotations.SerializedName;

/**
 * Created by dberge on 1/14/18.
 */


public class StcHomeworkByGrade {

    public SubjectsGradeEight GradeEight;

    public StcHomeworkByGrade() {
        GradeEight = new SubjectsGradeEight();
    }

    public class SubjectsGradeEight {

        // @SerializedName("Date of Event") public String dateOfEvent;

        public String date;

        @SerializedName("general reminder")
        public String genReminder;

        public String algebra;

        @SerializedName("algebra fundamentals")
        public String algebraFund;
        @SerializedName("english")
        public String english;
        @SerializedName("middle school math")
        public String midSchoolMath;
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
}
