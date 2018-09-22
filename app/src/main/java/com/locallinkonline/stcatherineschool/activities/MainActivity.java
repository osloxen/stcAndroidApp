package com.locallinkonline.stcatherineschool.activities;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;

import com.google.android.material.navigation.NavigationView;
import com.locallinkonline.stcatherineschool.R;
import com.locallinkonline.stcatherineschool.fragment.LunchFragment;
import com.locallinkonline.stcatherineschool.fragment.SchoolScheduleFragment;
import com.locallinkonline.stcatherineschool.fragment.TwitterFragment;
import com.locallinkonline.stcatherineschool.room.entity.MenuEntity;
import com.locallinkonline.stcatherineschool.room.repository.StCatherineRepository;
import com.locallinkonline.stcatherineschool.service.DataUpdateJob;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LiveData;

public class MainActivity extends AppCompatActivity implements
        LunchFragment.OnFragmentInteractionListener,
        SchoolScheduleFragment.OnFragmentInteractionListener,
        TwitterFragment.OnFragmentInteractionListener {

    private static final int LOAD_DATA_JOB_ID = 1234;
    private DrawerLayout mDrawerLayout;
    private Map<String, String> menuNameToIdentifier = new HashMap<>();
    private SparseArray<String> groupToTypeMap = new SparseArray<>();
    private Map<String, List<String>> groupToMenuItem = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        JobScheduler jobScheduler =
                (JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);
        int result = jobScheduler.schedule(new JobInfo.Builder(LOAD_DATA_JOB_ID,
                new ComponentName(this, DataUpdateJob.class))
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
                .setPeriodic(30_000)
                .build());

        setContentView(R.layout.main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar mActionBar = getSupportActionBar();
        mActionBar.setDisplayHomeAsUpEnabled(true);
        mActionBar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);

        setupNavigationView();
    }

    private void setupNavigationView() {
        final NavigationView navigationView = findViewById(R.id.nav_view);

        mDrawerLayout = findViewById(R.id.drawer_layout);

        configureMenu();

        SchoolScheduleFragment fragment = new SchoolScheduleFragment();
        Bundle schoolScheduleBundle = new Bundle();
        schoolScheduleBundle.putString("title", "School Schedule");
        schoolScheduleBundle.putString("identifier", "school-schedule");
        fragment.setArguments(schoolScheduleBundle);

        pushFragment(fragment);

        // Set action to perform when any menu-item is selected.
        navigationView.setNavigationItemSelectedListener(menuItem -> {
            // close drawer when item is tapped
            selectFragment(menuItem);
            mDrawerLayout.closeDrawers();
            return true;
        });

        LiveData<List<MenuEntity>> menuModel = new LiveData<List<MenuEntity>>() {
            private final StCatherineRepository stCatherineRepository = new StCatherineRepository(getApplication());
            @Override
            public List<MenuEntity> getValue() {
                return stCatherineRepository.getAllMenuItems();
            }
        };

        menuModel.observe(this, data -> {
            reCreateMenu();
        });
    }

    private void configureMenu() {
        new AsyncTask<Void, Void, Void>() {

            StCatherineRepository repo = new StCatherineRepository(getApplication());

            @Override
            protected Void doInBackground(Void... voids) {
                List<String> groups = repo.getMenuGroups();

                int i = 0;
                for (int groupIndex = 0; groupIndex < groups.size(); groupIndex++) {
                    String group = groups.get(groupIndex);

                    List<String> menuNames = new ArrayList<>();
                    groupToTypeMap.append(groupIndex, group);

                    List<MenuEntity> entities = repo.getMenuItemsForGroup(group);
                    for (int j = 0; j < entities.size(); j++) {
                        menuNames.add(entities.get(j).getDisplayName());
                        menuNameToIdentifier.put(entities.get(j).getDisplayName(), entities.get(j).getIdentifier());
                        i++;
                    }

                    groupToMenuItem.put(group, menuNames);
                }

                repo.getNewMenuItems();

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                reCreateMenu();
            }
        }.execute();
    }

    private void selectFragment(MenuItem item) {
        configureMenu();

        switch (item.getItemId()) {
            case 100:
                SchoolScheduleFragment schoolScheduleFragment = new SchoolScheduleFragment();
                Bundle schoolScheduleBundle = new Bundle();
                schoolScheduleBundle.putString("title", "School Schedule");
                schoolScheduleBundle.putString("identifier", "school-schedule");
                schoolScheduleFragment.setArguments(schoolScheduleBundle);
                pushFragment(schoolScheduleFragment);
                break;
            case 101:
                pushFragment(new LunchFragment());
                break;
            case 102:
                pushFragment(new TwitterFragment());
                break;
            default:
                Bundle bundle = new Bundle();
                bundle.putString("identifier", menuNameToIdentifier.get(item.getTitle()));
                bundle.putString("type", groupToTypeMap.get(item.getGroupId()));
                bundle.putString("title", item.getTitle().toString());
                SchoolScheduleFragment fragment = new SchoolScheduleFragment();
                fragment.setArguments(bundle);
                pushFragment(fragment);
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
                ft.addToBackStack(null);
                ft.commit();
            }
        }
    }

    private void reCreateMenu() {
        NavigationView view = findViewById(R.id.nav_view);
        Menu menu = view.getMenu();
        menu.clear();

        SubMenu generalMenu = menu.addSubMenu("General");

        //School Schedule
        MenuItem item = generalMenu.add(0,100,0, R.string.school_schedule_title);
        item.setIcon(R.drawable.ic_dashboard_black_24dp);

        //Lunch Schedule
        MenuItem lunchItem = generalMenu.add(0,101,0, R.string.lunch_title);
        lunchItem.setIcon(R.drawable.ic_notifications_black_24dp);

        int gIndex = 0;
        int i = 0;
        for(Map.Entry<String, List<String>> entry : groupToMenuItem.entrySet()) {
            String group = entry.getKey();
            SubMenu subMenu = menu.addSubMenu(gIndex, i,0, group);
            for(String menuItem : entry.getValue()) {
                subMenu.add(gIndex, i,0, menuItem);
                i++;
            }
            gIndex++;
        }

        //Social
        SubMenu socialMenu = menu.addSubMenu("Social");
        MenuItem twitterItem = socialMenu.add(1000,102,1000, R.string.twitter_title);
        twitterItem.setIcon(R.drawable.ic_icons8_twitter);

        view.invalidate();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
