package com.example.malava_constituency;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class ForgotPassword extends AppCompatActivity {
TextInputLayout textInputLayout;
FirebaseAuth firebaseAuth;
FirebaseUser firebaseUser;
ProgressDialog progressDialog;
//TextWatcher textWatcher;
    EditText email;
Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        textInputLayout=findViewById(R.id.editTextEmailAddress);
        submit=findViewById(R.id.submit);
        email=textInputLayout.getEditText();
       // firebaseUser=FirebaseAuth.getInstance().getCurrentUser();
        //email.setText(" "+firebaseUser.getEmail());
        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Processing.....");
        submit.setEnabled(false);

        email.addTextChangedListener(textWatcher);

        //  progressDialog.
        // progressDialog.setProgress(0);
        // progressDialog.setMax(100);

        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!Patterns.EMAIL_ADDRESS.matcher(email.getText().toString().trim()).matches())
                {
                    Toast.makeText(getApplicationContext(),"Email is in incorrect format",Toast.LENGTH_SHORT).show();
                }
                else{
                    progressDialog.show();
                FirebaseAuth.getInstance().sendPasswordResetEmail(email.getText().toString().trim()).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful())
                        {progressDialog.hide();
                            Toast.makeText(getApplicationContext(), "Reset link send to your gmail", Toast.LENGTH_SHORT).show();

                        }
                        else
                        {
                            progressDialog.hide();
                            Toast.makeText(getApplicationContext(), "Unable to reset the password "+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            }}
        });


    }
    private final TextWatcher textWatcher=new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
         if(email.getText().toString().isEmpty()) {
             Toast.makeText(ForgotPassword.this, "Email cannot be empty", Toast.LENGTH_SHORT).show();
             submit.setEnabled(false);
         }
         else
             submit.setEnabled(true);
        }

        @Override
        public void afterTextChanged(Editable s) {


        }
    };
}