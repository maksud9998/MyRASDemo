package com.example.myrasdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {
    EditText first_name, last_name,email,phone_no1, password, license_no;
    Button registerbtn;
    TextView loginlink;
    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        first_name = findViewById(R.id.Reg_First_Name);
        last_name = findViewById(R.id.Reg_Last_Name);
        email = findViewById(R.id.Reg_Email);
        phone_no1 = findViewById(R.id.Reg_Mobile);
        password = findViewById(R.id.Reg_Password);
        license_no = findViewById(R.id.Reg_Licence_Number);
        registerbtn = findViewById(R.id.Register);
        loginlink = findViewById(R.id.loginlink);
        loginlink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Register.this,Login.class);
                startActivity(i);
                finish();
            }
        });
        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str_first_name, str_last_name, str_email, str_phoneno1, str_password, str_licence_no;
                str_first_name = first_name.getText().toString();
                str_last_name = last_name.getText().toString();
                str_email = email.getText().toString();
                str_phoneno1 = phone_no1.getText().toString();
                str_password = password.getText().toString();
                str_licence_no = license_no.getText().toString();
                if (!str_first_name.equals("") && !str_last_name.equals("") && !str_email.equals("") && !str_phoneno1.equals("") && !str_password.equals("") && !str_licence_no.equals(""))
                {
                    rootNode = FirebaseDatabase.getInstance();
                    reference = rootNode.getReference("user_M");
                    UserHelperClass helperClass = new UserHelperClass(str_first_name,str_last_name,str_email,str_phoneno1,str_password,str_licence_no);
                    reference.child(str_phoneno1).setValue(helperClass);
                    Toast.makeText(Register.this,"Successfully Registered To Rent-A-Savari",Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(Register.this,Login.class);
                    startActivity(i);
                    finish();
                }
                else
                {
                    Toast.makeText(Register.this,"All Fields Are Required",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}