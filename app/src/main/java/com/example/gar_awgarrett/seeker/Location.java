package com.example.gar_awgarrett.seeker;

/**
 * Created by Amy on 1/3/2018.
 */


import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Location {

    String name;
    Double latitude;
    Double longitude;
    Double distance;
    Boolean collected;

    public Location(String name, Double latitude, Double longitude, Double distance, Boolean collected) {
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