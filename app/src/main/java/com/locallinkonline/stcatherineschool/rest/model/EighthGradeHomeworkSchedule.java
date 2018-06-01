package com.locallinkonline.stcatherineschool.rest.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by dberge on 3/17/18.
 */

public class EighthGradeHomeworkSchedule {

    @SerializedName("alexaResponse")
    private String alexaResponse;
    @SerializedName("homeworkArray")
    private List<HomeworkClassAllGrades> homeworkList;

    public EighthGradeHomeworkSchedule() {}

    public List<HomeworkClassAllGrades> getHomeworkList() { return homeworkList; }

    public HomeworkClassAllGrades[] getHomeworkAsArray() {

        return homeworkList.toArray(new HomeworkClassAllGrades[homeworkList.size()]);
    }



}
