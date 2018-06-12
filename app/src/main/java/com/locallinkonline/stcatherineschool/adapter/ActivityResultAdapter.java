package com.locallinkonline.stcatherineschool.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.locallinkonline.stcatherineschool.R;
import com.locallinkonline.stcatherineschool.rest.model.SportEvent;

import java.text.SimpleDateFormat;
import java.util.Objects;

import androidx.annotation.NonNull;

/**
 * Created by dberge on 3/15/18.
 */

public class ActivityResultAdapter extends ArrayAdapter<SportEvent> {

    public ActivityResultAdapter(@NonNull Context context, SportEvent[] events) {
        super(context, R.layout.activity_result_layout ,events);
    }

    @NonNull
    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        LayoutInflater eventListInflater = LayoutInflater.from(getContext());
        View eventsListView = eventListInflater.inflate(R.layout.activity_result_layout, viewGroup, false);

        SportEvent currentEventInArray = getItem(position);

        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd");

        TextView notes = eventsListView.findViewById(R.id.notesTextView);
        TextView startTime = eventsListView.findViewById(R.id.startTimeTextView);
        TextView endTime = eventsListView.findViewById(R.id.endTimeTextView);
        TextView date = eventsListView.findViewById(R.id.dateTextView);
        TextView locationName = eventsListView.findViewById(R.id.locationNameTextView);
        TextView locationAddress = eventsListView.findViewById(R.id.locationAddressTextView);


        notes.setText(Objects.requireNonNull(currentEventInArray).getNotes());
        startTime.setText(currentEventInArray.getStartTime());
        endTime.setText("end time goes here");
//        date.setText(currentEventInArray.getDate().toString());
        date.setText(sdf.format(currentEventInArray.getDate()));
        locationName.setText(currentEventInArray.getLocationName());
        locationAddress.setText(currentEventInArray.getLocationAddress());

        return eventsListView;
    }
}
