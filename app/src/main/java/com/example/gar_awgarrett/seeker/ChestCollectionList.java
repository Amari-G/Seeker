package com.example.gar_awgarrett.seeker;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.util.Log;

import java.util.ArrayList;
import java.util.Set;

import static android.content.Context.MODE_PRIVATE;

public class ChestCollectionList extends DialogFragment{
    SharedPreferences sharedPreferences;
    SharedPreferences sharedPreferencesLocation;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        //Intent intent = getActivity().getIntent();
        //collectedLocationList = intent.getStringArrayListExtra("collected locations");
        //Log.i("collectedLocationList", "Value is: " + collectedLocationList);
        sharedPreferences = getActivity().getSharedPreferences("user_details", MODE_PRIVATE);
        String name = sharedPreferences.getString("Name", null);
        String email = sharedPreferences.getString("Email", null);
        sharedPreferencesLocation = getActivity().getSharedPreferences("location_details", MODE_PRIVATE);
        String location = sharedPreferencesLocation.getString("Location", null);
        int counter = sharedPreferencesLocation.getInt("Counter", 0);
        Set<String> set = sharedPreferencesLocation.getStringSet("locations", null);
        ArrayList<String> collectedLocationList = null;
        if (set != null) {
            collectedLocationList = new ArrayList<>(set);
        }
        String title;
        if (counter == 1) {
            title = "You have collected " + counter + " emerald!";
        }
        else {
            title = "You have collected " + counter + " emeralds!";
        }

        //collectedLocationList = getCollectedLocations();
        Log.i("collectedLocationList", "Value is: " + collectedLocationList);
        StringBuilder message = new StringBuilder();
        if (collectedLocationList != null) {
            for (int i = 0; i < collectedLocationList.size(); i++) {
                message.append(collectedLocationList.get(i)).append("\n");
            }
        }

        Log.i("message", "Message is: " + message);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setIcon(R.drawable.treasurechestburned)
                .setTitle(title)
                .setMessage(message.toString())
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

