package com.locallinkonline.stcatherineschool.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.locallinkonline.stcatherineschool.R;
import com.locallinkonline.stcatherineschool.rest.tasks.GetSchoolActivityTask;

import androidx.fragment.app.Fragment;

/**
 * Created by dberge on 3/18/18.
 */

public class SchoolActivityFragment extends Fragment {

    private final GetSchoolActivityTask schoolActivityData;

    String activity;
    private TextView performancesScheduleView, regularScheduleDataView, notesDataView, announcementDataView;

    public SchoolActivityFragment() {
        this.schoolActivityData = new GetSchoolActivityTask(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.school_activity_layout, container, false);
        performancesScheduleView = view.findViewById(R.id.performanceSchedDataTV);
        regularScheduleDataView = view.findViewById(R.id.regScheduleDataTV);
        notesDataView = view.findViewById(R.id.notesDataTV);
        announcementDataView = view.findViewById(R.id.announcementDataTV);

        schoolActivityData.execute(activity);

        // Inflate the layout for this fragment
        return view;

    }

    public TextView getNotesDataView() {
        return notesDataView;
    }

    public TextView getAnnouncementDataView() {
        return announcementDataView;
    }

    public TextView getRegularScheduleDataView() {
        return regularScheduleDataView;
    }

    public TextView getPerformanceDataView() {
        return performancesScheduleView;
    }
}