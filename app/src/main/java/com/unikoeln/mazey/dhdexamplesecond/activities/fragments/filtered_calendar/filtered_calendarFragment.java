package com.unikoeln.mazey.dhdexamplesecond.activities.fragments.filtered_calendar;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.unikoeln.mazey.dhdexamplesecond.R;

/**
 * Created by DerSchmitz on 16.01.2018.
 */


public class filtered_calendarFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private View view;

    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.filtered_calendar_fragment, container, false);


        return view;
    }


    // CallBack-methode for  selection of items

    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
    }

    // CallBack-methode for not selecting any item

    public void onNothingSelected(AdapterView<?> adapterView) {

    }







}
