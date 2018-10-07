package com.locallinkonline.stcatherineschool.room.dao;

import com.locallinkonline.stcatherineschool.room.entity.MenuEntity;

import java.util.List;
import java.util.function.Predicate;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

@Dao
public abstract class DataResourcesDao {

    @Insert
    public abstract void insert(List<MenuEntity> items);

    @Insert
    abstract void insert(MenuEntity item);

    @Transaction
    public void updateMenuItems(List<MenuEntity> items) {
        List<String> ids = getIds();

        for(MenuEntity entity : items) {
            if(!ids.contains(entity.getIdentifier())) {
                insert(entity);
            } else {
                int index = ids.indexOf(entity.getIdentifier());
                if(index != -1) {
                    ids.remove(index);
                }
            }
        }

        //Clean up ids
        if(ids.size() > 0) {
            delete(ids);
        }
    }

    @Query("DELETE from menu_table WHERE identifier IN (:ids)")
    protected abstract void delete(List<String> ids);

    @Query("SELECT DISTINCT menu_item FROM menu_table")
    public abstract List<String> getGroups();

    @Query("SELECT * FROM menu_table WHERE menu_item == :menuName")
    public abstract List<MenuEntity> getItemsForMenu(String menuName);

    @Query("SELECT * FROM menu_table WHERE identifier == :id")
    abstract MenuEntity getEntity(String id);

    @Query("SELECT identifier FROM menu_table")
    abstract List<String> getIds();

    @Query("SELECT * FROM menu_table")
    public abstract List<MenuEntity> getAllItems();
}
