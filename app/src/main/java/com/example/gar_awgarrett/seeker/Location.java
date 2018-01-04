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

public class Location {


    double latitude;
    double longitude;
    double distance;
    boolean collected;

    public Location(double latitude, double longitude, double distance, boolean collected) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.distance = distance;
        this.collected = collected;
    }
}