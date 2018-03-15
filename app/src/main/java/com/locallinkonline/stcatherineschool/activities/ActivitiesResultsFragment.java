package com.locallinkonline.stcatherineschool.activities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.locallinkonline.stcatherineschool.R;
import com.locallinkonline.stcatherineschool.rest.controller.ActivityScheduleController;
import com.locallinkonline.stcatherineschool.rest.model.SportEvent;
import com.locallinkonline.stcatherineschool.rest.model.SportsSchedule;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by dberge on 3/14/18.
 */

public class ActivitiesResultsFragment extends android.app.Fragment {


    String[] results = {"Loading..."};

    ListView listView;
    ArrayAdapter<String> listViewAdapter;

    String activity;
    String grade;
    String gender;

    public ActivitiesResultsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_activities_results, container, false);


        listView = view.findViewById(R.id.activitiesResultsListView);

        listViewAdapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                results
        );

        listView.setAdapter(listViewAdapter);

        new GetActivityDataFromCloud().execute();

        // Inflate the layout for this fragment
        return view;

    }




    private class GetActivityDataFromCloud extends AsyncTask<Void,Void,Void> {



        @Override
        protected Void doInBackground(Void... params) {


            System.out.println("THIS IS ASYNC WORKING!!!");

            // DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date startDate = new Date();
            long ltime=startDate.getTime()+8*24*60*60*1000;
            Date endDate=new Date(ltime);


            ActivityScheduleController scheduleController = new ActivityScheduleController();
            //            List<Lunch> lunchList = lunchController.getLunches(startDate,endDate);
            SportsSchedule scheduleFromCloud = scheduleController.getSchedule("grade8",
                    startDate,
                    endDate,
                    "volleyball",
                    "girls",
                    "all");
            List<SportEvent> scheduleList = scheduleFromCloud.getSportScheduleArray();
            List<String> activitiesScheduleListFromCloud = new ArrayList<>();

            for(SportEvent event : scheduleList) {

                Log.d("Schedule Notes: ", event.getNotes());
                activitiesScheduleListFromCloud.add(event.getNotes());
            }

            ActivitiesResultsFragment.this.results = activitiesScheduleListFromCloud.toArray(new String[activitiesScheduleListFromCloud.size()]);

            return null;
        }


        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            Log.d("postExecute", Arrays.toString(ActivitiesResultsFragment.this.results));

            listViewAdapter = new ArrayAdapter<String>(
                    getActivity(),
                    android.R.layout.simple_list_item_1,
                    ActivitiesResultsFragment.this.results
            );

            ActivitiesResultsFragment.this.listView.setAdapter(listViewAdapter);
        }
    }



}
