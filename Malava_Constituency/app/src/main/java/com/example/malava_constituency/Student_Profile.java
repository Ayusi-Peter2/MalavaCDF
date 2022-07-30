package com.example.malava_constituency;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.rpc.context.AttributeContext;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Student_Profile extends AppCompatActivity {
    Button logOut,applySubsequent;
    FirebaseUser firebaseUser;
    FirebaseAuth firebaseAuth;
    ProfileInformation profileInformation;
    List<StudentListFirebase> studentListFirebaseList;
    FirebaseDatabase firebaseDatabase;
    TextView firstName,regNumber,schoolName,lastlogindate,approvalStatus,allocatedAmount,subsequent,textView40,allocatedTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_profile);
        firstName=findViewById(R.id.myName);
        regNumber=findViewById(R.id.regNumber);
        schoolName=findViewById(R.id.schoolName);
        lastlogindate=findViewById(R.id.lastLoginDate);
        approvalStatus=findViewById(R.id.approvalStatus);
        allocatedAmount=findViewById(R.id.allocatedAmount);
        subsequent=findViewById(R.id.subsequentStatus);
        textView40=findViewById(R.id.textView40);
        applySubsequent=findViewById(R.id.applySubsequent);
        allocatedTextView=findViewById(R.id.textView22);

        //firebaseUser=firebaseAuth.getCurrentUser();
        firebaseAuth=FirebaseAuth.getInstance();
        logOut=findViewById(R.id.logOut);
        firebaseUser=firebaseAuth.getCurrentUser();
        studentListFirebaseList=new ArrayList<>();
       // firebaseUser=FirebaseUser.g
        firebaseDatabase=FirebaseDatabase.getInstance();

        DatabaseReference databaseReference=firebaseDatabase.getReference();
        Calendar calendar=Calendar.getInstance();





        databaseReference.child("Students").child(firebaseUser.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists())
                {
//Calendar calendar=Calendar.getInstance();
                    //Toast.makeText(Student_Profile.this, " "+calendar., Toast.LENGTH_SHORT).show();
                    //DataSnapshot dataSnapshot=snapshot.child(firebaseUser.getUid());
                    //DataSnapshot assts=snapshot.

                   firstName.setText(snapshot.child("firstName").getValue().toString().trim());
                   schoolName.setText(snapshot.child("schoolName").getValue().toString().trim());
                   regNumber.setText(snapshot.child("admissionNumber").getValue().toString().trim());




                //DataSnapshot dataSnapshot=snapshot.getValue();
                    databaseReference.child("LoanDetails").child(firebaseUser.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            lastlogindate.setText(snapshot.child("lastLoginDate").getValue().toString().trim());
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                    databaseReference.child("LoanDetails").child(firebaseUser.getUid()).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                           // DataSnapshot data=snapshot.child(firebaseUser.getUid());
                           // lastlogindate.setText(snapshot.child("lastLoginDate").getValue().toString().trim());
                           //lastlogindate.setText(snapshot.child("lastLoginDate").getValue().toString().trim());
                           // lastlogindate.
                            //lastlogindate.setText(calendar.getTime().toString());
                            databaseReference.child("LoanDetails").child(firebaseUser.getUid()).child("lastLoginDate").setValue(calendar.getTime().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override

                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful())
                                    {

                                    }
                                   // Toast.makeText(Student_Profile.this, "", Toast.LENGTH_SHORT).show();
                                }
                            });
                        //lastlogindate.setText(snapshot.child("lastLoginDate").getValue().toString());
                            String appr=snapshot.child("approved").getValue().toString().trim();
                            String subsequentState=snapshot.child("subsequentApplied").getValue().toString().trim();
                            subsequent.setText(subsequentState);
                            if(appr.equalsIgnoreCase("Pending".trim()))
                            {
                               // subsequent.setText(data.child("subsequentApplied").getValue().toString());
                                subsequent.setVisibility(View.INVISIBLE);
                                textView40.setVisibility(View.INVISIBLE);
                                applySubsequent.setVisibility(View.INVISIBLE);
                                allocatedAmount.setVisibility(View.INVISIBLE);
                                allocatedTextView.setVisibility(View.INVISIBLE);

                            }
                            else
                            { //subsequent=subsequentState;
                                subsequent.setVisibility(View.VISIBLE);
                                textView40.setVisibility(View.VISIBLE);
                                applySubsequent.setVisibility(View.VISIBLE);
                                allocatedAmount.setVisibility(View.VISIBLE);
                                allocatedTextView.setVisibility(View.VISIBLE);
                                //subsequent.setText(subsequentState);
                                //String subsequent.setText();
                                applySubsequent.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        if(subsequentState.equalsIgnoreCase("Applied".trim()))
                                        {
                                            Toast.makeText(Student_Profile.this, " "+firstName.getText().toString()+",You have applied", Toast.LENGTH_SHORT).show();
                                           // Toast.makeText(Student_Profile.this, " "+firstName.getText().toString()+",You have applied", Toast.LENGTH_SHORT).show();
                                        }
                                        else
                                        { databaseReference.child("LoanDetails").child(firebaseUser.getUid()).child("subsequentApplied").setValue("Applied").addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if(task.isSuccessful())
                                                {
                                                Intent intent=new Intent(getApplicationContext(),Subsequent_Success.class);
                                                startActivity(intent);
                                                }
                                                else
                                                {
                                                    Toast.makeText(Student_Profile.this, "Unable to apply for subsequent", Toast.LENGTH_SHORT).show();
                                                }

                                            }
                                        });
                                          //  Toast.makeText(Student_Profile.this, "", Toast.LENGTH_SHORT).show();

                                        }

                                    }
                                });

                            }

                            allocatedAmount.setText(snapshot.child("amount").getValue().toString().trim());
                            //if(data.child("approved").getValue().toString()==false)
                            approvalStatus.setText(snapshot.child("approved").getValue().toString().trim());





                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(Student_Profile.this, "Error reading from Db "+error.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    });

            }
                else
                {
                    Toast.makeText(Student_Profile.this, "No data for the user", Toast.LENGTH_SHORT).show();
                }


        }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
                                                                              }
        );

        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(firebaseUser==null)
                {
                    Toast.makeText(getApplicationContext(), "No user currently in", Toast.LENGTH_SHORT).show();
                }
               // firebase
             else
                {
                  firebaseAuth.signOut();
                    Toast.makeText(Student_Profile.this, "logged out successfuly", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
            }
        });

    }

}