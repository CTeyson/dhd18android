package com.unikoeln.mazey.dhdexamplesecond.activities.utils.adapter;

import android.content.Context;
import android.util.EventLog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.unikoeln.mazey.dhdexamplesecond.R;
import com.unikoeln.mazey.dhdexamplesecond.activities.data.EventItem;
import com.unikoeln.mazey.dhdexamplesecond.activities.utils.SharedPreference;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;


public class CustomArrayAdapter extends ArrayAdapter<EventItem>{

    private List<EventItem> listData;

    private LayoutInflater layoutInflater;

    private Context context;

    SharedPreference sharedPreference;

    static class ViewHolder {
        TextView titleView;
        TextView descriptionView;
        TextView authorView;
        TextView reportedDateView;
        TextView locationView;
        ImageView favorite;
    }

    public CustomArrayAdapter(Context context, List<EventItem> listData) {
        super(context, R.layout.list_event_item, listData);
        this.context = context;
        this.listData = listData;
        Collections.sort(listData);
        sharedPreference = new SharedPreference();
    }

    @Override
    public int getCount() {
        return listData.size();
    }

//    @Override
//    public Object getItem(int position) {
//        return listData.get(position);
//    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_event_item, null);
            holder = new ViewHolder();

            holder.titleView = (TextView) convertView.findViewById(R.id.title);
            holder.authorView = (TextView) convertView.findViewById(R.id.author);
            holder.descriptionView = (TextView) convertView.findViewById(R.id.description);
            holder.locationView = (TextView) convertView.findViewById(R.id.location);
            holder.reportedDateView = (TextView) convertView.findViewById(R.id.time);

            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }
            holder.titleView.setText(listData.get(position).getTitle());
            holder.authorView.setText(listData.get(position).getAuthor());
            holder.descriptionView.setText(listData.get(position).getDescription());
            holder.locationView.setText(listData.get(position).getLocation());
            holder.reportedDateView.setText(getTime(position));

        /*ermöglicht bookmark*/
        holder.favorite = (ImageView) convertView.findViewById(R.id.bookmark);

        holder.favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listData.get(position).isSelected=!listData.get(position).isSelected;
                notifyDataSetChanged();

                if(listData.get(position).isSelected){

                    //if(checkFavoriteItem(listData.get(position))){

                    sharedPreference.addFavorite(context, listData.get(position));
                    System.out.println("Wurde hinzugetan: " + listData.get(position));
                    notifyDataSetChanged();


                    //addingFlist(listData.get(position), fList);
                    //System.out.println(listData.get(position));
                    //Favorites.getInstance().adding(listData.get(position));
                  //  notifyDataSetChanged();
                }else{
                    //sharedPreference.removeFavorite(context, listData.get(position));
                    System.out.println("Dann nicht " + listData.get(position));
                }
            }
        });

        if(listData.get(position).isSelected){
            holder.favorite.setSelected(true);
        }else{
            holder.favorite.setSelected(false);
        }
     /**/

        return convertView;
    }

    private String getTime(int position) {

        Date start = listData.get(position).getStartTime();
        Date end = listData.get(position).getEndTime();
        String startTime = start.toString();
        String endTime = end.toString();

        return String.format("%1s%2s%3s", start.toString().substring(11, 19), " - ", end.toString().substring(11, 19));
    }

    //SharedPreferences
    public boolean checkFavoriteItem(EventItem checkItem){
        boolean check = false;
        List<EventItem> favorites = sharedPreference.getFavorites(context);
        System.out.print("+++++++++++++");
        if(favorites != null){
            for (EventItem eventItem : favorites){
                if(eventItem.equals(checkItem)){
                    check = true;
                    break;
                }
            }
        }

        return check;
    }

    @Override
    public void add(EventItem eventItem){
        super.add(eventItem);
        listData.add(eventItem);
        notifyDataSetChanged();
    }

    @Override
    public void remove(EventItem eventItem){
        super.remove(eventItem);
        listData.remove(eventItem);
        notifyDataSetChanged();
    }

    //letzte Klammer

}
