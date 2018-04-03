package com.example.gar_awgarrett.seeker;

import java.util.ArrayList;

/**
 * Created by Amy on 3/13/2018.
 */

public class User {
    private String email;
    private String name;
    private int collected;
    private String collectedLocations;

    public User(String email, String name, int collected, String collectedLocations) {
        this.email = email;
        this.name = name;
        this.collected = collected;
        this.collectedLocations = collectedLocations;
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

    public String getCollectedLocations() {
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

    public void setCollectedLocations(String collectedLocations) {
        this.collectedLocations = collectedLocations;
    }
}

