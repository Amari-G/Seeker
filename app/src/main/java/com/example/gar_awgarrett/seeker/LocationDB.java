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

    String id;
    String name;
    String latitude;
    String longitude;


    public LocationDB(Location location) {
        this.latitude = location.getLatitude().toString();
        this.longitude = location.getLongitude().toString();
        this.name = location.getName();
        this.id = location.getId();
    }

    public String getId() { return id; }

    public String getName() { return name; }

    public String getLatitude() { return latitude; }

    public String getLongitude() {
        return longitude;
    }

}