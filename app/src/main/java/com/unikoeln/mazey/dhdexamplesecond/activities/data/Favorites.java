package com.unikoeln.mazey.dhdexamplesecond.activities.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Favorites {

    private List<EventItem> markierteListe = new ArrayList<EventItem>();

    public static Favorites getInstance(){
        return new Favorites();
    }

    public List<EventItem> getMarkierteListe() { return this.markierteListe; }

       private Favorites() {
       this.fillList();
    }

    private void fillList() {
        this.markierteListe.add(new EventItem("Titel 2", "Autor 2", "Ort 2 ", "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat", new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis())));
        this.markierteListe.add(new EventItem("Titel 3", "Autor 3", "Ort 3 ", "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat", new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis())));
        this.markierteListe.add(new EventItem("Titel 6", "Autor 6", "Ort 6 ", "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat", new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis())));
        this.markierteListe.add(new EventItem("Titel 8", "Autor 8", "Ort 8 ", "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat", new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis())));
        this.markierteListe.add(new EventItem("Titel 13", "Autor 13", "Ort 13 ", "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat", new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis())));
    }

}
