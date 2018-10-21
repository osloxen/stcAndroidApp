package com.locallinkonline.stcatherineschool.room.db

import android.content.Context

import com.locallinkonline.stcatherineschool.R
import com.locallinkonline.locallinkschool.room.converter.DateConverter
import com.locallinkonline.stcatherineschool.room.dao.DataResourcesDao
import com.locallinkonline.stcatherineschool.room.dao.LunchDao
import com.locallinkonline.stcatherineschool.room.dao.SchoolScheduleDao
import com.locallinkonline.stcatherineschool.room.entity.LunchEntity
import com.locallinkonline.stcatherineschool.room.entity.MenuEntity
import com.locallinkonline.stcatherineschool.room.entity.ScheduleEntity
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = arrayOf(LunchEntity::class, ScheduleEntity::class, MenuEntity::class), version = 2, exportSchema = false)
@TypeConverters(DateConverter::class)
abstract class StCatherineDatabase : RoomDatabase() {
    abstract fun lunchDao(): LunchDao
    abstract fun schoolScheduleDao(): SchoolScheduleDao
    abstract fun dataResourcesDao(): DataResourcesDao

    companion object {

        private var INSTANCE: StCatherineDatabase? = null

        fun getDatabase(context: Context): StCatherineDatabase? {
            if (INSTANCE == null) {
                synchronized(StCatherineDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                                context.applicationContext,
                                StCatherineDatabase::class.java,
                                "stc_database")
                                .fallbackToDestructiveMigration().build()
                    }
                }
            }
            return INSTANCE
        }
    }
}
