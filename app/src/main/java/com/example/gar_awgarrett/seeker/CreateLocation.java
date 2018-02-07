package com.example.gar_awgarrett.seeker;

import android.content.Intent;
import android.os.LocaleList;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static java.lang.Double.valueOf;

public class CreateLocation extends AppCompatActivity {

    private SavedQuestsActivity savedQuestsActivity;
    private DatabaseReference mDatabase;
    private LocaleList newLocationList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_create_location);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Emerald Locations");

        //creates quest page button in navigation bar
        Button createLocation = findViewById(R.id.button_create_location);

        //links quest page button to quest page
        createLocation.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText locationInput = findViewById(R.id.newLocName);
                Editable editable = locationInput.getText();
                String name = editable.toString();

                EditText latitudeInput = findViewById(R.id.newLocLat);
                Editable editableLat = latitudeInput.getText();
                String stringLat = editableLat.toString();
                Double latitude = Double.parseDouble(stringLat);

                EditText longitudeInput = findViewById(R.id.newLocLong);
                Editable editableLong = longitudeInput.getText();
                String stringLong = editableLong.toString();
                Double longitude = Double.parseDouble(stringLong);

                //Double distance = 0.0;
                //Boolean collected = false;
                String id = "temporary id";


                Location newLocation = new Location(id, name, latitude, longitude);

                LocationDB locationDB = new LocationDB(newLocation);

                //mDatabase.child(locationDB.getName());
                mDatabase.child(locationDB.getId());
                //mDatabase.child(locationDB.getName()).child("Collected").setValue(locationDB.isCollected());
                //mDatabase.child(locationDB.getName()).child("Distance").setValue(locationDB.getDistance());
                mDatabase.child(locationDB.getName()).child("name").setValue(locationDB.getId());
                mDatabase.child(locationDB.getName()).child("latitude").setValue(locationDB.getLatitude());
                mDatabase.child(locationDB.getName()).child("longitude").setValue(locationDB.getLongitude());

                startActivity(new Intent(CreateLocation.this, CreateLocation.class));
                finish();
            }
        });
    }
}
