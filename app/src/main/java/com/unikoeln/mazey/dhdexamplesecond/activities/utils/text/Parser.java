package com.unikoeln.mazey.dhdexamplesecond.activities.utils.text;

import com.unikoeln.mazey.dhdexamplesecond.activities.data.eventdata.presentations.Presentation;
import com.unikoeln.mazey.dhdexamplesecond.activities.data.eventdata.sessions.Session;

import org.dom4j.Element;
import org.dom4j.Node;

import java.util.ArrayList;
import java.util.List;

public class Parser {

    public List<Session> parseDataFromConfTool(List<Node> elements) {

        Session session = null;
        List<Session> sessions = new ArrayList<Session>();
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


            session = new Session(
                    Integer.valueOf(sessionID),
                    sessionShort,
                    sessionTitle,
                    sessionStart,
                    sessionEnd,
                    sessionRoomID,
                    sessionRoom,
                    sessionRoomInfo,
                    sessionAbstract,
                    sessionPresentations,
                    presentationsOfSession);

            sessions.add(session);
        }
        return sessions;
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
            counter = counter ++;
        }
        return tmpList;
    }

}
