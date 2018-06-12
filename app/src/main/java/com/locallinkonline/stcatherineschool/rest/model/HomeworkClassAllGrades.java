package com.locallinkonline.stcatherineschool.rest.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
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

    public String[] getAllGradeschoolHomeworkAsArray() {

        return new String[]{
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
    }



    public String[] getEigthGradeHomeworkAsArray() {

        return new String[]{
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
    }


    public String[] getSeventhGradeHomeworkAsArray() {
        return new String[]{
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
    }

    public String[] getSixthGradeHomeworkAsArray() {
        return new String[]{
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
    }
}
