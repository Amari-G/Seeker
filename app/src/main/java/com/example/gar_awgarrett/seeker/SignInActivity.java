package com.example.gar_awgarrett.seeker;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;



import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;


public class SignInActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener,
        View.OnClickListener{

   /* private static final String TAG = "GoogleActivity";
    private static final int RC_SIGN_IN = 9001;

    private FirebaseAuth mAuth;

    private FirebaseAuth.AuthStateListener mAuthListener;

    private GoogleApiClient mGoogleApiClient;

    private TextView myStatusTextView;

    private TextView myDetailTextView; */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN
       /* GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleApiClient = new GoogleApiClient.Builder(this);


        */
        //creates sign up button
        ImageButton bSIBack = findViewById(R.id.bSIBack);

        //links sign up button to sign up page
        bSIBack.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(SignInActivity.this, WelcomeActivity.class));
            }
        });

        Button bSISignIn = findViewById(R.id.bSISignIn);

        //links sign up button to map page
        bSISignIn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(SignInActivity.this, MapPage.class));
            }
        });

       // EditText username = findViewById(R.id.etSIPassword);
       // EditText password = findViewById(R.id.etSIUsername);

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
