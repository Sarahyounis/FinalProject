package com.example.sarah.finalproject;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Settings extends AppCompatActivity  {
Button Updatepass, addphone , clearall ,deleteacount;
    EditText Npassword , CNpassword, SpRphone ;
    FirebaseUser user;
    String emailAdress="user@example.com";
    FirebaseAuth auth;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Updatepass=(Button) findViewById(R.id.update);
        addphone=(Button) findViewById(R.id.addbt);
        clearall=(Button) findViewById(R.id.clear);
        deleteacount=(Button) findViewById(R.id.deleteacount);
        Npassword=(EditText)findViewById(R.id.editText6) ;
        CNpassword=(EditText)findViewById(R.id.editText7) ;
        SpRphone=(EditText)findViewById(R.id.editText8) ;
        user= FirebaseAuth.getInstance().getCurrentUser();
        auth=FirebaseAuth.getInstance();
        Updatepass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String NpasswordT =Npassword.getText().toString();
                String CNpasswordT= CNpassword.getText().toString();
                if(NpasswordT.equals("")&&CNpasswordT.equals(""))
                    Toast.makeText(Settings.this, "empty fields!", Toast.LENGTH_SHORT).show();
                if(!NpasswordT.equals(CNpasswordT))
                    Toast.makeText(Settings.this, "passwords are not matched!", Toast.LENGTH_SHORT).show();
                else{
                    auth.sendPasswordResetEmail(emailAdress).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful())
                                Toast.makeText(Settings.this, "email sent", Toast.LENGTH_SHORT).show();

                        }
                    });

                }


            }
        });
        addphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        clearall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        deleteacount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()) {
                            Toast.makeText(Settings.this, "user deleted !", Toast.LENGTH_SHORT).show();
                            Intent start = new Intent(Settings.this, MainActivity.class);
                            startActivity(start);
                        }

                    }
                });

            }
        });
    }



}
