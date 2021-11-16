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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

public class VerifyPhoneNo extends AppCompatActivity {
    Button verify_otp_btn;
    EditText otpEnteredByUser;
    TextView otp_msg;
    private String verificationId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_phone_no);
        verify_otp_btn = findViewById(R.id.verify_otp_btn);
        otpEnteredByUser = findViewById(R.id.otp);
        otp_msg.setText(String.format("OTP Sent To +91-%s",getIntent().getStringExtra("phoneno1")));
        verificationId = getIntent().getStringExtra("verificationId");
        verify_otp_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (otpEnteredByUser.getText().toString().isEmpty())
                {
                    Toast.makeText(VerifyPhoneNo.this,"Please Enter The OTP",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if (verificationId != null)
                    {
                        String code = otpEnteredByUser.getText().toString();
                        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);
                        FirebaseAuth.getInstance().signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful())
                                {
                                    Intent i = new Intent(VerifyPhoneNo.this,MainActivity.class);
                                    i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(i);
                                }
                                else
                                {
                                    Toast.makeText(VerifyPhoneNo.this,"Invalid OTP",Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                }
            }
        });
    }
}