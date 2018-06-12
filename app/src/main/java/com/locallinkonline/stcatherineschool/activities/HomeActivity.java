package com.locallinkonline.stcatherineschool.activities;

import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.locallinkonline.stcatherineschool.fragment.GradesHomeworkFragment;
import com.locallinkonline.stcatherineschool.fragment.HomeworkForGradeFragment;
import com.locallinkonline.stcatherineschool.fragment.LunchFragment;
import com.locallinkonline.stcatherineschool.R;
import com.locallinkonline.stcatherineschool.fragment.SelectActivityFragment;
import com.locallinkonline.stcatherineschool.fragment.SubjectHomeworkFragment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class HomeActivity extends AppCompatActivity implements
        GradesHomeworkFragment.OnFragmentInteractionListener,
                                                        SelectActivityFragment.OnFragmentInteractionListener,
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
        BottomNavigationView bottomNavigationView = findViewById(R.id.navigation);
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

    private void selectFragment(MenuItem item) {
        item.setChecked(true);
        switch (item.getItemId()) {
            case R.id.navigation_homework:
                pushFragment(new GradesHomeworkFragment());
                break;
            case R.id.navigation_activities:
                pushFragment(new SelectActivityFragment());
                break;
            case R.id.navigation_lunch:
                pushFragment(new LunchFragment());
                break;
        }
    }

    private void pushFragment(Fragment fragment) {
        if (fragment == null)
            return;

        FragmentManager fragmentManager = getSupportFragmentManager();
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
