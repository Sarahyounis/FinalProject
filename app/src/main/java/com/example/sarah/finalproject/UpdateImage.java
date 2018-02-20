package com.example.sarah.finalproject;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class UpdateImage extends AppCompatActivity {

    String text1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_image);
         ImageView imageView=(ImageView) findViewById(R.id.imageView2);

        final Intent update =getIntent();
        Bitmap myBitmap = BitmapFactory.decodeFile(update.getStringExtra("picture"));
        imageView.setImageBitmap(myBitmap);


        final TextView NewNote=(TextView)findViewById(R.id.textView);
        final  EditText NoTe=(EditText)findViewById(R.id.NoTe);
        Button UpDate=(Button) findViewById(R.id.update);
        UpDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text1=NoTe.getText().toString();
                if(text1.equals("")) {
                    Toast.makeText(UpdateImage.this, " empty field", Toast.LENGTH_SHORT).show();
                }
                else{
                    Intent back=new Intent(UpdateImage.this,PageOne.class);
                    back.putExtra("update",text1);
                    back.putExtra("check",true);
                    startActivity(back);

                }
            }
        });

    }
}
