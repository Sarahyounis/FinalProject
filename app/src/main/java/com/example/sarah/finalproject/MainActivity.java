package com.example.sarah.finalproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
EditText name,password;
    Button signin,signup,clear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name=(EditText)findViewById(R.id.editText);
        password=(EditText)findViewById(R.id.editText2);
        signin=(Button)findViewById(R.id.button2);
        signup=(Button)findViewById(R.id.button3);
        clear=(Button)findViewById(R.id.button1);
        clear.setOnClickListener(this);
       signin.setOnClickListener(this);
        signup.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        String text1=name.getText().toString();
        String text2=password.getText().toString();
        if(v==signin){
            if(text1.equals(" ")||text2.equals(""))
                Toast.makeText(this, "can't sign in,there are empty fields", Toast.LENGTH_SHORT).show();
            else
            {
                Intent Access= new Intent(this,PageOne.class);
                startActivity(Access);
            }

        }
        if(v==clear){
            name.setText("");
            password.setText("");

        }
        if(v==signup){
            Intent intent=new Intent(this, SignUp.class);

            startActivity(intent);
        }

    }
}
