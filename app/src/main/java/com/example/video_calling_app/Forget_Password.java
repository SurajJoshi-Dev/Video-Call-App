package com.example.video_calling_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.material.textfield.TextInputEditText;

public class Forget_Password extends AppCompatActivity {
    ImageView backbutton;
    TextInputEditText emailid, password, confpass;
    Button resetpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget__password);
        getSupportActionBar().hide();

        emailid = findViewById(R.id.Email_txt);
        password = findViewById(R.id.Paswword_txt);
        confpass = findViewById(R.id.FConfpsw_text);
        resetpassword = findViewById(R.id.ResetPswrd_btn);

        resetpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Forget_Password.this, Login.class);
                startActivity(intent);
            }
        });


        backbutton = findViewById(R.id.BackButton_btn);
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Forget_Password.this, Login.class);
                startActivity(intent);
            }
        });

    }
}