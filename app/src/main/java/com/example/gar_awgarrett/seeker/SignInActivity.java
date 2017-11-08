package com.example.gar_awgarrett.seeker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

public class SignInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_sign_in);

        //creates sign up button
        Button bSIBack = findViewById(R.id.bSIBack);

        //links sign up button to sign up page
        bSIBack.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(SignInActivity.this, WelcomeActivity.class));
            }
        });

        Button bSISignIn = findViewById(R.id.bSISignIn);

        //links sign up button to sign up page
        bSISignIn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(SignInActivity.this, MapPage.class));
            }
        });

       // EditText username = findViewById(R.id.etSIPassword);
       // EditText password = findViewById(R.id.etSIUsername);

    }
}
