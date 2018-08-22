package com.locallinkonline.stcatherineschool.room.dao;

import android.text.format.DateUtils;

import com.locallinkonline.stcatherineschool.room.entity.LunchEntity;

import java.time.Instant;
import java.util.Calendar;
import java.util.Date;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

@Dao
public abstract class LunchDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @Transaction
    public void insert(LunchEntity... lunchEntitys){

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.AM_PM, Calendar.AM);

        Date today = calendar.getTime();

        for(LunchEntity lunch : lunchEntitys) {
            if(!lunch.getEventDate().before(today)) {
                LunchEntity dbLunch = getLunch(lunch.getEventDate());
                if(dbLunch == null) { //If new entity
                    insertOne(lunch);
                } else if(!lunch.equals(dbLunch)) { //If updated entry
                    update(lunch);
                }
            }
        }

        deleteOldLunches(today); //Lets purge the past lunches now
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insertOne(LunchEntity lunchEntity);

    @Query("DELETE FROM lunch_table")
    public abstract void deleteAll();

    @Query("DELETE FROM lunch_table WHERE event_date < :before")
    public abstract void deleteOldLunches(Date before);

    @Query("SELECT * FROM lunch_table")
    public abstract LiveData<LunchEntity[]> getAllLunches();

    @Query("SELECT * FROM lunch_table WHERE event_date = :date")
    public abstract LunchEntity getLunch(Date date);

    @Update
    public abstract void update(LunchEntity lunchEntity);
}
