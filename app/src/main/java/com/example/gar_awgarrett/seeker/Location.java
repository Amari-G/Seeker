package com.example.gar_awgarrett.seeker;

/**
 * Created by Amy on 1/3/2018.
 */

public class Location {

    String id;
    String name;
    Double latitude;
    Double longitude;

    public Location(String id, String name, Double latitude, Double longitude) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.name = name;
    }

    //getters
    public String getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public Double getLatitude() { return latitude; }

    public Double getLongitude() {
        return longitude;
    }

}