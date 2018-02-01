package com.unikoeln.mazey.dhdexamplesecond.activities.utils.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.design.widget.Snackbar;

import com.unikoeln.mazey.dhdexamplesecond.R;
import com.unikoeln.mazey.dhdexamplesecond.activities.data.eventdata.EventItem;
import com.unikoeln.mazey.dhdexamplesecond.activities.utils.SharedPreference;

import java.util.Collections;
import java.util.List;

public class FavoriteEventAdapter extends RecyclerView.Adapter<FavoriteEventAdapter.ViewHolder> {

    private List<EventItem> boomarkedData;
    private LayoutInflater layoutInflater;
    private Context context;
    SharedPreference sharedPreference;

    public FavoriteEventAdapter(Context context, List<EventItem> boomarkedData) {
        this.context = context;
        this.boomarkedData = boomarkedData;
        Collections.sort(boomarkedData);
        sharedPreference = new SharedPreference();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        View view;

        TextView title;
        TextView author;
        TextView location;
        ImageView delete;
        TextView selector;

        public ViewHolder(View view) {
            super(view);

            this.view = view;

            title = (TextView) view.findViewById(R.id.favorite_title);
            author = (TextView) view.findViewById(R.id.favorite_author);
            location = (TextView) view.findViewById(R.id.favorite_location);
            selector = (TextView) view.findViewById(R.id.separator);
            delete = (ImageView) view.findViewById(R.id.delete);
        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.favorite_event_items, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position){

        final String date = boomarkedData.get(position).getStartTime().toString();
        final String fittingDate = date.substring(8, 10) + " " + date.substring(4, 7) + " " + date.substring(30, 34);

        holder.title.setText(boomarkedData.get(position).getTitle());
        holder.author.setText(boomarkedData.get(position).getAuthor());
        holder.location.setText(boomarkedData.get(position).getLocation());
        holder.selector.setText(fittingDate);

        /*ermöglicht löschen*/
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPreference.removeFavorite(context, boomarkedData.get(position));
                notifyDataSetChanged();

                Snackbar deleteSnackbar = Snackbar.make(view, R.string.deleted, Snackbar.LENGTH_LONG);
                deleteSnackbar.show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return boomarkedData.size();
    }

    //letzte Klammer
}
