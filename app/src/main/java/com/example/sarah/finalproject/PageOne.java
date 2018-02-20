package com.example.sarah.finalproject;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.IdRes;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static android.content.Context.MODE_PRIVATE;

public class PageOne extends AppCompatActivity implements View.OnClickListener,RadioGroup.OnCheckedChangeListener{

    TabHost tabs;
    RadioGroup rdg;
    RadioButton ten,twenty,thirty,forty;
    private int CAMERA_REQUEST;
    ListView Gallery;
    ArrayList<Gallery_Downloads> items=new ArrayList<Gallery_Downloads>();
    private static final String TAG="MainActivity";
    BluetoothAdapter mBluetoothAdapter;
    Bitmap bitmap;
    SharedPreferences preferences;
    Intent intent;

    private static final int REQUEST_ENABLED=1;
    private static final int REQUEST_DISABLED=0;
    private static final int REQUEST_DISCOVERABLE=2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_one);

        preferences = getSharedPreferences("profile", MODE_PRIVATE);

        //CustomListView
        Gallery = (ListView) findViewById(R.id.listView);

        intent = getIntent();


        if (intent.getBooleanExtra("check", false))
            items.add(new Gallery_Downloads(R.drawable.baby, intent.getStringExtra("update")));
        else
            items.add(new Gallery_Downloads(R.drawable.baby, "baby"));
        CustomGallery custom = new CustomGallery(this, R.layout.gallery, items);
        Gallery.setAdapter(custom);
        //ListView -Gallery --> if clicked  move to another page to view the image and to update the note;
        Gallery.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            //ListView-Gallery if clicked move to anther page to view the photo bigger and update the note.
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Intent update = new Intent(PageOne.this, UpdateImage.class);
                update.putExtra("picture", items.get(position).getFilePath());
                update.putExtra("note", items.get(position).getStrNote());
                startActivity(update);
                return true;
            }
        });

        // ListView-Gallery --> if clicked move to another page to view the image
        Gallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            //ListView-Gallery if clicked move to anther page to view the image
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent View = new Intent(PageOne.this, PhotoView.class);
                View.putExtra("picture", items.get(position).getFilePath());
                startActivity(View);

            }
        });


        //Tabhost
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
        spec = tabs.newTabSpec("Bluetooth");
        spec.setContent(R.id.tab3);
        spec.setIndicator("Bluetooth");
        tabs.addTab(spec);
        //Tab 4
        spec = tabs.newTabSpec("Awareness");
        spec.setContent(R.id.tab4);
        spec.setIndicator("Awareness");
        tabs.addTab(spec);


        rdg = (RadioGroup) findViewById(R.id.radio1);
        ten = (RadioButton) findViewById(R.id.radioButton);
        twenty = (RadioButton) findViewById(R.id.radioButton2);
        thirty = (RadioButton) findViewById(R.id.radioButton4);
        forty = (RadioButton) findViewById(R.id.radioButton5);
        rdg.setOnCheckedChangeListener(this);


        String path = preferences.getString("path", null);
        if (path != null) {
            bitmap = BitmapFactory.decodeFile(path);
        }


//////////////////////////////////BLUETOOTH//////////////////////////////////////////////////////////////////////////////////////////////////

        Button ButOnOff = (Button) findViewById(R.id.btnEnable);
        Button btnDiscover = (Button) findViewById(R.id.btnDiscover);
        Button btnDisable = (Button) findViewById(R.id.btnDisable);


        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter == null)
            Toast.makeText(this, "Blue Tooth not supported", Toast.LENGTH_LONG).show();
        ButOnOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //enableDisableBt();
                // Intent enableIntent =new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                //startActivityForResult(enableIntent,REQUEST_ENABLED);
                Intent intentOpenBluetoothSettings = new Intent();
                intentOpenBluetoothSettings.setAction(android.provider.Settings.ACTION_BLUETOOTH_SETTINGS);
                startActivity(intentOpenBluetoothSettings);
            }
        });
        btnDiscover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mBluetoothAdapter.isDiscovering()) {
                    Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
                    startActivityForResult(enableIntent, REQUEST_DISCOVERABLE);
                }
            }
        });
        btnDisable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBluetoothAdapter.disable();
            }
        });
    }
