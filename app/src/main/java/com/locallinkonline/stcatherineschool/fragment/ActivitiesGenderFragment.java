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

public class ActivitiesGenderFragment extends Fragment {

    String[] allGenders = { "Girls", "Boys"};

    ListView listView;
    ArrayAdapter<String> listViewAdapter;

    String activity;
    String grade;

    public ActivitiesGenderFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_activities_gender, container, false);

        listView = view.findViewById(R.id.activitiesGenderListView);

        listViewAdapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                allGenders
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
                Log.d("string value: ", allGenders[position]);


                ActivitiesResultsFragment actResultsFragment = new ActivitiesResultsFragment();
                actResultsFragment.grade = grade;
                actResultsFragment.activity = activity;
                actResultsFragment.gender = allGenders[position].toLowerCase();

                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.rootActivityView, actResultsFragment).commit();

            }
        });

        // Inflate the layout for this fragment
        return view;

    }
}
