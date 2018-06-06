package com.example.gar_awgarrett.seeker;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.content.res.AppCompatResources;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

/**
 * Created by gar_awgarrett on 11/8/2017.
 */

public class QuestActivity extends AppCompatActivity {
    private DatabaseReference mDatabase;
    private ListView mLocationList;
    private ListView mQuestList;
    //private TextView mTester;
    private ArrayList<String> mLocations = new ArrayList<>();
    private ArrayList<String> mQuests = new ArrayList<>();
    private static final String TAG = "UserAccountDeleted";
    FragmentManager fm = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
        setContentView(R.layout.activity_quest_page);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mLocationList = findViewById(R.id.location_list);
        //mTester = (TextView)findViewById(R.id.location);

        final ArrayAdapter<String>  locationAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, mLocations);
        mLocationList.setAdapter(locationAdapter);

        BottomNavigationView navView = findViewById(R.id.bottom_navigation_view);
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) navView.getChildAt(0);
        navView.setItemTextColor(AppCompatResources.getColorStateList
                (this, R.color.nav_bar_anim));
        BottomNavigationViewHelper.disableShiftMode(navView);

        for (int i = 0; i < menuView.getChildCount(); i++) {
            Log.i("Navigation Item Count: ", String.valueOf(menuView.getChildCount()));
            Log.i("Navigation Item Index: ", String.valueOf(i));
            final View iconView = menuView.getChildAt(i).findViewById(android.support.design.R.id.icon);
            final ViewGroup.LayoutParams layoutParams = iconView.getLayoutParams();
            final DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
            layoutParams.height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 32, displayMetrics);
            layoutParams.width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 32, displayMetrics);
            iconView.setLayoutParams(layoutParams);
        }


        navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.bNBCamera:
                        Intent cam = new Intent(getApplicationContext(), MapPage.class);
                        startActivity(cam);
                        break;
                    case R.id.bNBMap:
                        //go to map page
                        Intent map = new Intent(getApplicationContext(), MapPage.class);
                        startActivity(map);
                        break;
                    case R.id.bNBQuests:
                        //go to quest page
                    default:
                        return QuestActivity.super.onOptionsItemSelected(item);
                }
                return true;
            }
        });

        mDatabase.child("Emerald Locations").addChildEventListener(new ChildEventListener(){
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                //Log.d(TAG, "onChildAdded:" + dataSnapshot.getKey());
                //String value = dataSnapshot.getValue(String.class);
                String name = dataSnapshot.child("name").getValue().toString();
                mLocations.add(name);
                locationAdapter.notifyDataSetChanged();
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

        final ArrayAdapter<String>  questAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, mQuests);

        mQuestList = findViewById(R.id.quest_list);
        mQuestList.setAdapter(questAdapter);

        mDatabase.child("Quests").addChildEventListener(new ChildEventListener(){
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String name = dataSnapshot.child("name").getValue().toString();
                mQuests.add(name);
                questAdapter.notifyDataSetChanged();
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
    }

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
    }

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
                FirebaseAuth.getInstance().signOut();
                Intent signOut = new Intent(this, WelcomeActivity.class);
                this.startActivity(signOut);
                break;
            case R.id.quest_delete_account:
                //prompts user on whether or not they want to delete their account; leads to dialogfragment
                DeleteAccountWarning warning = new DeleteAccountWarning();
                warning.show(fm, "Delete Account");
                break;
            case R.id.action_add_location:
                //go to create location
                Intent addLocation = new Intent(this, CreateLocation.class);
                this.startActivity(addLocation);
                break;
            case R.id.action_add_quest:
                //go to create quest
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