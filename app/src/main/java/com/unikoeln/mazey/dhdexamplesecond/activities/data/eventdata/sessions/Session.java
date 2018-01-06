package com.unikoeln.mazey.dhdexamplesecond.activities.data.eventdata.sessions;

import com.unikoeln.mazey.dhdexamplesecond.activities.data.eventdata.presentations.Presentation;

import java.util.List;

public class Session {

    private int sessionID;
    private String sessionShort;
    private String sessionTitle;
    private String sessionStart;
    private String sessionEnd;
    private String sessionRoomID;
    private String sessionRoom;
    private String sessionRoomInfo;
    private String sessionAbstract;
    private int numberOfPresentations;
    private List<Presentation> sessionPresentations;

    public Session(
            int sessionID,
            String sessionShort,
            String sessionTitle,
            String sessionStart,
            String sessionEnd,
            String sessionRoomID,
            String sessionRoom,
            String sessionRoomInfo,
            String sessionAbstract,
            int numberOfPresentations,
            List<Presentation> sessionPresentations) {
        this.sessionID = sessionID;
        this.sessionShort = sessionShort;
        this.sessionTitle = sessionTitle;
        this.sessionStart = sessionStart;
        this.sessionEnd = sessionEnd;
        this.sessionRoomID = sessionRoomID;
        this.sessionRoom = sessionRoom;
        this.sessionRoomInfo = sessionRoomInfo;
        this.sessionAbstract = sessionAbstract;
        this.numberOfPresentations = numberOfPresentations;
        this.sessionPresentations = sessionPresentations;
    }

    public int getSessionID() {
        return sessionID;
    }

    public void setSessionID(int sessionID) {
        this.sessionID = sessionID;
    }

    public String getSessionShort() {
        return sessionShort;
    }

    public void setSessionShort(String sessionShort) {
        this.sessionShort = sessionShort;
    }

    public String getSessionTitle() {
        return sessionTitle;
    }

    public void setSessionTitle(String sessionTitle) {
        this.sessionTitle = sessionTitle;
    }

    public String getSessionStart() {
        return sessionStart;
    }

    public void setSessionStart(String sessionStart) {
        this.sessionStart = sessionStart;
    }

    public String getSessionEnd() {
        return sessionEnd;
    }

    public void setSessionEnd(String sessionEnd) {
        this.sessionEnd = sessionEnd;
    }

    public String getSessionRoomID() {
        return sessionRoomID;
    }

    public void setSessionRoomID(String sessionRoomID) {
        this.sessionRoomID = sessionRoomID;
    }

    public String getSessionRoom() {
        return sessionRoom;
    }

    public void setSessionRoom(String sessionRoom) {
        this.sessionRoom = sessionRoom;
    }

    public String getSessionRoomInfo() {
        return sessionRoomInfo;
    }

    public void setSessionRoomInfo(String sessionRoomInfo) {
        this.sessionRoomInfo = sessionRoomInfo;
    }

    public String getSessionAbstract() {
        return sessionAbstract;
    }

    public void setSessionAbstract(String sessionAbstract) {
        this.sessionAbstract = sessionAbstract;
    }

    public int getNumberOfPresentations() {
        return numberOfPresentations;
    }

    public void setNumberOfPresentations(int numberOfPresentations) {
        this.numberOfPresentations = numberOfPresentations;
    }

    public List<Presentation> getSessionPresentations() {
        return sessionPresentations;
    }

    public void setSessionPresentations(List<Presentation> sessionPresentations) {
        this.sessionPresentations = sessionPresentations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Session session = (Session) o;

        if (sessionID != session.sessionID) return false;
        if (numberOfPresentations != session.numberOfPresentations) return false;
        if (sessionShort != null ? !sessionShort.equals(session.sessionShort) : session.sessionShort != null)
            return false;
        if (sessionTitle != null ? !sessionTitle.equals(session.sessionTitle) : session.sessionTitle != null)
            return false;
        if (sessionStart != null ? !sessionStart.equals(session.sessionStart) : session.sessionStart != null)
            return false;
        if (sessionEnd != null ? !sessionEnd.equals(session.sessionEnd) : session.sessionEnd != null)
            return false;
        if (sessionRoomID != null ? !sessionRoomID.equals(session.sessionRoomID) : session.sessionRoomID != null)
            return false;
        if (sessionRoom != null ? !sessionRoom.equals(session.sessionRoom) : session.sessionRoom != null)
            return false;
        if (sessionRoomInfo != null ? !sessionRoomInfo.equals(session.sessionRoomInfo) : session.sessionRoomInfo != null)
            return false;
        if (sessionAbstract != null ? !sessionAbstract.equals(session.sessionAbstract) : session.sessionAbstract != null)
            return false;
        return sessionPresentations != null ? sessionPresentations.equals(session.sessionPresentations) : session.sessionPresentations == null;
    }

    @Override
    public int hashCode() {
        int result = sessionID;
        result = 31 * result + (sessionShort != null ? sessionShort.hashCode() : 0);
        result = 31 * result + (sessionTitle != null ? sessionTitle.hashCode() : 0);
        result = 31 * result + (sessionStart != null ? sessionStart.hashCode() : 0);
        result = 31 * result + (sessionEnd != null ? sessionEnd.hashCode() : 0);
        result = 31 * result + (sessionRoomID != null ? sessionRoomID.hashCode() : 0);
        result = 31 * result + (sessionRoom != null ? sessionRoom.hashCode() : 0);
        result = 31 * result + (sessionRoomInfo != null ? sessionRoomInfo.hashCode() : 0);
        result = 31 * result + (sessionAbstract != null ? sessionAbstract.hashCode() : 0);
        result = 31 * result + numberOfPresentations;
        result = 31 * result + (sessionPresentations != null ? sessionPresentations.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Session{" +
                "sessionID=" + sessionID +
                ", sessionShort='" + sessionShort + '\'' +
                ", sessionTitle='" + sessionTitle + '\'' +
                ", sessionStart='" + sessionStart + '\'' +
                ", sessionEnd='" + sessionEnd + '\'' +
                ", sessionRoomID='" + sessionRoomID + '\'' +
                ", sessionRoom='" + sessionRoom + '\'' +
                ", sessionRoomInfo='" + sessionRoomInfo + '\'' +
                ", sessionAbstract='" + sessionAbstract + '\'' +
                ", numberOfPresentations=" + numberOfPresentations +
                ", sessionPresentations=" + sessionPresentations +
                '}';
    }
}

