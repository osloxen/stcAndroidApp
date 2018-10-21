package com.locallinkonline.stcatherineschool.room.dao

import android.text.format.DateUtils

import com.locallinkonline.stcatherineschool.room.entity.LunchEntity

import java.time.Instant
import java.util.Calendar
import java.util.Date

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update

@Dao
abstract class LunchDao {

    @get:Query("SELECT * FROM lunch_table")
    abstract val allLunches: LiveData<List<LunchEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @Transaction
    fun insert(lunchEntitys: List<LunchEntity>) {

        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR, 0)
        calendar.set(Calendar.HOUR, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        calendar.set(Calendar.AM_PM, Calendar.AM)

        val today = calendar.time

        for (lunch in lunchEntitys) {
            if (!lunch.date.before(today)) {
                val dbLunch = getLunch(lunch.date)
                if (dbLunch == null) { //If new entity
                    insertOne(lunch)
                } else if (lunch != dbLunch) { //If updated entry
                    update(lunch)
                }
            }
        }

        deleteOldLunches(today) //Lets purge the past lunches now
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertOne(lunchEntity: LunchEntity)

    @Query("DELETE FROM lunch_table")
    abstract fun deleteAll()

    @Query("DELETE FROM lunch_table WHERE event_date < :before")
    abstract fun deleteOldLunches(before: Date)

    @Query("SELECT * FROM lunch_table WHERE event_date = :date")
    abstract fun getLunch(date: Date): LunchEntity?

    @Update
    abstract fun update(lunchEntity: LunchEntity)
}
