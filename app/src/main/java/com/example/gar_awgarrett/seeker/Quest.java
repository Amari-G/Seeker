package com.example.gar_awgarrett.seeker;

import java.util.ArrayList;

public class Quest {

    private ArrayList<Location> Quest = new ArrayList<Location>();

    public Quest() {

    }


    public void addLocation(Location location) {
        Quest.add(location);
    }
}