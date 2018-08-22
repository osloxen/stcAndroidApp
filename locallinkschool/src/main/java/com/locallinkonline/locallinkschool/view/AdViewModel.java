package com.locallinkonline.locallinkschool.view;

import android.app.Application;
import android.text.format.DateUtils;

import com.locallinkonline.locallinkschool.room.entity.AdEntity;
import com.locallinkonline.locallinkschool.room.repository.AdRepository;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class AdViewModel extends AndroidViewModel {
    private LiveData<AdEntity[]> currentAds;
    private final AdRepository adRepository;
    private static long lastUpdate;

    AdViewModel(Application application) {
        super(application);
        adRepository = new AdRepository(application);
        currentAds = adRepository.getAds();
        lastUpdate = System.currentTimeMillis();
    }

    public LiveData<AdEntity[]> getCurrentAds() {

        long millisSinceLastUpdate = System.currentTimeMillis() - lastUpdate;

        if(millisSinceLastUpdate > 5 * DateUtils.MINUTE_IN_MILLIS) {
            adRepository.checkForNewAds();
        }
        return currentAds;
    }
}
