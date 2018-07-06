package com.example.abhatripathi.foodcubo;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    Button btnAlert;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_login);
        Log.e(TAG, "onCreate: has been stared after setting the layouts");

        Button btnAlert = findViewById(R.id.location);
        final AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setTitle("ENABLE GPS")
                .setCancelable(false)
                .setPositiveButton("ALLOW", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setNegativeButton("DENY", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .setMessage("Allow FOODCUBO to access this device location?")
                .create();
        btnAlert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.show();
            }
        });


    }
}


