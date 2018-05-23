package com.example.gar_awgarrett.seeker;

/**
 * Created by gar_zccohen on 4/25/2018.
 */
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static android.content.Context.MODE_PRIVATE;

public class ChestCollectionList extends DialogFragment{
    SharedPreferences sharedPreferences;
    SharedPreferences sharedPreferencesLocation;
    private String Name;
    private String Email;
    private String Location;
    private DatabaseReference mUserDatabase;
    private DatabaseReference mUserBranch;
    private DatabaseReference mUserLocations;
    public String currentUserId;
    private ArrayList<String> collectedList;
    private ArrayList<String> collectedLocationList;
    private int Counter;
    private String title;
    private String message;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        //Intent intent = getActivity().getIntent();
        //collectedLocationList = intent.getStringArrayListExtra("collected locations");
        //Log.i("collectedLocationList", "Value is: " + collectedLocationList);
        sharedPreferences = getActivity().getSharedPreferences("user_details", MODE_PRIVATE);
        Name = sharedPreferences.getString("Name", null);
        Email = sharedPreferences.getString("Email", null);
        sharedPreferencesLocation = getActivity().getSharedPreferences("location_details", MODE_PRIVATE);
        Location = sharedPreferencesLocation.getString("Location", null);
        Counter = sharedPreferencesLocation.getInt("Counter", 0);
        Set<String> set = sharedPreferencesLocation.getStringSet("locations", null);
        collectedLocationList = new ArrayList<>(set);
        if (Counter == 1) {
            title = "You have collected " + Counter + " emerald!";
        }
        else {
            title = "You have collected " + Counter + " emeralds!";
        }

        //collectedLocationList = getCollectedLocations();
        Log.i("collectedLocationList", "Value is: " + collectedLocationList);
        message = "";
        for (int i = 0; i < collectedLocationList.size(); i++) {
            message = message + collectedLocationList.get(i) + "\n";
        }
        Log.i("message", "Message is: " + message);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setIcon(R.drawable.treasurechestburned)
                .setTitle(title)
                .setMessage(message)
                //.setPositiveButton("Collect", new DialogInterface.OnClickListener() {
                    //public void onClick(DialogInterface dialog, int which) {
                        //do something else
                   // }
                //})
                .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //do something else
                    }
                });
        return builder.create();
    }
}

