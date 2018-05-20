package com.example.gar_awgarrett.seeker;

import java.util.ArrayList;

/**
 * Created by Amy on 3/13/2018.
 */

public class User {
    private String email;
    private String name;
    private int collected;
    private ArrayList<String> collectedLocations;
    private String collectedPlaces;

    public User(String email, String name, int collected, ArrayList<String> collectedLocations) {
        this.email = email;
        this.name = name;
        this.collected = collected;
        this.collectedLocations = collectedLocations;
    }

    public User(String email, String name, int collected, String collectedPlaces) {
        this.email = email;
        this.name = name;
        this.collected = collected;
        this.collectedPlaces = collectedPlaces;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public int getCollected() {
        return collected;
    }

    public ArrayList<String> getCollectedLocations() {
        return collectedLocations;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCollected(int collected) {
        this.collected = collected;
    }

    public void setCollectedLocations(ArrayList<String> collectedLocations) {
        this.collectedLocations = collectedLocations;
    }

    public void updateCollected (){collected++;}
}

