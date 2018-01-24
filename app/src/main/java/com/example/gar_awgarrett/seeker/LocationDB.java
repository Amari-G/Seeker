package com.example.gar_awgarrett.seeker;

/**
 * Created by Amy on 1/3/2018.
 */


import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LocationDB {

    String name;
    String latitude;
    String longitude;
    String distance;
    String collected;

    public LocationDB(Location location) {
        this.latitude = location.getLatitude().toString();
        this.longitude = location.getLongitude().toString();
        this.distance = location.getDistance().toString();
        this.collected = location.isCollected().toString();
        this.name = location.getName();
    }

    public String getName() { return name; }

    public String getLatitude() { return latitude; }

    public String getLongitude() {
        return longitude;
    }

    public String getDistance() {
        return distance;
    }

    public String isCollected() { return collected; }


    /*public String convertLatitude(Location location) {
        latitude = this.latitude.toString();
        return latitude;
    }*/
}