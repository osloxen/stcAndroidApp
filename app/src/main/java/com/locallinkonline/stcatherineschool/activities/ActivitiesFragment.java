package com.locallinkonline.stcatherineschool.activities;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ActivitiesFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ActivitiesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ActivitiesFragment extends android.app.Fragment {


    String[] mItems = {"Loading..."};
    String[] allActivities = {"Volleyball", "Drama"};

    //List<Lunch> allLunches = new List<Lunch>();
    ArrayList<String> scheduleList = new ArrayList<>();

    ListView listView;
    ArrayAdapter<String> listViewAdapter;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ActivitiesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ActivitiesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ActivitiesFragment newInstance(String param1, String param2) {
        ActivitiesFragment fragment = new ActivitiesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_activities, container, false);


        listView = view.findViewById(R.id.activitiesList);

        listViewAdapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                allActivities
        );

        listView.setAdapter(listViewAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // When clicked, show a toast with the TextView text
                Toast.makeText(getActivity(),
                        ((TextView) view).getText(), Toast.LENGTH_SHORT).show();

                Log.d("position", Integer.toString(position));
                Log.d("id", Long.toString(id));
                Log.d("string value: ", allActivities[position]);

                if (allActivities[position].equals("Volleyball")) {

                    ActivitiesGradesFragment actGradeFragment = new ActivitiesGradesFragment();
                    actGradeFragment.activity = allActivities[position].toLowerCase().replaceAll("\\s+","");

                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.rootActivityView, actGradeFragment).commit();
                } else if (allActivities[position].equals("Drama")) {

                    SchoolActivityFragment schoolActivityFragment = new SchoolActivityFragment();

                    schoolActivityFragment.activity = "drama";

                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.rootActivityView, schoolActivityFragment).commit();

                }


            }
        });


        // Inflate the layout for this fragment
        return view;


    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }





    private class GetLunchData extends AsyncTask<Void,Void,Void> {



        @Override
        protected Void doInBackground(Void... params) {


            System.out.println("THIS IS ASYNC WORKING!!!");

            // DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date startDate = new Date();
            long ltime=startDate.getTime()+8*24*60*60*1000;
            Date endDate=new Date(ltime);


            ActivityScheduleController scheduleController = new ActivityScheduleController();
            //            List<Lunch> lunchList = lunchController.getLunches(startDate,endDate);
            SportsSchedule scheduleFromCloud = scheduleController.getSchedule("8",
                                                                    startDate,
                                                                    endDate,
                                                                    "volleyball",
                                                                    "girls",
                                                                    "all");
            List<SportEvent> scheduleList = scheduleFromCloud.getSportScheduleArray();
            List<String> updateScheduleWithThisArray = new ArrayList<>();

            for(SportEvent event : scheduleList) {

                Log.d("Lunch Description: ", event.getNotes());
                updateScheduleWithThisArray.add(event.getNotes());
            }

            ActivitiesFragment.this.allActivities = updateScheduleWithThisArray.toArray(new String[updateScheduleWithThisArray.size()]);

            return null;
        }


        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            Log.d("postExecute", Arrays.toString(ActivitiesFragment.this.allActivities));


            listViewAdapter = new ArrayAdapter<String>(
                    getActivity(),
                    android.R.layout.simple_list_item_1,
                    ActivitiesFragment.this.allActivities
            );



            ActivitiesFragment.this.listView.setAdapter(listViewAdapter);

        }
    }
}
