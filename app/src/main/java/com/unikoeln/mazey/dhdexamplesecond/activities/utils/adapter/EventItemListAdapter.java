package com.unikoeln.mazey.dhdexamplesecond.activities.utils.adapter;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
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

import java.util.Date;
import java.util.List;

public class EventItemListAdapter extends RecyclerView.Adapter<EventItemListAdapter.ViewHolder> {

    private List<EventItem> events;
    private Context context;
    private SharedPreference sharedPreference;

    private String adding;
    private String delete;

    public EventItemListAdapter(List<EventItem> events, Context context) {
        this.events = events;
        this.context = context;
        sharedPreference = new SharedPreference();
        adding = context.getString(R.string.marked);
        delete = context.getString(R.string.removed);
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
        ImageView shareButton;

        public ViewHolder(View view) {
            super(view);

            this.view = view;

            selector = view.findViewById(R.id.event_separator);
            titleView = view.findViewById(R.id.presentationTitle);
            authorView = view.findViewById(R.id.presentationAuthor);
            descriptionView = view.findViewById(R.id.presentationDescription);
            locationView = view.findViewById(R.id.location);
            reportedDateView = view.findViewById(R.id.time);
            imageView = view.findViewById(R.id.bookmark);
            imageView.setTag(R.id.bookmark);
            shareButton = view.findViewById(R.id.share);
            shareButton.setTag(R.id.share);

        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_event_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        final String date = events.get(position).getPresentationStartTime().toString();
        final String formatted = date.substring(8, 10) + " " + date.substring(4, 7) + " " + date.substring(30, 34);

        //holder.selector.setText(formatted);
        holder.titleView.setText(events.get(position).getPresentationTitle() == null ? events.get(position).getSessionTitle() : events.get(position).getPresentationTitle());
        holder.authorView.setText(events.get(position).getPresentationAuthor() == null ? "" : events.get(position).getPresentationAuthor().replaceAll(";", ""));
        if (holder.descriptionView.length() >= 300) {
            if (!(events.get(position).getPresentationDescription() == null) && !events.get(position).getPresentationDescription().equalsIgnoreCase("")) {
                holder.descriptionView.setText(events.get(position).getSessionAbstract());
            } else if (!(events.get(position).getSessionAbstract() == null) && !events.get(position).getSessionAbstract().equalsIgnoreCase("")) {
                holder.descriptionView.setText(events.get(position).getPresentationDescription());
            } else {
                // Conventional way of getting a string from strings.xml does not work
                    // String placeholder = context.getResources().getString(R.string.placeholder);
                    // holder.descriptionView.setText(placeholder);
                // Therefore hard coded text in code
                holder.descriptionView.setText("                                                                                                                                              ");
            }
        } else {
            holder.descriptionView.setText(events.get(position).getPresentationDescription());
        }
        holder.locationView.setText(events.get(position).getSessionRoom());
        holder.reportedDateView.setText(getTime(position));

        // Format des Datums verschönern
        if (formatted.contains("26 Feb")) {
            holder.selector.setText(context.getString(R.string.monday));
        }
        else if (formatted.contains("27 Feb")) {
            holder.selector.setText(context.getString(R.string.tuesday));
        }
        else if (formatted.contains("28 Feb")) {
            holder.selector.setText(context.getString(R.string.wednesday));
        }
        else if (formatted.contains("01 Mar")) {
            holder.selector.setText(context.getString(R.string.thursday));
        }
        else if (formatted.contains("02 Mar")) {
            holder.selector.setText(context.getString(R.string.friday));
        }

        holder.descriptionView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventDetailFragment eventDetailFragment = new EventDetailFragment();

                Bundle event = new Bundle();
                if (events.get(position).getPresentationTitle() != null) {
                    event.putString("Title", events.get(position).getPresentationTitle());
                } else if (events.get(position).getSessionTitle() != null) {
                    event.putString("Title", events.get(position).getSessionTitle());
                }
                event.putString("Abstract", events.get(position).getPresentationDescription());
                event.putString("Author", events.get(position).getPresentationAuthor() == null ? "" : events.get(position).getPresentationAuthor().replaceAll(";", ""));
                event.putString("Location", events.get(position).getSessionRoom());
                event.putString("Time", getTime(position));
                event.putString("Date", formatted);
                event.putString("Type", events.get(position).getPresentationContributionType());
                eventDetailFragment.setArguments(event);

                FragmentTransaction transaction = ((Activity) context).getFragmentManager().beginTransaction();
                transaction.addToBackStack(null);

                transaction.replace(R.id.main_activity, eventDetailFragment);
                transaction.commit();
            }
        });

        holder.shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strDateText = formatted;
                String strTimeText = getTime(position);
                String strTitleText = "Title";
                if (events.get(position).getPresentationTitle() != null) {
                    strTitleText = events.get(position).getPresentationTitle();
                } else if (events.get(position).getSessionTitle() != null) {
                    strTitleText = events.get(position).getSessionTitle();
                }
                String strLocationText = events.get(position).getSessionRoom();
                String shareVia = context.getString(R.string.share_via);

                if (strDateText.contains("26 Feb")) {
                    strDateText = context.getString(R.string.monday_short);
                } else if (strDateText.contains("27 Feb")) {
                    strDateText = context.getString(R.string.tuesday_short);
                } else if (strDateText.contains("28 Feb")) {
                    strDateText = context.getString(R.string.wednesday_short);
                } else if (strDateText.contains("01 Mar")) {
                    strDateText = context.getString(R.string.thursday_short);
                } else if (strDateText.contains("02 Mar")) {
                    strDateText = context.getString(R.string.friday_short);
                }

                String shareOutput = ("DHd 2018: " + strDateText + ", " + strTimeText + ": " + strTitleText + " in " + strLocationText);

                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, shareOutput);
                sendIntent.setType("text/plain");
                context.startActivity(Intent.createChooser(sendIntent, shareVia));
            }
        });


        //set tag
        if (

                checkFavoriteItem(events.get(position)))

        {
            holder.imageView.setImageResource(R.drawable.ic_bookmark_black_24dp_copy_3);
            holder.imageView.setTag("checked");
        } else

        {
            holder.imageView.setImageResource(R.drawable.ic_bookmark_border_black_24dp_copy_3);
            holder.imageView.setTag("removed");
        }

        holder.imageView.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View view) {

                String tag = holder.imageView.getTag().toString();

                if (tag.equalsIgnoreCase("removed")) {

                    //adding to list
                    sharedPreference.addFavorite(context, events.get(position));
                    holder.imageView.setTag("checked");
                    holder.imageView.setImageResource(R.drawable.ic_bookmark_black_24dp_copy_3);
                    notifyDataSetChanged();

                    //snackbar
                    Snackbar addSnackbar = Snackbar.make(view, adding, Snackbar.LENGTH_LONG);
                    View viewSnack = addSnackbar.getView();
                    TextView textView = (TextView) viewSnack.findViewById(android.support.design.R.id.snackbar_text);
                    addSnackbar.show();

                } else {

                    //remove and change tag
                    sharedPreference.removeFavorite(context, events.get(position));
                    holder.imageView.setTag("removed");
                    holder.imageView.setImageResource(R.drawable.ic_bookmark_border_black_24dp_copy_3);
                    notifyDataSetChanged();

                    //snackbar
                    Snackbar deleteSnackbar = Snackbar.make(view, delete, Snackbar.LENGTH_LONG);
                    View viewSnack = deleteSnackbar.getView();
                    TextView textView = (TextView) viewSnack.findViewById(android.support.design.R.id.snackbar_text);
                    deleteSnackbar.show();
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
        return events.size();
    }

    private String getTime(int position) {

        Date start = events.get(position).getPresentationStartTime();
        Date end = events.get(position).getPresentationEndTime();

        return String.format("%1s%2s%3s", start.toString().substring(11, 16), " - ", end.toString().substring(11, 16));
    }


}
