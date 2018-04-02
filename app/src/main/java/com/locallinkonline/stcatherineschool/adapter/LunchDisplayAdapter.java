package com.locallinkonline.stcatherineschool.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.locallinkonline.stcatherineschool.R;
import com.locallinkonline.stcatherineschool.rest.model.Lunch;

import java.text.SimpleDateFormat;

/**
 * Created by dberge on 3/15/18.
 */

public class LunchDisplayAdapter extends ArrayAdapter<Lunch> {


    public LunchDisplayAdapter(@NonNull Context context, Lunch[] homework) {
        super(context, R.layout.homework_subject_header ,homework);
    }


    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd");

        LayoutInflater eventListInflater = LayoutInflater.from(getContext());
        View homeworkListView = eventListInflater.inflate(R.layout.lunch_layout, viewGroup, false);

        Lunch currentLunch = getItem(position);

        TextView dateTV = (TextView) homeworkListView.findViewById(R.id.lunchDateTV);
        TextView menuItemTV = (TextView) homeworkListView.findViewById(R.id.lunchMenuTV);


        dateTV.setText(sdf.format(currentLunch.getDate()));
        menuItemTV.setText(currentLunch.getDescription());


        return homeworkListView;
    }
}
