package com.unikoeln.mazey.dhdexamplesecond.activities.data.eventdata.observable;

import android.util.Log;

import com.unikoeln.mazey.dhdexamplesecond.activities.fragments.WorkInProgressFragment;

import java.util.Observable;

public class EventDataObservable extends Observable {

    public EventDataObservable(boolean sessionsLoaded) {
        this.addObserver(new WorkInProgressFragment());
        this.notifyDataIsAvailable(sessionsLoaded);
    }

    public void notifyDataIsAvailable(boolean sessionsLoaded) {
        this.setChanged();
        this.notifyObservers(sessionsLoaded);
        Log.i("Observer Message", "Data changed.");
    }
}
