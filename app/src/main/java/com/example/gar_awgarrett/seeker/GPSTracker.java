package com.example.gar_awgarrett.seeker;

import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.LocationListener;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.content.ContextCompat;
import android.util.Log;

/**
 * Created by Amy on 11/27/2017.
 */

public class GPSTracker extends Service implements LocationListener {

    private final Context context;

    boolean isGPSEnabled = false;
    boolean isNetworkEnabled = false;
    boolean canGetLocation = false;
    boolean inProximity = false;

    Location location;
    protected LocationManager locationManager;

    public GPSTracker(Context context){
        this.context = context;
    }

    // Create a GetLocation method //
    public Location getLocation() {
        try{

            locationManager = (LocationManager) context.getSystemService(LOCATION_SERVICE);
            isGPSEnabled = locationManager.isProviderEnabled(locationManager.GPS_PROVIDER);
            isNetworkEnabled = locationManager.isProviderEnabled(locationManager.NETWORK_PROVIDER);

            if(ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                    || ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED){

                if(isGPSEnabled){
                    if(location == null){
                        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 10, this);
                        if(locationManager != null){
                            location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                        }
                    }
                }

                // if location is not found from GPS, then it will be found from network //
                if(location == null){
                    if(isNetworkEnabled){
                            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 10, this);
                            if(locationManager != null){
                                location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                            }
                    }
                }
            }
        } catch(Exception ex){

        }

        return location;

    }

    //public void checkUserProximity



    // followings are the default method if we implement LocationListener //
    public void onLocationChanged(Location location) {
        Log.i("called", "onLocationChanged");


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
