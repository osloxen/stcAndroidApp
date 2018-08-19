package com.locallinkonline.stcatherineschool.fragment;

import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.locallinkonline.locallinkschool.fragment.StandardTextViewListFragment;
import com.locallinkonline.locallinkschool.listener.StandardTouchListener;
import com.locallinkonline.stcatherineschool.R;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by dberge on 3/14/18.
 */

class ActivitiesGradesFragment extends StandardTextViewListFragment {


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
    protected String[] getData() {
        return allGrades;
    }

    @Override
    protected StandardTouchListener.ClickListener getClickListener(RecyclerView view) {
        return new StandardTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Toast.makeText(getActivity(),
                        ((TextView) view).getText(), Toast.LENGTH_SHORT).show();

                Log.d("position", Integer.toString(position));
                Log.d("string value: ", allGrades[position]);

                ActivitiesGenderFragment actGenderFragment = new ActivitiesGenderFragment();
                actGenderFragment.activity = activity;
                actGenderFragment.grade = allGrades[position].toLowerCase().replaceAll("\\s+","");

                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.rootActivityView, actGenderFragment).commit();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        };
    }

} // end of ActivitiesGradesFragment
