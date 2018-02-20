package com.example.sarah.finalproject;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUp extends AppCompatActivity implements View.OnClickListener{
    Button signup,clear;
    EditText email,passw,phone;
    private FirebaseAuth auth​;
    private FirebaseAuth.AuthStateListener authStateListener​;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        signup=(Button)findViewById(R.id.button5);
        clear=(Button)findViewById(R.id.button4);
        email=(EditText)findViewById(R.id.editText3);
        passw=(EditText)findViewById(R.id.editText4);
        phone=(EditText)findViewById(R.id.editText5);
        signup.setOnClickListener(this);
        clear.setOnClickListener(this);
        auth​=FirebaseAuth.getInstance();

        authStateListener​ = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d("FireBase", "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d("FireBase", "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };
    }

    @Override
    public void onClick(View v) {


        String text1=email.getText().toString();
        String text2=passw.getText().toString();
        String text3=phone.getText().toString();
        if(v==signup){
            if(text1.equals(" ")||text2.equals("")||text3.equals(""))
                Toast.makeText(this, "can't sign up,there are empty fields", Toast.LENGTH_SHORT).show();

            else
            {
                SignUpMethod(text1,text2);

            }


        }
        if(v==clear){
            email.setText("");
            passw.setText("");
            phone.setText(" ");

        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        auth​.addAuthStateListener(authStateListener​);

    }

    @Override
    protected void onStop() {
        super.onStop();
        if(authStateListener​!=null)
            auth​.removeAuthStateListener(authStateListener​);
    }

    private void SignUpMethod(String email, String passw) {
        auth​.createUserWithEmailAndPassword(email, passw)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d("Sign Up", "createUserWithEmail:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Toast.makeText(SignUp.this,"Failed",
                                    Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(SignUp.this,"Succeed",
                                    Toast.LENGTH_SHORT).show();
                        }
                        // ...
                    }
                });


    }
}
/**
 *
 Toast.makeText(getApplicationContext()​, "Authentication Successful."​,1000).show();
 //updateUserProfile(task.getResult().getUser());
 finish();
 }
 else {
 Toast.makeText(getApplicationContext()​, "Authentication failed."​,1000).show();

 task.getException().printStackTrace();
 }
 **/