package com.unikoeln.mazey.dhdexamplesecond.activities.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.unikoeln.mazey.dhdexamplesecond.R;
import com.unikoeln.mazey.dhdexamplesecond.activities.data.eventdata.Session;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

// TODO Access data via "singleton object"

public class WorkInProgressFragment extends Fragment implements Observer {

    private View view;
    private TextView textView;

    boolean sessionsLoaded;

    List<Session> sessions = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.work_in_progress_layout, container, false);
        textView = view.findViewById(R.id.progress);

        this.showSnack();
        return view;
    }

    private void showSnack() {
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Work In Progress!", Snackbar.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void update(Observable observable, Object o) {
        sessionsLoaded = (Boolean) o;
    }
}
