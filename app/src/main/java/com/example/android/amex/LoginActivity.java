package com.example.android.amex;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    EditText email,password;
    Button button;
    Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Intent intent=getIntent();
        bundle=intent.getExtras();
        email = (EditText)findViewById(R.id.email);
        password = (EditText)findViewById(R.id.password);
        button = (Button)findViewById(R.id.submit);

        mAuth = FirebaseAuth.getInstance();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });

    }

    public void registerUser(){
        final String emailId = email.getEditableText().toString().trim();
        String pass = password.getEditableText().toString().trim();

        if (emailId.isEmpty()){
            email.setError("Enter a valid email");
            email.requestFocus();
            return;
        }
//
//        if(!Patterns.EMAIL_ADDRESS.matcher(emailId).matches()){
//            email.setError("");
//            email.requestFocus();
//            return;
//        }

        if (pass.isEmpty()){
            password.setError("Enter a password");
            password.requestFocus();
            return;
        }

        if (pass.length()<6){
            password.setError("Minimum length required: 6");
            password.requestFocus();
            return;
        }

        mAuth.createUserWithEmailAndPassword(emailId,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(LoginActivity.this,"User registered successfully",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(LoginActivity.this,OptionsActivity.class);
                    intent.putExtra("NAME",emailId);
                    intent.putExtras(bundle);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
                else{
                    if (task.getException() instanceof FirebaseAuthUserCollisionException){
                        Toast.makeText(getApplicationContext(),"You are already registered",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }


//    @Override
//    public void onStart() {
//        super.onStart();
//        mAuth.addAuthStateListener(mAuthListener);
//    }
//    @Override
//    public void onStop() {
//        super.onStop();
//        if (mAuthListener != null) {
//            mAuth.removeAuthStateListener(mAuthListener);
//        }
//    }
}