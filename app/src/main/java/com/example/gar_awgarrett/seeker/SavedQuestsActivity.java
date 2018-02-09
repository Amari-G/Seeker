package com.example.gar_awgarrett.seeker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import static java.lang.Double.valueOf;

public class SavedQuestsActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private ListView mLocationList;
    //private TextView mTester;
    //private ArrayList<String> mLocations = new ArrayList<>();
    private ArrayList<Location> mLocations = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_quests);
        /*
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        */
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Emerald Locations");
        mLocationList = (ListView) findViewById(R.id.location_list);
        //mTester = (TextView)findViewById(R.id.location);

        final ArrayAdapter<Location>  arrayAdapter = new ArrayAdapter<Location>(this, android.R.layout.simple_list_item_1, mLocations);
        mLocationList.setAdapter(arrayAdapter);

        ChildEventListener childEventListener = mDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String id = dataSnapshot.getKey();
                String name = dataSnapshot.child("name").getValue().toString();
                Double latitude = Double.parseDouble(dataSnapshot.child("latitude").getValue().toString());
                Double longitude = Double.parseDouble(dataSnapshot.child("longitude").getValue().toString());
                Location newLocation = new Location(id, name, latitude, longitude);
                mLocations.add(newLocation);
                arrayAdapter.notifyDataSetChanged();
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
    public ArrayList getmLocations() {
        ArrayList mLocations = null;
        mLocations = this.mLocations;

        return mLocations;

    }

}