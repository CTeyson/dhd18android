package com.unikoeln.mazey.dhdexamplesecond.activities.utils.adapter;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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

    private String marked;
    private String removed;

    public FavoriteEventAdapter(Context context, List<EventItem> boomarkedData) {
        this.context = context;
        this.boomarkedData = boomarkedData;
        Collections.sort(boomarkedData);
        sharedPreference = new SharedPreference();
        marked = context.getString(R.string.marked);
        removed = context.getString(R.string.removed);
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

        /*ermöglicht löschen und neu: direktes erneutes hinzufügen, elegantes umgehen des fragmentaktualsiertproblems*/
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkFavoriteItem(boomarkedData.get(position))){

                sharedPreference.removeFavorite(context, boomarkedData.get(position));
                holder.delete.setImageResource(R.drawable.add_26);
                notifyDataSetChanged();

                    Snackbar deleteSnackbar = Snackbar.make(view, removed, Snackbar.LENGTH_LONG);
                    TextView snackView = (TextView) deleteSnackbar.getView().findViewById(android.support.design.R.id.snackbar_text);
                    deleteSnackbar.show();

                }else{

                sharedPreference.addFavorite(context, boomarkedData.get(position));
                holder.delete.setImageResource(R.drawable.trash_24px);
                notifyDataSetChanged();

                    Snackbar markedSnackbar = Snackbar.make(view, marked, Snackbar.LENGTH_LONG);
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

    @Override
    public int getItemCount() {
        return boomarkedData.size();
    }

    //letzte Klammer
}
