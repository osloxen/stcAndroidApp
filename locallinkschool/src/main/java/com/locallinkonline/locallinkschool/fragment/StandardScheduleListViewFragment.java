package com.locallinkonline.locallinkschool.fragment;


import com.locallinkonline.locallinkschool.listener.StandardTouchListener;

import androidx.recyclerview.widget.RecyclerView;

public class StandardScheduleListViewFragment extends StandardRecyclerViewFragment<String[]> {
    @Override
    protected String[] getData() {
        return new String[0];
    }

    @Override
    protected RecyclerView.Adapter getViewAdapter(String[] data) {
        return null;
    }

    @Override
    protected StandardTouchListener.ClickListener getClickListener(RecyclerView view) {
        return null;
    }
}
