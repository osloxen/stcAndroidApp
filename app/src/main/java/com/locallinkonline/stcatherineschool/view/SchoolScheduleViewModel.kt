package com.locallinkonline.stcatherineschool.view

import android.app.Application
import android.os.Bundle
import android.text.format.DateUtils

import com.locallinkonline.locallinkschool.view.LiveDataViewModel
import com.locallinkonline.stcatherineschool.room.entity.ScheduleEntity
import com.locallinkonline.stcatherineschool.room.repository.StCatherineRepository

import androidx.lifecycle.LiveData

class SchoolScheduleViewModel(application: Application) : LiveDataViewModel<List<ScheduleEntity>>(application) {

    private val stCatherineRepository: StCatherineRepository
    private var arguments: Bundle? = null
    private var identifier: String? = null
    private var type: String? = null

    init {
        stCatherineRepository = StCatherineRepository(application)
    }

    override fun getData(): LiveData<List<ScheduleEntity>>? {
        val millisSinceLastUpdate = System.currentTimeMillis() - lastUpdate

        if (this.liveData!!.value == null || this.liveData!!.value!!.isEmpty() || millisSinceLastUpdate > 5 * DateUtils.MINUTE_IN_MILLIS) {
            stCatherineRepository.updateSchedule(arrayOf(identifier!!, type!!))
            lastUpdate = System.currentTimeMillis()
        }

        return liveData
    }



    fun setArguments(arguments: Bundle?) {
        this.arguments = arguments
        if (arguments != null && arguments.containsKey("identifier")) {
            identifier = arguments.getString("identifier");
            type = arguments.getString("type");
            liveData = stCatherineRepository.getSchedule(identifier!!)
        } else {
            liveData = stCatherineRepository.schoolSchedule
        }
    }

    companion object {
        private var lastUpdate = System.currentTimeMillis()
    }
}
