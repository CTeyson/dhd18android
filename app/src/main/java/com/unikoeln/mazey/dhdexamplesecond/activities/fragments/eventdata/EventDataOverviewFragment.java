package com.unikoeln.mazey.dhdexamplesecond.activities.fragments.eventdata;

import android.app.Fragment;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.unikoeln.mazey.dhdexamplesecond.R;
import com.unikoeln.mazey.dhdexamplesecond.activities.data.eventdata.sessions.Session;
import com.unikoeln.mazey.dhdexamplesecond.activities.data.http.HttpURL;
import com.unikoeln.mazey.dhdexamplesecond.activities.utils.text.Parser;
import com.unikoeln.mazey.dhdexamplesecond.activities.utils.text.XmlCreater;

import org.dom4j.Document;
import org.dom4j.Node;

import java.util.List;

// TODO Hard coded strings into xml
// TODO Make sure a valid internet connection is available

public class EventDataOverviewFragment extends Fragment {

    private View view;
    private ProgressBar bar;
    private ListView listView;

    private Context context;

    private List<Session> sessions;

    private ConfToolCommunicationAsyncTask confToolCommunicationAsyncTask;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.event_data_overview_fragment, container, false);
        bar = view.findViewById(R.id.progress);
        listView = view.findViewById(R.id.list_event);

//        List<EventData> eventData = DummyEventDataDummy.getInstance().getEventData();
//
//        ArrayAdapter adapter = new EventListArrayAdapter(getContext(), R.layout.event_list_item, eventData);
//        listView.setAdapter(adapter);

//        context = view.getContext();
//
//        if (this.isNetworkAvailable()) {
//            confToolCommunicationAsyncTask = new ConfToolCommunicationAsyncTask();
//            confToolCommunicationAsyncTask.execute();
//        } else {
//            this.makeSureThereIsValidConnectionSnack();
//        }

        return view;
    }

    private void updateUI() {
        if (sessions == null) {
            bar.setVisibility(View.GONE);
            Snackbar.make(view, "Was not able to collect data.", Snackbar.LENGTH_SHORT).show();
        } else {
            bar.setVisibility(View.GONE);
            Snackbar.make(view, String.valueOf(sessions.size()), Snackbar.LENGTH_SHORT).show();
        }
    }

    private void makeSureThereIsValidConnectionSnack() {
        Snackbar.make(view, "Make sure you have a valid connection established.", Snackbar.LENGTH_SHORT).show();
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    class ConfToolCommunicationAsyncTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            Log.i("Server Communication", "Start request");

            HttpURL url = new HttpURL();


            RequestQueue queue = Volley.newRequestQueue(getContext());
            queue.add(new StringRequest(Request.Method.GET, url.getResourceUrlC4me(), new Response.Listener<String>() {

                Parser parser = new Parser();
                XmlCreater creater = new XmlCreater();

                @Override
                public void onResponse(String response) {
                    if (response.contains("<result>false</result>")) {
                        Log.e("Check Server Response", response);
                        updateUI();
                    } else {
                        Log.i("Check Server Response", "Success.");
                        //createSessionList(response);
                        Document document = creater.createXmlDocumentFromString(response);
                        List<Node> nodes = creater.getNodesOfXml(document, "//sessions/session");
                        sessions = parser.parseDataFromConfTool(nodes);
                        updateUI();

                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("Check Server Response", error.getMessage());
                    Snackbar.make(view, "Was not able to collect data.", Snackbar.LENGTH_SHORT).show();
                }
            }));
            return null;
        }

        private void createSessionList(String serverResponse) {


        }

    }
}
