package com.unikoeln.mazey.dhdexamplesecond.activities.asynctask;

import android.os.AsyncTask;

import com.unikoeln.mazey.dhdexamplesecond.activities.data.eventdata.Session;
import com.unikoeln.mazey.dhdexamplesecond.activities.data.http.HttpURL;
import com.unikoeln.mazey.dhdexamplesecond.activities.utils.httpcommunication.ConfToolConnection;
import com.unikoeln.mazey.dhdexamplesecond.activities.utils.text.Parser;
import com.unikoeln.mazey.dhdexamplesecond.activities.utils.text.XmlCreater;

import org.dom4j.Document;
import org.dom4j.Node;

import java.util.List;

public class ConfToolCommunication extends AsyncTask<Void, Void, List<Session>> {

    private HttpURL httpURL;
    private ConfToolConnection confToolConnection;
    private XmlCreater creater;
    private Parser parser;

    @Override
    protected List<Session> doInBackground(Void... voids) {

        httpURL = new HttpURL();
        confToolConnection = new ConfToolConnection();
        creater = new XmlCreater();
        parser = new Parser();

        String c4meUrl = httpURL.getResourceUrlC4me();
        String resource = confToolConnection.getResource(c4meUrl);
        Document document = creater.createXmlDocumentFromString(resource);
        List<Node> nodes = creater.getNodesOfXml(document, "//sessions/session");

        return parser.parseDataFromConfTool(nodes);
    }
}
