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

public class WorkInProgressFragment extends Fragment {

    private View view;
    private TextView textView;

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
}
