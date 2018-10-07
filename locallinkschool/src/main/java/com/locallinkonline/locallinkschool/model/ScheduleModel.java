package com.locallinkonline.locallinkschool.model;

import java.util.Date;

public interface ScheduleModel {
    Date getDate();

    String getSummary();

    String getStartTime();

    String getEndTime();

    String getLongDescription();
}