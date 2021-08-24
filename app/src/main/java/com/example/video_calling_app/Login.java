package com.example.video_calling_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    TextInputEditText emailadd, password;
    TextView forgetpassword, registernow;
    Button login;
    FirebaseAuth firebaseAuth;
    ProgressDialog progressDialog;

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle("Exit")
                .setCancelable(false)
                .setMessage("Do you really want to exit?")

                .setPositiveButton("Yes Exit", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        finish();
                    }
                })
                .setNegativeButton("No Continue", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //jwenfejrn
                    }
                });
        builder.show();

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait...");

        firebaseAuth = FirebaseAuth.getInstance();
        emailadd = findViewById(R.id.Email_txt);
        password = findViewById(R.id.Paswword_txt);
        forgetpassword = findViewById(R.id.ForgetPassword_txt);
        registernow = findViewById(R.id.RegistrNow_txt);
        login = findViewById(R.id.Login_btn);

        registernow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, SignUp.class);
                startActivity(intent);
            }
        });

        forgetpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, SignUp.class);
                startActivity(intent);
            }
        });


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.show();
                String email, pswrd;
                email = emailadd.getText().toString();
                pswrd = password.getText().toString();

                firebaseAuth.signInWithEmailAndPassword(email, pswrd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful()) {
                            Intent intent = new Intent(Login.this, Dashboard_Activity.class);
                            startActivity(intent);
                            Toast.makeText(Login.this, "Logged in", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(Login.this, task.getException().getLocalizedMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });


    }
}