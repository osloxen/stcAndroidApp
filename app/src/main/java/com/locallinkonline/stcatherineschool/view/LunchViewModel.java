package com.locallinkonline.stcatherineschool.view;

import android.app.Application;

import com.locallinkonline.stcatherineschool.rest.liveData.LunchLiveData;
import com.locallinkonline.stcatherineschool.rest.model.LunchResponseObject;

import androidx.lifecycle.LiveData;

public class LunchViewModel extends ViewModelWithAds {
    private final LunchLiveData data;

    public LunchViewModel(Application application) {
        super(application);
        data = new LunchLiveData();
    }

    public LiveData<LunchResponseObject> getData() {
        return data;
    }
}
