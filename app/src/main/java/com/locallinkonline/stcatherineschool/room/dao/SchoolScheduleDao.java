package com.locallinkonline.stcatherineschool.room.dao;

import com.locallinkonline.stcatherineschool.room.entity.SchoolScheduleEntity;

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
    void insert(List<SchoolScheduleEntity> entities);

    @Query("SELECT * FROM schedule_table WHERE event_date >= :after")
    LiveData<List<SchoolScheduleEntity>> getSchedule(Date after);
}
