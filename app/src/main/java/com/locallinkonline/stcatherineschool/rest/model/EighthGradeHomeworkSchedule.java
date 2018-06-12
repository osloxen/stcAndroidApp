package com.locallinkonline.stcatherineschool.rest.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Created by dberge on 3/17/18.
 */
@Getter
@NoArgsConstructor
public class EighthGradeHomeworkSchedule {
    @SerializedName("alexaResponse")
    private String alexaResponse;
    @SerializedName("homeworkArray")
    private List<HomeworkClassAllGrades> homeworkList;
}
