package com.example.sarah.finalproject;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TabHost;
import android.widget.Toast;

public class PageOne extends AppCompatActivity implements View.OnClickListener,RadioGroup.OnCheckedChangeListener{

    TabHost tabs;
    RadioGroup rdg;
    RadioButton ten,twenty,thirty,forty;
   Button btn;
    private int CAMERA_REQUEST;
    ImageView phot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_one);
        tabs = (TabHost) findViewById(R.id.tabsAlarm);
        tabs.setup();

        //Tab 1
        TabHost.TabSpec spec = tabs.newTabSpec("Reminder");
        spec.setContent(R.id.tab1);
        spec.setIndicator("Reminder");
        tabs.addTab(spec);

        //Tab 2
        spec = tabs.newTabSpec("Gallery");
        spec.setContent(R.id.tab2);
        spec.setIndicator("Gallery");
        tabs.addTab(spec);

        //Tab 3
        spec = tabs.newTabSpec("Awareness");
        spec.setContent(R.id.tab3);
        spec.setIndicator("Awareness");
        tabs.addTab(spec);
        rdg=(RadioGroup)findViewById(R.id.radio1);
        rdg.setOnCheckedChangeListener(this);
        ten=(RadioButton)findViewById(R.id.radioButton);
        twenty=(RadioButton)findViewById(R.id.radioButton2);
        thirty=(RadioButton)findViewById(R.id.radioButton4);
        forty=(RadioButton)findViewById(R.id.radioButton5);
        btn=(Button)findViewById(R.id.button7);
        btn.setOnClickListener(this);
        phot=(ImageView)findViewById(R.id.imageView);



    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            phot.setImageBitmap(photo);
        }
    }

    @Override
    public void onClick(View v) {
        if(v==btn){
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, CAMERA_REQUEST);
        }

    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        if(checkedId==R.id.radioButton){
            Toast.makeText(this, "the alarm will remind you every 1o mins", Toast.LENGTH_SHORT).show();
        }
        if(checkedId==R.id.radioButton2){
            Toast.makeText(this, "the alarm will remind you every 2o mins", Toast.LENGTH_SHORT).show();
        }
        if(checkedId==R.id.radioButton4){
            Toast.makeText(this, "the alarm will remind you every 3o mins", Toast.LENGTH_SHORT).show();
        }
        if(checkedId==R.id.radioButton5){
            Toast.makeText(this, "the alarm will remind you every 4o mins", Toast.LENGTH_SHORT).show();
        }




    }
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId())
        {
            case R.id.settings:
                Intent s=new Intent(this,Settings.class);
                startActivity(s);
                break;
            case R.id.Email:
                Intent e=new Intent(this,Email.class);
                startActivity(e);
                break;

        }
        return true;
    }
}
