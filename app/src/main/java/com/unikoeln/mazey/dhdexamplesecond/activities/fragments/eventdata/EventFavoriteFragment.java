package com.unikoeln.mazey.dhdexamplesecond.activities.fragments.eventdata;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.unikoeln.mazey.dhdexamplesecond.R;
import com.unikoeln.mazey.dhdexamplesecond.activities.utils.SharedPreference;
import com.unikoeln.mazey.dhdexamplesecond.activities.data.eventdata.EventItem;
import com.unikoeln.mazey.dhdexamplesecond.activities.utils.adapter.FavoriteEventAdapter;

import java.util.List;

public class EventFavoriteFragment extends Fragment{

    private RecyclerView favoriteView;
    private RecyclerView.LayoutManager layoutManager;

    private View view;

    private SharedPreference sharedPreference;
    private List<EventItem> favoriteList;
    private FavoriteEventAdapter favoriteEventAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.favorite_event_list_overview, container, false);

        sharedPreference = new SharedPreference();
        favoriteList = sharedPreference.getFavorites(getContext());

        favoriteView = view.findViewById(R.id.favoritenListe);
        layoutManager = new LinearLayoutManager(getContext());
        favoriteView.setLayoutManager(this.layoutManager);

        if(favoriteList.size() == 0){//
//            favoriteEventAdapter = new FavoriteEventAdapter(getContext(), favoriteList);
//            favoriteView.setAdapter(favoriteEventAdapter);

//            Snackbar addSnackbar = Snackbar.make(view, R.string.added, Snackbar.LENGTH_LONG);
//            addSnackbar.show();

        }else{
            if(favoriteList != null){
                favoriteEventAdapter = new FavoriteEventAdapter(getContext(), favoriteList);
                favoriteView.setAdapter(favoriteEventAdapter);
                favoriteEventAdapter.notifyDataSetChanged();
            }
        }

        return view;
    }

    @Override
    public void onResume(){
        super.onResume();
//        favoriteEventAdapter.notifyDataSetChanged();
    }

    //Letzte Klammer
}
