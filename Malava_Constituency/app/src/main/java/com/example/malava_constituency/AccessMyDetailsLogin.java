package com.example.malava_constituency;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AccessMyDetailsLogin extends AppCompatActivity {

    //EditText email,password;
    TextInputLayout email, password;
    //TextInputEditText password1;
    Button login;
    private FirebaseAuth auth;
    private FirebaseDatabase firebaseDatabase;
    FirebaseUser firebaseUser;
    DatabaseReference databaseReference;
    ProgressDialog progressDialog;
    TextInputLayout textInputLayout;
    TextView textView, signup1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_access_my_details_login);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Login page");
        login = findViewById(R.id.buttonLogin);
        email = findViewById(R.id.editTextEmailAddress);
        password = findViewById(R.id.editTextLoginPassword);
        textView = findViewById(R.id.forgotPassword);
        signup1 = findViewById(R.id.signup);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Processing.....");
        //  progressDialog.
        // progressDialog.setProgress(0);
        // progressDialog.setMax(100);
        signup1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toApply = new Intent(getApplicationContext(), Login.class);
                startActivity(toApply);
            }
        });

        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        //password1=findViewById(R.id.editPassword);
        //  email.getEditText().getText().toString().isEmpty();
        // password.getEditText().getText().toString().trim().isEmpty();
        login.setEnabled(false);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ForgotPassword.class);
                startActivity(intent);
            }
        });

        //email.addTextChangedListener(textWatcher);
        email.getEditText().addTextChangedListener(textWatcher);
        password.getEditText().addTextChangedListener(textWatcher);

        // ProgressBar progressBar=new ProgressBar(getApplicationContext());
        // progressBar.setIndeterminate(true);
        // progressBar.setAccessibilityPaneTitle("Obtaining");
        //  progressBar.setVisibility(View.INVISIBLE);
//FirebaseAuth.getInstance().create


        // login=findViewById(R.id.buttonLogin);
        login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                //  progressBar.setVisibility(View.VISIBLE);
                String email2 = email.getEditText().getText().toString().trim();
                String password1 = password.getEditText().getText().toString();
                if (!Patterns.EMAIL_ADDRESS.matcher(email.getEditText().getText().toString().trim()).matches()) {//progressBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(getApplicationContext(), "Email is in incorrect format", Toast.LENGTH_SHORT).show();

                } else {
                    firebaseDatabase=FirebaseDatabase.getInstance();
                   databaseReference=firebaseDatabase.getReference("LoanDetails");
                    progressDialog.show();
                    auth = FirebaseAuth.getInstance();
                    auth.signInWithEmailAndPassword(email2, password1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful())


                            // firebaseUser=task.getResult().getUser();

                            { //auth.getCurrentUser().getMetadata().getLastSignInTimestamp();
                                //progressBar.setVisibility(View.INVISIBLE);
                                // Toast.makeText(getApplicationContext(),"Signed in correctly",Toast.LENGTH_SHORT).show();

                                //firebaseUser=task.getResult().getUser();
                                firebaseUser = auth.getCurrentUser();
                                if (firebaseUser.isEmailVerified()) {//progressBar.setVisibility(View.INVISIBLE);
                                    {
                                        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                if(snapshot.hasChild(firebaseUser.getUid()))
                                                { progressDialog.dismiss();
                                                    Toast.makeText(AccessMyDetailsLogin.this, "Login successful", Toast.LENGTH_SHORT).show();
                                                   Intent intent=new Intent(getApplicationContext(),Student_Profile.class);
                                                   startActivity(intent);
                                                   finish();
                                                }
                                                else
                                                {  progressDialog.dismiss();
                                                    Toast.makeText(AccessMyDetailsLogin.this, "You have not applied for loan", Toast.LENGTH_SHORT).show();
                                                }
                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError error) {
                                                progressDialog.dismiss();
                                                Toast.makeText(AccessMyDetailsLogin.this, "Database error", Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                   // progressDialog.hide();
                                    // String d= Long.parseLong(firebaseUser.getMetadata().getLastSignInTimestamp());
                                    //Calendar calendar=Calendar.getInstance(Locale.ENGLISH);
                                    //calendar.setTimeInMillis(d*1000);
                                    // Long.parseLong(calendar.getTimeInMillis()*d*1000);
                                    // Date y=calendar.getTime();

                                    // String date=Long.parseLong( DateFormat.format("dd-MM-yyyy",calendar));
                                    // String date= DateFormat.format('dd-MM-yyyy',calendar);
                                    // date.t

                                    //Toast.makeText(getApplicationContext(), "Email is verified", Toast.LENGTH_SHORT).show();
                                   // Intent intent = new Intent(getApplicationContext(), Register.class);
                                   // startActivity(intent);
                                   // finish();
                                        }

                                } else {
                                    auth.signOut();
                                    progressDialog.dismiss();
                                    Toast.makeText(getApplicationContext(), "You have not been verified ", Toast.LENGTH_SHORT).show();
                                }
                                // FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser()).setValue(user)
                                //Toast.makeText(getApplicationContext(),"User has registered successfully ",Toast.LENGTH_SHORT).show();

                            } else { //progressBar.setVisibility(View.INVISIBLE);
                                progressDialog.hide();
                                Toast.makeText(getApplicationContext(), "Unable to log in" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });


                }
            }
        });

    }

    private final TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            login.setEnabled(!email.getEditText().getText().toString().isEmpty() && !password.getEditText().getText().toString().trim().isEmpty());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

}