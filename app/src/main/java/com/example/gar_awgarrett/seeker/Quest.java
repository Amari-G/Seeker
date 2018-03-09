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

public class Quest {

    String name;
    String location1;
    String location2;
    String location3;
    String id;

    public Quest(String name, String location1, String location2, String location3, String id) {
        this.name = name;
        this.location1 = location1;
        this.location2 = location2;
        this.location3 = location3;
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public String getLocation1() { return location1; }

    public String getLocation2() {
        return location2;
    }

    public String getLocation3() { return location3; }

    public String getId(){return id;}

}