package com.example.gar_awgarrett.seeker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * Created by gar_awgarrett on 11/8/2017.
 */

public class QuestActivity extends AppCompatActivity {
    private DatabaseReference mDatabase;
    private ListView mLocationList;
    //private TextView mTester;
    private ArrayList<String> mLocations = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
        setContentView(R.layout.activity_quest_page);

        //ListView list = findViewById(R.id.locationsList);

        //MyAdapter adapter = new MyAdapter(this, generateData());
        /*String[] emeraldLocations = {"Space Needle", "Add More Emeralds!"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.target_item, emeraldLocations);*/
        //list.setAdapter(adapter);

        /*new Model(R.drawable.emerald, "Menu Item 1", "1");
        new Model("Group Title");*/

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Emerald Locations");
        mLocationList = (ListView) findViewById(R.id.location_list);
        //mTester = (TextView)findViewById(R.id.location);

        final ArrayAdapter<String>  arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mLocations);
        mLocationList.setAdapter(arrayAdapter);

        mDatabase.addChildEventListener(new ChildEventListener(){
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                //Log.d(TAG, "onChildAdded:" + dataSnapshot.getKey());
                //String value = dataSnapshot.getValue(String.class);
                String value = dataSnapshot.getKey().toString();
                mLocations.add(value);
                arrayAdapter.notifyDataSetChanged();
                //mTester.setText("Location: " + value);
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

        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        //creates quest page button in navigation bar
        ImageButton bNBQuest = findViewById(R.id.bNBList);

        /*
        //links quest page button to quest page
        bNBQuest.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(QuestActivity.this, QuestActivity.class));
            }
        });*/

        //creates map page button in navigation bar
        ImageButton bNBMap = findViewById(R.id.bNBMap);

        //links map button to map page
        bNBMap.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
                startActivity(new Intent(QuestActivity.this, MapPage.class));
            }
        });

        //creates a camera button in navigation bar
        ImageButton bNBCamera = findViewById(R.id.bNBCamera);

        //links camera button to camera page
        bNBCamera.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
                startActivity(new Intent(QuestActivity.this, SavedQuestsActivity.class));
            }
        });
    }
    /*
    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        onStartNewActivity();
    }

    @Override
    public void startActivity(Intent intent, Bundle options) {
        super.startActivity(intent, options);
        onStartNewActivity();
    }

    protected void onStartNewActivity() {
        overridePendingTransition(R.anim.slide_out_left, R.anim.slide_in_left);
    }

    @Override
    public void finish() {
        super.finish();
        onLeaveThisActivity();
    }

    protected void onLeaveThisActivity() {
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.actionbuttons, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.quest_settings:
                //go to settings page
                Intent settings = new Intent(this, SettingsActivity.class);
                this.startActivity(settings);
                break;
            case R.id.quest_Sign_Out:
                //go to welcome page
                Intent signOut = new Intent(this, WelcomeActivity.class);
                this.startActivity(signOut);
                break;
            case R.id.action_add_location:
                //go to create location
                Intent addLocation = new Intent(this, CreateLocation.class);
                this.startActivity(addLocation);
                break;
            case R.id.action_add_quest:
                //go to create location
                Intent addQuest = new Intent(this, CreateQuest.class);
                this.startActivity(addQuest);
                break;
            case R.id.quest_saved_quests:
                //go to create location
                Intent SavedQuests = new Intent(this, SavedQuestsActivity.class);
                this.startActivity(SavedQuests);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }
}
