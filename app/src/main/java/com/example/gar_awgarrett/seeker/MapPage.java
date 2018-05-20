package com.example.gar_awgarrett.seeker;

import android.content.SharedPreferences;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatDelegate;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


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
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
//import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class MapPage extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private GPSTracker gpsTracker;
    private Location mLocation;
    double latitude, longitude;
    FragmentManager fm = getSupportFragmentManager();
    int collectedCounter = 0;
    public TextView mInputDisplay;
    public String mInput;
    private String Name;
    private String Email;
    public String currentUserId;

    SharedPreferences sharedPreferences;

    private DatabaseReference mDatabase;
    private ArrayList<com.example.gar_awgarrett.seeker.Location> mLocations = new ArrayList<>();
    private ArrayList<String> locationList = new ArrayList<>();

    private DatabaseReference mUserDatabase;
    private DatabaseReference mUserBranch;
    private DatabaseReference collectedRef;

    private boolean inProximity = false;
    private com.example.gar_awgarrett.seeker.Location proximityLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
        setContentView(R.layout.activity_map_page);
        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText(" x ");

        //TextView textView = (TextView)findViewById(R.id.textView);
        textView.setText(" x " + collectedCounter);
        Log.i("collectedCounter", "Size is " + collectedCounter);

        sharedPreferences = getSharedPreferences("user_details", MODE_PRIVATE);
        Name = sharedPreferences.getString("Name", null);
        Email = sharedPreferences.getString("Email", null);
        getCurrentUserBranch();

        gpsTracker = new GPSTracker(getApplicationContext());
        mLocation = gpsTracker.getLocation();

        // Check for location
        // Alert user
        if (gpsTracker.canGetLocation)
        {
            double latitude = mLocation.getLatitude();
            double longitude = mLocation.getLongitude();

            //Toast.makeText(getApplicationContext(), "Your Location : \nLattitude " + latitude + "\nLongitude " + longitude, Toast.LENGTH_LONG).show();
            Toast.makeText(getApplicationContext(), "Happy emerald hunting " + Name + "!", Toast.LENGTH_LONG).show();
        }
        else {
            gpsTracker.showSettingsAlert();
        }


        latitude = mLocation.getLatitude();
        longitude = mLocation.getLongitude();

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        ImageButton bNBQuest = findViewById(R.id.bNBList);

        bNBQuest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MapPage.this, QuestActivity.class));
                //writeAndReadFromDatabase();
            }
        });

        ImageButton bNBCam = findViewById(R.id.bNBCamera);

        ImageButton chestButton = findViewById(R.id.imageButton2);

        chestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new Intent(MapPage.this, QuestActivity.class));
                ChestCollectionList collectionList = new ChestCollectionList();
                collectionList.show(fm, "Collection List");
            }
        });
        bNBCam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new Intent(MapPage.this, QuestActivity.class));
                EmeraldCollector emeraldCollector = new EmeraldCollector();
                emeraldCollector.show(fm, "Emerald Collector");
                collectedCounter++;
                TextView textView = (TextView) findViewById(R.id.textView);
                textView.setText(" x " + collectedCounter);
                Log.i("collectedCounter", "Size is " + collectedCounter);
            }
        });
    }

    // This method uses the Haversine formula to calculate the distance between two locations given latitudes and longitudes
    // Distance is in miles, rounded to two decimal places
    // Our code currently does not account for altitude
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

        // Add a marker in current location and move the camera
        LatLng currentLocation = new LatLng(latitude, longitude);
        mMap.addMarker(new MarkerOptions().position(currentLocation).title("Current Location"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(currentLocation));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 11.5f));

        //The following is sample code to add a marker at a locally saved Space Needle location
        /*
        double endLat = 47.6205;
        double endLong = -122.3493;
        LatLng spaceNeedle = new LatLng(endLat, endLong);
        String distanceToMarker = String.valueOf(distance(latitude, endLat, longitude, endLong, 0.0, 0.0)) + " mi";
        com.example.gar_awgarrett.seeker.Location spaceNeedleLocation = new com.example.gar_awgarrett.seeker.Location("Space Needle testing id", "Space Needle 1", endLat, endLong);
        displayLocation(mMap, spaceNeedleLocation);
        */

        /*
        mUserDatabase = FirebaseDatabase.getInstance().getReference().child("Users");
        //mUserBranch = FirebaseDatabase.getInstance().getReference().child("Users").child("-LBMRM19FzNYwDVpzh6B");
        mUserDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String id = dataSnapshot.getKey();
                String name = dataSnapshot.child("name").getValue().toString();
                String email = dataSnapshot.child("email").getValue().toString();
                if (email.equals(Email)) {
                    currentUserId = id;
                    Log.i("mUserDatabase", "Email is: " + email);
                    mUserBranch = FirebaseDatabase.getInstance().getReference().child("Users").child(currentUserId);
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        **/

        //Retrieve emerald locations from Firebase database and automatically display them on the map
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Emerald Locations");
        ChildEventListener childEventListener = mDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String id = dataSnapshot.getKey();
                String name = dataSnapshot.child("name").getValue().toString();
                Double latitude = Double.parseDouble(dataSnapshot.child("latitude").getValue().toString());
                Double longitude = Double.parseDouble(dataSnapshot.child("longitude").getValue().toString());
                com.example.gar_awgarrett.seeker.Location newLocation = new com.example.gar_awgarrett.seeker.Location(id, name, latitude, longitude);
                mLocations.add(newLocation);
                displayLocation(mMap, newLocation);
                //next two lines keeps track of mLocations size for testing
                int size = mLocations.size();
                Log.i("mLocations", "Size is: " + String.valueOf(size));
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        Gson gson = new Gson();
        String json = sharedPreferences.getString("location list", null);
        Type type = new TypeToken<ArrayList<String>>() {}.getType();
        locationList = gson.fromJson(json, type);
        if (locationList == null) {
            locationList = new ArrayList<>();
        }
        int size = locationList.size();
        Log.i("locationList", "Size is: " + String.valueOf(size));
    }

    //This sample code and testing message shows an example of how to read and write from the database
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
                //Log.d("Amy", "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("Amy", "Failed to read value.", error.toException());
            }
        });
    }

    public void displayLocation(GoogleMap googleMap, com.example.gar_awgarrett.seeker.Location location){
        mMap = googleMap;
        LatLng latLngLocation = new LatLng(location.getLatitude(), location.getLongitude());
        String distanceToMarker = String.valueOf(MapPage.distance(latitude, location.getLatitude(), longitude, location.getLongitude(), 0.0, 0.0)) + " mi";
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(latLngLocation));
        //mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLngLocation, 11.5f));
        mMap.addMarker(new MarkerOptions().position(latLngLocation).title(location.getName()).snippet(distanceToMarker) .icon(BitmapDescriptorFactory.fromResource(R.drawable.emerald_resized_1)));
        checkInProximity(mLocations, latitude, longitude);
        if (inProximity){
            EmeraldCollector emeraldCollector = new EmeraldCollector();
            emeraldCollector.show(fm, "Emerald Collector");
            collectedCounter++;
            TextView textView = (TextView) findViewById(R.id.textView);
            textView.setText(" x " + collectedCounter);
            Log.i("collectedCounter", "Size is: " + collectedCounter);
            Log.i("inProximity", "Proximity location is: " + location.getName());
            //mUserBranch = FirebaseDatabase.getInstance().getReference().child("Users").child("-LBMRM19FzNYwDVpzh6B");
            //getCurrentUserBranch();
            if (mUserBranch != null) {
                collectedRef = mUserBranch.child("collectedLocations");
                DatabaseReference newCollectedLocationPath = mUserBranch.push();
                final String pathId = newCollectedLocationPath.getKey();
                collectedRef.child(pathId).setValue(location.getId());
            }
        }
    }

    public com.example.gar_awgarrett.seeker.Location checkInProximity(ArrayList<com.example.gar_awgarrett.seeker.Location> mLocations, double latitude, double longitude){

        for(int i = 0; i <= mLocations.size() - 1; i++){
            if (mLocations.get(i).getLatitude().intValue() != 0  && mLocations.get(i).getLongitude().intValue() != 0){
                double distance = distance(latitude, mLocations.get(i).getLatitude(), longitude, mLocations.get(i).getLongitude(), 0.0, 0.0);
                if(distance <= 0.1){
                    inProximity = true;
                    proximityLocation = mLocations.get(i);
                }
            }
        }
        return proximityLocation;
    }

    public void getCurrentUserBranch(){
        mUserDatabase = FirebaseDatabase.getInstance().getReference().child("Users");
        //mUserBranch = FirebaseDatabase.getInstance().getReference().child("Users").child("-LBMRM19FzNYwDVpzh6B");
        mUserDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String id = dataSnapshot.getKey();
                String name = dataSnapshot.child("name").getValue().toString();
                String email = dataSnapshot.child("email").getValue().toString();
                if (email.equals(Email)) {
                    currentUserId = id;
                    Log.i("mUserDatabase", "Email is: " + email);
                    mUserBranch = FirebaseDatabase.getInstance().getReference().child("Users").child(currentUserId);
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}