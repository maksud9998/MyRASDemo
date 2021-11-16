package com.example.myrasdemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class BookingDetails extends AppCompatActivity {
    Button checkoutbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_details);
        checkoutbtn = findViewById(R.id.confirm_booking_btn);
        checkoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(BookingDetails.this,PreviousBooking.class);
                startActivity(i);
            }
        });
    }
}