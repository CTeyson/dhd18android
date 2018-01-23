package com.unikoeln.mazey.dhdexamplesecond.activities.fragments.eventdata;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.unikoeln.mazey.dhdexamplesecond.R;
import com.unikoeln.mazey.dhdexamplesecond.activities.utils.SharedPreference;
import com.unikoeln.mazey.dhdexamplesecond.activities.data.eventdata.EventItem;
import com.unikoeln.mazey.dhdexamplesecond.activities.utils.adapter.FavoriteEventAdapter;

import java.util.List;

public class EventFavoriteFragment extends Fragment{

    private ListView favoriteView;
    private View view;
    private ImageView remove;

    SharedPreference sharedPreference;

    List<EventItem> favoriteList;

    FavoriteEventAdapter favoriteEventAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.favorite_event_list_overview, container, false);

        sharedPreference = new SharedPreference();
        favoriteList = sharedPreference.getFavorites(getContext());

        if(favoriteList == null){
            showAlert("Willkommen zu deinen Favoriten", "Du kannst hier deine Lieblingsveranstaltungen festhalten");
        }else{
            if(favoriteList.size() == 0){
                showAlert("Willkommen zu deinen Favoriten", "Du kannst hier deine Lieblingsveranstaltungen festhalten");
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

    public void showAlert(String title, String message) {
        if (getActivity() != null && !getActivity().isFinishing()) {
            AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
                    .create();
            alertDialog.setTitle(title);
            alertDialog.setMessage(message);
            alertDialog.setCancelable(false);

            // setting OK Button
            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK",
                    new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            // activity.finish();
                            getFragmentManager().popBackStackImmediate();
                        }
                    });
            alertDialog.show();
        }
    }

    @Override
    public void onResume(){
        super.onResume();
        favoriteEventAdapter.notifyDataSetChanged();
    }

    //Letzte Klammer
}
