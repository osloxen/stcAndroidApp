package com.locallinkonline.stcatherineschool.fragment;

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

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

/**
 * Created by dberge on 3/14/18.
 */

class ActivitiesGradesFragment extends Fragment {


    private final String[] allGrades = { "Grade 8",
            "Grade 7",
            "Grade 6",
            "Grade 5",
            "Grade 4",
            "Grade 3",
            "Grade 2",
            "Grade 1",
            "Kindergarten"};

    String activity;

    public ActivitiesGradesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_activities_grades, container, false);


        /*
        DAVID ADD ALL THE CREATE VIEW FROM LUNCHES HERE.

        DON'T FORGET YOU NEED TO LIST THE SPORTS AND GRADES EVENTUALLY
         */


        ListView listView = view.findViewById(R.id.activitesGradesListView);

        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                allGrades
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
                Log.d("string value: ", allGrades[position]);


                ActivitiesGenderFragment actGenderFragment = new ActivitiesGenderFragment();
                actGenderFragment.activity = activity;
                actGenderFragment.grade = allGrades[position].toLowerCase().replaceAll("\\s+","");

                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.rootActivityView, actGenderFragment).commit();

            }
        });

        // Inflate the layout for this fragment
        return view;

    }



} // end of ActivitiesGradesFragment
