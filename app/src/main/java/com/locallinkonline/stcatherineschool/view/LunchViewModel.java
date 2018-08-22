package com.locallinkonline.stcatherineschool.view;

import android.app.Application;
import android.text.format.DateUtils;

import com.locallinkonline.stcatherineschool.room.entity.LunchEntity;
import com.locallinkonline.stcatherineschool.room.repository.StCatherineRepository;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class LunchViewModel extends AndroidViewModel {

    private final LiveData<LunchEntity[]> data;
    private final StCatherineRepository stCatherineRepository;

    private static long lastUpdate = System.currentTimeMillis();

    public LunchViewModel(Application application) {
        super(application);
        stCatherineRepository = new StCatherineRepository(application);
        data = stCatherineRepository.getLunches();
    }

    public LiveData<LunchEntity[]> getData() {

        long millisSinceLastUpdate = System.currentTimeMillis() - lastUpdate;

        if(millisSinceLastUpdate == 5 * DateUtils.MINUTE_IN_MILLIS) {
            stCatherineRepository.checkForNewLunches();
            lastUpdate = System.currentTimeMillis();
        }
        return data;
    }
}
