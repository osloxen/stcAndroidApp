package com.locallinkonline.stcatherineschool.activities;

import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.locallinkonline.stcatherineschool.fragment.LunchFragment;
import com.locallinkonline.stcatherineschool.R;
import com.locallinkonline.stcatherineschool.fragment.SchoolScheduleFragment;
import com.locallinkonline.stcatherineschool.fragment.TwitterFragment;

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
        TwitterFragment.OnFragmentInteractionListener{

    private DrawerLayout mDrawerLayout;
    private ActionBar mActionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mActionBar = getSupportActionBar();
        mActionBar.setDisplayHomeAsUpEnabled(true);
        mActionBar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);
        setupNavigationView();
    }

    private void setupNavigationView() {
        final NavigationView navigationView = findViewById(R.id.nav_view);
        mDrawerLayout = findViewById(R.id.drawer_layout);

        //Start w/ the first option
        selectFragment(navigationView.getMenu().getItem(0));

        if (navigationView != null) {
            // Set action to perform when any menu-item is selected.
            navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(MenuItem menuItem) {
                    // close drawer when item is tapped
                    selectFragment(menuItem);
                    mDrawerLayout.closeDrawers();
                    return true;
                }
            });
        }
    }

    private void selectFragment(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_school_schedule:
                pushFragment(new SchoolScheduleFragment());
                break;
            case R.id.navigation_lunch:
                pushFragment(new LunchFragment());
                break;
            case R.id.navigation_twitter:
                pushFragment(new TwitterFragment());
                break;
            default:
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
