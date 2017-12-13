package com.example.gar_awgarrett.seeker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;

/**
 * Created by gar_awgarrett on 11/8/2017.
 */

public class QuestActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_quest_page);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);


        //creates list page button in navigation bar
        ImageButton bNBQuest = findViewById(R.id.bNBList);

        //links sign up button to sign up page
        bNBQuest.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(QuestActivity.this, QuestActivity.class));
            }
        });

        //creates list page button in navigation bar
        ImageButton bNBMap = findViewById(R.id.bNBMap);

        //links sign up button to sign up page
        bNBMap.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(QuestActivity.this, MapPage.class));
            }
        });
    }
}
