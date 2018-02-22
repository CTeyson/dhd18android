package com.unikoeln.mazey.dhdexamplesecond.activities.data.eventdata;

import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class EventItem implements Comparable<EventItem>, Serializable {

    private int sessionID;
    private String sessionShort;
    private String sessionTitle;
    private String sessionRoomID;
    private String sessionRoom;
    private String sessionRoomInfo;
    private String sessionAbstract;
    private String presentationContributionType;
    private String presentationTitle;
    private String presentationAuthor;
    private String presentationDescription;
    private Date presentationStartTime;
    private Date presentationEndTime;

    public EventItem(int sessionID, String sessionShort, String sessionTitle, String sessionRoomID, String sessionRoom, String sessionRoomInfo, String sessionAbstract, Date startTime, Date presentationEndTime) {
        this.sessionID = sessionID;
        this.sessionShort = sessionShort;
        this.sessionTitle = sessionTitle;
        this.sessionRoomID = sessionRoomID;
        this.sessionRoom = sessionRoom;
        this.sessionRoomInfo = sessionRoomInfo;
        this.sessionAbstract = sessionAbstract;
        this.presentationStartTime = startTime;
        this.presentationEndTime = presentationEndTime;
    }

    public EventItem(int sessionID, String sessionShort, String sessionTitle, String sessionRoomID, String sessionRoom, String sessionRoomInfo, String sessionAbstract, String presentationContributionType, String presentationTitle, String presentationAuthor, String presentationDescription, Date presentationStartTime, Date presentationEndTime) {
        this.sessionID = sessionID;
        this.sessionShort = sessionShort;
        this.sessionTitle = sessionTitle;
        this.sessionRoomID = sessionRoomID;
        this.sessionRoom = sessionRoom;
        this.sessionRoomInfo = sessionRoomInfo;
        this.sessionAbstract = sessionAbstract;
        this.presentationContributionType = presentationContributionType;
        this.presentationTitle = presentationTitle;
        this.presentationAuthor = presentationAuthor;
        this.presentationDescription = presentationDescription;
        this.presentationStartTime = presentationStartTime;
        this.presentationEndTime = presentationEndTime;
    }

    //    public EventItem(String presentationTitle, String presentationAuthor, String location, String description, Date startTime, Date presentationEndTime) {
//        this.presentationTitle = presentationTitle;
//        this.presentationAuthor = presentationAuthor;
//        this.location = location;
//        this.presentationDescription = description;
//        this.presentationStartTime = startTime;
//        this.presentationEndTime = presentationEndTime;
//    }

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

    public String getPresentationContributionType() {
        return presentationContributionType;
    }

    public void setPresentationContributionType(String presentationContributionType) {
        this.presentationContributionType = presentationContributionType;
    }

    public String getPresentationTitle() {
        return presentationTitle;
    }

    public void setPresentationTitle(String presentationTitle) {
        this.presentationTitle = presentationTitle;
    }

    public String getPresentationAuthor() {
        return presentationAuthor;
    }

    public void setPresentationAuthor(String presentationAuthor) {
        this.presentationAuthor = presentationAuthor;
    }

    public String getPresentationDescription() {
        return presentationDescription;
    }

    public void setPresentationDescription(String presentationDescription) {
        this.presentationDescription = presentationDescription;
    }

    public Date getPresentationStartTime() {
        return presentationStartTime;
    }

    public void setPresentationStartTime(Date presentationStartTime) {
        this.presentationStartTime = presentationStartTime;
    }

    public Date getPresentationEndTime() {
        return presentationEndTime;
    }

    public void setPresentationEndTime(Date presentationEndTime) {
        this.presentationEndTime = presentationEndTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EventItem eventItem = (EventItem) o;

        if (sessionID != eventItem.sessionID) return false;
        if (sessionShort != null ? !sessionShort.equals(eventItem.sessionShort) : eventItem.sessionShort != null)
            return false;
        if (sessionTitle != null ? !sessionTitle.equals(eventItem.sessionTitle) : eventItem.sessionTitle != null)
            return false;
        if (sessionRoomID != null ? !sessionRoomID.equals(eventItem.sessionRoomID) : eventItem.sessionRoomID != null)
            return false;
        if (sessionRoom != null ? !sessionRoom.equals(eventItem.sessionRoom) : eventItem.sessionRoom != null)
            return false;
        if (sessionRoomInfo != null ? !sessionRoomInfo.equals(eventItem.sessionRoomInfo) : eventItem.sessionRoomInfo != null)
            return false;
        if (sessionAbstract != null ? !sessionAbstract.equals(eventItem.sessionAbstract) : eventItem.sessionAbstract != null)
            return false;
        if (presentationTitle != null ? !presentationTitle.equals(eventItem.presentationTitle) : eventItem.presentationTitle != null)
            return false;
        if (presentationAuthor != null ? !presentationAuthor.equals(eventItem.presentationAuthor) : eventItem.presentationAuthor != null)
            return false;
        if (presentationDescription != null ? !presentationDescription.equals(eventItem.presentationDescription) : eventItem.presentationDescription != null)
            return false;
        if (presentationStartTime != null ? !presentationStartTime.equals(eventItem.presentationStartTime) : eventItem.presentationStartTime != null)
            return false;
        return presentationEndTime != null ? presentationEndTime.equals(eventItem.presentationEndTime) : eventItem.presentationEndTime == null;
    }

    @Override
    public int hashCode() {
        int result = sessionID;
        result = 31 * result + (sessionShort != null ? sessionShort.hashCode() : 0);
        result = 31 * result + (sessionTitle != null ? sessionTitle.hashCode() : 0);
        result = 31 * result + (sessionRoomID != null ? sessionRoomID.hashCode() : 0);
        result = 31 * result + (sessionRoom != null ? sessionRoom.hashCode() : 0);
        result = 31 * result + (sessionRoomInfo != null ? sessionRoomInfo.hashCode() : 0);
        result = 31 * result + (sessionAbstract != null ? sessionAbstract.hashCode() : 0);
        result = 31 * result + (presentationContributionType != null ? presentationContributionType.hashCode() : 0);
        result = 31 * result + (presentationTitle != null ? presentationTitle.hashCode() : 0);
        result = 31 * result + (presentationAuthor != null ? presentationAuthor.hashCode() : 0);
        result = 31 * result + (presentationDescription != null ? presentationDescription.hashCode() : 0);
        result = 31 * result + (presentationStartTime != null ? presentationStartTime.hashCode() : 0);
        result = 31 * result + (presentationEndTime != null ? presentationEndTime.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "EventItem{" +
                "sessionID=" + sessionID +
                ", sessionShort='" + sessionShort + '\'' +
                ", sessionTitle='" + sessionTitle + '\'' +
                ", sessionRoomID='" + sessionRoomID + '\'' +
                ", sessionRoom='" + sessionRoom + '\'' +
                ", sessionRoomInfo='" + sessionRoomInfo + '\'' +
                ", sessionAbstract='" + sessionAbstract + '\'' +
                ", presentationContributionType='" + presentationContributionType + '\'' +
                ", presentationTitle='" + presentationTitle + '\'' +
                ", presentationAuthor='" + presentationAuthor + '\'' +
                ", presentationDescription='" + presentationDescription + '\'' +
                ", presentationStartTime=" + presentationStartTime +
                ", presentationEndTime=" + presentationEndTime +
                '}';
    }

    @Override
    public int compareTo(@NonNull EventItem eventItem) {
        return getPresentationStartTime().compareTo(eventItem.getPresentationStartTime());
    }
}
