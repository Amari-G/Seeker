package com.example.gar_awgarrett.seeker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Iterator;

public class CreateQuest extends AppCompatActivity {

    private DatabaseReference rDatabase;
    private DataSnapshot sDatabase;
    //private String locationId1, locationId2, locationId3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_create_quest);

        rDatabase = FirebaseDatabase.getInstance().getReference().child("Quests");


        Button createQuest = findViewById(R.id.button_create_quest);

        createQuest.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                EditText questNameInput = findViewById(R.id.newQuestName);
                Editable editable = questNameInput.getText();
                String questName = editable.toString();

                EditText et_Loc1 = findViewById(R.id.newLocation1);
                Editable eb_Loc1 = et_Loc1.getText();
                String locationName1 = eb_Loc1.toString();

                EditText et_Loc2 = findViewById(R.id.newLocation2);
                Editable eb_Loc2 = et_Loc2.getText();
                String locationName2 = eb_Loc2.toString();

                EditText et_Loc3 = findViewById(R.id.newLocation3);
                Editable eb_Loc3 = et_Loc3.getText();
                String locationName3 = eb_Loc3.toString();

                //DatabaseReference ref1 = rDatabase.getRef();

                //creates quest in database by generating a new path
                DatabaseReference newQuestPath = rDatabase.push();

                //creates new string with value set as the quest's unique id
                String pathId = newQuestPath.getKey();

                //creates location the location object with values created above
                //Quest quest = new Quest(questName, locationName1, locationName2, locationName3, pathId);

                //adds location object to the path created on line 59
                //rDatabase.child(pathId).setValue(quest);

                //Restarts activity with empty fields
                startActivity(new Intent(CreateQuest.this, CreateQuest.class));
                finish();

                //TODO: Allow user to add locations to quest via search engine
                /*ref1.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot){
                        Iterator i = dataSnapshot.getChildren().iterator();

                        while (i.hasNext()) {
                            locationId1 = (String) ((DataSnapshot) i.next()).getValue();
                            Log.i("Location Found:", locationId1);
                        }

                            //creates quest in database by generating a new path
                        //DatabaseReference newQuestPath = rDatabase.child("Quests").push();

                        //creates new string with value set as the quest's unique id
                        //String pathId = newQuestPath.getKey();

                        //creates location the location object with values created above
                        //Quest quest = new Quest(questName, locationName1, locationId1, locationName2, locationId2, locationName3, locationId3, pathId);

                        //adds location object to the path created on line 59
                        //rDatabase.child(pathId).setValue(quest);

                        //Restarts activity with empty fields
                        startActivity(new Intent(CreateQuest.this, CreateQuest.class));
                        finish();
                    }

                    @Override
                    public void onCancelled(DatabaseError e) {}
                });*/
            }
        });
    }
}
