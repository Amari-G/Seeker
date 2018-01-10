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
    FirebaseDatabase database;
    double lat_result;

    public Location(double latitude, double longitude, double distance, boolean collected) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.distance = distance;
        this.collected = collected;
    }

    public double getLatitude() {
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
    }

    public double getLongitude() {
        return longitude;
    }

    public double getDistance() {
        return distance;
    }

    public boolean isCollected() {
        return collected;
    }
}