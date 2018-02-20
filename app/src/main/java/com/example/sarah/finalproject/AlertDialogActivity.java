package com.example.sarah.finalproject;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

public class AlertDialogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_alert_dialog);

        AlertDialog.Builder Builder=new AlertDialog.Builder(this)
                .setMessage("Do You Want continue ?")
                .setTitle("exit")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setNegativeButton(R.string.No, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton(R.string.Yes,null);
        AlertDialog alertDialog=Builder.create();
        alertDialog.show();
    }
}
