package com.example.abhatripathi.foodcubo;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Activity extends AppCompatActivity {
    Button btnAlert;
    @Override

    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.signup_login);
//        Button btnAlert = findViewById(R.id.location);
//        Intent i=new Intent(getApplicationContext(),SecondActivity.class);
//        startActivity(i);
//        final AlertDialog alertDialog = new AlertDialog.Builder(this)
//                .setTitle("ENABLE GPS")
//                .setCancelable(false)
//                .setIcon(Integer.parseInt("@drawable/location"))
//                .setPositiveButton("ALLOW", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//
//                    }
//                })
//                .setNegativeButton("DENY", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                    }
//                })
//                .setMessage("Allow FOODCUBO to access this device location?")
//                .create();
//        btnAlert.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                alertDialog.show();
//            }
//        });


    }
}

