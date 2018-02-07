package com.example.gar_awgarrett.seeker;

import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
//import com.google.gson.Gson;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class MapPage extends FragmentActivity implements OnMapReadyCallback {

    private SavedQuestsActivity savedQuests;
    private GoogleMap mMap;
    private GPSTracker gpsTracker;
    private Location mLocation;
    double latitude, longitude;

    DatabaseReference databaseLocations;
    //ArrayList<Location> locationList;
    private ArrayList<com.example.gar_awgarrett.seeker.Location> locationList = new ArrayList<>();
    SavedQuestsActivity instanceOfSavedQuests;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        setContentView(R.layout.activity_map_page);

        gpsTracker = new GPSTracker(getApplicationContext());
        mLocation = gpsTracker.getLocation();

        latitude = mLocation.getLatitude();
        longitude = mLocation.getLongitude();

        databaseLocations = FirebaseDatabase.getInstance().getReference("Emerald Locations");
        //ArrayList<Location> locations = savedQuests.getmLocations();

        Bundle b = getIntent().getExtras();
        if (b != null) {
            ArrayList<Location> locationList = b.getParcelableArrayList("Location List");
            Log.i("List", "Passed Array List :: " + locationList);
        }
        else {
            Log.i("List", " Passing data did not work");
        }

        //Log.i("List", " Passing data did not work");

        //locationList = instanceOfSavedQuests.getmLocations();

        //String test = instanceOfSavedQuests.getmLocations();
        //Log.i("List", test);


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        ImageButton bNBQuest = findViewById(R.id.bNBList);

        //bNBQuest.setOnClickListener(new View.OnClickListener(){
        //    public void onClick(View v){
        //        startActivity(new Intent(MapPage.this, QuestActivity.class));
        //    }
        //});

        bNBQuest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MapPage.this, QuestActivity.class));
                writeAndReadFromDatabase();
            }
        //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
        //.setAction("Action", null).show();
        //}
        });


        //Gson gson = new Gson();
        String mLocationsAsAString = getIntent().getStringExtra("Locations Array");
        Log.i("List", " Passing data worked");
        //Intent intent = getIntent();
        //locationList = intent.getExtras().getParcelableArrayList("location_array");

    }

    public static double distance(double lat1, double lat2, double lon1, double lon2, double el1, double el2) {
        final int R = 6371; // Radius of the earth
        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000; // convert to meters
        double height = el1 - el2;
        distance = Math.pow(distance, 2) + Math.pow(height, 2);
        distance = Math.sqrt(distance);
        distance = 0.001 * distance;
        distance = 0.621371 * distance;
        BigDecimal bd = new BigDecimal(distance).setScale(2, RoundingMode.HALF_UP);
        double roundedDistance = bd.doubleValue();
        return roundedDistance;
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        //LatLng sydney = new LatLng(-34, 151);
        LatLng currentLocation = new LatLng(latitude, longitude);
        //mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.addMarker(new MarkerOptions().position(currentLocation).title("Current Location"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(currentLocation));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 11.5f));

        double endLat = 47.6205;
        double endLong = -122.3493;
        LatLng spaceNeedle = new LatLng(endLat, endLong);


        String distanceToMarker = String.valueOf(distance(latitude, endLat, longitude, endLong, 0.0, 0.0)) + " mi";



        mMap.addMarker(new MarkerOptions().position(spaceNeedle).title("Space Needle").snippet(distanceToMarker) .icon(BitmapDescriptorFactory.fromResource(R.drawable.emerald_resized_1)));
    }

    private void writeAndReadFromDatabase() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");
        DatabaseReference locationRef = database.getReference("Emerald Location");

        myRef.setValue("Yay this is working!!!");
        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                Log.d("Amy", "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("Amy", "Failed to read value.", error.toException());
            }
        });

        locationRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                Log.d("Emerald Location", "Value is: " + value);
                //for(DataSnapshot uniqueKeySnapshot : dataSnapshot.getChildren()){
                  //  for(DataSnapshot emeraldsSnapshot : uniqueKey.child(""))
                //}
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("Amy", "Failed to read value.", error.toException());
            }
        });
    }

}
