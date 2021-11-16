package com.example.myrasdemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.concurrent.TimeUnit;

public class Register extends AppCompatActivity {
    EditText first_name, last_name,email,phone_no1, password, license_no;
    Button registerbtn;
    TextView loginlink;
    private FirebaseAuth mAuth;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    DatabaseReference referenceCheck = FirebaseDatabase.getInstance().getReference("user_M");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
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
                String str_first_name, str_last_name, str_email, str_phoneno1, str_password, str_licence_no, str_utype, str_status;
                str_first_name = first_name.getText().toString();
                str_last_name = last_name.getText().toString();
                str_email = email.getText().toString();
                str_phoneno1 = phone_no1.getText().toString();
                str_password = password.getText().toString();
                str_licence_no = license_no.getText().toString();
                str_utype = "Customer";
                str_status = "Active";
                if (!str_first_name.equals("") && !str_last_name.equals("") && !str_email.equals("") && !str_phoneno1.equals("") && !str_password.equals("") && !str_licence_no.equals(""))
                {
                    Query checkUser = referenceCheck.orderByChild("phoneno1").equalTo(str_phoneno1);
                    checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.exists())
                            {
                                String phoneno1DB = snapshot.child(str_phoneno1).child("phoneno1").getValue(String.class);
                                if (phoneno1DB.equals(str_phoneno1))
                                {
                                    Toast.makeText(Register.this,"Mobile Number Already Registered",Toast.LENGTH_SHORT).show();
                                }
                            }
                            else
                            {
                                otpSend();
                                rootNode = FirebaseDatabase.getInstance();
                                reference = rootNode.getReference("user_M");
                                UserHelperClass helperClass = new UserHelperClass(str_first_name,str_last_name,str_email,str_phoneno1,str_password,str_licence_no,str_utype,str_status);
                                reference.child(str_phoneno1).setValue(helperClass);
                                Toast.makeText(Register.this,"Successfully Registered To Rent-A-Savari",Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(Register.this,Login.class);
                                startActivity(i);
                                finish();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

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

    private void otpSend() {
        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            @Override
            public void onVerificationCompleted(PhoneAuthCredential credential) {

            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                Toast.makeText(Register.this,e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCodeSent(@NonNull String verificationId, @NonNull PhoneAuthProvider.ForceResendingToken token) {
                Toast.makeText(Register.this,"OTP Sent Successfully",Toast.LENGTH_SHORT).show();
                Intent i = new Intent(Register.this,VerifyPhoneNo.class);
                i.putExtra("phoneno1",phone_no1.getText().toString());
                i.putExtra("verificationId",verificationId);
                startActivity(i);
            }
        };
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber("+91" + phone_no1.getText().toString())       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }
}