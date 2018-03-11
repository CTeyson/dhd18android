package com.unikoeln.mazey.dhdexamplesecond.activities.fragments.eventdata;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.unikoeln.mazey.dhdexamplesecond.R;
import com.unikoeln.mazey.dhdexamplesecond.activities.utils.adapter.EventItemListAdapter;
import com.unikoeln.mazey.dhdexamplesecond.activities.data.eventdata.EventItem;
import com.unikoeln.mazey.dhdexamplesecond.activities.utils.file.FileUtils;

import java.util.List;

public class EventOverviewListFragment extends Fragment {

    private RecyclerView eventData;
    private RecyclerView.LayoutManager layoutManager;

    private ProgressBar progressBar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.event_data_overview_fragment, container, false);

        progressBar = view.findViewById(R.id.progress);

        eventData = view.findViewById(R.id.list_event);
        layoutManager = new LinearLayoutManager(getContext());
        eventData.setLayoutManager(layoutManager);

        InitTask task = new InitTask();
        task.execute();

        return view;
    }

    private class InitTask extends AsyncTask<Void, Void, EventItemListAdapter> {

        @Override
        protected EventItemListAdapter doInBackground(Void... voids) {
            FileUtils utils = new FileUtils();
            List<EventItem> events = utils.deserializeEventItemList(getContext());

            return new EventItemListAdapter(events, getContext());
        }

        @Override
        protected void onPostExecute(EventItemListAdapter adapter) {
            super.onPostExecute(adapter);

            progressBar.setVisibility(View.INVISIBLE);
            RecyclerView.Adapter mAdapter = adapter;
            eventData.setAdapter(mAdapter);
        }
    }
}

