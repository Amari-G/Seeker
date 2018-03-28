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
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.provider.Settings;
import android.util.Log;


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

    public GPSTracker(Context context) {
        this.context = context;
    }

    // Create a GetLocation method //
    public Location getLocation() {
        try {

            locationManager = (LocationManager) context.getSystemService(LOCATION_SERVICE);
            isGPSEnabled = locationManager.isProviderEnabled(locationManager.GPS_PROVIDER);
            isNetworkEnabled = locationManager.isProviderEnabled(locationManager.NETWORK_PROVIDER);

            if (!isGPSEnabled && !isNetworkEnabled) {

            } else {
                this.canGetLocation = true;


                if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                        || ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

                    if (isGPSEnabled) {
                        if (location == null) {
                            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 10, this);
                            if (locationManager != null) {
                                location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                            }
                        }
                    }

                    // if location is not found from GPS, then it will be found from network //
                    if (location == null) {
                        if (isNetworkEnabled) {
                            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 10, this);
                            if (locationManager != null) {
                                location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                            }
                        }
                    }
                }
            }
        }  catch (Exception ex) {
ex.printStackTrace();
           }


        return location;

    }

    public void stopUsingGPS()
    {
        if(locationManager != null)
        {
            locationManager.removeUpdates(GPSTracker.this);
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

    public void showSettingsAlert() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);

        // Alert title
        alertDialog.setTitle("Location Is Turned Off");

        // Alert content
        alertDialog.setMessage("You have your location settings turned off... please activate this to continue");

        // Alert Icon
        //alertDialog.setIcon(R.drawable.delete);

        // Settings button
        alertDialog.setPositiveButton("Settings", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                context.startActivity(intent);
            }
        });

        // Cancel button
        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        // Show message box
        alertDialog.show();
    }}

