package com.example.myrasdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class PreviousBooking extends AppCompatActivity {
    RecyclerView bookinglistrecyclerView;
    String []arrb={"Item1","Item2","Item3","Item4","Item5","Item6","Item7","Item8","Item9","Item10","Item2","Item3","Item4","Item5","Item6","Item7","Item8","Item9","Item10"};
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