package com.locallinkonline.stcatherineschool.room.dao;

import com.locallinkonline.stcatherineschool.room.entity.ScheduleEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

@Dao
public abstract class SchoolScheduleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insert(List<ScheduleEntity> entities);

    @Transaction
    public void updateScheduleItems(List<ScheduleEntity> entities) {

        List<ScheduleEntity> entitiesToInsert = new ArrayList<>();

        for(ScheduleEntity newEntity : entities) {
            ScheduleEntity entity = getEntity(newEntity.getId(), newEntity.getDate());

            if(entity == null || !entity.equals(newEntity)) {
              entitiesToInsert.add(newEntity);
            }
        }
        insert(entitiesToInsert);
    }

    @Query("SELECT * FROM schedule_table WHERE id = :id AND event_date = :date")
    abstract ScheduleEntity getEntity(String id, Date date);

    @Query("SELECT * FROM schedule_table WHERE event_date >= :after AND schedule_type_id = 'school-schedule'")
    public abstract LiveData<List<ScheduleEntity>> getSchedule(Date after);

    @Query("SELECT * FROM schedule_table WHERE schedule_type_id = :type AND event_date >= :after")
    public abstract LiveData<List<ScheduleEntity>> getSchedule(String type, Date after);
}
