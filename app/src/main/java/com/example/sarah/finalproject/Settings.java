package com.example.sarah.finalproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class Settings extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
Spinner spinner;
    int position=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        spinner=(Spinner)findViewById(R.id.spinner);
        String [] languages={"arabic" ,"english","hebrew","german","spanish" };
        ArrayAdapter adapter= new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,languages);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch(position)
        {

            case 0:
                Toast.makeText(this, "arabic was selected", Toast.LENGTH_SHORT).show();
                break;
            case 1:
                Intent s=new Intent(this,Email.class);
                startActivity(s);
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
