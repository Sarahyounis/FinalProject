package com.example.sarah.finalproject;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
//import android.support.design.widget.BottomNavigationView;
//import android.support.v7.app.AppCompatActivity;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import static android.support.v7.app.AlertDialog.*;


public class PhotoView extends AppCompatActivity {
ImageView imageView;
ImageButton whatsapp;
    Intent shareIntent = new Intent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_view);
        imageView=(ImageView) findViewById(R.id.imageView3);
        final Intent V =getIntent();
        Bitmap myBitmap = BitmapFactory.decodeFile(V.getStringExtra("picture"));
        imageView.setImageBitmap(myBitmap);

        final AlertDialog.Builder dbuilder=new AlertDialog.Builder(this);

        dbuilder.setMessage("Are you sure you want to delete this photo ?");
        dbuilder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(PhotoView.this, "photo is deleted", Toast.LENGTH_SHORT).show();
            }
        });

        dbuilder.setNegativeButton("cancel", null);
        AlertDialog alert = dbuilder.create();

        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_favorites:

                              //  AlertDialog.Builder dbuilder=new AlertDialog.Builder();

                                dbuilder.show();
                                break;
                            case R.id.action_schedules:


                                        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                                        Uri screenshotUri = Uri.parse(V.getStringExtra("picture"));

                                        sharingIntent.setType("image/jpeg");
                                        sharingIntent.putExtra(Intent.EXTRA_STREAM, screenshotUri);
                                        startActivity(Intent.createChooser(sharingIntent, "Share image using"));



                                break;

                        }
                        return false;
                    }
                });

    }
}

