package com.example.gar_awgarrett.seeker;
import com.google.firebase.database.DataSnapshot;

/*
    The intended purpose of this object is to take information from a datasnapshot,
        and use the information to construct a Location object.
    This may be unnecessary however, as the client could just use the id of a location
        to collect the desired information directly from the database.
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
