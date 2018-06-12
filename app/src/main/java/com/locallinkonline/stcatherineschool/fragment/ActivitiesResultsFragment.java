package com.locallinkonline.stcatherineschool.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.locallinkonline.stcatherineschool.R;
import com.locallinkonline.stcatherineschool.adapter.ActivityResultAdapter;
import com.locallinkonline.stcatherineschool.rest.controller.ActivityScheduleController;
import com.locallinkonline.stcatherineschool.rest.model.SportEvent;
import com.locallinkonline.stcatherineschool.rest.model.SportsSchedule;
import com.locallinkonline.stcatherineschool.rest.tasks.GetAdImpressionTask;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import androidx.fragment.app.Fragment;

/**
 * Created by dberge on 3/14/18.
 */

class ActivitiesResultsFragment extends Fragment {
    private final String[] results = {"Loading..."};
    private final SportEvent sportEvent = new SportEvent();
    private SportEvent[] events = {sportEvent.getLoadingSportEvent()};

    private ListView listView;
    private ActivityResultAdapter eventListAdapter;

    String activity;
    String grade;
    String gender;

    private final GetAdImpressionTask adImpressionRetriever;

    public ActivitiesResultsFragment() {
        this.adImpressionRetriever = new GetAdImpressionTask(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_activities_results, container, false);

        listView = view.findViewById(R.id.activitiesResultsListView);

        eventListAdapter = new ActivityResultAdapter(this.getContext(),events);

        listView.setAdapter(eventListAdapter);

        adImpressionRetriever.execute();

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
            //long ltime=startDate.getTime()+20*24*60*60*1000;  // 30 is for 30 days
            Calendar c=new GregorianCalendar();
            c.add(Calendar.DATE, 30);
            Date endDate = c.getTime();
            //Date endDate=new Date(ltime);

            Log.d("grade: ", grade);
            Log.d("gender: ", gender);
            Log.d("activity: ", activity);

            ActivityScheduleController scheduleController = new ActivityScheduleController();

            SportsSchedule scheduleFromCloud = scheduleController.getSchedule(
                    grade,
                    startDate,
                    endDate,
                    activity,
                    gender,
                    "all");

            events = scheduleFromCloud.getActivityScheduleAsArray();

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            Log.d("postExecute", Arrays.toString(ActivitiesResultsFragment.this.results));

            eventListAdapter = new ActivityResultAdapter(ActivitiesResultsFragment.this.getContext(),events);

            ActivitiesResultsFragment.this.listView.setAdapter(eventListAdapter);

            if (events.length < 1) {
                Toast.makeText(getActivity(),
                        "I do not have information on " +
                                "grade: " + grade + " with activity: " + activity +
                                "with gender: " + gender + " Please make sure the activity " +
                                "is in season and if it is let the school know this app needs " +
                                "to be updated.", Toast.LENGTH_LONG).show();
            }
        }
    }
}
