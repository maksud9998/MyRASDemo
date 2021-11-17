package com.example.myrasdemo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Picasso;

public class BookingDetails extends AppCompatActivity {
    TextView car_name, transmission_type, body_type, car_no_plate, fuel_type, seat_capacity, kms, start_date, start_time, end_date, end_time, trip_fare_price, total_price;
    Button checkoutbtn;
    private String str_car_image, str_car_name, str_transmission_type, str_body_type, str_car_no_plate, str_fuel_type, str_seat_capacity, str_kms, str_start_date, str_start_time, str_end_date, str_end_time, str_rent_price, str_trip_fare_price, str_total_price, str_phone_no1, str_booking_id;
    Integer int_kms, int_rent_price, int_trip_fare_price, int_total_price;
    ImageView car_image;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    ProgressDialog progressDialog;
    private static int timeOut=500;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_details);
        car_image = findViewById(R.id.carImage);
        car_name = findViewById(R.id.carName);
        transmission_type = findViewById(R.id.transmission_txt);
        body_type = findViewById(R.id.bodytype_txt);
        car_no_plate = findViewById(R.id.noplatetxt);
        fuel_type = findViewById(R.id.fueltype_txt);
        seat_capacity = findViewById(R.id.seat_capacity_txt);
        kms = findViewById(R.id.kms_txt);
        start_date = findViewById(R.id.start_date_txt);
        start_time = findViewById(R.id.start_time_txt);
        end_date = findViewById(R.id.end_date_txt);
        end_time = findViewById(R.id.end_time_txt);
        trip_fare_price = findViewById(R.id.trip_fare_price_txt);
        total_price = findViewById(R.id.total_price_txt);
        Intent i = getIntent();
        str_car_image = i.getStringExtra("car_image");
        Picasso.get().load(str_car_image).into(car_image);
        str_car_name = i.getStringExtra("car_name");
        str_transmission_type = i.getStringExtra("transmission_type");
        str_body_type = i.getStringExtra("body_type");
        str_car_no_plate = i.getStringExtra("car_no_plate");
        str_fuel_type = i.getStringExtra("fuel_type");
        str_seat_capacity = i.getStringExtra("seat_capacity");
        str_kms = i.getStringExtra("kms");
        str_rent_price = i.getStringExtra("rent_price");
        str_start_date = i.getStringExtra("start_date");
        str_start_time = i.getStringExtra("start_time");
        str_end_date = i.getStringExtra("end_date");
        str_end_time = i.getStringExtra("end_time");
        str_phone_no1 = i.getStringExtra("phone_no1");
        str_booking_id = str_phone_no1 +" - "+ str_car_no_plate +" - "+ str_start_date +" - "+ str_start_time +" - "+ str_end_date +" - "+ str_end_time;
        car_name.setText(str_car_name);
        transmission_type.setText(str_transmission_type);
        body_type.setText(str_body_type);
        car_no_plate.setText(str_car_no_plate);//
        fuel_type.setText(str_fuel_type);
        seat_capacity.setText(str_seat_capacity+" Seater");
        kms.setText(str_kms);//
        start_date.setText(str_start_date);//
        start_time.setText(str_start_time);//
        end_date.setText(str_end_date);//
        end_time.setText(str_end_time);//
        int_kms = Integer.parseInt(str_kms);
        int_rent_price = Integer.parseInt(str_rent_price);
        int_trip_fare_price = int_kms*int_rent_price;
        str_trip_fare_price = int_trip_fare_price.toString();
        trip_fare_price.setText(str_trip_fare_price);
        int_total_price = int_trip_fare_price+2000;
        str_total_price = int_total_price.toString();
        total_price.setText(str_total_price);
        checkoutbtn = findViewById(R.id.confirm_booking_btn);
        checkoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog = new ProgressDialog(BookingDetails.this);
                progressDialog.show();
                progressDialog.setContentView(R.layout.progress_dialog);
                progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        String str_status = "Booked";
                        rootNode = FirebaseDatabase.getInstance();
                        reference = rootNode.getReference("booking_M");
                        BookingHelperClass bookingHelperClass = new BookingHelperClass(str_booking_id,str_car_name,str_car_image,str_car_no_plate,str_kms,str_start_date,str_start_time,str_end_date,str_end_time,str_phone_no1,str_status,str_trip_fare_price);
                        reference.child(str_booking_id).setValue(bookingHelperClass);
                        Toast.makeText(BookingDetails.this,"Booking Successfull",Toast.LENGTH_SHORT).show();
                        DatabaseReference referenceUpdateStatus = FirebaseDatabase.getInstance().getReference("car_M");
                        referenceUpdateStatus.child(str_car_no_plate).child("status").setValue(str_status);
                        Intent i = new Intent(BookingDetails.this,PreviousBooking.class);
                        startActivity(i);
                        finish();
                    }
                },timeOut);
            }
        });
    }
}