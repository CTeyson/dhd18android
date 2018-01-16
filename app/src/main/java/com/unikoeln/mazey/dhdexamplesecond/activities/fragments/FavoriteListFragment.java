package com.unikoeln.mazey.dhdexamplesecond.activities.fragments;

import java.util.List;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

import com.unikoeln.mazey.dhdexamplesecond.R;
import com.unikoeln.mazey.dhdexamplesecond.activities.data.EventItem;
import com.unikoeln.mazey.dhdexamplesecond.activities.utils.SharedPreference;
import com.unikoeln.mazey.dhdexamplesecond.activities.utils.adapter.FavoriteArrayAdapter;


public class FavoriteListFragment extends Fragment{

    private ListView favoriteView;
    private View view;
    private ImageView remove;

    SharedPreference sharedPreference;

    List<EventItem> favoriteList;

    FavoriteArrayAdapter favoriteArrayAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.work_in_progress_layout, container, false);

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
                favoriteArrayAdapter = new FavoriteArrayAdapter(getContext(), favoriteList);
                favoriteArrayAdapter.notifyDataSetChanged();
                favoriteView.setAdapter(favoriteArrayAdapter);


                //remove = (ImageView) view.findViewById(R.id.delete);

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
        favoriteArrayAdapter.notifyDataSetChanged();
    }

    //letzte Klammer
}
