package com.example.malava_constituency;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Register extends AppCompatActivity {
    Button submit,admissionLetter,studentID,fathersID,mothersID,feeStructure,birthCertificate;
    //Spinner schoolSpinner;
    FirebaseAuth auth;
    FirebaseUser firebaseUser;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    TextInputLayout firstName, otherName, admissionNo;
    DatePicker date1;
    Spinner ward, gender, formClass,schools;
    StudentModel studentModel;
    ProgressDialog progressDialog;
    String admissionPath;
    String studentIDPath;
    String fathersIDPath;
    String mothersPath;
    String feeStructurePath;
    String birthCertificatePath;
    Uri admissionLetterDoc=null;
    Uri studentIDDoc=null;
    Uri fathersIDDoc=null;
    Uri mothersDoc=null;
    Uri feeStructureDoc=null;
    Uri birthCertificateDoc=null;
    List<Uri> uri;
    List<String> downloadLinks;
    List<String> schoolSpy;
    Calendar calendar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        submit = findViewById(R.id.Submit);
        firstName = findViewById(R.id.editTextFname);
        otherName = findViewById(R.id.otherName);
        admissionNo = findViewById(R.id.regNumber);
        date1 = findViewById(R.id.date1);
        ward = findViewById(R.id.wardName);
        gender = findViewById(R.id.gender);
        formClass = findViewById(R.id.yearOfStudy);
        schools=findViewById(R.id.school);
        schoolSpy=new ArrayList<>();


        admissionLetter = findViewById(R.id.AdmissionLetter);
        studentID = findViewById(R.id.StudentID);
        fathersID = findViewById(R.id.FathersID);
        mothersID = findViewById(R.id.MothersID);
        feeStructure = findViewById(R.id.FeeStructure);
        //schoolsSpinner=findViewById(R.id.school);
        birthCertificate = findViewById(R.id.BirthCerificate);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Processing.....");

        downloadLinks=new ArrayList<>();

        schoolSpy.add("Kivaywa sec school");
        schoolSpy.add("Malava boys high");
        schoolSpy.add("Malava girls");
        schoolSpy.add("Malava girls");
        schoolSpy.add("Bukhakunga sec school");
        schoolSpy.add("Lugusi Sec school");
        schoolSpy.add("Butieli sec school");
        schoolSpy.add("Starehe boys high");
        schoolSpy.add("Kakamega high school");
        schoolSpy.add("Khasoko sec school");
        schoolSpy.add("Namikelo sec school");
        schoolSpy.add("Butali Junior");
        schoolSpy.add("Shivanga sec school");
        schoolSpy.add("Kimang'eti Girls");
        schoolSpy.add("Kimang'eti Boys");
        schoolSpy.add("Kibabii university");
        schoolSpy.add("Masinde Muliro niversity");
        schoolSpy.add("Maseno university");
        schoolSpy.add("Rongo university");
        schoolSpy.add("Alupe university");
        schoolSpy.add("University of Nairobi");
        schoolSpy.add("Kenyatta university");
        schoolSpy.add("Multimedia university");
        schoolSpy.add("Kirinyaga university");
        schoolSpy.add("Laikipia university");
        schoolSpy.add("Dedan Kimathi university");
        schoolSpy.add("Maasai mara university");
        schoolSpy.add("Strathmore university");
        schoolSpy.add("Kisii university");
        schoolSpy.add("Kaimos university");
        Calendar calendar3=Calendar.getInstance();
        ArrayAdapter<String>arrayAdapter=new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,schoolSpy);
        schools.setAdapter(arrayAdapter);
       // downloadLinks=new List(){" "};

        //  progressDialog.

        // progressDialog.setProgress(0);
        // progressDialog.setMax(100);







        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        admissionLetter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectDocumen(1);
               // uri.add(admissionLetterDoc);
            }
        });
        studentID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectDocumen(2);
                //uri.add(studentIDDoc);
            }
        });
        fathersID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectDocumen(3);
                //uri.add(fathersIDDoc);
            }
        });
        mothersID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectDocumen(4);
               // uri.add(mothersDoc);

            }
        });
        feeStructure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectDocumen(5);
                //uri.add(feeStructureDoc);
            }
        });
        birthCertificate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectDocumen(6);
              //  uri.add(birthCertificateDoc);

            }
        });



        submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                progressDialog.show();
                String firstName1 = firstName.getEditText().getText().toString().trim();
                String otherName1 = otherName.getEditText().getText().toString().trim();
                String admission1 = admissionNo.getEditText().getText().toString().trim();
                String day1 = String.valueOf(date1.getDayOfMonth());
                String months1 = String.valueOf(date1.getMonth());
                String year1 = String.valueOf(date1.getYear());
                String dateBorn = day1 + "/" + months1 + "/" + year1;
                String ward1 = ward.getSelectedItem().toString();
                String gender1 = gender.getSelectedItem().toString();
                String formClass1 = formClass.getSelectedItem().toString();
                String school1=schools.getSelectedItem().toString();
               // Uri admissionLetterDoc = null;
               // Uri studentIDDoc = null;
               // Uri fathersIDDoc = null;
               // Uri mothersDoc = null;
              //  Uri feeStructureDoc = null;
               // Uri birthCertificateDoc = null;



                //studentIDDoc=selectDocumen(10);


                firebaseUser = FirebaseAuth.getInstance().getCurrentUser();


