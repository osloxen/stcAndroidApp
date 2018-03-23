package com.locallinkonline.stcatherineschool.rest.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;



public class HomeworkClassAllGrades {
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
    @SerializedName("pre-algebra")
    private String preAlgebra;
    @SerializedName("middle school math")
    private String middleSchoolMath;
    @SerializedName("social studies")
    private String socialStudiesMiddleSchool;
    @SerializedName("spanish")
    private String spanish;
    @SerializedName("science")
    private String science;
    @SerializedName("music")
    private String music;
    @SerializedName("current class project")
    private String currentProject;
    @SerializedName("next special event")
    private String nextEvent;
    @SerializedName("math")
    private String math;
    @SerializedName("reading")
    private String reading;
    @SerializedName("religion")
    private String religion;
    @SerializedName("socialStudies")
    private String socialStudiesGradeSchool;
    @SerializedName("spelling")
    private String spelling;
    @SerializedName("undefined")
    private String undefinedColumnHeader;



    public HomeworkClassAllGrades() {}



    public String[] getAllGradeschoolHomeworkAsArray() {
        String[] resultArray = {
                "Reminder",
                getReminder(),
                "Math",
                getMath(),
                "Reading",
                getReading(),
                "English",
                getEnglish(),
                "Social Studies",
                getSocialStudiesGradeSchool(),
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



    public String[] getEigthGradeHomeworkAsArray() {
        String[] resultArray = {
                "Reminder",
                getReminder(),
                "Algebra",
                getAlgebra(),
                "Algebra Fundamentals",
                getAlgebraFundamentals(),
                "Middle School Math",
                getMiddleSchoolMath(),
                "English",
                getEnglish(),
                "Social Studies",
                getSocialStudiesMiddleSchool(),
                "Spanish",
                getSpanish(),
                "Science",
                getScience(),
                "Music",
                getMusic(),
                "Current Class Project",
                getCurrentProject(),
                "Next Special Event",
                getNextEvent()
        };

        return resultArray;
    }


    public String[] getSeventhGradeHomeworkAsArray() {
        String[] resultArray = {
                "Reminder",
                getReminder(),
                "Algebra",
                getAlgebra(),
                "Pre-Algebra",
                getPreAlgebra(),
                "Middle School Math",
                getMiddleSchoolMath(),
                "English",
                getEnglish(),
                "Social Studies",
                getSocialStudiesMiddleSchool(),
                "Spanish",
                getSpanish(),
                "Science",
                getScience(),
                "Music",
                getMusic(),
                "Current Class Project",
                getCurrentProject(),
                "Next Special Event",
                getNextEvent()
        };

        return resultArray;
    }


    public String[] getSixthGradeHomeworkAsArray() {
        String[] resultArray = {
                "Reminder",
                getReminder(),
                "Pre-Algebra",
                getPreAlgebra(),
                "Middle School Math",
                getMiddleSchoolMath(),
                "English",
                getEnglish(),
                "Social Studies",
                getSocialStudiesMiddleSchool(),
                "Spanish",
                getSpanish(),
                "Science",
                getScience(),
                "Music",
                getMusic(),
                "Religion",
                getReligion()
        };

        return resultArray;
    }


    public Date getDate() {

        return date;
    }

    public String getReminder() {
        return reminder;
    }

    public String getPreAlgebra() { return preAlgebra; }

    public String getMath() { return math; }

    public String getAlgebra() {
        return algebra;
    }

    public String getAlgebraFundamentals() {
        return algebraFundamentals;
    }

    public String getEnglish() {
        return english;
    }

    public String getReading() {
        return reading;
    }

    public String getMiddleSchoolMath() {
        return middleSchoolMath;
    }

    public String getSocialStudiesMiddleSchool() {
        return socialStudiesMiddleSchool;
    }

    public String getSocialStudiesGradeSchool() {
        return socialStudiesGradeSchool;
    }

    public String getScience() { return science; };

    public String getSpanish() {
        return spanish;
    }

    public String getMusic() {
        return music;
    }

    public String getReligion() { return religion; }

    public String getSpelling() { return spelling; }

    public String getCurrentProject() {
        return currentProject;
    }

    public String getNextEvent() {
        return nextEvent;
    }
}
