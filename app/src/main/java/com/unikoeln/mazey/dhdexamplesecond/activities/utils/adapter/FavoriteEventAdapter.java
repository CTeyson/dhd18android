package com.unikoeln.mazey.dhdexamplesecond.activities.utils.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.unikoeln.mazey.dhdexamplesecond.R;
import com.unikoeln.mazey.dhdexamplesecond.activities.data.eventdata.EventItem;
import com.unikoeln.mazey.dhdexamplesecond.activities.utils.SharedPreference;

import java.util.Collections;
import java.util.List;

public class FavoriteEventAdapter extends BaseAdapter{

    private List<EventItem> boomarkedData;
    private LayoutInflater layoutInflater;
    private Context context;

    SharedPreference sharedPreference = new SharedPreference();

    static class ViewHolder {
        TextView titel;
        TextView autor;
        TextView ort;
        ImageView delete;
    }

    public FavoriteEventAdapter(Context context, List<EventItem> boomarkedData) {
        this.context = context;
        this.boomarkedData = boomarkedData;
        Collections.sort(boomarkedData);
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

            holder.titel = (TextView) convertView.findViewById(R.id.titel);
            holder.autor = (TextView) convertView.findViewById(R.id.autor);
            holder.ort = (TextView) convertView.findViewById(R.id.ort);

            convertView.setTag(holder);

        } else {
            holder = (FavoriteEventAdapter.ViewHolder) convertView.getTag();
        }

        holder.titel.setText(boomarkedData.get(position).getTitle());
        holder.autor.setText(boomarkedData.get(position).getAuthor());
        holder.ort.setText(boomarkedData.get(position).getLocation());

        /*ermöglicht löschen*/
        holder.delete = (ImageView) convertView.findViewById(R.id.delete);

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPreference.removeFavorite(context, boomarkedData.get(position));
                notifyDataSetChanged();
                System.out.println("Entfert wurde: " + boomarkedData.get(position));
                Toast.makeText(context, "Wurde erfolgreich gelöscht!", Toast.LENGTH_SHORT).show();
            }
        });

        notifyDataSetChanged();
        return convertView;
    }

    //letzte Klammer
}
