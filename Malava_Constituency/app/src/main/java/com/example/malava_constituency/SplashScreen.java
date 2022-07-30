package com.example.malava_constituency;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //;
        setContentView(R.layout.activity_splash_screen);
        getSupportActionBar().hide();
        //Handler handler=new Handler();
       // handler.postDelayed(,3000);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(getApplication(),MainActivity.class);
                startActivity(intent);
                finish();
            }

        },1500);

    }
}