package com.unikoeln.mazey.dhdexamplesecond.activities.utils.adapter;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.unikoeln.mazey.dhdexamplesecond.R;
import com.unikoeln.mazey.dhdexamplesecond.activities.data.EventItem;
import com.unikoeln.mazey.dhdexamplesecond.activities.fragments.WorkInProgressFragment;

import java.util.Collections;
import java.util.Date;
import java.util.List;

public class CustomArrayAdapter extends BaseAdapter {

    private List<EventItem> listData;

    private LayoutInflater layoutInflater;

    private Context context;

    private boolean isBookmarked;

    static class ViewHolder {
        TextView selelctor;
        TextView titleView;
        TextView descriptionView;
        TextView authorView;
        TextView reportedDateView;
        TextView locationView;
        ImageView imageView;
    }

    public CustomArrayAdapter(Context context, List<EventItem> listData) {
        this.context = context;
        this.listData = listData;
        Collections.sort(listData);
    }


    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_event_item, null);
            holder = new ViewHolder();

            holder.selelctor = convertView.findViewById(R.id.event_separator);
            holder.titleView = (TextView) convertView.findViewById(R.id.title);
            holder.authorView = (TextView) convertView.findViewById(R.id.author);
            holder.descriptionView = (TextView) convertView.findViewById(R.id.description);
            holder.locationView = (TextView) convertView.findViewById(R.id.location);
            holder.reportedDateView = (TextView) convertView.findViewById(R.id.time);
            holder.imageView = convertView.findViewById(R.id.bookmark);
            holder.imageView.setTag(R.id.bookmark);
            isBookmarked = false;

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        String date = listData.get(position).getStartTime().toString();

        String formatted = date.substring(8, 10) + " " + date.substring(4, 7) + " " + date.substring(30, 34);

        holder.selelctor.setText(formatted);
        holder.titleView.setText(listData.get(position).getTitle());
        holder.authorView.setText(listData.get(position).getAuthor().replaceAll(";", ""));
        holder.descriptionView.setText(listData.get(position).getDescription());
        holder.locationView.setText(listData.get(position).getLocation() + ", ");
        holder.reportedDateView.setText(getTime(position));

        holder.descriptionView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WorkInProgressFragment eventOverviewListFragment = new WorkInProgressFragment();
                FragmentTransaction transaction = ((Activity) context).getFragmentManager().beginTransaction();
                transaction.addToBackStack(null);
                transaction.add(R.id.main_activity, eventOverviewListFragment);
                transaction.commit();
            }
        });


        // TODO Refactor
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isBookmarked) {
                    holder.imageView.setImageResource(R.drawable.ic_bookmark_border_black_24dp_copy_3);
                } else {
                    holder.imageView.setImageResource(R.drawable.ic_bookmark_black_24dp_copy_3);
                }
                isBookmarked = !isBookmarked;
            }
        });

        return convertView;
    }

    private String getTime(int position) {

        Date start = listData.get(position).getStartTime();
        Date end = listData.get(position).getEndTime();

        return String.format("%1s%2s%3s", start.toString().substring(11, 16), " - ", end.toString().substring(11, 16));
    }


}
