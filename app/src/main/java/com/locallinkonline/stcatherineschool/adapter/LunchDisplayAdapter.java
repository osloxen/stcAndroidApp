package com.locallinkonline.stcatherineschool.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.locallinkonline.stcatherineschool.R;
import com.locallinkonline.stcatherineschool.room.entity.LunchEntity;

import java.text.SimpleDateFormat;
import java.util.Objects;

import androidx.annotation.NonNull;

/**
 * Created by dberge on 3/15/18.
 */

public class LunchDisplayAdapter extends ArrayAdapter<LunchEntity> {


    public LunchDisplayAdapter(@NonNull Context context, LunchEntity[] homework) {
        super(context, R.layout.homework_subject_header ,homework);
    }

    @NonNull
    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd");

        LayoutInflater eventListInflater = LayoutInflater.from(getContext());
        View homeworkListView = eventListInflater.inflate(R.layout.lunch_layout, viewGroup, false);

        LunchEntity currentLunch = getItem(position);

        TextView dateTV = homeworkListView.findViewById(R.id.lunchDateTV);
        TextView menuItemTV = homeworkListView.findViewById(R.id.lunchMenuTV);

        dateTV.setText(sdf.format(Objects.requireNonNull(currentLunch.getEventDate())));
        menuItemTV.setText(currentLunch.getSummary());

        return homeworkListView;
    }
}
