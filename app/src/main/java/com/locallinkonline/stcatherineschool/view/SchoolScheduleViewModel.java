package com.locallinkonline.stcatherineschool.view;

import android.app.Application;
import android.text.format.DateUtils;

import com.locallinkonline.locallinkschool.view.LiveDataViewModel;
import com.locallinkonline.stcatherineschool.room.entity.SchoolScheduleEntity;
import com.locallinkonline.stcatherineschool.room.repository.StCatherineRepository;

import java.util.List;

import androidx.lifecycle.LiveData;

public class SchoolScheduleViewModel extends LiveDataViewModel<List<SchoolScheduleEntity>> {

    private final StCatherineRepository stCatherineRepository;

    private static long lastUpdate = System.currentTimeMillis();
    
    public SchoolScheduleViewModel(Application application) {
        super(application);
        stCatherineRepository = new StCatherineRepository(application);
        data = stCatherineRepository.getSchoolSchedule();
    }

    @Override
    public LiveData<List<SchoolScheduleEntity>> getData() {
        long millisSinceLastUpdate = System.currentTimeMillis() - lastUpdate;

        if(this.data.getValue() == null || this.data.getValue().size() == 0 ||millisSinceLastUpdate > 5 * DateUtils.MINUTE_IN_MILLIS) {
            stCatherineRepository.updateSchedule();
            lastUpdate = System.currentTimeMillis();
        }
        return data;
    }
}
