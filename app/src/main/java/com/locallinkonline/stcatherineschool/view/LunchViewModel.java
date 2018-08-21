package com.locallinkonline.stcatherineschool.view;

import android.app.Application;
import com.locallinkonline.stcatherineschool.room.entity.LunchEntity;
import com.locallinkonline.stcatherineschool.room.repository.StCatherineRepository;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import lombok.Getter;

public class LunchViewModel extends AndroidViewModel {
    @Getter
    private final LiveData<LunchEntity[]> data;

    public LunchViewModel(Application application) {
        super(application);
        StCatherineRepository stCatherineRepository = new StCatherineRepository(application);
        data = stCatherineRepository.getLunches();
    }
}
