package com.example.malava_constituency;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.internal.Storage;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.net.URL;

public class SelectFile extends AppCompatActivity {
Button selectFile,upload;
TextView selectedData;
FirebaseDatabase firebaseDatabase;
FirebaseStorage firebaseStorage;
ProgressDialog progressDialog;
Uri pdfUri;//are actually urls meant for local storage
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_file);
        selectFile=findViewById(R.id.selectButton);
        upload=findViewById(R.id.upload);

        selectedData=findViewById(R.id.selectedFile);
        firebaseStorage=FirebaseStorage.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance();

        selectFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ContextCompat.checkSelfPermission(SelectFile.this, Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED)
                {
                          selectPDF();
                }
                else
                {
                    //Manifest.class
                    ActivityCompat.requestPermissions(SelectFile.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},9);
                }
            }
        });

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pdfUri!=null)
                {
                    upload(pdfUri);
                }
                else
                {
                    Toast.makeText(SelectFile.this, "You haven't seected any file", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void upload(Uri pdfUri) {
        progressDialog=new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL );
        progressDialog.setTitle("Uploading");
       // progressDialog.dr
      //  progressDialog.setProgress(0);
        progressDialog.show();
        //String fileName=System.currentTimeMillis;
        StorageReference storageReference=FirebaseStorage.getInstance().getReference();// firebaseStorage.getReference();//returns the root path
        storageReference.child("Uploads").child("fileName").putFile(pdfUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                progressDialog.hide();
                Toast.makeText(SelectFile.this, "File is stored in firestore", Toast.LENGTH_SHORT).show();
             //String url=taskSnapshot.getDownloadUrl().t
                taskSnapshot.getUploadSessionUri();
               // taskSnapshot.getMetadata().get;
                //String url=  taskSnapshot.getUploadSessionUri().toString();
                //store url in realtime firebase
                DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference();
                databaseReference.child("fileName").setValue("com/0050/18").addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful())
                        {  progressDialog.hide();
                            Toast.makeText(SelectFile.this, "File is uploaded successfully", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {progressDialog.hide();
                            Toast.makeText(SelectFile.this, "File is not uploaded successfully", Toast.LENGTH_SHORT).show();
                        }


                    }
                }).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {

                    }
                }).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {

                    }
                });

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressDialog.hide();
                Toast.makeText(SelectFile.this, "File is not uploaded successfully "+e, Toast.LENGTH_SHORT).show();


            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
         if(requestCode==9 && grantResults[0]==PackageManager.PERMISSION_GRANTED)
         {
            selectPDF();
         }
         else
         {
             Toast.makeText(SelectFile.this, "Please provide permission", Toast.LENGTH_SHORT).show();
         }
    }

    private void selectPDF() {
        Intent intent=new Intent();
        intent.setType("application/pdf");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,10);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Check whether a user has selected a file or not
        if(requestCode==10 && resultCode==RESULT_OK && data!=null)
        { selectedData.setText(data.getData().toString());
         pdfUri=data.getData(); //returns url of selected file

        }
        else
        {
            Toast.makeText(SelectFile.this, "Please select a file", Toast.LENGTH_SHORT).show();
        }



    }
}