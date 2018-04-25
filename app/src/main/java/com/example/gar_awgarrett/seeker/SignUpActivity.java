
package com.example.gar_awgarrett.seeker;


import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.ImageButton;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    ProgressBar progressBar;
    EditText editTextEmail, editTextPassword, editTextName;

    private FirebaseAuth mAuth;
    private DatabaseReference userRef;
    private DatabaseReference collectedRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        editTextName = findViewById(R.id.etSUName);
        editTextEmail = findViewById(R.id.etSUEmail);
        editTextPassword = findViewById(R.id.etSUPassword);
        progressBar = findViewById(R.id.progressbar);

        mAuth = FirebaseAuth.getInstance();

        findViewById(R.id.bSUSignUp).setOnClickListener(this);
        findViewById(R.id.tvSignIn).setOnClickListener(this);

        ImageButton bSUBack = findViewById(R.id.bSUBack);
        bSUBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignUpActivity.this, WelcomeActivity.class));
            }
        });
    }

    private void registerUser() {
        final String name = editTextName.getText().toString().trim();
        final String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if (email.isEmpty()) {
            editTextEmail.setError("Email is required");
            editTextEmail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError("Please enter a valid email");
            editTextEmail.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            editTextPassword.setError("Password is required");
            editTextPassword.requestFocus();
            return;
        }

        if (password.length() < 6) {
            editTextPassword.setError("Minimum length of password should be 6");
            editTextPassword.requestFocus();
            return;
        }

        if (name.isEmpty()) {
            editTextName.setError("Name is required");
            editTextName.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);


        //ArrayList<String> locations = new ArrayList<>();

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar.setVisibility(View.GONE);
                if (task.isSuccessful()) {
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    userRef = database.getReference("Users");
                    DatabaseReference newUserPath = userRef.push();
                    final String pathId = newUserPath.getKey();

                    //create a new user object
                    User user = new User(email, name, 0, "");

                    //create a new child in the Users branch of the Firebase database
                    userRef.child(pathId).setValue(user);
                    collectedRef = FirebaseDatabase.getInstance().getReference().child("Users").child(pathId).child("collectedLocations");
                    //DatabaseReference newUserLocationPath = collectedRef.push();
                    //String locationPathId = newUserLocationPath.getKey();
                    //collectedRef.child(locationPathId).setValue("false");
                    //final String[] locations = new String[]{};
                    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("Emerald Locations");
                    ChildEventListener childEventListener = mDatabase.addChildEventListener(new ChildEventListener() {
                        @Override
                        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                            //DatabaseReference newUserLocationPath = collectedRef.push();
                            String id = dataSnapshot.getKey();
                            collectedRef.child(id).setValue("false");
                            //locations.add(id);
                            //locations.add(id);
                            //next two lines keeps track of mLocations size for testing
                            //int size = locations.size();
                            //Log.i("mLocations", "Size is: " + String.valueOf(size));


                            //for (String location : locations){
                            //    collectedRef.child(location).setValue("false");
                            //}
                        }
                        @Override
                        public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                        }

                        @Override
                        public void onChildRemoved(DataSnapshot dataSnapshot) {

                        }

                        @Override
                        public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                    finish();
                    startActivity(new Intent(SignUpActivity.this, MapPage.class));
                } else {

                    if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                        Toast.makeText(getApplicationContext(), "You are already registered", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });

    }



    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bSUSignUp:
                registerUser();
                break;

            case R.id.tvSignIn:
                finish();
                startActivity(new Intent(this, SignInActivity.class));
                break;

            case R.id.bSUBack:
                finish();
                startActivity(new Intent(this, WelcomeActivity.class));
                break;
        }
    }
}

