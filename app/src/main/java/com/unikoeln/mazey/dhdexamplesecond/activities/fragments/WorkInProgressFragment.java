//Markierte Veranstaltungen

package com.unikoeln.mazey.dhdexamplesecond.activities.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.unikoeln.mazey.dhdexamplesecond.R;
import com.unikoeln.mazey.dhdexamplesecond.activities.data.Favorites;
import com.unikoeln.mazey.dhdexamplesecond.activities.utils.adapter.FavoriteArrayAdapter;

public class WorkInProgressFragment extends Fragment {

    private View view;
    private TextView textView;
    private ListView favoritenListe;

    private TextView titel;
    private TextView autor;
    private TextView ort;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.work_in_progress_layout, container, false);

        favoritenListe = view.findViewById(R.id.favoritenListe);
        final FavoriteArrayAdapter favoriteArrayAdapter = new FavoriteArrayAdapter(getContext(), Favorites.getInstance().getMarkierteListe());
        favoritenListe.setAdapter(favoriteArrayAdapter);
        favoriteArrayAdapter.notifyDataSetChanged();

        //textView = view.findViewById(R.id.titel);
        //this.showSnack();

        return view;
    }

    private void showSnack() {
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Work In Progress!", Snackbar.LENGTH_SHORT).show();
            }
        });
    }
}
