// die neue custom array adapter version - zeigt events einzeln an

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
import android.widget.Toast;

import com.unikoeln.mazey.dhdexamplesecond.R;
import com.unikoeln.mazey.dhdexamplesecond.activities.data.eventdata.EventItem;
import com.unikoeln.mazey.dhdexamplesecond.activities.fragments.eventdata.EventDetailFragment;
import com.unikoeln.mazey.dhdexamplesecond.activities.utils.SharedPreference;

import java.util.Date;
import java.util.List;

public class EventItemListAdapter extends RecyclerView.Adapter<EventItemListAdapter.ViewHolder> {

    private List<EventItem> events;
    private Context context;

    SharedPreference sharedPreference;

    public EventItemListAdapter(List<EventItem> events, Context context) {
        this.events = events;
        this.context = context;
        sharedPreference = new SharedPreference();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        View view;

        TextView selector;
        TextView titleView;
        TextView descriptionView;
        TextView authorView;
        TextView reportedDateView;
        TextView locationView;
        ImageView imageView;

        public ViewHolder(View view) {
            super(view);

            this.view = view;

            selector = view.findViewById(R.id.event_separator);
            titleView =  view.findViewById(R.id.title);
            authorView =  view.findViewById(R.id.author);
            descriptionView =  view.findViewById(R.id.description);
            locationView =  view.findViewById(R.id.location);
            reportedDateView =  view.findViewById(R.id.time);
            imageView = view.findViewById(R.id.bookmark);
            //imageView.setTag(R.id.bookmark);

        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_event_item, parent, false);
        return new ViewHolder(view);
    }

    //holder musste wegen Vergleich auf final gesetzt werden
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        final String date = events.get(position).getStartTime().toString();

        final String formatted = date.substring(8, 10) + " " + date.substring(4, 7) + " " + date.substring(30, 34);

        holder.selector.setText(formatted);
        holder.titleView.setText(events.get(position).getTitle());
        holder.authorView.setText(events.get(position).getAuthor().replaceAll(";", ""));
        holder.descriptionView.setText(events.get(position).getDescription());
        holder.locationView.setText(events.get(position).getLocation() + ", ");
        holder.reportedDateView.setText(getTime(position));

        holder.descriptionView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventDetailFragment eventDetailFragment = new EventDetailFragment();

                Bundle event = new Bundle();
                event.putString("Title", events.get(position).getTitle());
                event.putString("Abstract", events.get(position).getDescription());
                event.putString("Author", events.get(position).getAuthor().replaceAll(";", ""));
                event.putString("Location", events.get(position).getLocation());
                event.putString("Time", getTime(position));
                event.putString("Date", formatted);
                eventDetailFragment.setArguments(event);

                FragmentTransaction transaction = ((Activity) context).getFragmentManager().beginTransaction();
                transaction.addToBackStack(null);

                transaction.replace(R.id.main_activity, eventDetailFragment);
                transaction.commit();
            }
        });

        //set tag
        if (checkFavoriteItem(events.get(position))) {
            holder.imageView.setImageResource(R.drawable.ic_bookmark_black_24dp_copy_3);
            holder.imageView.setTag("checked");
        } else {
            holder.imageView.setImageResource(R.drawable.ic_bookmark_border_black_24dp_copy_3);
            holder.imageView.setTag("removed");
        }

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tag = holder.imageView.getTag().toString();

                if(tag.equalsIgnoreCase("removed")){
                    sharedPreference.addFavorite(context, events.get(position));
                    holder.imageView.setTag("checked");
                    holder.imageView.setImageResource(R.drawable.ic_bookmark_black_24dp_copy_3);
                    notifyDataSetChanged();

                    Snackbar addSnackbar = Snackbar.make(view, R.string.deleted, Snackbar.LENGTH_LONG);
                    addSnackbar.show();

                }else{

                    sharedPreference.removeFavorite(context, events.get(position));
                    holder.imageView.setTag("removed");
                    holder.imageView.setImageResource(R.drawable.ic_bookmark_border_black_24dp_copy_3);
                    notifyDataSetChanged();

                    Snackbar deleteSnackbar = Snackbar.make(view, R.string.added, Snackbar.LENGTH_LONG);
                    deleteSnackbar.show();
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    private String getTime(int position) {

        Date start = events.get(position).getStartTime();
        Date end = events.get(position).getEndTime();

        return String.format("%1s%2s%3s", start.toString().substring(11, 16), " - ", end.toString().substring(11, 16));
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


}
