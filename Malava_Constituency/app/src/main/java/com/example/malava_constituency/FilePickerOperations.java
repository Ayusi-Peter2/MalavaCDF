package com.example.malava_constituency;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class FilePickerOperations extends AppCompatActivity {
    TextView dispalyFile, chooseFile;
    Intent intent, choose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_picker_operations);
        dispalyFile = findViewById(R.id.file);
        chooseFile = findViewById(R.id.ChooseFile);

        try {

            chooseFile.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    intent = new Intent(Intent.ACTION_GET_CONTENT);
                    intent.setType("*/*");
                    intent = Intent.createChooser(choose, "Select the file");
                    startActivityForResult(intent, 10);


                }
            });
        } catch (Exception e) {
            dispalyFile.setText("Exception "+e.getMessage());
        }


    }

    //@SuppressLint("MissingSuperCall")
    @Override

    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {


            super.onActivityResult(requestCode, resultCode, data);
        try {
            switch (requestCode) {
                case 10:
                    if (resultCode == RESULT_OK) {
                        String info = data.getData().getPath();
                        dispalyFile.setText(" " + info);
                    }
                    break;
            }


        }
        catch (Exception e)
        {
        dispalyFile.setText(" "+e.getMessage());
        }
    }
    }




    //public void onAct
