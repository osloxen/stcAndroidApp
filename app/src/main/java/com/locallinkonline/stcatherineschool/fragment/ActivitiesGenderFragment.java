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

class ActivitiesGenderFragment extends StandardTextViewListFragment {

    private final String[] allGenders = { "Girls", "Boys"};

    String activity;
    String grade;

    public ActivitiesGenderFragment() {
        // Required empty public constructor
    }

    @Override
    protected String[] getData() {
        return allGenders;
    }

    @Override
    protected StandardTouchListener.ClickListener getClickListener(RecyclerView view) {
        return new StandardTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Toast.makeText(getActivity(),
                        ((TextView) view).getText(), Toast.LENGTH_SHORT).show();

                Log.d("position", Integer.toString(position));
                Log.d("string value: ", allGenders[position]);


                ActivitiesResultsFragment actResultsFragment = new ActivitiesResultsFragment();
                actResultsFragment.grade = grade;
                actResultsFragment.activity = activity;
                actResultsFragment.gender = allGenders[position].toLowerCase();

                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.rootActivityView, actResultsFragment).commit();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        };
    }
}
