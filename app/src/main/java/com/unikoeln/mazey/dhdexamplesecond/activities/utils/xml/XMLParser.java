package com.unikoeln.mazey.dhdexamplesecond.activities.utils.xml;

import com.unikoeln.mazey.dhdexamplesecond.activities.data.eventdata.EventItem;
import com.unikoeln.mazey.dhdexamplesecond.activities.data.eventdata.presentations.Presentation;

import org.dom4j.Element;
import org.dom4j.Node;
import org.jsoup.Jsoup;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * XML Parser for locally availble file that can be found on the device.
 */

public class XMLParser {

    public List<EventItem> createEventItemListFromLocalFile(List<Node> nodes) {

        List<EventItem> events = new ArrayList<EventItem>();

        for (int i = 0; i < nodes.size(); i++) {
            Element element = (Element) nodes.get(i);

            String eventStart = element.elementText("session_start");
            String eventEnd = element.elementText("session_end");
            int counter;

            if (element.elementText("presentations") == null || element.elementText("presentations") == "") {
                counter = 0;
            } else {
                counter = Integer.valueOf(element.elementText("presentations"));
            }

            events = getPresentationsFromLocalFile(events, element, eventStart, eventEnd, counter);
        }
        return events;
    }

    private List<EventItem> getPresentationsFromLocalFile(List<EventItem> events, Element element, String eventStart, String eventEnd, int counter) {
        if (counter != 0) {
            int countVariable = 1;
            for (int j = 0; j < 68; j++) {
                String pX = "p" + String.valueOf(countVariable);

                if (element.elementText(pX + "_paperID") != "") {
                    String eventTitle = element.elementText(pX + "_title");
                    // String eventAuthor = element.elementText(pX + "_presenting_author");
                    String eventAuthor = element.elementText(pX + "_authors").replaceAll("[0-9\\(\\);]", "");
                    String eventLocation = "Ort nicht bekannt";
                    String eventAbstract = element.elementText(pX + "_abstract");

                    eventAbstract = Jsoup.parse(eventAbstract).text();

                    Date start = this.getDate(eventStart);
                    Date end = this.getDate(eventEnd);

                    // events.add(new EventItem(eventTitle, eventAuthor, eventLocation, eventAbstract, start, end));
                }

                countVariable = countVariable + 1;
            }
        }
        return events;
    }

    private Date getDate(String eventStart) {
        Date tmp = null;
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
            tmp = dateFormat.parse(eventStart);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return tmp;
    }

    public List<EventItem> parseDataFromConfToolC4me(List<Node> elements) {

        List<EventItem> eventItems = new ArrayList<EventItem>();
        List<Presentation> presentationsOfSession = null;

        for (int i = 0; i < elements.size(); i++) {
            Element element = (Element) elements.get(i);

            String sessionID = element.elementText("session_ID");
            String sessionShort = element.elementText("session_short");
            String sessionTitle = element.elementText("session_title");
            String sessionStart = element.elementText("session_start");
            String sessionEnd = element.elementText("session_end");
            String sessionRoomID = element.elementText("session_room_ID");
            String sessionRoom = element.elementText("session_room");
            String sessionRoomInfo = element.elementText("session_room_info");

            String sessionAbstract = null;
            if (element.elementText("presentations") == null) {
                sessionAbstract = "";
            } else {
                sessionAbstract = element.elementText("session_abstract");
            }

            int sessionPresentations;
            if (element.elementText("presentations") == null) {
                sessionPresentations = 0;
            } else {
                sessionPresentations = Integer.valueOf(element.elementText("presentations"));
            }

            if (sessionPresentations != 0) {
                presentationsOfSession = this.getPresentations(element, sessionPresentations);
            }

            String parsedSessionAbstract = null;
            if (sessionAbstract != null) {
                parsedSessionAbstract = Jsoup.parse(sessionAbstract).text();
            }

            if (presentationsOfSession == null) {
                EventItem eventItem = new EventItem(
                        Integer.valueOf(sessionID),
                        sessionShort,
                        sessionTitle,
                        sessionRoomID,
                        sessionRoom,
                        sessionRoomInfo,
                        parsedSessionAbstract,
                        this.getDate(sessionStart),
                        this.getDate(sessionEnd));
                if (!eventItems.contains(eventItem)) eventItems.add(eventItem);
            } else {
                for (Presentation presentation : presentationsOfSession) {
                    EventItem eventItem = new EventItem(
                            Integer.valueOf(sessionID),
                            sessionShort,
                            sessionTitle,
                            sessionRoomID,
                            sessionRoom,
                            sessionRoomInfo,
                            parsedSessionAbstract,
                            presentation.getPresentationContributionType(),
                            presentation.getPresentationTitle(),
                            presentation.getPresentationAuthors().replaceAll("[0-9\\(\\);]", ""),
                            Jsoup.parse(presentation.getPresentationAbstract()).text(),
                            this.getDate(sessionStart),
                            this.getDate(sessionEnd));
                    if (!eventItems.contains(eventItem)) eventItems.add(eventItem);
                }
                presentationsOfSession = null;
            }
        }
        return eventItems;
    }

    private List<Presentation> getPresentations(Element element, int sessionPresentations) {
        List<Presentation> tmpList = new ArrayList<Presentation>();
        int counter = 1;
        for (int j = 0; j < sessionPresentations; j++) {

            String pX = "p" + String.valueOf(counter);
            String paperId = element.elementText(pX + "_paperID");
            String paperContributionType = element.elementText(pX + "_contribution_type");
            String paperAuthors = element.elementText(pX + "_authors");
            String paperOrganizations = element.elementText(pX + "_organisations");
            String paperPresentingAuthor = element.elementText(pX + "_presenting_author");
            String paperTitle = element.elementText(pX + "_title");
            String paperAbstract = element.elementText(pX + "_abstract");

            Presentation presentation = new Presentation(Integer.valueOf(paperId), paperContributionType, paperAuthors, paperOrganizations, paperPresentingAuthor, paperTitle, paperAbstract);

            tmpList.add(presentation);
            counter = counter++;
        }
        return tmpList;
    }

}
