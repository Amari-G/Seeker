package com.example.gar_awgarrett.seeker;

import android.app.Service;
import android.content.Context;
import android.location.LocationListener;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.content.Intent;
import android.os.IBinder;

/**
 * Created by Amy on 11/27/2017.
 */

public class GPSTracker extends Service implements LocationListener {

    private final Context context;

    boolean isGPSEnabled = false;
    boolean isNetworkEnabled = false;
    boolean canGetLocation = false;

    Location location;
    protected LocationManager locationManager;

    public GPSTracker(Context context){
        this.context = context;
    }

    // Create a GetLocation method //
    public Location getLocation() {
        try{

            locationManager = (LocationManager) context.getSystemService(LOCATION_SERVICE);

        }
    }

    // followings are the default method if we implement LocationListener //
    public void onLocationChanged(Location location) {

    }

    public void onStatusChanged(String Provider, int status, Bundle extras) {
    }

    public void onProviderEnabled(String Provider) {
    }

    public void onProviderDisabled(String Provider) {
    }

    public IBinder onBind(Intent arg0) {
        return null;
    }
}
