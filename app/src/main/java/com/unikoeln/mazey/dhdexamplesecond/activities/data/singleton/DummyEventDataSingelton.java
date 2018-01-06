package com.unikoeln.mazey.dhdexamplesecond.activities.data.singleton;

import com.unikoeln.mazey.dhdexamplesecond.activities.data.EventItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class DummyEventDataSingelton {

    private List<EventItem> dummyDataList;


    public static DummyEventDataSingelton getInstance(){
        return new DummyEventDataSingelton();
    }

    public List<EventItem> getDummyDataList() {
        return dummyDataList;
    }

    private DummyEventDataSingelton() {
        this.fillList();
    }

    private void fillList() {
        this.dummyDataList = new ArrayList<EventItem>();
        this.dummyDataList.add(new EventItem("Titel 1", "Autor 1", "Ort 1 ", "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat", new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis())));
        this.dummyDataList.add(new EventItem("Titel 2", "Autor 2", "Ort 2 ", "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat", new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis())));
        this.dummyDataList.add(new EventItem("Titel 3", "Autor 3", "Ort 3 ", "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat", new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis())));
        this.dummyDataList.add(new EventItem("Titel 4", "Autor 4", "Ort 4 ", "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat", new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis())));
        this.dummyDataList.add(new EventItem("Titel 5", "Autor 5", "Ort 5 ", "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat", new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis())));
        this.dummyDataList.add(new EventItem("Titel 6", "Autor 6", "Ort 6 ", "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat", new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis())));
        this.dummyDataList.add(new EventItem("Titel 7", "Autor 7", "Ort 7 ", "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat", new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis())));
        this.dummyDataList.add(new EventItem("Titel 8", "Autor 8", "Ort 8 ", "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat", new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis())));
        this.dummyDataList.add(new EventItem("Titel 9", "Autor 9", "Ort 9 ", "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat", new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis())));
        this.dummyDataList.add(new EventItem("Titel 13", "Autor 13", "Ort 13 ", "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat", new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis())));
    }


}
