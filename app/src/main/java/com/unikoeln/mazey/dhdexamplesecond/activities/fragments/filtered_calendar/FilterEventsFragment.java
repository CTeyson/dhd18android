package com.unikoeln.mazey.dhdexamplesecond.activities.fragments.filtered_calendar;

import android.app.AlertDialog;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.unikoeln.mazey.dhdexamplesecond.R;




    public class FilterEventsFragment extends Fragment implements AdapterView.OnItemSelectedListener {

        private View view;

        @Nullable
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
                            //  filtered_calendar_fragment
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
