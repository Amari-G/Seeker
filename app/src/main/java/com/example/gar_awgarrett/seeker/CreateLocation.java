package com.example.gar_awgarrett.seeker;

import android.content.Intent;
import android.os.LocaleList;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import android.location.Location;

import android.location.LocationListener;
import android.location.Location;
import android.location.LocationManager;


public class CreateLocation extends AppCompatActivity {

    private SavedQuestsActivity savedQuestsActivity;
    private DatabaseReference mDatabase;
    private LocaleList newLocationList;
    FragmentManager fm = getSupportFragmentManager();

    private boolean inProximity = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_create_location);

        //refers to "Emerald Locations" child in database
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Emerald Locations");

        //creates quest page button in navigation bar
        Button createLocation = findViewById(R.id.button_create_location);

        //The following code executes when the create location button is pressed
        createLocation.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //takes text input from "Location Name" field
                EditText locationInput = findViewById(R.id.newLocName);
                Editable editable = locationInput.getText();
                String name = editable.toString();

                //takes text input from "Latitude" field
                EditText latitudeInput = findViewById(R.id.newLocLat);
                Editable editableLat = latitudeInput.getText();
                String stringLat = editableLat.toString();
                Double latitude = Double.parseDouble(stringLat);

                //takes text input from "Longitude" field
                EditText longitudeInput = findViewById(R.id.newLocLong);
                Editable editableLong = longitudeInput.getText();
                String stringLong = editableLong.toString();
                Double longitude = Double.parseDouble(stringLong);
                GPSTracker user = new GPSTracker(getApplicationContext());

                //checks proximity of location before adding to datatbase
                checkProximity(latitude, longitude, user);

                if(inProximity == false){
                    ProximityError proximityError = new ProximityError();
                    proximityError.show(fm, "ProximityError");
                }

                else {

                    //creates location in database by generating a new path
                    DatabaseReference newLocationPath = mDatabase.push();

                    //creates new string with value set as the locations unique id
                    String pathId = newLocationPath.getKey();

                    //creates the location object with values created above
                    com.example.gar_awgarrett.seeker.Location location = new com.example.gar_awgarrett.seeker.Location(pathId, name, latitude, longitude);


                    //adds location object to the path created on line 59
                    mDatabase.child(pathId).setValue(location);

                    //Restarts activity with empty fields
                    startActivity(new Intent(CreateLocation.this, CreateLocation.class));
                    finish();
                }
            }
        });

    }


    //if the location is within 0.1 miles, that location will not be viable
    public boolean checkProximity(double latitude, double longitude, GPSTracker user) {
        double userLat = user.getLocation().getLatitude();
        double userLong = user.getLocation().getLongitude();
        double distance = MapPage.distance(latitude, userLat, longitude, userLong, 0.0, 0.0);
        if (distance <= 0.1) {
            inProximity = false;
        }
        return inProximity;
    }


}
