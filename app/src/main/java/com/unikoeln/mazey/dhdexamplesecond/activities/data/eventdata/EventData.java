package com.unikoeln.mazey.dhdexamplesecond.activities.data.eventdata;

import java.util.Date;

public class EventData {

    // TODO Change eventTime to Date.Util

    private String eventTitle;
    private String eventTime;
    private String eventAuthor;
    private String eventLocation;
    private String eventAbstract;

    public EventData(String eventTitle,
                     String eventTime,
                     String eventAuthor,
                     String eventLocation,
                     String eventAbstract) {
        this.eventTitle = eventTitle;
        this.eventTime = eventTime;
        this.eventAuthor = eventAuthor;
        this.eventLocation = eventLocation;
        this.eventAbstract = eventAbstract;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public String getEventTime() {
        return eventTime;
    }

    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }

    public String getEventAuthor() {
        return eventAuthor;
    }

    public void setEventAuthor(String eventAuthor) {
        this.eventAuthor = eventAuthor;
    }

    public String getEventLocation() {
        return eventLocation;
    }

    public void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation;
    }

    public String getEventAbstract() {
        return eventAbstract;
    }

    public void setEventAbstract(String eventAbstract) {
        this.eventAbstract = eventAbstract;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EventData eventData = (EventData) o;

        if (eventTitle != null ? !eventTitle.equals(eventData.eventTitle) : eventData.eventTitle != null)
            return false;
        if (eventTime != null ? !eventTime.equals(eventData.eventTime) : eventData.eventTime != null)
            return false;
        if (eventAuthor != null ? !eventAuthor.equals(eventData.eventAuthor) : eventData.eventAuthor != null)
            return false;
        if (eventLocation != null ? !eventLocation.equals(eventData.eventLocation) : eventData.eventLocation != null)
            return false;
        return eventAbstract != null ? eventAbstract.equals(eventData.eventAbstract) : eventData.eventAbstract == null;
    }

    @Override
    public int hashCode() {
        int result = eventTitle != null ? eventTitle.hashCode() : 0;
        result = 31 * result + (eventTime != null ? eventTime.hashCode() : 0);
        result = 31 * result + (eventAuthor != null ? eventAuthor.hashCode() : 0);
        result = 31 * result + (eventLocation != null ? eventLocation.hashCode() : 0);
        result = 31 * result + (eventAbstract != null ? eventAbstract.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "EventData{" +
                "eventTitle='" + eventTitle + '\'' +
                ", eventTime='" + eventTime + '\'' +
                ", eventAuthor='" + eventAuthor + '\'' +
                ", eventLocation='" + eventLocation + '\'' +
                ", eventAbstract='" + eventAbstract + '\'' +
                '}';
    }
}
