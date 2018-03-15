package com.locallinkonline.stcatherineschool;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.locallinkonline.stcatherineschool.activities.ActivitiesFragment;

public class HomeActivity extends AppCompatActivity implements
                                                        GradesHomeworkFragment.OnFragmentInteractionListener,
                                                        ActivitiesFragment.OnFragmentInteractionListener,
                                                        LunchFragment.OnFragmentInteractionListener,
                                                        HomeworkForGradeFragment.OnFragmentInteractionListener,
                                                        SubjectHomeworkFragment.OnFragmentInteractionListener {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        setupNavigationView();
    }



    private void setupNavigationView() {
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        if (bottomNavigationView != null) {
            // Select first menu item by default and show Fragment accordingly.
            Menu menu = bottomNavigationView.getMenu();
            selectFragment(menu.getItem(0));
            // Set action to perform when any menu-item is selected.
            bottomNavigationView.setOnNavigationItemSelectedListener(
                    new BottomNavigationView.OnNavigationItemSelectedListener() {
                        @Override
                        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                            selectFragment(item);
                            return false;
                        }
                    });
        }
    }



    protected void selectFragment(MenuItem item) {
        item.setChecked(true);
        switch (item.getItemId()) {
            case R.id.navigation_homework:
                pushFragment(new GradesHomeworkFragment());
                break;
            case R.id.navigation_activities:
                pushFragment(new ActivitiesFragment());
                break;
            case R.id.navigation_lunch:
                pushFragment(new LunchFragment());
                break;
        }
    }



    protected void pushFragment(Fragment fragment) {
        if (fragment == null)
            return;

        FragmentManager fragmentManager = getFragmentManager();
        if (fragmentManager != null) {
            FragmentTransaction ft = fragmentManager.beginTransaction();
            if (ft != null) {
                ft.replace(R.id.rootActivityView, fragment);
                ft.commit();
            }
        }
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
