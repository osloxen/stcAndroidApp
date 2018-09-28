package com.locallinkonline.stcatherineschool.view;

import android.app.Application;
import android.os.Bundle;
import android.text.format.DateUtils;

import com.locallinkonline.locallinkschool.view.LiveDataViewModel;
import com.locallinkonline.stcatherineschool.room.entity.ScheduleEntity;
import com.locallinkonline.stcatherineschool.room.repository.StCatherineRepository;

import java.util.List;

import androidx.lifecycle.LiveData;

public class SchoolScheduleViewModel extends LiveDataViewModel<List<ScheduleEntity>> {

    private final StCatherineRepository stCatherineRepository;
    private static long lastUpdate = System.currentTimeMillis();
    private Bundle arguments;

    public SchoolScheduleViewModel(Application application) {
        super(application);
        stCatherineRepository = new StCatherineRepository(application);
    }

    @Override
    public LiveData<List<ScheduleEntity>> getData() {
        long millisSinceLastUpdate = System.currentTimeMillis() - lastUpdate;

        if(this.data.getValue() == null || this.data.getValue().size() == 0 || millisSinceLastUpdate > 5 * DateUtils.MINUTE_IN_MILLIS) {
            if(arguments.getString("identifier") == null) {
                stCatherineRepository.updateSchedule(null);
            } else {
                stCatherineRepository.updateSchedule(new String[] {arguments.getString("identifier"), arguments.getString("type")});
            }

            lastUpdate = System.currentTimeMillis();
        }

        return data;
    }

    public void setArguments(Bundle arguments) {
        this.arguments = arguments;
        if(arguments != null && arguments.getString("identifier") != null) {
            data = stCatherineRepository.getSchedule(arguments.getString("identifier"));
        } else {
            data = stCatherineRepository.getSchoolSchedule();
        }
    }
}
