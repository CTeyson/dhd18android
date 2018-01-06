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
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.unikoeln.mazey.dhdexamplesecond.R;
// this class is impemented when imprint-menupoint is clicked
public class ImprintFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private int check;

    private Context context;
    private View view;
    private Spinner spinner;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        // change fragment-xml file to start an other fragment then originally intended
        //view = inflater.inflate(R.layout.settings_fragment, container, false);
        view = inflater.inflate(R.layout.imprint_privacy, container, false);


        /* // This isnt needed for now, because the imprint is a static fragment
        context = this.getContext();
        check = 0;
        spinner = view.findViewById(R.id.language_spinner);
        this.initSpinner();
        this.clickListener();*/

        return view;
    }

    // No Listener needed yet
    private void clickListener() {

    }

    /*
    private void initSpinner() {
        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(context, R.array.language_choices, android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(this);
    }
    */


    // ???
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
    }

    // Easter EGG!!
    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        Snackbar.make(view, "You came here for nothing?!", Snackbar.LENGTH_SHORT).show();
    }
}
