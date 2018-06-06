package com.example.gar_awgarrett.seeker;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class EmeraldCollector extends DialogFragment {

    private static final String TAG = "EmeraldCollector";
    private EditText mInput;
    private TextView mAcionOk, mActionCancel;
    SharedPreferences sharedPreferences;
    SharedPreferences sharedPreferencesLocation;
    private String Name;
    private String Email;
    private String Location;
    private int numberCollected;
    private DatabaseReference mUserDatabase;
    private DatabaseReference mUserBranch;
    public String currentUserId;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        sharedPreferences = getActivity().getSharedPreferences("user_details", MODE_PRIVATE);
        Name = sharedPreferences.getString("Name", null);
        Email = sharedPreferences.getString("Email", null);
        sharedPreferencesLocation = getActivity().getSharedPreferences("location_details", MODE_PRIVATE);
        numberCollected = sharedPreferences.getInt("Number Collected", 0);
        Location = sharedPreferencesLocation.getString("Location", null);
        Log.i("Location", "Value is: " + Location);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setIcon(R.drawable.emerald)
                .setTitle("You found an emerald!")
                .setMessage("           " + Location)
                .setPositiveButton("Collect", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //do something else
                    }
               // })
                //.setNegativeButton("Skip", new DialogInterface.OnClickListener() {
                   // public void onClick(DialogInterface dialog, int which) {
                        //do something else
                    //}
                });
        return builder.create();
    }

    public void getCurrentUserBranch(){
        mUserDatabase = FirebaseDatabase.getInstance().getReference().child("Users");
        mUserDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String id = dataSnapshot.getKey();
                String name = dataSnapshot.child("name").getValue().toString();
                String email = dataSnapshot.child("email").getValue().toString();
                if (email.equals(Email)) {
                    currentUserId = id;
                    Log.i("EmeraldCollector", "currentUserId is: " + currentUserId);
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
