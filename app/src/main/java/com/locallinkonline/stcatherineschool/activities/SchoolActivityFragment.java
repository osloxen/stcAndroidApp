package com.locallinkonline.stcatherineschool.activities;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.locallinkonline.stcatherineschool.R;
import com.locallinkonline.stcatherineschool.rest.controller.ActivityController;
import com.locallinkonline.stcatherineschool.rest.controller.ActivityScheduleController;
import com.locallinkonline.stcatherineschool.rest.model.Activities;


/**
 * Created by dberge on 3/18/18.
 */

public class SchoolActivityFragment extends android.app.Fragment {

    String notesData = "Loading...";
    String announcementData;
    String performanceData;
    String regularScheduleData;

    String activity;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.school_activity_layout, container, false);

        TextView notes = (TextView) view.findViewById(R.id.notesDataTV);

        notes.setText(notesData);


        new GetSchoolActivityData().execute();


        // Inflate the layout for this fragment
        return view;

    }



    private class GetSchoolActivityData extends AsyncTask<Void,Void,Void> {



        @Override
        protected Void doInBackground(Void... params) {


            System.out.println("THIS IS ASYNC WORKING!!!");

            ActivityController activityController = new ActivityController();
            Activities activityInfoFromCloud = activityController.getActivityNotifications(SchoolActivityFragment.this.activity);

            String[] activityDataArrayFromCloud = activityInfoFromCloud.getNotifications();

            SchoolActivityFragment.this.announcementData = activityDataArrayFromCloud[1];
            SchoolActivityFragment.this.notesData = activityDataArrayFromCloud[3];
            SchoolActivityFragment.this.regularScheduleData = activityDataArrayFromCloud[5];
            SchoolActivityFragment.this.performanceData = activityDataArrayFromCloud[7];

            return null;
        }


        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            Log.d("Annoucement","This is onPostExecute");

            SchoolActivityFragment.this.getView().invalidate();

            TextView notesTextView = (TextView) getView().findViewById(R.id.notesDataTV);
            notesTextView.setText(SchoolActivityFragment.this.notesData);
            notesTextView.setMovementMethod(new ScrollingMovementMethod());

            TextView announcementTextView = (TextView) getView().findViewById(R.id.announcementDataTV);
            announcementTextView.setText(SchoolActivityFragment.this.announcementData);
            announcementTextView.setMovementMethod(new ScrollingMovementMethod());

            TextView regSchedTV = (TextView) getView().findViewById(R.id.regScheduleDataTV);
            regSchedTV.setText(SchoolActivityFragment.this.regularScheduleData);
            regSchedTV.setMovementMethod(new ScrollingMovementMethod());

            TextView productionSched = (TextView) getView().findViewById(R.id.performanceSchedDataTV);
            productionSched.setText(SchoolActivityFragment.this.regularScheduleData);
            productionSched.setMovementMethod(new ScrollingMovementMethod());

            TextView activityTitle = (TextView) getView().findViewById(R.id.activityTextView);
            activityTitle.setText(SchoolActivityFragment.this.activity);
        }
    }


}