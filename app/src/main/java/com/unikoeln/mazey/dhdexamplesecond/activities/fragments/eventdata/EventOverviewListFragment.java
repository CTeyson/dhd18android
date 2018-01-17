package com.unikoeln.mazey.dhdexamplesecond.activities.fragments.eventdata;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.unikoeln.mazey.dhdexamplesecond.R;
import com.unikoeln.mazey.dhdexamplesecond.activities.data.eventdata.EventItem;
import com.unikoeln.mazey.dhdexamplesecond.activities.utils.adapter.CustomArrayAdapter;
import com.unikoeln.mazey.dhdexamplesecond.activities.utils.file.FileUtils;

import java.util.List;

public class EventOverviewListFragment extends Fragment {

    private ListView eventData;

    private ProgressBar progressBar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.event_data_overview_fragment, container, false);
        eventData = view.findViewById(R.id.list_event);
        progressBar = view.findViewById(R.id.progress);

        InitTask initTask = new InitTask();
        initTask.execute();

        return view;
    }

    private class InitTask extends AsyncTask<Void, Void, CustomArrayAdapter> {

        @Override
        protected CustomArrayAdapter doInBackground(Void... voids) {

            FileUtils utils = new FileUtils();
            List<EventItem> events = utils.deserializeEventItemList(getContext());

            return new CustomArrayAdapter(getContext(), events);
        }

        @Override
        protected void onPostExecute(CustomArrayAdapter customArrayAdapter) {
            super.onPostExecute(customArrayAdapter);
            progressBar.setVisibility(View.INVISIBLE);
            eventData.setAdapter(customArrayAdapter);
        }

    }

}

