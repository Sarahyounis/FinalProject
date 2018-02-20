package com.example.sarah.finalproject;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Sarah on 9/20/2017.
 */

public class CustomGallery extends ArrayAdapter<Gallery_Downloads> {


    public CustomGallery(@NonNull Context context, int gallery, ArrayList<Gallery_Downloads> arr1) {
        super(context,R.layout.gallery,arr1);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater ImageInflater=LayoutInflater.from(getContext());
        View customView=ImageInflater.inflate(R.layout.gallery,parent,false);

        Gallery_Downloads item = getItem(position);
        ImageView imageView=(ImageView)customView.findViewById(R.id.imageView);
        TextView textView=(TextView)customView.findViewById(R.id.note);
        if(item.getFilePath()!=null){
            Bitmap myBitmap = BitmapFactory.decodeFile(item.getFilePath());
            imageView.setImageBitmap(myBitmap);
        }else{
            imageView.setImageResource(item.getImv());
        }
        textView.setText(item.getStrNote());

        return customView;
    }
}
