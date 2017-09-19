package com.example.sarah.finalproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUp extends AppCompatActivity implements View.OnClickListener{
Button signup,clear;
    EditText name,password,phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        signup=(Button)findViewById(R.id.button5);
        clear=(Button)findViewById(R.id.button4);
        name=(EditText)findViewById(R.id.editText3);
        password=(EditText)findViewById(R.id.editText4);
        phone=(EditText)findViewById(R.id.editText5);
        signup.setOnClickListener(this);
        clear.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        String text1=name.getText().toString();
        String text2=password.getText().toString();
        String text3=phone.getText().toString();
        if(v==signup){
            if(text1.equals(" ")||text2.equals("")||text3.equals(""))
                Toast.makeText(this, "can't sign up,there are empty fields", Toast.LENGTH_SHORT).show();
            else
                {
                Intent access= new Intent(this,PageOne.class);
                    startActivity(access);
            }


        }
        if(v==clear){
            name.setText("");
            password.setText("");
            phone.setText(" ");

        }



    }
}
