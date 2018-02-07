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

    String id;
    String name;
    Double latitude;
    Double longitude;
    //Double distance;
    //Boolean collected;

    public Location(String id, String name, Double latitude, Double longitude) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        //this.distance = distance;
        //this.collected = collected;
        this.name = name;
    }

    public String getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public Double getLatitude() {
        /*
        database = FirebaseDatabase.getInstance();
        DatabaseReference locationRef = database.getReference("Emerald Location");
        locationRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                double value = dataSnapshot.getValue(double.class);
                Log.d("Emerald Location", "Value is: " + value);
                lat_result = value;
                // return result;
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("Amy", "Failed to read value.", error.toException());
            }
        });
        // latitude = result;
        return lat_result;
        */
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    /*public Double getDistance() {
        return distance;
    }

    public Boolean isCollected() {
        return collected;
    }
    */

    /*public String convertLatitude(Location location) {
        latitude = this.latitude.toString();
        return latitude;
    }*/
}