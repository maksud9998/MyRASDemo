package com.example.myrasdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {
    EditText phone_no1, password;
    Button loginbtn;
    TextView registerlink;
    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("user_M");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        phone_no1 = findViewById(R.id.phone_no1);
        password = findViewById(R.id.passwd_otp);
        loginbtn = findViewById(R.id.loginbtn);
        registerlink = findViewById(R.id.registerlink);
        registerlink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login.this,Register.class);
                startActivity(i);
                finish();
            }
        });
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str_phone_no1, str_password;
                str_phone_no1 = phone_no1.getText().toString();
                str_password = password.getText().toString();
                if (!str_phone_no1.equals("") && !str_password.equals(""))
                {
                    Query checkUser = reference.orderByChild("phoneno1").equalTo(str_phone_no1);
                    checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.exists())
                            {
                                String passwordDB = snapshot.child(str_phone_no1).child("password").getValue(String.class);
                                if (passwordDB.equals((str_password)))
                                {
                                    String str_first_name = snapshot.child(str_phone_no1).child("first_name").getValue(String.class);
                                    String str_last_name = snapshot.child(str_phone_no1).child("last_name").getValue(String.class);
                                    String str_phone_no2 = snapshot.child(str_phone_no1).child("phoneno2").getValue(String.class);
                                    String str_email = snapshot.child(str_phone_no1).child("email").getValue(String.class);
                                    String str_licence_no = snapshot.child(str_phone_no1).child("licence_no").getValue(String.class);
                                    String str_address = snapshot.child(str_phone_no1).child("address").getValue(String.class);
                                    String str_area = snapshot.child(str_phone_no1).child("area").getValue(String.class);
                                    String str_city = snapshot.child(str_phone_no1).child("city").getValue(String.class);
                                    String str_state = snapshot.child(str_phone_no1).child("state").getValue(String.class);
                                    String str_pincode = snapshot.child(str_phone_no1).child("pincode").getValue(String.class);
                                    Toast.makeText(Login.this,"Welcome To Rent-A-Savari "+str_first_name,Toast.LENGTH_SHORT).show();
                                    Intent i = new Intent(Login.this,MainActivity.class);
                                    i.putExtra("first_name",str_first_name);
                                    i.putExtra("last_name",str_last_name);
                                    i.putExtra("phoneno1",str_phone_no1);
                                    i.putExtra("phoneno2",str_phone_no2);
                                    i.putExtra("email",str_email);
                                    i.putExtra("password",str_password);
                                    i.putExtra("licence_no",str_licence_no);
                                    i.putExtra("address",str_address);
                                    i.putExtra("area",str_area);
                                    i.putExtra("city",str_city);
                                    i.putExtra("state",str_state);
                                    i.putExtra("pincode",str_pincode);
                                    startActivity(i);
                                    finish();
                                }
                                else
                                {
                                    Toast.makeText(Login.this,"Password Incorrect",Toast.LENGTH_SHORT).show();
                                }
                            }
                            else
                            {
                                Toast.makeText(Login.this,"Mobile Number Incorrect",Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
                else
                {
                    Toast.makeText(Login.this,"All Fields Are Required",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}