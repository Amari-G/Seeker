package com.example.gar_awgarrett.seeker;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Camera;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class WelcomeActivity extends AppCompatActivity {
    private Camera mCamera = null;
    private CameraActivity mCameraActivity = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        //creates sign in button
        Button bWSignIn = findViewById(R.id.bWSignIn);

        //links sign in button to the sign in page
        bWSignIn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(WelcomeActivity.this, SignInActivity.class));
            }
        });

        //creates sign up button
        Button bWSignUp = findViewById(R.id.bWSignUp);

        //links sign up button to sign up page
        bWSignUp.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(WelcomeActivity.this, SignUpActivity.class));
            }
        });
    }
}
