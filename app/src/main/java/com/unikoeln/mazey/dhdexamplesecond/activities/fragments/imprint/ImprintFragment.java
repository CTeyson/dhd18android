package com.unikoeln.mazey.dhdexamplesecond.activities.fragments.imprint;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.unikoeln.mazey.dhdexamplesecond.R;

// this class is implemented when imprint-menupoint is clicked
public class ImprintFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private View view;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        //  inflate xml-fragment imprint_privacy_fragment
        view = inflater.inflate(R.layout.imprint_privacy, container, false);


        return view;
    }


    // CallBack-methode for  selection of items
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
    }

    // CallBack-methode for not selecting any item
    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        Snackbar.make(view, "You came here for nothing?!", Snackbar.LENGTH_SHORT).show();
    }
}