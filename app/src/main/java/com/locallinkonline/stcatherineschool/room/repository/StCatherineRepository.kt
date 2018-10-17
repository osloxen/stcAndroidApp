package com.locallinkonline.stcatherineschool.room.repository

import android.app.Application

import com.locallinkonline.stcatherineschool.R
import com.locallinkonline.stcatherineschool.rest.tasks.GetNewDataResourcesTask
import com.locallinkonline.stcatherineschool.rest.tasks.GetNewLunchesTask
import com.locallinkonline.stcatherineschool.rest.tasks.GetNewScheduleTask
import com.locallinkonline.stcatherineschool.room.dao.DataResourcesDao
import com.locallinkonline.stcatherineschool.room.dao.LunchDao
import com.locallinkonline.stcatherineschool.room.dao.SchoolScheduleDao
import com.locallinkonline.stcatherineschool.room.db.StCatherineDatabase
import com.locallinkonline.stcatherineschool.room.entity.LunchEntity
import com.locallinkonline.stcatherineschool.room.entity.MenuEntity
import com.locallinkonline.stcatherineschool.room.entity.ScheduleEntity

import java.util.Date

import androidx.lifecycle.LiveData

class StCatherineRepository(application: Application) {
    private val lunchDao: LunchDao
    private val db: StCatherineDatabase? = StCatherineDatabase.getDatabase(application)
    private val url: String
    private val schoolScheduleDao: SchoolScheduleDao
    private val dataResourcesDao: DataResourcesDao

    val lunches: LiveData<List<LunchEntity>>
        get() = lunchDao.allLunches

    val schoolSchedule: LiveData<List<ScheduleEntity>>
        get() = schoolScheduleDao.getSchedule(Date())

    val menuGroups: List<String>
        get() = dataResourcesDao.groups

    val allMenuItems: List<MenuEntity>
        get() = dataResourcesDao.allItems

    init {
        this.lunchDao = db!!.lunchDao()
        this.schoolScheduleDao = db.schoolScheduleDao()
        this.dataResourcesDao = db.dataResourcesDao()
        this.url = application.getString(R.string.stcBaseUrl)
    }

    fun getMenuItemsForGroup(groupName: String): List<MenuEntity> {
        return dataResourcesDao.getItemsForMenu(groupName)
    }

    fun checkForNewLunches() {
        GetNewLunchesTask(db!!, url).execute()
    }

    fun updateSchedule(args: Array<String>) {
        GetNewScheduleTask(db!!, url).execute(*args)
    }

    fun getNewMenuItems() {
        GetNewDataResourcesTask(db!!, url, null).execute()
    }

    fun getSchedule(identifier: String): LiveData<List<ScheduleEntity>> {
        return schoolScheduleDao.getSchedule(identifier, Date())
    }
}
