package com.locallinkonline.stcatherineschool.classrooms;


import com.google.gson.annotations.SerializedName;
import com.locallinkonline.stcatherineschool.classrooms.SchoolClassrooms;

/**
 * Created by dberge on 1/15/18.
 */


public class GradeEight extends SchoolClassrooms {

    public String algebra;

    @SerializedName("algebra fundamentals")
    public String algebraFund;

    @SerializedName("middle school math")
    public String midSchoolMath;


}
