package com.example.sarah.finalproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Email extends AppCompatActivity implements View.OnClickListener{
    Button send,clear;
    EditText email, message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);
        send=(Button)findViewById(R.id.send);
        clear=(Button)findViewById(R.id.clear);
        email=(EditText)findViewById(R.id.email);
        message=(EditText)findViewById(R.id.message);
        send.setOnClickListener(this);
        clear.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        String text1=email.getText().toString();
        String text2=message.getText().toString();

        if(v==clear){
            email.setText("");
            message.setText("");
        }

        if(v==send){
            if(text1.equals("")|| text2.equals(""))
                Toast.makeText(this, "can't send mail,there are empty fields", Toast.LENGTH_SHORT).show();
            else{
                Intent email = new Intent(Intent.ACTION_SEND);
                email.putExtra(Intent.EXTRA_EMAIL, new String[]{String.valueOf(email)});
                email.putExtra(Intent.EXTRA_TEXT,message.getText());

                //need this to prompts email client only
                email.setType("message/rfc822");

                startActivity(Intent.createChooser(email, "Choose an Email client :"));

            }
        }
    }

    }

