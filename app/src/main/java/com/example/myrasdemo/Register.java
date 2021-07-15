package com.example.myrasdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class Register extends AppCompatActivity {
    EditText first_name, last_name,email,phone_no1, password, license_no;
    Button registerbtn;
    TextView loginlink;
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
                String strfirst_name, strlast_name, str_email, str_phoneno1, str_password, str_licence_no;
                strfirst_name = first_name.getText().toString();
                strlast_name = last_name.getText().toString();
                str_email = email.getText().toString();
                str_phoneno1 = phone_no1.getText().toString();
                str_password = password.getText().toString();
                str_licence_no = license_no.getText().toString();
                if (!strfirst_name.equals("") && !strlast_name.equals("") && !str_email.equals("") && !str_phoneno1.equals("") && !str_password.equals("") && !str_licence_no.equals(""))
                {
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            String[] field = new String[6];
                            field[0] = "first_name";
                            field[1] = "last_name";
                            field[2] = "email";
                            field[3] = "phone_no1";
                            field[4] = "password";
                            field[5] = "license_no";
                            //Creating array for data
                            String[] data = new String[6];
                            data[0] = strfirst_name;
                            data[1] = strlast_name;
                            data[2] = str_email;
                            data[3] = str_phoneno1;
                            data[4] = str_password;
                            data[5] = str_licence_no;
                            //Repelace The IP Address In The Following url With Your PC IP Address
                            //Find Your PC IP Address By Writing ipconfig In CMD
                            PutData putData = new PutData("http://192.168.1.222/LoginRegister/signup.php", "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    String result = putData.getResult();
                                    if (result.equals("Sign Up Success"))
                                    {
                                        Toast.makeText(Register.this,"Successfully Registered To Rent-A-Savari",Toast.LENGTH_SHORT).show();
                                        Intent i = new Intent(Register.this,Login.class);
                                        startActivity(i);
                                        finish();
                                    }
                                    else
                                    {
                                        Toast.makeText(Register.this,result,Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        }
                    });
                }
                else
                {
                    Toast.makeText(Register.this,"All Fields Are Required",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}