package com.locallinkonline.stcatherineschool.room.dao;

import com.locallinkonline.stcatherineschool.room.entity.AdEntity;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface AdDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(AdEntity... adEntity);

    @Query("DELETE FROM ad_table")
    void deleteAll();

    @Query("DELETE FROM ad_table WHERE business_id NOT IN(:adIds)")
    void deleteAds(String[] adIds);

    @Query("SELECT * FROM ad_table")
    List<AdEntity> getAllAds();
}
