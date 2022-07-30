package com.example.malava_constituency;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

//import com.google.android.ads.mediationtestsuite.dataobjects.Country;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.hbb20.CountryCodePicker;
import com.hbb20.CountryCodePicker.OnCountryChangeListener;

public class MainActivity extends AppCompatActivity {
ImageView contact,help;
CountryCodePicker countryCodePicker ;
Button login, register,go,file,loginVerify3;
    private FirebaseAnalytics mFirebaseAnalytics;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        setContentView(R.layout.activity_main);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        contact=findViewById(R.id.Contact);
        help=findViewById(R.id.Help);
        login=findViewById(R.id.Login);
        countryCodePicker=findViewById(R.id.countryPicker);
        //loginVerify3.findViewById(R.id.login3);
        register=findViewById(R.id.Register);
        file=findViewById(R.id.file);
        go=findViewById(R.id.go);

        /*loginVerify3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Login2.class);
                startActivity(intent);
            }
        });*/
        countryCodePicker.setOnCountryChangeListener(new OnCountryChangeListener() {
            @Override
            public void onCountrySelected() {
                Toast.makeText(MainActivity.this, "Thank you for selecting "+countryCodePicker.getSelectedCountryName()+" "+countryCodePicker.getDefaultCountryCode(), Toast.LENGTH_SHORT).show();
            }

        });

            //@Override


      //  });

        file.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,AccessMyDetailsLogin.class);
                startActivity(intent);
            }
        });

        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Contact.class);
                startActivity(intent);
            }
        });
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Help.class);
                startActivity(intent);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Login.class);
                startActivity(intent);
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Login.class);
                startActivity(intent);
            }
        });
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,recycler_show.class);
                startActivity(intent);
            }
        });

    }
}