package com.locallinkonline.stcatherineschool.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.locallinkonline.stcatherineschool.R;
import com.locallinkonline.stcatherineschool.rest.model.SportEvent;

import java.text.SimpleDateFormat;

/**
 * Created by dberge on 3/15/18.
 */

public class HomeworkSubjectDisplayAdapter extends ArrayAdapter<String> {


    public HomeworkSubjectDisplayAdapter(@NonNull Context context, String[] homework) {
        super(context, R.layout.homework_subject_header ,homework);
    }


    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        LayoutInflater eventListInflater = LayoutInflater.from(getContext());
        View homeworkListView = eventListInflater.inflate(R.layout.homework_subject_header, viewGroup, false);

        String currentHomework = getItem(position);

        TextView titleTV = (TextView) homeworkListView.findViewById(R.id.subjectTitleTV);
        TextView homeworkTV = (TextView) homeworkListView.findViewById(R.id.homeworkTV);


        if ((position%2) == 0) {  // Check for odd number

            homeworkTV.setAlpha(0.0f);
            titleTV.setAlpha(1.0f);
            titleTV.setText(currentHomework);
        } else {

            titleTV.setAlpha(0.0f);
            homeworkTV.setAlpha(1.0f);
            homeworkTV.setText(currentHomework);

        }

        return homeworkListView;
    }
}