////////////////////////////////////////////////////////////////////BLUETOOTH////////////////////////////////////////////////////////////////



    public String saveImage(Bitmap bitmap){
        File root = Environment.getExternalStorageDirectory();// internal storage launching .
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());


        String filePath = root.getAbsolutePath()+"/DCIM/Camera/IMG_"+timeStamp+".jpg";
        File file = new File(filePath);// determinig the type of the file and its place.

        try
        {
            // if gallary nit full create a file and save images
            file.createNewFile();// create new file to save image.
            FileOutputStream ostream = new FileOutputStream(file);//saves root in this file
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, ostream);// compass bitmap in file
            ostream.close();// close
        }
        catch (Exception e)
        {
            e.printStackTrace();
            Toast.makeText(this, "Faild to save image", Toast.LENGTH_SHORT).show();
        }
        return filePath;
    }
    // camera request manegement
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            saveImage(photo);
            items.add(new Gallery_Downloads(saveImage(photo),"-Long Click to add a note-"));
        }
    }

    @Override
    public void onClick(View v) {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Are you sure you want to exit ? ");
        builder.setMessage("hhhhh");
        builder.setPositiveButton("Exit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // finish();
            }
        });
        builder.setNegativeButton("Nope", null);
        AlertDialog alert = builder.create();
        builder.show();
    }


    // radiobuttons manegements
    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        if(checkedId==R.id.radioButton){
            Toast.makeText(this, "the alarm will remind you every 3 mins", Toast.LENGTH_SHORT).show();
        }
        if(checkedId==R.id.radioButton2){
            Toast.makeText(this, "the alarm will remind you every 5 mins", Toast.LENGTH_SHORT).show();
        }
        if(checkedId==R.id.radioButton4){
            Toast.makeText(this, "the alarm will remind you every 7 mins", Toast.LENGTH_SHORT).show();
        }
        if(checkedId==R.id.radioButton5){
            Toast.makeText(this, "the alarm will remind you every 10 mins", Toast.LENGTH_SHORT).show();
        }




    }
    //menu manegnent
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    //Menu Manegment
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
            case R.id.camera:
                Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(i, CAMERA_REQUEST);
                break;

            case R.id.help:
                Intent h = new Intent(this,Help.class);
                startActivity(h);

                break;

        }
        return true;
    }

    @Override
    public void onBackPressed() {

        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Are you sure you want to exit ? ");
        builder.setMessage("hhhhh");
        builder.setPositiveButton("Exit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                closeActivity();
            }
        });
        builder.setNegativeButton("Nope", null);
        AlertDialog alert = builder.create();
        builder.show();
    }

    public void closeActivity(){
        this.finish();
    }
//////////////////////////////////////////////////////////////BLUETOOTH///////////////////////////////////////////////////////////////////

    public void enableDisableBt(){
        if(!mBluetoothAdapter.isEnabled())
        {
            Intent enableIntent =new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivity(enableIntent);

            IntentFilter BTIntent= new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED);
            registerReceiver(mBroadcastReceiver1,BTIntent);
        }
        if(mBluetoothAdapter.isEnabled())
            mBluetoothAdapter.disable();
        IntentFilter BTIntent= new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED);
        registerReceiver(mBroadcastReceiver1,BTIntent);


    }

    // Create a BroadcastReceiver for ACTION_FOUND.
    private final BroadcastReceiver mBroadcastReceiver1 = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(BluetoothAdapter.ACTION_STATE_CHANGED))
            {
                final int state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, BluetoothAdapter.ERROR);

                switch(state){
                    case BluetoothAdapter.STATE_OFF:
                        Log.d(TAG, "onReceive: STATE OFF");
                        break;
                    case BluetoothAdapter.STATE_TURNING_OFF:
                        Log.d(TAG, "mBroadcastReceiver1: STATE TURNING OFF");
                        break;
                    case BluetoothAdapter.STATE_ON:
                        Log.d(TAG, "mBroadcastReceiver1: STATE ON");
                        break;
                    case BluetoothAdapter.STATE_TURNING_ON:
                        Log.d(TAG, "mBroadcastReceiver1: STATE TURNING ON");
                        break;
                }

            }
        }
    };
    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy: called.");
        super.onDestroy();
        unregisterReceiver(mBroadcastReceiver1);
    }
////////////////////////////////////////////////////////////BLUETOOTH/////////////////////////////////////////////////////////////////////////
}

