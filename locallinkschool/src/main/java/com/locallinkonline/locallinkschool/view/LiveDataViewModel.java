package com.locallinkonline.locallinkschool.view;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public abstract class LiveDataViewModel<T> extends AndroidViewModel {

    protected LiveData<T> data;

    public LiveDataViewModel(Application application) {
        super(application);
    }

    public abstract LiveData<T> getData();
}
