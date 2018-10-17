package com.locallinkonline.stcatherineschool.room.dao

import com.locallinkonline.stcatherineschool.room.entity.ScheduleEntity

import java.util.ArrayList
import java.util.Date

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction

@Dao
abstract class SchoolScheduleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(entities: List<ScheduleEntity>)

    @Transaction
    open fun updateScheduleItems(entities: List<ScheduleEntity>) {

        val entitiesToInsert = ArrayList<ScheduleEntity>()

        for (newEntity in entities) {
            val entity = getEntity(newEntity.id, newEntity.date)

            if (entity == null || entity != newEntity) {
                entitiesToInsert.add(newEntity)
            }
        }
        insert(entitiesToInsert)
    }

    @Query("SELECT * FROM schedule_table WHERE id = :id AND event_date = :date")
    internal abstract fun getEntity(id: String, date: Date): ScheduleEntity?

    @Query("SELECT * FROM schedule_table WHERE event_date >= :after AND schedule_type_id = 'school-schedule'")
    abstract fun getSchedule(after: Date): LiveData<List<ScheduleEntity>>

    @Query("SELECT * FROM schedule_table WHERE schedule_type_id = :type AND event_date >= :after")
    abstract fun getSchedule(type: String, after: Date): LiveData<List<ScheduleEntity>>
}
