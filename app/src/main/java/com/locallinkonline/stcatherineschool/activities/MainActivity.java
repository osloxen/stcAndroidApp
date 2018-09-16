package com.locallinkonline.stcatherineschool.activities;

import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.locallinkonline.stcatherineschool.R;
import com.locallinkonline.stcatherineschool.fragment.LunchFragment;
import com.locallinkonline.stcatherineschool.fragment.SchoolScheduleFragment;
import com.locallinkonline.stcatherineschool.fragment.TwitterFragment;
import com.locallinkonline.stcatherineschool.rest.tasks.MenuAsyncTask;
import com.locallinkonline.stcatherineschool.room.repository.StCatherineRepository;

import java.util.HashMap;
import java.util.Map;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity implements
        LunchFragment.OnFragmentInteractionListener,
        SchoolScheduleFragment.OnFragmentInteractionListener,
        TwitterFragment.OnFragmentInteractionListener {

    private DrawerLayout mDrawerLayout;
    private StCatherineRepository repo;
    private Map<Integer, String> menuItemToIdentifier = new HashMap<>();
    private Map<Integer, String> groupToTypeMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

        Menu menu = navigationView.getMenu();

        configureMenu(menu);
        //Start w/ the first option
        SchoolScheduleFragment fragment = new SchoolScheduleFragment();
        Bundle schoolScheduleBundle = new Bundle();
        schoolScheduleBundle.putString("title", "School Schedule");
        fragment.setArguments(schoolScheduleBundle);

        pushFragment(fragment);

        // Set action to perform when any menu-item is selected.
        navigationView.setNavigationItemSelectedListener(menuItem -> {
            // close drawer when item is tapped
            selectFragment(menuItem);
            mDrawerLayout.closeDrawers();
            return true;
        });

        navigationView.invalidate();
    }

    private void configureMenu(Menu menu) {
        new MenuAsyncTask(this, menuItemToIdentifier, groupToTypeMap).execute(menu);
    }

    private void selectFragment(MenuItem item) {
        switch (item.getItemId()) {
            case 100:
                SchoolScheduleFragment schoolScheduleFragment = new SchoolScheduleFragment();
                Bundle schoolScheduleBundle = new Bundle();
                schoolScheduleBundle.putString("title", "School Schedule");
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
                bundle.putString("identifier", menuItemToIdentifier.get(item.getItemId()));
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
