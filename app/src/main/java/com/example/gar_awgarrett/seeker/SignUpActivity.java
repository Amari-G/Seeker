package com.example.gar_awgarrett.seeker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import android.widget.ImageButton;


public class SignUpActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_sign_up);

        ImageButton bSUBack = findViewById(R.id.bSUBack);

        //links sign up button to sign up page
        bSUBack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(SignUpActivity.this, WelcomeActivity.class));
            }
        });
        findViewById(R.id.tvSignIn).setOnClickListener(this);

    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tvSignIn:

                startActivity(new Intent(this, SignInActivity.class));

                break;
        }
    }
}

