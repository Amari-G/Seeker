package com.example.gar_awgarrett.seeker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class CreateQuest extends AppCompatActivity {

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_create_quest);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Quests");

        Button createQuest = findViewById(R.id.button_create_quest);

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


        //creates location in database by generating a new path
        DatabaseReference newQuestPath = mDatabase.push();

        //creates new string with value set as the locations unique id
        String pathId = newQuestPath.getKey();

        //creates location the location object with values created above
        Quest quest = new Quest(questName, locationName1, locationName2, locationName3, pathId);

        //adds location object to the path created on line 59
        mDatabase.child(pathId).setValue(quest);

        //Restarts activity with empty fields
        startActivity(new Intent(CreateQuest.this, CreateQuest.class));
        finish();
    }
}
