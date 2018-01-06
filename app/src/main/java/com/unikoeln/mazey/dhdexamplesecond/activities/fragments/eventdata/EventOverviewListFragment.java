package com.unikoeln.mazey.dhdexamplesecond.activities.fragments.eventdata;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.unikoeln.mazey.dhdexamplesecond.R;
import com.unikoeln.mazey.dhdexamplesecond.activities.data.singleton.DummyEventDataSingelton;
import com.unikoeln.mazey.dhdexamplesecond.activities.utils.adapter.CustomArrayAdapter;

public class EventOverviewListFragment extends Fragment{

    private View view;
    private ListView eventData;
    private ProgressBar progressBar;
    private ImageView bookButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.event_data_overview_fragment,container,false);
        eventData = view.findViewById(R.id.list_event);
        CustomArrayAdapter customArrayAdapter = new CustomArrayAdapter(getContext(), DummyEventDataSingelton.getInstance().getDummyDataList());

        eventData.setAdapter(customArrayAdapter);
        progressBar = view.findViewById(R.id.progress);
        progressBar.setVisibility(View.INVISIBLE);

        eventData.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Bundle bundle = new Bundle();

                bundle.putString("title", DummyEventDataSingelton.getInstance().getDummyDataList().get(i).getTitle());

                EventDetailFragment eventOverviewListFragment = new EventDetailFragment();
                eventOverviewListFragment.setArguments(bundle);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.add(R.id.main_activity, eventOverviewListFragment);
                transaction.commit();
               // Toast.makeText(getContext(), String.valueOf(i), Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

}
