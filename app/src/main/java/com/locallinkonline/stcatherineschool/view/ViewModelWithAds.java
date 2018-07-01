package com.locallinkonline.stcatherineschool.view;

import android.app.Application;

import com.locallinkonline.stcatherineschool.rest.liveData.AdLiveData;
import com.locallinkonline.stcatherineschool.rest.model.AdUnit;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class ViewModelWithAds extends AndroidViewModel {
    private final AdLiveData data;

    public ViewModelWithAds(Application application) {
        super(application);
        data = new AdLiveData();
    }

    public LiveData<AdUnit> getAdData() {
        return data;
    }
}
