package com.locallinkonline.stcatherineschool.room.dao;

import com.locallinkonline.stcatherineschool.room.entity.LunchEntity;

import java.util.Date;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface LunchDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(LunchEntity... adEntity);

    @Query("DELETE FROM lunch_table")
    void deleteAll();

    @Query("DELETE FROM lunch_table WHERE event_date < :before")
    void deleteOldLunches(Date before);

    @Query("SELECT * FROM lunch_table")
    LiveData<LunchEntity[]> getAllLunches();
}
