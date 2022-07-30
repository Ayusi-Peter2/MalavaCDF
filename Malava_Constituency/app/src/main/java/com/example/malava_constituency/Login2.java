package com.example.malava_constituency;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import java.lang.Exception;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import com.github.ybq.android.spinkit.SpinKitView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class Login2 extends AppCompatActivity {
    //EditText email,password;
    TextInputLayout email,password;
    //TextInputEditText password1;
    Button login;
    private FirebaseAuth auth;
    private FirebaseDatabase firebaseDatabase;
    FirebaseUser firebaseUser;
    ProgressDialog progressDialog;
    TextInputLayout textInputLayout;
    TextView textView,signup1;
    private SpinKitView spinKitView;
    AlertDialog.Builder builder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Login page");
        login=findViewById(R.id.buttonLogin);
        email=findViewById(R.id.editTextEmailAddress);
        password=findViewById(R.id.editTextLoginPassword);
        textView=findViewById(R.id.forgotPassword);
        signup1=findViewById(R.id.signup);
        progressDialog=new ProgressDialog(this);
        spinKitView=findViewById(R.id.spinTester);
        builder=new AlertDialog.Builder(Login2.this);
       // Snackbar snackbar=new Snackbar.Callback();
        spinKitView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
spinKitView.setVisibility(View.INVISIBLE);
spinKitView.setActivated(false);
//getApplicationContext().getPackageName();
getApplication().onTerminate();
//setFinishOnTouchOutside(true);
//spinKitView.setActivated(false);

            }
        });


       // progressDialog.setMessage("Processing.....");
      //  progressDialog.
       // progressDialog.setProgress(0);
       // progressDialog.setMax(100);
        signup1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toApply=new Intent(getApplicationContext(),Login.class);
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
                Intent intent=new Intent(getApplicationContext(),ForgotPassword.class);
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
                String email2=email.getEditText().getText().toString().trim();
                String password1=password.getEditText().getText().toString();
                if(!Patterns.EMAIL_ADDRESS.matcher(email.getEditText().getText().toString().trim()).matches())
                {//progressBar.setVisibility(View.INVISIBLE);
                    Snackbar.make(login,"Email is in incorrect format",Snackbar.LENGTH_SHORT).show();
                    //Toast.makeText(getApplicationContext(),"Email is in incorrect format",Toast.LENGTH_SHORT).show();

                }


                {//progressDialog.show();
                    //builder.setTitle("Processing");
                    //builder.setMessage(spinKitView.setVisibility(View.VISIBLE));
                    spinKitView.setVisibility(View.VISIBLE);
                    auth=FirebaseAuth.getInstance();
                auth.signInWithEmailAndPassword(email2,password1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())


                       // firebaseUser=task.getResult().getUser();

                        { //auth.getCurrentUser().getMetadata().getLastSignInTimestamp();
                            //progressBar.setVisibility(View.INVISIBLE);
                           // Toast.makeText(getApplicationContext(),"Signed in correctly",Toast.LENGTH_SHORT).show();

                            //firebaseUser=task.getResult().getUser();
                           firebaseUser= auth.getCurrentUser();
                           if(firebaseUser.isEmailVerified())

                           {//progressBar.setVisibility(View.INVISIBLE);
                              // spinKitView.setVisibility(View.VISIBLE);
                               spinKitView.setVisibility(View.INVISIBLE);
                             //  progressDialog.hide();
                            // String d= Long.parseLong(firebaseUser.getMetadata().getLastSignInTimestamp());
                               //Calendar calendar=Calendar.getInstance(Locale.ENGLISH);
                               //calendar.setTimeInMillis(d*1000);
                              // Long.parseLong(calendar.getTimeInMillis()*d*1000);
                              // Date y=calendar.getTime();

                              // String date=Long.parseLong( DateFormat.format("dd-MM-yyyy",calendar));
                              // String date= DateFormat.format('dd-MM-yyyy',calendar);
                              // date.t
                               Snackbar.make(login,"Email is verified",Snackbar.LENGTH_SHORT).show();
                              // Toast.makeText(getApplicationContext(),"Email is verified",Toast.LENGTH_SHORT).show();
                               Intent intent=new Intent(getApplicationContext(),Register.class);
                               startActivity(intent);
                              finish();


                           }
                           else {
                               auth.signOut();
                               int y;
                               spinKitView.setVisibility(View.INVISIBLE);
                               //progressDialog.dismiss();
                               Snackbar.make(login,"You have not been verified ",Snackbar.LENGTH_SHORT).show();

                               // Toast.makeText(getApplicationContext(), "You have not been verified ", Toast.LENGTH_SHORT).show();
                           }
                            // FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser()).setValue(user)
                            //Toast.makeText(getApplicationContext(),"User has registered successfully ",Toast.LENGTH_SHORT).show();

                        }
                        else
                        { //progressBar.setVisibility(View.INVISIBLE);
                            //spinKitView
                            spinKitView.setVisibility(View.INVISIBLE);
                           // progressDialog.hide();
                            Snackbar.make(login,"Failed to register the user "+task.getException().getMessage(),Snackbar.LENGTH_SHORT).show();
                           // Toast.makeText(getApplicationContext(),"Failed to register the user "+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });



        }}}  );

    }
    private final TextWatcher textWatcher=new TextWatcher() {
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