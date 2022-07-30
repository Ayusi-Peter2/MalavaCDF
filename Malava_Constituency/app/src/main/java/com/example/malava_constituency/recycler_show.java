package com.example.malava_constituency;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;
import android.widget.HorizontalScrollView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class recycler_show extends AppCompatActivity {
    //FirebaseUser firebaseUser;
   // FirebaseAuth firebaseAuth;
    //List<Student> stud;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    List<Student> students;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toast.makeText(this, "Here we are", Toast.LENGTH_SHORT).show();
        setContentView(R.layout.activity_recycler_show);
        RecyclerView recyclerView=findViewById(R.id.recycle);
       // Toast.makeText(this, "Here we are", Toast.LENGTH_SHORT).show();
        students=new ArrayList<>();


        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference();
        Toast.makeText(this, "Here we are", Toast.LENGTH_SHORT).show();




       /* Student[] students=new Student[]
                {
                        new Student("Ayusi Peter","Computer Science","Kibabii University"),
                        new Student("Joseph Ali","Computer Science","Kibabii University"),
                        new Student("Monica Juma","Computer Science","Kibabii University"),
                        new Student("Isabella Kwendo","Computer Science","Kibabii University"),
                        new Student("Boyi Ali","Computer Science","Kibabii University"),
                        new Student("Moses","Computer Science","Kibabii University"),
                        new Student("Getry","Computer Science","Kibabii University"),
                        new Student("Kamau","Computer Science","Kibabii University"),
                        new Student("Godrine","Computer Science","Kibabii University"),
                        new Student("Mercy","Computer Science","Kibabii University"),
                        new Student("Wairimor","Computer Science","Kibabii University")

                };*/


        databaseReference.child("Students").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                //Toast.makeText(recycler_show.this, "Number of students "+snapshot.getChildrenCount(), Toast.LENGTH_SHORT).show();

                for(DataSnapshot data:snapshot.getChildren())
                {

                    String myName=data.child("firstName").getValue().toString().trim();
                    String mySchoolName=data.child("schoolName").getValue().toString().trim();
                    String regNumber1=data.child("admissionNumber").getValue().toString().trim();

                    Student student1=new Student(myName,regNumber1,mySchoolName);
                    students.add(student1);


                    // Toast.makeText(Student_Profile.this, " Name "+studentListFirebaseList., Toast.LENGTH_SHORT).show();

                    //String firstname=data.child("");

                //   for(StudentListFirebase student:studentListFirebaseList)
                //  {
                //System.e
                //StudentListFirebase studentListFirebase=students.get(0);
                //Toast.makeText(Student_Profile.this, "Name: "+studentListFirebase.getFirstName()+" School "+studentListFirebase.getSchoolName()+" Reg No: "+studentListFirebase.getAdmissionNumber(), Toast.LENGTH_SHORT).show();
                //  }
                //Toast.makeText(Student_Profile.this, "Name "+, Toast.LENGTH_SHORT).show();
           }Toast.makeText(recycler_show.this, "Ok "+students.get(1).getName(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
       });
        //students.add(new Student("Ayusi Peter","Computer Science","Kibabii University"));
        //students.add(new Student("Joseph Ali","Computer Science","Kibabii University"));
                //Student student2=new Student("Joseph Ali","Computer Science","Kibabii University");
                //students.add(student2);
                //students.add(new Student("Monica Juma","Computer Science","Kibabii University"));

        MyCustomAdapter myCustomAdapter=new MyCustomAdapter(students);
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myCustomAdapter);
    }
}