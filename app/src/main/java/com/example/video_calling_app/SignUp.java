package com.example.video_calling_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class SignUp extends AppCompatActivity {

    TextInputEditText fullname, email, password, cpassword;
    Button signupBtn;
    TextView login;
    ImageView backbutton;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        getSupportActionBar().hide();
        firebaseAuth = FirebaseAuth.getInstance();
        database = FirebaseFirestore.getInstance();

        fullname = findViewById(R.id.name_txt);
        email = findViewById(R.id.RegisterEmail_txt);
        password = findViewById(R.id.RegisterPassword_txt);
        cpassword = findViewById(R.id.FConfpsw_text);

        signupBtn = findViewById(R.id.Signup_btn);
        login = findViewById(R.id.Reg_loginBtn);
        backbutton = findViewById(R.id.BackButton_btn);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignUp.this, Login.class));
            }
        });


        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUp.this, Login.class);
                startActivity(intent);
            }
        });

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name, emailadd, pass, cpass;

                emailadd = email.getText().toString();
                pass = password.getText().toString();
                cpass = cpassword.getText().toString();
                name = fullname.getText().toString();

                User user = new User();
                user.setEmail(emailadd);
                user.setPass(pass);
                user.setCpass(cpass);
                user.setName(name);

                firebaseAuth.createUserWithEmailAndPassword(emailadd, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            database.collection("Users")
                                    .document().set(user)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            Intent intent = new Intent(SignUp.this, Login.class);
                                            startActivity(intent);
                                        }
                                    });
                            Toast.makeText(SignUp.this, "Account is Created", Toast.LENGTH_SHORT).show();
                            //success
                        } else {
                            Toast.makeText(SignUp.this, task.getException().getLocalizedMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });

            }
        });
    }
}