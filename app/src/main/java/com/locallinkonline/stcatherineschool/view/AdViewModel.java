package com.locallinkonline.stcatherineschool.view;

import android.app.Application;
import android.os.AsyncTask;

import com.locallinkonline.stcatherineschool.room.entity.AdEntity;
import com.locallinkonline.stcatherineschool.room.repository.AdRepository;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import lombok.Getter;

public class AdViewModel extends AndroidViewModel {

    private final AdRepository adRepository;

    private final AtomicInteger counter = new AtomicInteger();

    @Getter
    private LiveData<AdEntity> currentAd;

    AdViewModel(Application application) {
        super(application);
        adRepository = new AdRepository(application);
        currentAd = new LiveData<AdEntity>() {
            @Override
            protected void onActive() {
                super.onActive();
                new AsyncTask<Void, Void, List<AdEntity>>() {
                    @Override
                    protected List<AdEntity> doInBackground(Void... voids) {
                        return adRepository.getAds();
                    }

                    @Override
                    protected void onPostExecute (List<AdEntity> ads) {
                        if(ads != null && ads.size() > 0) {
                            setValue(ads.get(counter.getAndIncrement() % ads.size()));
                        }
                    }
                }.execute();
                adRepository.checkForNewAds();
            }
        };
    }
}
