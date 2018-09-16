package com.locallinkonline.stcatherineschool.room.dao;

import com.locallinkonline.locallinkschool.view.LiveDataViewModel;
import com.locallinkonline.stcatherineschool.room.entity.ScheduleEntity;

import java.util.Date;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface SchoolScheduleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<ScheduleEntity> entities);

    @Query("SELECT * FROM schedule_table WHERE event_date >= :after AND schedule_type_id = 'school-schedule'")
    LiveData<List<ScheduleEntity>> getSchedule(Date after);

    @Query("SELECT * FROM schedule_table WHERE schedule_type_id = :type AND event_date >= :after")
    LiveData<List<ScheduleEntity>> getSchedule(String type, Date after);
}
