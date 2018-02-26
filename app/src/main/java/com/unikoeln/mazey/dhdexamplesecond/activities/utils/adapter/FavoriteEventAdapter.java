package com.unikoeln.mazey.dhdexamplesecond.activities.utils.adapter;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.unikoeln.mazey.dhdexamplesecond.R;
import com.unikoeln.mazey.dhdexamplesecond.activities.data.eventdata.EventItem;
import com.unikoeln.mazey.dhdexamplesecond.activities.fragments.eventdata.EventDetailFragment;
import com.unikoeln.mazey.dhdexamplesecond.activities.utils.SharedPreference;

import java.util.Collections;
import java.util.Date;
import java.util.List;

public class FavoriteEventAdapter extends RecyclerView.Adapter<FavoriteEventAdapter.ViewHolder> {

    private List<EventItem> boomarkedData;
    private LayoutInflater layoutInflater;
    private Context context;
    private SharedPreference sharedPreference;

    public FavoriteEventAdapter(Context context, List<EventItem> boomarkedData) {
        this.context = context;
        this.boomarkedData = boomarkedData;
        Collections.sort(boomarkedData);
        sharedPreference = new SharedPreference();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        View view;

        TextView title;
        TextView author;
        TextView location;
        //
        TextView time;
        ImageView delete;
        TextView selector;

        public ViewHolder(View view) {
            super(view);

            this.view = view;

            title = (TextView) view.findViewById(R.id.favorite_title);
            author = (TextView) view.findViewById(R.id.favorite_author);
            location = (TextView) view.findViewById(R.id.favorite_location);
            //
            time = (TextView) view.findViewById(R.id.favorite_time);
            selector = (TextView) view.findViewById(R.id.separator);
            delete = (ImageView) view.findViewById(R.id.delete);
        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.favorite_event_items, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        final String date = boomarkedData.get(position).getPresentationStartTime().toString();
        final String fittingDate = date.substring(8, 10) + " " + date.substring(4, 7) + " " + date.substring(30, 34);

        holder.title.setText(boomarkedData.get(position).getPresentationTitle());
        holder.author.setText(boomarkedData.get(position).getPresentationAuthor());
        holder.location.setText(boomarkedData.get(position).getSessionRoom());
        //
        holder.time.setText(getTime(position));
        holder.selector.setText(fittingDate);

        //einfügen der Detailseite
        holder.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventDetailFragment eventDetailFragment = new EventDetailFragment();

                Bundle event = new Bundle();
                if (boomarkedData.get(position).getPresentationTitle() != null) {
                    event.putString("Title", boomarkedData.get(position).getPresentationTitle());
                } else if (boomarkedData.get(position).getSessionTitle() != null) {
                    event.putString("Title", boomarkedData.get(position).getSessionTitle());
                }
                //event.putString("Title", boomarkedData.get(position).getPresentationTitle() == "" ? boomarkedData.get(position).getSessionTitle() : boomarkedData.get(position).getPresentationTitle());
                event.putString("Abstract", boomarkedData.get(position).getPresentationDescription());
                event.putString("Author", boomarkedData.get(position).getPresentationAuthor() == null ? "" : boomarkedData.get(position).getPresentationAuthor().replaceAll(";", ""));
                event.putString("Location", boomarkedData.get(position).getSessionRoom());
                event.putString("Time", getTime(position));
                event.putString("Date", fittingDate);
                event.putString("Type", boomarkedData.get(position).getPresentationContributionType());
                eventDetailFragment.setArguments(event);

                FragmentTransaction transaction = ((Activity) context).getFragmentManager().beginTransaction();
                transaction.addToBackStack(null);

                transaction.replace(R.id.main_activity, eventDetailFragment);
                transaction.commit();
            }
        });

        /*ermöglicht löschen und neu: direktes erneutes hinzufügen, elegantes umgehen des fragmentaktualsiertproblems*/
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkFavoriteItem(boomarkedData.get(position))) {

                    sharedPreference.removeFavorite(context, boomarkedData.get(position));
                    holder.delete.setImageResource(R.drawable.add_26);
                    notifyDataSetChanged();

                    Snackbar deleteSnackbar = Snackbar.make(view, R.string.removed, Snackbar.LENGTH_LONG);
                    TextView snackView = (TextView) deleteSnackbar.getView().findViewById(android.support.design.R.id.snackbar_text);
                    deleteSnackbar.show();

                } else {

                    sharedPreference.addFavorite(context, boomarkedData.get(position));
                    holder.delete.setImageResource(R.drawable.trash_24px);
                    notifyDataSetChanged();

                    Snackbar markedSnackbar = Snackbar.make(view, R.string.marked, Snackbar.LENGTH_LONG);
                    TextView snackView = (TextView) markedSnackbar.getView().findViewById(android.support.design.R.id.snackbar_text);
                    markedSnackbar.show();

                }
            }
        });
    }

    public boolean checkFavoriteItem(EventItem checkEvent) {
        boolean check = false;
        List<EventItem> favorites = sharedPreference.getFavorites(context);
        if (favorites != null) {
            for (EventItem item : favorites) {
                if (item.equals(checkEvent)) {
                    check = true;
                    break;
                }
            }
        }

        return check;
    }

    //Zeit hinzugefügt
    private String getTime(int position) {

        Date start = boomarkedData.get(position).getPresentationStartTime();
        Date end = boomarkedData.get(position).getPresentationEndTime();

        return String.format("%1s%2s%3s", start.toString().substring(11, 16), " - ", end.toString().substring(11, 16));
    }

    @Override
    public int getItemCount() {
        return boomarkedData.size();
    }

    //letzte Klammer
}