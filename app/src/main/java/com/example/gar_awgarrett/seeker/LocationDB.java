package com.example.gar_awgarrett.seeker;
import com.google.firebase.database.DataSnapshot;

/**
 * Created by gar_awgarrett on 1/23/2018.
 */

public class LocationDB {

    String name;
    Double latitude;
    Double longitude;
    Double distance;
    Boolean collected;

    public LocationDB(DataSnapshot dataSnapshot, String name, Double latitude, Double longitude, Double distance, Boolean collected) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.distance = distance;
        this.collected = collected;
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public Double getLatitude() { return latitude; }

    public Double getLongitude() {
        return longitude;
    }

    public Double getDistance() {
        return distance;
    }

    public Boolean isCollected() {
        return collected;
    }

}
