package com.locallinkonline.stcatherineschool.room.dao;

import com.locallinkonline.stcatherineschool.room.entity.MenuEntity;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

@Dao
public abstract class DataResourcesDao {

    @Insert
    public abstract void insert(List<MenuEntity> items);

    @Query("DELETE from menu_table")
    public abstract void deleteAll();

    @Transaction
    public void updateMenuItems(List<MenuEntity> items) {
        deleteAll();
        insert(items);
    }

    @Query("SELECT DISTINCT menu_item FROM menu_table")
    public abstract List<String> getGroups();

    @Query("SELECT * FROM menu_table WHERE menu_item == :menuName")
    public abstract List<MenuEntity> getItemsForMenu(String menuName);
}
