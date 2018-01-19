package com.example.gar_awgarrett.seeker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

public class CreateQuest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_create_quest);
    }
}
