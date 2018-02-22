package com.unikoeln.mazey.dhdexamplesecond.activities.utils.eventlist;

import com.unikoeln.mazey.dhdexamplesecond.activities.data.eventdata.EventItem;
import com.unikoeln.mazey.dhdexamplesecond.activities.utils.xml.XMLCreater;
import com.unikoeln.mazey.dhdexamplesecond.activities.utils.xml.XMLParser;

import org.dom4j.Document;
import org.dom4j.Node;

import java.util.List;

public class EventListUtil {

    public List<EventItem> initList(String content) {
        List<EventItem> events;
        XMLParser parser = new XMLParser();
        XMLCreater xml = new XMLCreater();
        Document document = xml.createXmlDocumentFromString(content);
        List<Node> nodes = xml.getNodesOfXml(document, "//sessions/session");
        //events = parser.parseDataFromConfToolC4me(nodes);
        return parser.parseDataFromConfToolC4me(nodes);
    }

}
