package com.example.malava_constituency;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

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

public class Login extends AppCompatActivity {
//EditText email,password;
TextInputLayout email,password4,confirmPassword12;
//TextInputEditText password1;
    ProgressDialog progressDialog;
Button login;
private FirebaseAuth auth;
private FirebaseDatabase firebaseDatabase;
FirebaseUser firebaseUser;
TextView forgotPassword,signup;
private  int currentProgress;
ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Login page");
        login=findViewById(R.id.buttonLogin);
        email=findViewById(R.id.editTextEmailAddress);
        password4=findViewById(R.id.editTextLoginPassword);
        forgotPassword=findViewById(R.id.forgotPassword);
        confirmPassword12=findViewById(R.id.confirmPasssword);
        signup=findViewById(R.id.signup);
        progressBar=findViewById(R.id.signinProgressbar);
        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Processing.....");
progressDialog.setProgressStyle(7);
        //password1=findViewById(R.id.editPassword);
      //  email.getEditText().getText().toString().isEmpty();
       // password.getEditText().getText().toString().trim().isEmpty();
        login.setEnabled(false);


        //email.addTextChangedListener(textWatcher);
        email.getEditText().addTextChangedListener(textWatcher);
        password4.getEditText().addTextChangedListener(textWatcher);
        confirmPassword12.getEditText().addTextChangedListener(textWatcher);

       // login=findViewById(R.id.buttonLogin);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toApply=new Intent(getApplicationContext(),Login2.class);
                startActivity(toApply);
            }
        });
 login.setOnClickListener(new View.OnClickListener() {
     @Override
     public void onClick(View v) {

         String email2=email.getEditText().getText().toString().trim();
         String password1=password4.getEditText().getText().toString();
         String confirmPassword1=confirmPassword12.getEditText().getText().toString();

         if(!Patterns.EMAIL_ADDRESS.matcher(email.getEditText().getText().toString().trim()).matches() )
         {
                //progressDialog.dismiss();
             Toast.makeText(getApplicationContext(),"Email is in incorrect format",Toast.LENGTH_SHORT).show();


         }
         else if(!password1.equals(confirmPassword1))
         {
             Toast.makeText(getApplicationContext(),"Your passwords are not the same",Toast.LENGTH_SHORT).show();
         }




         else  {

                 //progressBar.setVisibility(View.VISIBLE);
             progressDialog.show();
                 auth = FirebaseAuth.getInstance();
                 auth.createUserWithEmailAndPassword(email2, password1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                     @Override
                     public void onComplete(@NonNull Task<AuthResult> task) {
                         if (task.isSuccessful()) { //auth.getCurrentUser().getMetadata().getLastSignInTimestamp();
                             progressDialog.dismiss();
                             // if(task.)
                             // FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser()).setValue(user)
                             Toast.makeText(getApplicationContext(), "User has registered successfully ", Toast.LENGTH_SHORT).show();
                             firebaseUser = task.getResult().getUser();
                             firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                 @Override
                                 public void onComplete(@NonNull Task<Void> task) {
                                     if (task.isSuccessful()) {
                                        // progressBar.setVisibility(View.INVISIBLE);
                                         progressDialog.dismiss();
                                         Toast.makeText(getApplicationContext(), "Email verification code has been send to your email", Toast.LENGTH_SHORT).show();
                                         //Toast.makeText(Login.this, "", Toast.LENGTH_SHORT).show();
                                         Intent next =new Intent(Login.this,Login2.class);
                                         startActivity(next);
                                         finish();

                                     } else {
                                        // progressBar.setVisibility(View.INVISIBLE);
                                         progressDialog.dismiss();
                                         Toast.makeText(getApplicationContext(), "Unable to send the email link " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                     }
                                 }
                             });
                         } else {
                             //progressBar.setVisibility(View.INVISIBLE);
                             progressDialog.dismiss();
                             Toast.makeText(getApplicationContext(), "Failed to register the user " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                         }
                     }
                 });
             }

     }
 });

    }
    private final TextWatcher textWatcher=new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            login.setEnabled(!email.getEditText().getText().toString().isEmpty() && !password4.getEditText().getText().toString().isEmpty() && !confirmPassword12.getEditText().getText().toString().isEmpty());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

}