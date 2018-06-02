package com.example.gar_awgarrett.seeker;

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
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by isabellaceriale on 5/30/18.
 */

public class ProximityError extends DialogFragment {

    private static final String TAG = "ProximityError";
    private EditText mInput;
    private TextView mAcionOk, mActionCancel;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.Builder(getActivity())
                .setIcon(R.drawable.error_mark)
                .setTitle("New locations must be at least 0.1 miles away from the user creating them.")
                .setPositiveButton("Exit", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //do something else
                    }

                }).create();
    }
}
