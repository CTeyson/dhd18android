package com.unikoeln.mazey.dhdexamplesecond.activities.fragments.eventdata;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.unikoeln.mazey.dhdexamplesecond.R;

import java.util.Calendar;

public class EventDetailFragment extends Fragment {

    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.event_detail_fragment, container, false);

        // Strings aus EventOverviewList holen
        String strAbstractText = getArguments().getString("Abstract");
        String strTitleText = getArguments().getString("Title");
        String strAuthorText = getArguments().getString("Author");
        String strLocationText = getArguments().getString("Location");
        String strTimeText = getArguments().getString("Time");
        String strDateText = getArguments().getString("Date");
        String strTypeText = getArguments().getString("Type");

        // Views definieren
        TextView abstractText = view.findViewById(R.id.event_abstract);
        TextView titleText = view.findViewById(R.id.event_title);
        TextView authorText = view.findViewById(R.id.event_author);
        TextView locationText = view.findViewById(R.id.event_location);
        TextView timeText = view.findViewById(R.id.event_time);
        TextView dateText = view.findViewById(R.id.event_separator);
        TextView typeText = view.findViewById(R.id.event_type);

        ImageView shareImg = view.findViewById(R.id.event_share);
        ImageView calImg = view.findViewById(R.id.event_calendar);

        // Strings den TextViews zuweisen
        abstractText.setText(strAbstractText);
        titleText.setText(strTitleText);
        authorText.setText(strAuthorText);
        locationText.setText(strLocationText);
        timeText.setText(strTimeText);
        dateText.setText(strDateText);
        typeText.setText(strTypeText);

        // Format des Datums verschönern
        if (strDateText.contains("26 Feb")) {
            dateText.setText(getString(R.string.monday));
        }
        else if (strDateText.contains("27 Feb")) {
            dateText.setText(getString(R.string.tuesday));
        }
        else if (strDateText.contains("28 Feb")) {
            dateText.setText(getString(R.string.wednesday));
        }
        else if (strDateText.contains("01 Mar")) {
            dateText.setText(getString(R.string.thursday));
        }
        else if (strDateText.contains("02 Mar")) {
            dateText.setText(getString(R.string.friday));
        }

        // contribution_type in der XML
        // wegen Schreibfehler im Tag des Datenexports
        if (strTypeText != null && strTypeText.equalsIgnoreCase("Worshop")) {
            typeText.setText("Workshop");
        }
        // Anpassung für englischsprachige Version, bei anderen Types nicht nötig
        if (strTypeText != null && strTypeText.equalsIgnoreCase("Vortrag")) {
            typeText.setText(getString(R.string.talk));
        }

        shareImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareEvent();
            }
        });
        calImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToCalendar();
            }
        });

        return view;
    }

    // Share-Button: Zu teilenden Text erstellen und per Intent mit externer App teilen
    private void shareEvent() {
        String strDateText = getArguments().getString("Date");
        String strTimeText = getArguments().getString("Time");
        String strTitleText = getArguments().getString("Title");
        String strLocationText = getArguments().getString("Location");
        String shareVia = getString(R.string.share_via);

        // für kommende Jahre anzupassen
        if (strDateText.contains("26 Feb")) {
            strDateText = getString(R.string.monday_short);
        }
        else if (strDateText.contains("27 Feb")) {
            strDateText = getString(R.string.tuesday_short);
        }
        else if (strDateText.contains("28 Feb")) {
            strDateText = getString(R.string.wednesday_short);
        }
        else if (strDateText.contains("01 Mar")) {
            strDateText = getString(R.string.thursday_short);
        }
        else if (strDateText.contains("02 Mar")) {
            strDateText = getString(R.string.friday_short);
        }

        String shareOutput = ("DHd 2018: " + strDateText + ", " + strTimeText + ": " + strTitleText + " in " + strLocationText);

        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, shareOutput);
        sendIntent.setType("text/plain");
        startActivity(Intent.createChooser(sendIntent, shareVia));
    }

    // Kalendar-Button: Daten zum Event sammeln und per Intent in Kalendar-App übergeben
    // für kommende Jahre anzupassen
    private void addToCalendar() {
        String strTitleText = getArguments().getString("Title");
        String strDateText = getArguments().getString("Date");
        String strTimeText = getArguments().getString("Time");
        String strLocationText = getArguments().getString("Location");

        int year = 2018;
        int month = 0;
        int day = 0;
        int hStart = Integer.parseInt(strTimeText.substring(0,2));
        int mStart = Integer.parseInt(strTimeText.substring(3,5));
        int hEnd = Integer.parseInt(strTimeText.substring(8,10));
        int mEnd = Integer.parseInt(strTimeText.substring(11,13));

        StringBuilder sb = new StringBuilder();
        sb.append("DHd 2018: ");
        sb.append(strTitleText);

        if (strDateText.contains("26 Feb")) {
            month = 01;
            day = 26;
        }
        else if (strDateText.contains("27 Feb")) {
            month = 01;
            day = 27;
        }
        else if (strDateText.contains("28 Feb")) {
            month = 01;
            day = 28;
        }
        else if (strDateText.contains("01 Mar")) {
            month = 02;
            day = 01;
        }
        else if (strDateText.contains("02 Mar")) {
            month = 02;
            day = 02;
        }

        Calendar beginTime = Calendar.getInstance();
        beginTime.set(year, month, day, hStart, mStart);
        Calendar endTime = Calendar.getInstance();
        endTime.set(year, month, day, hEnd, mEnd);
        Intent intent = new Intent(Intent.ACTION_INSERT)
                .setData(CalendarContract.Events.CONTENT_URI)
                .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, beginTime.getTimeInMillis())
                .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endTime.getTimeInMillis())
                .putExtra(CalendarContract.Events.TITLE, sb.toString())
                .putExtra(CalendarContract.Events.EVENT_LOCATION, strLocationText);

        startActivity(intent);
    }

}