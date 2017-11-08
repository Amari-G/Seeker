package com.example.gar_awgarrett.seeker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

/**
 * Created by gar_awgarrett on 11/7/2017.
 */

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);
    }

    /** Called when the user taps the Back button */
    public void goToActivityMap(View view) {
        Intent intent = new Intent(this, MapPage.class);
        startActivity(intent);
    }
}
