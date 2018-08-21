package com.locallinkonline.locallinkschool.view;

import android.app.Application;

import com.locallinkonline.locallinkschool.room.entity.AdEntity;
import com.locallinkonline.locallinkschool.room.repository.AdRepository;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class AdViewModel extends AndroidViewModel {
    private LiveData<AdEntity[]> currentAds;
    private final AdRepository adRepository;

    AdViewModel(Application application) {
        super(application);
        adRepository = new AdRepository(application);
        currentAds = adRepository.getAds();
    }

    public LiveData<AdEntity[]> getCurrentAds() {
        adRepository.checkForNewAds();
        return currentAds;
    }
}
