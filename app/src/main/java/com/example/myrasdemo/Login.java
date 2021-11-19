package com.example.myrasdemo;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.Task;
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
    ProgressDialog progressDialog;
    String encryptedPassword = "", decryptedPassword = "";
    private static int timeOut=500;
    String str_first_name, str_last_name, str_profile_image, str_phone_no1, str_phone_no2, str_password, str_email, str_licence_no, str_address_proof_no, str_address, str_area, str_city, str_state, str_pincode, str_utype;
    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("user_M");
    Integer key=1;
    Character ch;
    Integer i;
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
                progressDialog = new ProgressDialog(Login.this);
                progressDialog.show();
                progressDialog.setContentView(R.layout.progress_dialog);
                progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        str_phone_no1 = phone_no1.getText().toString();
                        str_password = password.getText().toString();

                        for(i = 0; i < str_password.length(); ++i){
                            ch = str_password.charAt(i);
                            if(ch >= 'a' && ch <= 'z'){
                                ch = (char)(ch + key);
                                if(ch > 'z'){
                                    ch = (char)(ch - 'z' + 'a' - 1);
                                }
                                encryptedPassword += ch;
                            }
                            else if(ch >= 'A' && ch <= 'Z'){
                                ch = (char)(ch + key);
                                if(ch > 'Z'){
                                    ch = (char)(ch - 'Z' + 'A' - 1);
                                }
                                encryptedPassword += ch;
                            }
                            else {
                                encryptedPassword += ch;
                            }
                        }

                        if (!str_phone_no1.equals("") && !str_password.equals(""))
                        {
                            Query checkUser = reference.orderByChild("phoneno1").equalTo(str_phone_no1);
                            checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    if (snapshot.exists())
                                    {
                                        String passwordDB = snapshot.child(str_phone_no1).child("password").getValue(String.class);
                                        String statusDB = snapshot.child(str_phone_no1).child("status").getValue(String.class);
                                        str_first_name = snapshot.child(str_phone_no1).child("first_name").getValue(String.class);
                                        str_last_name = snapshot.child(str_phone_no1).child("last_name").getValue(String.class);
                                        str_password = snapshot.child(str_phone_no1).child("password").getValue(String.class);

                                        for (Integer i = 0; i < encryptedPassword.length(); ++i) {
                                            ch = str_password.charAt(i);
                                            if (ch >= 'a' && ch <= 'z') {
                                                ch = (char) (ch - key);

                                                if (ch < 'a') {
                                                    ch = (char) (ch + 'z' - 'a' + 1);
                                                }

                                                decryptedPassword += ch;
                                            } else if (ch >= 'A' && ch <= 'Z') {
                                                ch = (char) (ch - key);

                                                if (ch < 'A') {
                                                    ch = (char) (ch + 'Z' - 'A' + 1);
                                                }

                                                decryptedPassword += ch;
                                            } else {
                                                decryptedPassword += ch;
                                            }
                                        }

                                        str_profile_image = snapshot.child(str_phone_no1).child("profile_image").getValue(String.class);
                                        str_phone_no2 = snapshot.child(str_phone_no1).child("phoneno2").getValue(String.class);
                                        str_email = snapshot.child(str_phone_no1).child("email").getValue(String.class);
                                        str_licence_no = snapshot.child(str_phone_no1).child("licence_no").getValue(String.class);
                                        str_address_proof_no = snapshot.child(str_phone_no1).child("address_proof_no").getValue(String.class);
                                        str_address = snapshot.child(str_phone_no1).child("address").getValue(String.class);
                                        str_area = snapshot.child(str_phone_no1).child("area").getValue(String.class);
                                        str_city = snapshot.child(str_phone_no1).child("city").getValue(String.class);
                                        str_state = snapshot.child(str_phone_no1).child("state").getValue(String.class);
                                        str_pincode = snapshot.child(str_phone_no1).child("pincode").getValue(String.class);
                                        str_utype = snapshot.child(str_phone_no1).child("utype").getValue(String.class);
                                        if (passwordDB.equals((encryptedPassword)))
                                        {
                                            if (statusDB.equals("Active"))
                                            {
                                                SharedPreferences sp = getSharedPreferences("credentials",MODE_PRIVATE);
                                                SharedPreferences.Editor editor = sp.edit();
                                                editor.putString("phoneno1",phone_no1.getText().toString());
                                                editor.apply();
                                                Toast.makeText(Login.this,"Welcome To Rent-A-Savari "+str_first_name,Toast.LENGTH_SHORT).show();
                                                if(str_utype.equals("Admin"))
                                                {
                                                    Intent i = new Intent(Login.this,Admin_DashBoard.class);
                                                    i.putExtra("first_name",str_first_name);
                                                    i.putExtra("last_name",str_last_name);
                                                    i.putExtra("profile_image",str_profile_image);
                                                    i.putExtra("phoneno1",str_phone_no1);
                                                    i.putExtra("phoneno2",str_phone_no2);
                                                    i.putExtra("email",str_email);
                                                    i.putExtra("password",decryptedPassword);
                                                    i.putExtra("licence_no",str_licence_no);
                                                    i.putExtra("address_proof_no",str_address_proof_no);
                                                    i.putExtra("address",str_address);
                                                    i.putExtra("area",str_area);
                                                    i.putExtra("city",str_city);
                                                    i.putExtra("state",str_state);
                                                    i.putExtra("pincode",str_pincode);
                                                    i.putExtra("utype",str_utype);
                                                    startActivity(i);
                                                    finish();
                                                }
                                                else
                                                {
                                                    Intent i = new Intent(Login.this,MainActivity.class);
                                                    i.putExtra("first_name",str_first_name);
                                                    i.putExtra("last_name",str_last_name);
                                                    i.putExtra("profile_image",str_profile_image);
                                                    i.putExtra("phoneno1",str_phone_no1);
                                                    i.putExtra("phoneno2",str_phone_no2);
                                                    i.putExtra("email",str_email);
                                                    i.putExtra("password",decryptedPassword);
                                                    i.putExtra("licence_no",str_licence_no);
                                                    i.putExtra("address_proof_no",str_address_proof_no);
                                                    i.putExtra("address",str_address);
                                                    i.putExtra("area",str_area);
                                                    i.putExtra("city",str_city);
                                                    i.putExtra("state",str_state);
                                                    i.putExtra("pincode",str_pincode);
                                                    i.putExtra("utype",str_utype);
                                                    startActivity(i);
                                                    finish();
                                                }
                                            }
                                            else if (statusDB.equals("Deactivated"))
                                            {
                                                android.app.AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
                                                builder.setTitle("Account Activation");
                                                builder.setMessage("Previously You Deactivated Your Account.\nDo You Want To Activate Your Account Again?");
                                                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        SharedPreferences sp = getSharedPreferences("credentials",MODE_PRIVATE);
                                                        SharedPreferences.Editor editor = sp.edit();
                                                        editor.putString("phoneno1",phone_no1.getText().toString());
                                                         editor.apply();
                                                        Task<Void> referenceUser;
                                                        referenceUser = FirebaseDatabase.getInstance().getReference().child("user_M").child(str_phone_no1).child("status").setValue("Active");
                                                        Toast.makeText(Login.this, "Profile Activated Successfully",Toast.LENGTH_SHORT).show();
                                                        if(str_utype.equals("Admin"))
                                                        {
                                                            Intent i = new Intent(Login.this,Admin_DashBoard.class);
                                                            i.putExtra("first_name",str_first_name);
                                                            i.putExtra("last_name",str_last_name);
                                                            i.putExtra("profile_image",str_profile_image);
                                                            i.putExtra("phoneno1",str_phone_no1);
                                                            i.putExtra("phoneno2",decryptedPassword);
                                                            i.putExtra("email",str_email);
                                                            i.putExtra("password",str_password);
                                                            i.putExtra("licence_no",str_licence_no);
                                                            i.putExtra("address_proof_no",str_address_proof_no);
                                                            i.putExtra("address",str_address);
                                                            i.putExtra("area",str_area);
                                                            i.putExtra("city",str_city);
                                                            i.putExtra("state",str_state);
                                                            i.putExtra("pincode",str_pincode);
                                                            i.putExtra("utype",str_utype);
                                                            startActivity(i);
                                                            finish();
                                                        }
                                                        else
                                                        {
                                                            Intent i = new Intent(Login.this,MainActivity.class);
                                                            i.putExtra("first_name",str_first_name);
                                                            i.putExtra("last_name",str_last_name);
                                                            i.putExtra("profile_image",str_profile_image);
                                                            i.putExtra("phoneno1",str_phone_no1);
                                                            i.putExtra("phoneno2",str_phone_no2);
                                                            i.putExtra("email",str_email);
                                                            i.putExtra("password",decryptedPassword);
                                                            i.putExtra("licence_no",str_licence_no);
                                                            i.putExtra("address_proof_no",str_address_proof_no);
                                                            i.putExtra("address",str_address);
                                                            i.putExtra("area",str_area);
                                                            i.putExtra("city",str_city);
                                                            i.putExtra("state",str_state);
                                                            i.putExtra("pincode",str_pincode);
                                                            i.putExtra("utype",str_utype);
                                                            startActivity(i);
                                                            finish();
                                                        }
                                                    }
                                                });
                                                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which)
                                                    {
                                                        progressDialog.dismiss();
                                                        dialog.dismiss();
                                                    }
                                                });
                                                builder.show();
                                            }
                                        }
                                        else
                                        {
                                            progressDialog.dismiss();
                                            encryptedPassword = "";
                                            Toast.makeText(Login.this,"Password Incorrect",Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                    else
                                    {
                                        progressDialog.dismiss();
                                        encryptedPassword = "";
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
                            progressDialog.dismiss();
                            encryptedPassword = "";
                            Toast.makeText(Login.this,"All Fields Are Required",Toast.LENGTH_SHORT).show();
                        }
                    }
                },timeOut);
            }
        });
    }
}