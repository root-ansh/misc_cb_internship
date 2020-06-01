package com.example.abhatripathi.foodcubo;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e("TAG", "onCreate: splash activity called" );
//        ImageView image = findViewById(R.id.image);
//        image.setImageDrawable(getResources().getDrawable(R.drawable.splash));

        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Intent i = new Intent(SecondActivity.this, MainActivity.class);
                            startActivity(i);
                            finish();
                        }
                    });

                } catch (InterruptedException e) {
                    Log.e("TAG", "run: error occurred:"+e.getMessage());
                    e.printStackTrace();
                }
            }
        }.start();

    }
}
