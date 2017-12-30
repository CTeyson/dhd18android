package com.unikoeln.mazey.dhdexamplesecond.activities.utils.httpcommunication;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.unikoeln.mazey.dhdexamplesecond.activities.data.eventdata.observable.EventDataObservable;
import com.unikoeln.mazey.dhdexamplesecond.activities.data.eventdata.Session;
import com.unikoeln.mazey.dhdexamplesecond.activities.data.http.HttpURL;
import com.unikoeln.mazey.dhdexamplesecond.activities.utils.text.Parser;
import com.unikoeln.mazey.dhdexamplesecond.activities.utils.text.XmlCreater;

import org.dom4j.Document;
import org.dom4j.Node;

import java.util.List;

public class NetworkComunicationVolley {

    private HttpURL httpURL;

    private final Context context;

    public NetworkComunicationVolley(Context context) {
        this.context = context;
    }

    public void loadData() {

        httpURL = new HttpURL();

        RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(new StringRequest(Request.Method.GET, httpURL.getResourceUrlC4me(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.contains("<result>false</result>")) {
                    Log.e("Error", response);
                } else {
                    // TODO Refactor
                    Log.i("Response", "Success.");
                    XmlCreater creater = new XmlCreater();
                    Parser parser = new Parser();
                    Document document = creater.createXmlDocumentFromString(response);
                    List<Node> sessionNodes = creater.getNodesOfXml(document, "//sessions/session");
                    List<Session> eventData = parser.parseDataFromConfTool(sessionNodes);
                    // TODO Implement "Singleton Object" to make sure data is available globally
                    new EventDataObservable(true);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error", error.getMessage());
            }
        }));
    }
}
