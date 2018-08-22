package com.locallinkonline.locallinkschool.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.locallinkonline.locallinkschool.adapter.ScheduleViewAdapter;
import com.locallinkonline.locallinkschool.model.ScheduleModel;
import com.locallinkonline.locallinkschool.view.LiveDataViewModel;

import java.util.Collections;
import java.util.List;

import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

public abstract class StandardScheduleListViewFragment<T extends ScheduleModel> extends StandardRecyclerViewFragment<List<T>> {

    @Override
    protected RecyclerView.Adapter getViewAdapter(List<T> data) {
        return new ScheduleViewAdapter<>(data);
    }

    @Override
    protected void configureViewModel(LiveDataViewModel<List<T>> viewModel) {
        ScheduleViewAdapter<T> viewAdapter = new ScheduleViewAdapter<T>(null);
        this.recyclerView.setAdapter(viewAdapter);
        viewModel.getData().observe(this, viewAdapter::setScheduleModels);
    }
}
