package com.unikoeln.mazey.dhdexamplesecond.activities.fragments.eventdata;

import android.app.Fragment;
import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.unikoeln.mazey.dhdexamplesecond.R;
import com.unikoeln.mazey.dhdexamplesecond.activities.data.EventItem;
import com.unikoeln.mazey.dhdexamplesecond.activities.utils.adapter.CustomArrayAdapter;
import com.unikoeln.mazey.dhdexamplesecond.activities.utils.text.Parser;
import com.unikoeln.mazey.dhdexamplesecond.activities.utils.text.XmlCreater;

import org.apache.commons.io.IOUtils;
import org.dom4j.Document;
import org.dom4j.Node;

import java.io.IOException;
import java.util.List;

public class EventOverviewListFragment extends Fragment {

    private View view;
    private ListView eventData;

    private ProgressBar progressBar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.event_data_overview_fragment, container, false);
        eventData = view.findViewById(R.id.list_event);
        progressBar = view.findViewById(R.id.progress);


        InitTask initTask = new InitTask();
        initTask.execute();

        return view;
    }

    private class InitTask extends AsyncTask<Void, Void, CustomArrayAdapter> {

        @Override
        protected CustomArrayAdapter doInBackground(Void... voids) {

            List<EventItem> events = this.initList(this.getAsstes());
            CustomArrayAdapter customArrayAdapter = new CustomArrayAdapter(getContext(), events);

            return customArrayAdapter;
        }

        @Override
        protected void onPostExecute(CustomArrayAdapter customArrayAdapter) {
            super.onPostExecute(customArrayAdapter);
            progressBar.setVisibility(View.INVISIBLE);
            eventData.setAdapter(customArrayAdapter);
        }

        private String getAsstes() {
            String content = null;
            try {
                AssetManager assetManager = getActivity().getAssets();
                content = IOUtils.toString(assetManager.open("DHd_Tagung2018_sessions_2017-12-19_14-36-02.xml"), "UTF-8");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return content;
        }

        private List<EventItem> initList(String content) {
            List<EventItem> events = null;
            Parser parser = new Parser();
            XmlCreater xml = new XmlCreater();
            Document document = xml.createXmlDocumentFromString(content);
            List<Node> nodes = xml.getNodesOfXml(document, "//sessions/session");
            events = parser.createEventItemListFromLocalFile(nodes);
            return events;
        }

    }

}
