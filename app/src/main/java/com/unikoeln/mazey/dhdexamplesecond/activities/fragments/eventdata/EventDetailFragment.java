package com.unikoeln.mazey.dhdexamplesecond.activities.fragments.eventdata;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.unikoeln.mazey.dhdexamplesecond.R;

public class EventDetailFragment extends Fragment {

    TextView textView;

    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.event_detail_fragment, container, false);


        String strAbstractText = getArguments().getString("Abstract");
        String strTitleText = getArguments().getString("Title");
        String strAuthorText = getArguments().getString("Author");
        String strLocationText = getArguments().getString("Location");
        String strTimeText = getArguments().getString("Time");
        String strDateText = getArguments().getString("Date");


        TextView abstractText = (TextView) view.findViewById(R.id.event_abstract);
        TextView titleText = (TextView) view.findViewById(R.id.event_title);
        TextView authorText = (TextView) view.findViewById(R.id.event_author);
        TextView locationText = (TextView) view.findViewById(R.id.event_location);
        TextView timeText = (TextView) view.findViewById(R.id.event_time);
        TextView dateText = (TextView) view.findViewById(R.id.event_separator);

        abstractText.setText(strAbstractText);
        titleText.setText(strTitleText);
        authorText.setText(strAuthorText);
        locationText.setText(strLocationText);
        timeText.setText(strTimeText);
        dateText.setText(strDateText);

/*      Muss noch zweisprachig gestaltet werden
        if (strDateText.contains("26 Feb")) {
            dateText.setText("Montag, 26.02.2018");
        }
        else if (strDateText.contains("27 Feb")) {
            dateText.setText("Dienstag, 27.02.2018");
        }
        else if (strDateText.contains("28  Feb")) {
            dateText.setText("Mittwoch, 28.02.2018");
        }
        else if (strDateText.contains("01 Mar")) {
            dateText.setText("Donnerstag, 01.03.2018");
        }
        else if (strDateText.contains("02 Mar")) {
            dateText.setText("Freitag, 02.03.2018");
        }*/

        ImageView myImg = (ImageView) view.findViewById(R.id.event_share);
        myImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareIt();
            }
        });

        return view;
    }


    private void shareIt() {
        String strDateText = getArguments().getString("Date");
        String strTimeText = getArguments().getString("Time");
        String strTitleText = getArguments().getString("Title");
        //String strLocationText = getArguments().getString("Location");

        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        StringBuilder sb = new StringBuilder();
        sb.append("DHd 2018: ");
        sb.append(strDateText);
        sb.append(", ");
        sb.append(strTimeText);
        sb.append(": ");
        sb.append(strTitleText);

        sendIntent.putExtra(Intent.EXTRA_TEXT, sb.toString());

        sendIntent.setType("text/plain");
        startActivity(sendIntent);
    }

}