//                Toast.makeText(getApplicationContext(), " "+firebaseUser, Toast.LENGTH_SHORT).show();
                if (firebaseUser == null) {
                    // Uri.
                    progressDialog.hide();
                    Toast.makeText(getApplicationContext(), "No user is signed in", Toast.LENGTH_SHORT).show();
                }
                    else if(firstName1.isEmpty() || otherName1.isEmpty() || admission1.isEmpty())
                    {
                        progressDialog.hide();
                        Toast.makeText(Register.this, "Some field are empty", Toast.LENGTH_SHORT).show();
                    }
                 else {

                        if (studentIDDoc == null || admissionLetterDoc == null || fathersIDDoc == null || mothersDoc == null || birthCertificateDoc == null || feeStructureDoc == null) {
                            progressDialog.hide();
                            Toast.makeText(Register.this, "Some documents are not selected", Toast.LENGTH_SHORT).show();

                        }


                        else
                        {



                    // firebaseUser.reload();
                    //Toast.makeText(getApplicationContext(), "User is signed in " + firebaseUser.getEmail().toString(), Toast.LENGTH_SHORT).show();
                    firebaseDatabase = FirebaseDatabase.getInstance();
                    FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
                    StorageReference storageReference = firebaseStorage.getReference();

                    DatabaseReference databaseReference = firebaseDatabase.getReference();
                            DatabaseReference databaseReference2 = firebaseDatabase.getReference();
                    databaseReference.child("LoanDetails").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            if (snapshot.hasChild(firebaseUser.getUid())) {

                                progressDialog.hide();
                                Toast.makeText(Register.this, "User has already applied", Toast.LENGTH_SHORT).show();
                              //  DataSnapshot s = snapshot.child(firebaseUser.getUid());
                               // Toast.makeText(Register.this, "Number of students = " + snapshot.getChildrenCount() + " " + snapshot.child(firebaseUser.getUid()).child("firstNname").getValue().toString() + " D.O.B= " + s.child("dateOf").getValue().toString(), Toast.LENGTH_SHORT).show();
                            } else {


                                    storageReference.child("Uploads").child(firebaseUser.getUid()).child("AdmissionLetter").putFile(admissionLetterDoc).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                                        @Override
                                        public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                                            if(task.isSuccessful())

                                            {  
                                                Toast.makeText(Register.this, "Admission letter submitted", Toast.LENGTH_SHORT).show();
                                                storageReference.child("Uploads").child(firebaseUser.getUid()).child("StudentID").putFile(studentIDDoc).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                                                        if(task.isSuccessful()) {
                                                           // progressDialog.hide();
                                                            Toast.makeText(Register.this, "Student ID submitted uri ", Toast.LENGTH_SHORT).show();
                                                            storageReference.child("Uploads").child(firebaseUser.getUid()).child("Fathers ID").putFile(fathersIDDoc).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                                                                @Override
                                                                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                                                                    if(task.isSuccessful())
                                                                    {//progressDialog.hide();
                                                                        Toast.makeText(Register.this, "Father's ID submitted", Toast.LENGTH_SHORT).show();
                                                                        storageReference.child("Uploads").child(firebaseUser.getUid()).child("Mothers ID").putFile(mothersDoc).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                                                                            @Override
                                                                            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                                                                              if(task.isSuccessful())  {
                                                                                  //progressDialog.hide();
                                                                                  Toast.makeText(Register.this, "Mothers's ID submitted", Toast.LENGTH_SHORT).show();
                                                                                  storageReference.child("Uploads").child(firebaseUser.getUid()).child("Fee structure").putFile(feeStructureDoc).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                                                                                      @Override
                                                                                      public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                                                                                          if(task.isSuccessful())
                                                                                          {
                                                                                            //  progressDialog.hide();
                                                                                              Toast.makeText(Register.this, "Fee structure submitted", Toast.LENGTH_SHORT).show();
                                                                                              storageReference.child("Uploads").child(firebaseUser.getUid()).child("Birth Certificate").putFile(birthCertificateDoc).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                                                                                                  @Override
                                                                                                  public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                                                                                                      if(task.isSuccessful())
                                                                                                      {//progressDialog.hide();

                                                                                                          Toast.makeText(Register.this, "Birth certificate submitted", Toast.LENGTH_SHORT).show();
                                                                                                          studentModel=new StudentModel(firstName1,otherName1,dateBorn,ward1,gender1,formClass1,admission1,school1,"","","","","","");
                                                                                                          //storageReference.child(firebaseUser.getUid()).
                                                                                                          databaseReference.child("Students").child(firebaseUser.getUid()).setValue(studentModel).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                                                              @Override
                                                                                                              public void onComplete(@NonNull Task<Void> task) {
                                                                                                                  if(task.isSuccessful())
                                                                                                                  { // progressDialog.dismiss();
                                                                                                                     // firebaseUser.getMetadata().getLastSignInTimestamp();
                                                                                                                      LoanDetails loanDetails=new LoanDetails(admission1,"Pending",0.0,calendar3.getTime().toString()  ,"Not applied");
                                                                                                                      databaseReference.child("LoanDetails").child(firebaseUser.getUid()).setValue(loanDetails).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                                                                          @Override
                                                                                                                          public void onComplete(@NonNull Task<Void> task) {
                                                                                                                              if(task.isSuccessful())
                                                                                                                              {
                                                                                                                                  progressDialog.dismiss();
                                                                                                                                  Toast.makeText(Register.this, "All details submitted", Toast.LENGTH_SHORT).show();
                                                                                                                              }
                                                                                                                              else
                                                                                                                              {progressDialog.dismiss();
                                                                                                                                  Toast.makeText(Register.this, "All details haven't been submitted "+ task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                                                                                                                              }
                                                                                                                          }
                                                                                                                      });

                                                                                                                  }
                                                                                                                  else
                                                                                                                  {
                                                                                                                      progressDialog.dismiss();
                                                                                                                      Toast.makeText(Register.this, "All details haven't been submitted "+ task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                                                                                                                  }

                                                                                                              }
                                                                                                          });
                                                                                                      }
                                                                                                      else
                                                                                                      {
                                                                                                          progressDialog.dismiss();
                                                                                                          Toast.makeText(Register.this, "Birth certificate not submitted", Toast.LENGTH_SHORT).show();
                                                                                                      }
                                                                                                  }
                                                                                              });
                                                                                          }
                                                                                          else
                                                                                          {
                                                                                              progressDialog.dismiss();
                                                                                              Toast.makeText(Register.this, "Fee structure not submitted", Toast.LENGTH_SHORT).show();
                                                                                          }

                                                                                      }
                                                                                  });
                                                                                }
                                                                              else
                                                                              { progressDialog.dismiss();
                                                                                  Toast.makeText(Register.this, "Mothers's ID not submitted", Toast.LENGTH_SHORT).show();

                                                                              }
                                                                            }
                                                                        });
                                                                    }
                                                                    else
                                                                    { progressDialog.dismiss();
                                                                        Toast.makeText(Register.this, "Father's ID not submitted", Toast.LENGTH_SHORT).show();

                                                                    }
                                                                }
                                                            });

                                                        }
                                                        else
                                                        {
                                                            progressDialog.dismiss();
                                                            Toast.makeText(Register.this, "Student ID not submitted ", Toast.LENGTH_SHORT).show();
                                                        }
                                                    }

                                                });
                                            //progressDialog.hide();

                                        }
                                            else
                                            {
                                                progressDialog.dismiss();
                                                Toast.makeText(Register.this, "Admission letter not submitted", Toast.LENGTH_SHORT).show();
                                                //progressDialog.hide();
                                            }
                                    }});








                            }}

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            progressDialog.dismiss();

                        }


                    });}}}});}

                        public void selectDocumen(int codeNumber)
    {
        Intent intent=new Intent();
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        startActivityForResult(intent,codeNumber);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1 && resultCode==RESULT_OK && data!=null)
        {
            admissionLetterDoc=data.getData();
            admissionLetter.setText("You have selected letter");
            admissionLetter.setBackgroundColor(Color.GREEN);
        }

        else if(requestCode==2 && resultCode==RESULT_OK && data!=null)
        {
            studentIDDoc=data.getData();
            studentID.setText("Student ID selected");
           studentID.setBackgroundColor(Color.GREEN);
        }
        else if(requestCode==3 && resultCode==RESULT_OK && data!=null)
        {
            fathersIDDoc=data.getData();
            fathersID.setText("Fathers ID selected");
            fathersID.setBackgroundColor(Color.GREEN);
        }
        else if(requestCode==4 && resultCode==RESULT_OK && data!=null)
        {
            mothersDoc=data.getData();
            mothersID.setText("Mothers Id selected");
            mothersID.setBackgroundColor(Color.GREEN);
        }
        else if(requestCode==5 && resultCode==RESULT_OK && data!=null)
        {
            feeStructureDoc=data.getData();
            feeStructure.setText("Fee structure selected");
            feeStructure.setBackgroundColor(Color.GREEN);
        }
        else if(requestCode==6 && resultCode==RESULT_OK && data!=null)
        {
            birthCertificateDoc=data.getData();
            birthCertificate.setText("Birth certificate selected");
            birthCertificate.setBackgroundColor(Color.GREEN);
        }

    }
}
