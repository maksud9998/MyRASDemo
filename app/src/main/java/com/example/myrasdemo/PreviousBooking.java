package com.example.myrasdemo;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class PreviousBooking extends AppCompatActivity {
    RecyclerView bookinglistrecyclerView;
    String []arrb={"Hundai Creta","Hundai Creta","Hundai Creta","Hundai Creta","Hundai Creta","Hundai Creta","Hundai Creta","Hundai Creta","Hundai Creta","Hundai Creta"};
    BookingListAdapter blAdapter = new BookingListAdapter(arrb);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_previous_booking);
        bookinglistrecyclerView = findViewById(R.id.bookinglistrecyclerView);
        bookinglistrecyclerView.setLayoutManager(new LinearLayoutManager(this));
        bookinglistrecyclerView.setAdapter(blAdapter);
    }
}