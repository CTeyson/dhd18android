package com.unikoeln.mazey.dhdexamplesecond.activities.activities;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.unikoeln.mazey.dhdexamplesecond.R;
import com.unikoeln.mazey.dhdexamplesecond.activities.asynctask.ConfToolCommunication;
import com.unikoeln.mazey.dhdexamplesecond.activities.data.eventdata.Session;
import com.unikoeln.mazey.dhdexamplesecond.activities.fragments.WorkInProgressFragment;
import com.unikoeln.mazey.dhdexamplesecond.activities.fragments.filtered_calendar.FilterEventsFragment;
import com.unikoeln.mazey.dhdexamplesecond.activities.fragments.location.MapFragment;
import com.unikoeln.mazey.dhdexamplesecond.activities.fragments.imprint.ImprintFragment;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private List<Session> sessions = null;
    private ConfToolCommunication communication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        sessions = this.loadDataFromConfTool();

        this.openWithEventOverview();
    }

    private List<Session> loadDataFromConfTool() {
        List<Session> tmp = null;
        try {
            communication = new ConfToolCommunication();
            communication.execute();
            tmp = communication.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return tmp;
    }


    private void openWithEventOverview() {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_activity, new WorkInProgressFragment());
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override       // navigation item is clicked
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // read item id
        int id = item.getItemId();

        Fragment fragment = null;
        // switch cases with the id read from xml-file
        if (id == R.id.nav_events) {
            fragment = new WorkInProgressFragment();
        } else if (id == R.id.nav_timetable) {
            fragment = new WorkInProgressFragment();
        } else if (id == R.id.nav_filter_events) {
            fragment = new FilterEventsFragment();
        }  else if (id == R.id.nav_navigation) {
            fragment = new MapFragment();
            //if the item with the id nav_settings is read
        } else if (id == R.id.nav_settings) {
            // save the new Object "ImprinFragment" in fragment
            fragment = new ImprintFragment();
        }

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.main_activity, fragment);
        transaction.commit();

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
