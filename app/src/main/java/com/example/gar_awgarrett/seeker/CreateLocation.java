package com.example.gar_awgarrett.seeker;

import android.content.Intent;
import android.os.LocaleList;
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

                //creates location in database by generating a new path
                DatabaseReference newLocationPath = mDatabase.push();

                //creates new string with value set as the locations unique id
                String pathId = newLocationPath.getKey();

                //creates location the location object with values created above
                Location location = new Location(pathId, name, latitude, longitude);

                //adds location object to the path created on line 59
                mDatabase.child(pathId).setValue(location);

                //Restarts activity with empty fields
                startActivity(new Intent(CreateLocation.this, CreateLocation.class));
                finish();
            }
        });
    }
}
