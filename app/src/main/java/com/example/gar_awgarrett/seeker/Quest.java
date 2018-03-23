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

    private String name;
    private String location1;
    private String location2;
    private String location3;
    private String locationId1;
    private String locationId2;
    private String locationId3;
    private String id;

    public Quest(String name, String location1, String location2, String location3, String questId) {
        this.name = name;
        this.location1 = location1;
        this.location2 = location2;
        this.location3 = location3;
        this.id = questId;
    }

    public Quest(String name, String location1, String locationId1, String location2,
                 String locationId2, String location3, String locationId3, String questId) {
        this.name = name;
        this.location1 = location1;
        this.location2 = location2;
        this.location3 = location3;
        this.locationId1 = locationId1;
        this.locationId2 = locationId2;
        this.locationId3 = locationId3;
        this.id = questId;
    }

    public String getName(){
        return name;
    }

    public String getLocation1() { return location1; }

    public String getLocationId1() { return locationId1; }

    public String getLocation2() {
        return location2;
    }

    public String getLocationId2() { return locationId2; }

    public String getLocation3() { return location3; }

    public String getLocationId3() { return locationId3; }

    public String getQuestId(){return id;}

}