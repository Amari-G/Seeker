package com.example.gar_awgarrett.seeker;

/**
 * Created by gar_zccohen on 4/25/2018.
 */
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ChestCollectionList extends DialogFragment{
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.Builder(getActivity())
                .setIcon(R.drawable.treasurechestburned)
                .setTitle("You have collected _ emeralds! Here's the ones you've collected:")
                .setMessage("Space Needle, " +
                        "Garfield High School, " +
                        "Ezells")
                //.setPositiveButton("Collect", new DialogInterface.OnClickListener() {
                    //public void onClick(DialogInterface dialog, int which) {
                        //do something else
                   // }
                //})
                .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //do something else
                    }
                }).create();
    }
}

