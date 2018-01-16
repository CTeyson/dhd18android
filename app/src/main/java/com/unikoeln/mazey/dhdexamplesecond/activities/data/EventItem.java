package com.unikoeln.mazey.dhdexamplesecond.activities.data;

import android.support.annotation.NonNull;

import java.util.Date;
import java.util.Objects;

public class EventItem implements Comparable<EventItem> {

private String title;
private String author;
private String location;
private String description;
private Date startTime;
private Date endTime;
 public boolean isSelected;

public EventItem(String title, String author, String location, String description, Date startTime, Date endTime) {
    this.title = title;
    this.author = author;
    this.location = location;
    this.description = description;
    this.startTime = startTime;
    this.endTime = endTime;
}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EventItem eventItem = (EventItem) o;
        return Objects.equals(title, eventItem.title) &&
                Objects.equals(author, eventItem.author) &&
                Objects.equals(location, eventItem.location) &&
                Objects.equals(description, eventItem.description) &&
                Objects.equals(startTime, eventItem.startTime) &&
                Objects.equals(endTime, eventItem.endTime);
    }

    @Override
    public int hashCode() {

        return Objects.hash(title, author, location, description, startTime, endTime);
    }

    @Override
    public String toString() {
        return "EventItem{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", location='" + location + '\'' +
                ", description='" + description + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }


    @Override
    public int compareTo(@NonNull EventItem eventItem) {
        return getStartTime().compareTo(eventItem.getStartTime());
    }
}
