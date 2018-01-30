package com.unikoeln.mazey.dhdexamplesecond.activities.fragments.eventdata;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.unikoeln.mazey.dhdexamplesecond.R;
import com.unikoeln.mazey.dhdexamplesecond.activities.utils.SharedPreference;
import com.unikoeln.mazey.dhdexamplesecond.activities.data.eventdata.EventItem;
import com.unikoeln.mazey.dhdexamplesecond.activities.utils.adapter.FavoriteEventAdapter;

import java.util.List;

public class EventFavoriteFragment extends Fragment{

    private ListView favoriteView;
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

        if(favoriteList == null){
            Snackbar welcomeSnackbar = Snackbar.make(view, R.string.welcome_screen_title, Snackbar.LENGTH_LONG);
            welcomeSnackbar.show();
        }else{
            if(favoriteList.size() == 0){
                Snackbar messageSnackbar = Snackbar.make(view, R.string.welcome_screen_message, Snackbar.LENGTH_LONG);
                messageSnackbar.show();
            }

            favoriteView = (ListView) view.findViewById(R.id.favoritenListe);

            if(favoriteList != null){
                favoriteEventAdapter = new FavoriteEventAdapter(getContext(), favoriteList);
                favoriteEventAdapter.notifyDataSetChanged();
                favoriteView.setAdapter(favoriteEventAdapter);
            }
        }

        return view;
    }

    @Override
    public void onResume(){
        super.onResume();
        favoriteEventAdapter.notifyDataSetChanged();
    }

    //Letzte Klammer
}
