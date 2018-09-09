package com.locallinkonline.stcatherineschool.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.locallinkonline.locallinkschool.fragment.StandardScheduleListViewFragment;
import com.locallinkonline.locallinkschool.listener.StandardTouchListener;
import com.locallinkonline.locallinkschool.view.LiveDataViewModel;
import com.locallinkonline.stcatherineschool.R;
import com.locallinkonline.stcatherineschool.room.entity.SchoolScheduleEntity;
import com.locallinkonline.stcatherineschool.view.SchoolScheduleViewModel;

import java.util.List;

import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

public class SchoolScheduleFragment extends StandardScheduleListViewFragment<SchoolScheduleEntity> {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Toolbar toolbar = container.findViewById(R.id.toolbar);

        toolbar.setTitle(R.string.school_schedule_title);

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected List<SchoolScheduleEntity> getStaticData() {
        return null;
    }

    @Override
    protected StandardTouchListener.ClickListener getClickListener(View view) {
        return new StandardTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
               
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        };
    }

    @Override
    protected LiveDataViewModel<List<SchoolScheduleEntity>> getViewModel() {
        return ViewModelProviders.of(this).get(SchoolScheduleViewModel.class);
    }

    public interface OnFragmentInteractionListener {
    }
}
