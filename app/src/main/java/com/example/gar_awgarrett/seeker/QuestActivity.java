package com.example.gar_awgarrett.seeker;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
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
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by gar_awgarrett on 11/8/2017.
 */

public class QuestActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_quest_page);

        //ListView list = findViewById(R.id.locationsList);

        //MyAdapter adapter = new MyAdapter(this, generateData());
        /*String[] emeraldLocations = {"Space Needle", "Add More Emeralds!"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.target_item, emeraldLocations);*/
        //list.setAdapter(adapter);

        new Model(R.drawable.emerald, "Menu Item 1", "1");
        new Model("Group Title");

        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        //creates list page button in navigation bar
        ImageButton bNBQuest = findViewById(R.id.bNBList);

        //links sign up button to sign up page
        /*bNBQuest.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(QuestActivity.this, QuestActivity.class));
            }
        });*/

        bNBQuest.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(QuestActivity.this, SavedQuestsActivity.class));
            }
        });


        //creates list page button in navigation bar
        ImageButton bNBMap = findViewById(R.id.bNBMap);

        //links sign up button to sign up page
        bNBMap.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(QuestActivity.this, MapPage.class));
            }
        });
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
                //go to settings page();
                Intent intent = new Intent(this, SettingsActivity.class);
                this.startActivity(intent);
                break;
            case R.id.saved_quests_activity:
                //go to settings page();
                Intent intent2 = new Intent(this, SavedQuestsActivity.class);
                this.startActivity(intent2);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }


    private ArrayList<Model> generateData(){
        ArrayList<Model> models = new ArrayList<>();
        models.add(new Model("Objectives"));
        models.add(new Model(R.drawable.emerald_resized_1,"Space Needle","2"));

        return models;
    }
}
