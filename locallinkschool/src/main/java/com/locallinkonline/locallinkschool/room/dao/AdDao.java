package com.locallinkonline.locallinkschool.room.dao;

import com.locallinkonline.locallinkschool.room.entity.AdEntity;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

@Dao
public abstract class AdDao {

    @Transaction
    public void insertAds(AdEntity... adEntities) {

        for(AdEntity ad : adEntities) {
            AdEntity dbAd = getAd(ad.getBusinessId());

            if(dbAd == null) {
                insert(ad);
            } else if(!dbAd.equals(ad)) {
                update(ad);
            }
        }
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract void insert(AdEntity adEntity);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    abstract void update(AdEntity adEntity);

    @Query("DELETE FROM ad_table")
    abstract void deleteAll();

    @Query("DELETE FROM ad_table WHERE business_id NOT IN(:adIds)")
    public abstract void deleteAds(String[] adIds);

    @Query("SELECT * FROM ad_table")
    public abstract LiveData<AdEntity[]> getAllAds();

    @Query("SELECT * FROM ad_table WHERE business_id = :businessId")
    abstract AdEntity getAd(String businessId);
}
