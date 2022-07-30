package com.example.malava_constituency;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Contact extends AppCompatActivity {

    private TextView phone1,phone2,email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        phone1=findViewById(R.id.textView13);
        phone2=findViewById(R.id.textView15);
        email=findViewById(R.id.textView17);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Our contacts");
       // actionBar.
         phone1.setText("0712398765");
         phone2.setText("0798127643");
         email.setText("malavacdf@gmail.com");
       // Intent intent1=getIntent();
       /* Contacts contact= (Contacts) intent1.getSerializableExtra("Contacts");
        //lastLogin.setText(student.getLastLogin());
        phone1.setText("0"+contact.getFirstNumber());
        phone2.setText("0"+contact.getSecondNumber());
        email.setText(contact.getEmail());*/

        phone1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Uri number=Uri.parse("tel: "+phone1.getText().toString());
                //  intent.putExtra(Intent,"Inventor of this application");
                Intent intent=new Intent(Intent.ACTION_DIAL,number);
                //intent.setType("message/rfc822");
                startActivity(intent);
            }
        });
        phone2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri number=Uri.parse("tel: "+phone2.getText().toString());
                //  intent.putExtra(Intent,"Inventor of this application");
                Intent intent=new Intent(Intent.ACTION_DIAL,number);
                //intent.setType("message/rfc822");
                startActivity(intent);
            }
        });
        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                //Intent.ACTION_SENDTO
                //intent.putExtra(Intent.EXTRA_TEXT,"Welcome to our translator application");
                intent.putExtra(Intent.EXTRA_EMAIL,""+email.getText().toString());
                intent.setType("message/rfc822");
                startActivity(Intent.createChooser(intent,"Choose an email client"));
            }
        });


        //Student student = (Student) intent.getSerializableExtra("Student");
        //intent.getSerializableExtra()





    }
}