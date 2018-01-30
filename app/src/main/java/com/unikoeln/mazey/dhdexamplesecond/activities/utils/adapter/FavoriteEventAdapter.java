package com.unikoeln.mazey.dhdexamplesecond.activities.utils.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.design.widget.Snackbar;

import com.unikoeln.mazey.dhdexamplesecond.R;
import com.unikoeln.mazey.dhdexamplesecond.activities.data.eventdata.EventItem;
import com.unikoeln.mazey.dhdexamplesecond.activities.utils.SharedPreference;

import java.util.Collections;
import java.util.List;

public class FavoriteEventAdapter extends BaseAdapter{

    private List<EventItem> boomarkedData;
    private LayoutInflater layoutInflater;
    private Context context;
    SharedPreference sharedPreference;

    static class ViewHolder {
        TextView title;
        TextView author;
        TextView location;
        ImageView delete;
        TextView selector;
    }

    public FavoriteEventAdapter(Context context, List<EventItem> boomarkedData) {
        this.context = context;
        this.boomarkedData = boomarkedData;
        Collections.sort(boomarkedData);
        sharedPreference = new SharedPreference();
    }

    @Override
    public int getCount() {
        return boomarkedData.size();
    }

    @Override
    public Object getItem(int position) {
        return boomarkedData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        final FavoriteEventAdapter.ViewHolder holder;

        if (convertView == null) {
            layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.favorite_event_items, null);
            holder = new FavoriteEventAdapter.ViewHolder();

            holder.title = (TextView) convertView.findViewById(R.id.favorite_title);
            holder.author = (TextView) convertView.findViewById(R.id.favorite_author);
            holder.location = (TextView) convertView.findViewById(R.id.favorite_location);
            holder.selector = (TextView) convertView.findViewById(R.id.separator);

            convertView.setTag(holder);

        } else {
            holder = (FavoriteEventAdapter.ViewHolder) convertView.getTag();
        }

        holder.title.setText(boomarkedData.get(position).getTitle());
        holder.author.setText(boomarkedData.get(position).getAuthor());
        holder.location.setText(boomarkedData.get(position).getLocation());

        final String date = boomarkedData.get(position).getStartTime().toString();
        final String angepasst = date.substring(8, 10) + " " + date.substring(4, 7) + " " + date.substring(30, 34);

        holder.selector.setText(angepasst);

        /*ermöglicht löschen*/
        holder.delete = (ImageView) convertView.findViewById(R.id.delete);

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPreference.removeFavorite(context, boomarkedData.get(position));
                notifyDataSetChanged();

                Snackbar deleteSnackbar = Snackbar.make(view, R.string.deleted, Snackbar.LENGTH_LONG);
                deleteSnackbar.show();

                }
        });

        notifyDataSetChanged();
        return convertView;
    }

    //letzte Klammer
}
