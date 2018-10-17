package com.locallinkonline.stcatherineschool.room.dao

import com.locallinkonline.stcatherineschool.room.entity.MenuEntity

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction

@Dao
abstract class DataResourcesDao {

    @get:Query("SELECT DISTINCT menu_item FROM menu_table")
    abstract val groups: List<String>

    @get:Query("SELECT identifier FROM menu_table")
    internal abstract val ids: MutableList<String>

    @get:Query("SELECT * FROM menu_table")
    abstract val allItems: List<MenuEntity>

    @Insert
    abstract fun insert(items: List<MenuEntity>)

    @Insert
    internal abstract fun insert(item: MenuEntity)

    @Transaction
    open fun updateMenuItems(items: List<MenuEntity>) {
        val ids = ids

        for (entity in items) {
            if (!ids.contains(entity.identifier)) {
                insert(entity)
            } else {
                val index = ids.indexOf(entity.identifier)
                if (index != -1) {
                    ids.removeAt(index)
                }
            }
        }

        //Clean up ids
        if (ids.isNotEmpty()) {
            delete(ids)
        }
    }

    @Query("DELETE from menu_table WHERE identifier IN (:ids)")
    protected abstract fun delete(ids: List<String>)

    @Query("SELECT * FROM menu_table WHERE menu_item == :menuName")
    abstract fun getItemsForMenu(menuName: String): List<MenuEntity>

    @Query("SELECT * FROM menu_table WHERE identifier == :id")
    internal abstract fun getEntity(id: String): MenuEntity
}
