package com.locallinkonline.locallinkschool.fragment;

import com.locallinkonline.locallinkschool.adapter.TextListViewAdapter;
import com.locallinkonline.locallinkschool.view.LiveDataViewModel;

import androidx.recyclerview.widget.RecyclerView;

public abstract class StandardTextViewListFragment extends StandardRecyclerViewFragment<String[]> {

    @Override
    protected RecyclerView.Adapter getViewAdapter(String[] data) {
        return new TextListViewAdapter(data);
    }

    @Override
    protected void configureViewModel(LiveDataViewModel<String[]> viewModel) {
        //Nothing to Configure
    }

    @Override
    protected LiveDataViewModel<String[]> getViewModel() { return null; }
}
