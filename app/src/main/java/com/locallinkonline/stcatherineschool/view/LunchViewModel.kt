package com.locallinkonline.stcatherineschool.view

import android.app.Application
import android.text.format.DateUtils

import com.locallinkonline.locallinkschool.view.LiveDataViewModel
import com.locallinkonline.stcatherineschool.room.entity.LunchEntity
import com.locallinkonline.stcatherineschool.room.repository.StCatherineRepository

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class LunchViewModel(application: Application) : LiveDataViewModel<List<LunchEntity>>(application) {

    private val stCatherineRepository: StCatherineRepository

    init {
        stCatherineRepository = StCatherineRepository(application)
        liveData = stCatherineRepository.lunches
    }

    override fun getData(): LiveData<List<LunchEntity>>? {

        val millisSinceLastUpdate = System.currentTimeMillis() - lastUpdate

        if (this.liveData!!.value == null || this.liveData!!.value!!.isEmpty() || millisSinceLastUpdate > 5 * DateUtils.MINUTE_IN_MILLIS) {
            stCatherineRepository.checkForNewLunches()
            lastUpdate = System.currentTimeMillis()
        }
        return liveData
    }

    companion object {

        private var lastUpdate = System.currentTimeMillis()
    }
}
