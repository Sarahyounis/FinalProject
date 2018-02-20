package com.example.sarah.finalproject;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText name, password;
    Button signin, signup, clear;
    private FirebaseAuth auth​;
    private FirebaseAuth.AuthStateListener authStateListener​;
    private static final String LOG_TAG = "SMSReceiver";
    public static final int NOTIFICATION_ID_RECEIVED = 0x1221;
    static final String ACTION = "android.provider.Telephony.SMS_RECEIVED";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        auth​ = FirebaseAuth.getInstance();
        name = (EditText) findViewById(R.id.editText);
        password = (EditText) findViewById(R.id.editText2);
        signin = (Button) findViewById(R.id.button2);
        signup = (Button) findViewById(R.id.button3);
        clear = (Button) findViewById(R.id.button1);
        clear.setOnClickListener(this);
        signin.setOnClickListener(this);
        signup.setOnClickListener(this);

        IntentFilter filter = new IntentFilter();
        filter.addAction(BluetoothDevice.ACTION_ACL_CONNECTED);
        filter.addAction(BluetoothDevice.ACTION_ACL_DISCONNECT_REQUESTED);
        filter.addAction(BluetoothDevice.ACTION_ACL_DISCONNECTED);
        this.registerReceiver(mReceiver, filter);
    }


    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);

            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                //Device found
            }
            else if (BluetoothDevice.ACTION_ACL_CONNECTED.equals(action)) {
                //Device is now connected
                Toast.makeText(getApplicationContext(), "nice 1", Toast.LENGTH_SHORT).show();
            }
            else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {
                //Done searching
            }
            else if (BluetoothDevice.ACTION_ACL_DISCONNECT_REQUESTED.equals(action)) {
                //Device is about to disconnect
                Toast.makeText(getApplicationContext(), "nice 2", Toast.LENGTH_SHORT).show();
            }
            else if (BluetoothDevice.ACTION_ACL_DISCONNECTED.equals(action)) {
                    //your SMS processing code
                Intent i=new Intent(context.getApplicationContext(),AlertDialogActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
                Toast.makeText(getApplicationContext(), "nice yo", Toast.LENGTH_SHORT).show();
             }
        }
    };
    @Override
    public void onClick(View v) {
        String text1 = name.getText().toString();
        String text2 = password.getText().toString();
        if (v == signin) {
            if (text1.equals(" ") || text2.equals(""))
                Toast.makeText(this, "can't sign in,there are empty fields", Toast.LENGTH_SHORT).show();
            else {
                SingIn(text1,text2);

            }

        }
        if (v == clear) {
            name.setText("");
            password.setText("");

        }
        if (v == signup) {
            Intent intent = new Intent(this, SignUp.class);

            startActivity(intent);
        }

    }

    public void SingIn(String email, String pass) {

      auth​.signInWithEmailAndPassword(email,pass).addOnCompleteListener(this,new OnCompleteListener<AuthResult>() {
        @Override
        public void onComplete (@NonNull Task< AuthResult > task) {
            Toast.makeText(MainActivity.this, "inside on complete createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();

            // If sign in fails, display a message to the user. If sign in succeeds
            // the auth state listener will be notified and logic to handle the
            // signed in user can be handled in the listener.
            if (!task.isSuccessful()) {
                Toast.makeText(MainActivity.this, R.string.auth_failed, Toast.LENGTH_SHORT).show();
            }else
            {
                Intent Access = new Intent(MainActivity.this, PageOne.class);
                startActivity(Access);}

            // ...
        }
    });
}
    private void displayAlert()
    {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to exit?").setCancelable(
                false).setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                }).setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();

       /* AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle(" confirmation alert ");
        builder.setMessage("Do you have your children in the car?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "you will be notified every 3 mins", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("Nope", null);
        AlertDialog alert = builder.create();
        builder.show();*/
    }
}
