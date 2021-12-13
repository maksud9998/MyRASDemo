package com.example.myrasdemo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PreviousBooking extends AppCompatActivity {

    RecyclerView bookinglistrecyclerView;
    ArrayList<PreviousBookingHelperClass> list;
    PreviousBookingListAdapterAdmin previousBookingListAdapterAdmin;
    PreviousBookingListAdapter previousBookingListAdapter;
    DatabaseReference database;
    private static int timeOut=500;
    ProgressDialog progressDialog;
    private static  PreviousBooking instance;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_previous_booking);
        instance = this;
        Intent i = getIntent();
        String str_utype = i.getStringExtra("utype");
        if (str_utype.equals("Admin"))
        {
            contentAdmin();
        }
        else
        {
            content();
        }
    }

    public void contentAdmin() {
        progressDialog = new ProgressDialog(PreviousBooking.this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                bookinglistrecyclerView = findViewById(R.id.bookinglistrecyclerView);
                database = FirebaseDatabase.getInstance().getReference().child("booking_M");
                bookinglistrecyclerView.setHasFixedSize(true);
                bookinglistrecyclerView.setLayoutManager(new LinearLayoutManager(PreviousBooking.this));
                list = new ArrayList<>();
                previousBookingListAdapterAdmin = new PreviousBookingListAdapterAdmin(PreviousBooking.this,list);
                bookinglistrecyclerView.setAdapter(previousBookingListAdapterAdmin);
                database.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                            PreviousBookingHelperClass previousBookingHelperClass = dataSnapshot.getValue(PreviousBookingHelperClass.class);
                            list.add(previousBookingHelperClass);
                        }
                        previousBookingListAdapterAdmin.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                progressDialog.dismiss();
            }
        },timeOut);
    }

    public static PreviousBooking getInstance()
    {
        return instance;
    }

    public void content() {

        progressDialog = new ProgressDialog(PreviousBooking.this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                bookinglistrecyclerView = findViewById(R.id.bookinglistrecyclerView);
                database = FirebaseDatabase.getInstance().getReference().child("booking_M");
                bookinglistrecyclerView.setHasFixedSize(true);
                bookinglistrecyclerView.setLayoutManager(new LinearLayoutManager(PreviousBooking.this));
                list = new ArrayList<>();
                previousBookingListAdapter = new PreviousBookingListAdapter(PreviousBooking.this,list);
                bookinglistrecyclerView.setAdapter(previousBookingListAdapter);
                database.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                            PreviousBookingHelperClass previousBookingHelperClass = dataSnapshot.getValue(PreviousBookingHelperClass.class);
                            list.add(previousBookingHelperClass);
                        }
                        previousBookingListAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                progressDialog.dismiss();
            }
        },timeOut);
    }
}