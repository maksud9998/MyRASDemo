package com.example.myrasdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Register extends AppCompatActivity {
    Button registerbtn,haveanaccountBtn;
    EditText firstName,lastName,email,mobileNo,password,licenseNo,aadharCard;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        registerbtn = findViewById(R.id.verify_Email);
        haveanaccountBtn = findViewById(R.id.already_account);

        firstName = findViewById(R.id.Reg_First_Name);
        lastName = findViewById(R.id.Reg_Last_Name);
        email  = findViewById(R.id.Reg_Email);
        mobileNo = findViewById(R.id.Reg_Mobile);
        password = findViewById(R.id.Reg_Password);
        licenseNo = findViewById(R.id.Reg_Licence_Number);
        aadharCard = findViewById(R.id.Reg_IDDetail);







        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Register.this,EmailVerify.class);
                startActivity(i);
            }
        });
        haveanaccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Register.this,Login.class);
                startActivity(i);
            }
        });


    }
}