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

public class Login extends AppCompatActivity {
    EditText email, password;
    Button loginbtn;
    TextView registerlink;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email = findViewById(R.id.email);
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
                String str_email, str_password;
                str_email = email.getText().toString();
                str_password = password.getText().toString();
                if (!str_email.equals("") && !str_password.equals(""))
                {
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            String[] field = new String[2];
                            field[0] = "email";
                            field[1] = "password";
                            String[] data = new String[2];
                            data[0] = str_email;
                            data[1] = str_password;
                            //Repelace The IP Address In The Following url With Your PC IP Address
                            //Find Your PC IP Address By Writing ipconfig In CMD
                            PutData putData = new PutData("http://192.168.1.222/LoginRegister/login.php", "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    String result = putData.getResult();
                                    if (result.equals("Login Success"))
                                    {
                                        Toast.makeText(Login.this,"Welcome To Rent-A-Savari",Toast.LENGTH_SHORT).show();
                                        Intent i = new Intent(Login.this,MainActivity.class);
                                        startActivity(i);
                                        finish();
                                    }
                                    else
                                    {
                                        Toast.makeText(Login.this,result,Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
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