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
import com.google.firebase.database.Exclude;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Location {

    private String name;
    private String id;
    private Double latitude;
    private Double longitude;
    private Double altitude;
    //private Double distance;

    public Location(String id, String name, Double latitude, Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.altitude = altitude;
        this.name = name;
        this.id = id;
    }

    public Location(String name, Double latitude, Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.altitude = altitude;
        this.name = name;
        this.id = "";
    }



    //return location object's name as a string
    public String getName(){
        return name;
    }

    //return location object's id as a string
    public String getId() {
        return id;
    }

    //return location object's latitude as a double
    public Double getLatitude() {
        return latitude;
    }

    //returns location object's latitude as a string
    @Exclude
    public String getLatitudeAsString() {
        return this.latitude.toString();
    }

    //returns location object's longitude as a double
    public Double getLongitude() {
        return longitude;
    }

    //returns location object's longitude as a string
    @Exclude
    public String getLongitudeAsString() {
        return this.latitude.toString();
    }

    //returns location object's distance using a set of coordinates in the (lat, long) format
    public Double getDistance(Double userLat, Double userLong) {
        final int R = 6371; // Radius of the earth
        double latDistance = Math.toRadians(this.latitude - userLat);
        double lonDistance = Math.toRadians(this.longitude - userLong);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(userLat)) * Math.cos(Math.toRadians(this.latitude))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000; // convert to meters
        distance = Math.pow(distance, 2);
        distance = Math.sqrt(distance);
        distance = 0.001 * distance;
        distance = 0.621371 * distance;
        BigDecimal bd = new BigDecimal(distance).setScale(2, RoundingMode.HALF_UP);

        return bd.doubleValue();
    }
}