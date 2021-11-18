package com.example.myrasdemo;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CarListAdapter extends RecyclerView.Adapter<CarListAdapter.MyViewHolder>{

    Context contextActivity;
    Context contextCar;
    ArrayList<CarHelperClass> list;
    private  String str_start_date, str_start_time, str_end_date, str_end_time, str_phone_no1;

    public CarListAdapter(Context contextActivity, ArrayList<CarHelperClass> list, Context contextCar, String start_date, String start_time, String end_date, String end_time, String phone_no1) {
        this.contextActivity = contextActivity;
        this.contextCar = contextCar;
        this.list = list;
        this.str_start_date = start_date;
        this.str_start_time = start_time;
        this.str_end_date = end_date;
        this.str_end_time = end_time;
        this.str_phone_no1 = phone_no1;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(contextActivity).inflate(R.layout.cars_list,parent,false);
        return new MyViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        String str_rent_price, str_small_trip_fare_price, str_medium_trip_fare_price, str_high_trip_fare_price;
        Integer int_small_package_kms=150, int_medium_package_kms=350, int_high_package_kms=600, int_rent_price, int_small_trip_fare_price, int_medium_trip_fare_price, int_high_trip_fare_price;
        CarHelperClass carHelperClass = list.get(position);
        String str_statusDB = carHelperClass.getStatus();
        if (str_statusDB.equals("Available")) {
            holder.car_name.setText(carHelperClass.getCar_name());
        }
        else
        {
            holder.unavailable.setText("*Unavailable*");
        }
        holder.seat_capacity.setText(carHelperClass.getSeat_capacity());
        holder.fuel_type.setText(carHelperClass.getFuel_type());
        holder.transmission_type.setText(carHelperClass.getTransmission_type());
        Picasso.get().load(carHelperClass.getCar_image()).into(holder.car_image);
        str_rent_price = carHelperClass.getRent_price();
        int_rent_price = Integer.parseInt(str_rent_price);
        int_small_trip_fare_price = int_small_package_kms*int_rent_price;
        str_small_trip_fare_price = int_small_trip_fare_price.toString();
        holder.smallpackagebtn.setText("Rs "+str_small_trip_fare_price);
        holder.smallpackagebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str_statusDB = carHelperClass.getStatus();
                if (str_statusDB.equals("Available")) {
                    String str_kms = "150";
                    Intent intent = new Intent(contextCar, BookingDetails.class);
                    intent.putExtra("body_type", carHelperClass.getBody_type());
                    intent.putExtra("car_image", carHelperClass.getCar_image());
                    intent.putExtra("car_name", carHelperClass.getCar_name());
                    intent.putExtra("car_no_plate", carHelperClass.getCar_no_plate());
                    intent.putExtra("fuel_type", carHelperClass.getFuel_type());
                    intent.putExtra("car_name", carHelperClass.getCar_name());
                    intent.putExtra("rent_price", carHelperClass.getRent_price());
                    intent.putExtra("seat_capacity", carHelperClass.getSeat_capacity());
                    intent.putExtra("transmission_type", carHelperClass.getTransmission_type());
                    intent.putExtra("kms", str_kms);
                    intent.putExtra("start_date", str_start_date);
                    intent.putExtra("start_time", str_start_time);
                    intent.putExtra("end_date", str_end_date);
                    intent.putExtra("end_time", str_end_time);
                    intent.putExtra("phone_no1", str_phone_no1);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    contextCar.startActivity(intent);
                }
                else
                {
                    Toast.makeText(contextActivity, "This Car Is Not Available Right Now", Toast.LENGTH_SHORT).show();
                }
            }
        });

        int_medium_trip_fare_price = int_medium_package_kms*int_rent_price;
        str_medium_trip_fare_price = int_medium_trip_fare_price.toString();
        holder.mediumpackagebtn.setText("Rs "+str_medium_trip_fare_price);
        holder.mediumpackagebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str_statusDB = carHelperClass.getStatus();
                if (str_statusDB.equals("Available")) {
                    String str_kms = "350";
                    Intent intent = new Intent(contextCar, BookingDetails.class);
                    intent.putExtra("body_type", carHelperClass.getBody_type());
                    intent.putExtra("car_image", carHelperClass.getCar_image());
                    intent.putExtra("car_name", carHelperClass.getCar_name());
                    intent.putExtra("car_no_plate", carHelperClass.getCar_no_plate());
                    intent.putExtra("fuel_type", carHelperClass.getFuel_type());
                    intent.putExtra("car_name", carHelperClass.getCar_name());
                    intent.putExtra("rent_price", carHelperClass.getRent_price());
                    intent.putExtra("seat_capacity", carHelperClass.getSeat_capacity());
                    intent.putExtra("transmission_type", carHelperClass.getTransmission_type());
                    intent.putExtra("kms", str_kms);
                    intent.putExtra("start_date", str_start_date);
                    intent.putExtra("start_time", str_start_time);
                    intent.putExtra("end_date", str_end_date);
                    intent.putExtra("end_time", str_end_time);
                    intent.putExtra("phone_no1", str_phone_no1);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    contextCar.startActivity(intent);
                }
                else
                {
                    Toast.makeText(contextActivity, "This Car Is Not Available Right Now", Toast.LENGTH_SHORT).show();
                }}
        });

        int_high_trip_fare_price = int_high_package_kms*int_rent_price;
        str_high_trip_fare_price = int_high_trip_fare_price.toString();
        holder.highpackagebtn.setText("Rs "+str_high_trip_fare_price);
        holder.highpackagebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str_statusDB = carHelperClass.getStatus();
                if (str_statusDB.equals("Available")) {
                    String str_kms = "600";
                    Intent intent = new Intent(contextCar, BookingDetails.class);
                    intent.putExtra("body_type", carHelperClass.getBody_type());
                    intent.putExtra("car_image", carHelperClass.getCar_image());
                    intent.putExtra("car_name", carHelperClass.getCar_name());
                    intent.putExtra("car_no_plate", carHelperClass.getCar_no_plate());
                    intent.putExtra("fuel_type", carHelperClass.getFuel_type());
                    intent.putExtra("car_name", carHelperClass.getCar_name());
                    intent.putExtra("rent_price", carHelperClass.getRent_price());
                    intent.putExtra("seat_capacity", carHelperClass.getSeat_capacity());
                    intent.putExtra("transmission_type", carHelperClass.getTransmission_type());
                    intent.putExtra("kms", str_kms);
                    intent.putExtra("start_date", str_start_date);
                    intent.putExtra("start_time", str_start_time);
                    intent.putExtra("end_date", str_end_date);
                    intent.putExtra("end_time", str_end_time);
                    intent.putExtra("phone_no1", str_phone_no1);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    contextCar.startActivity(intent);
                }
                else
                {
                    Toast.makeText(contextActivity, "This Car Is Not Available Right Now", Toast.LENGTH_SHORT).show();
                }}
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView car_image;
        TextView car_name, seat_capacity, fuel_type, transmission_type, unavailable;
        Button smallpackagebtn, mediumpackagebtn, highpackagebtn;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            car_image = itemView.findViewById(R.id.carImage);
            car_name = itemView.findViewById(R.id.carName);
            seat_capacity = itemView.findViewById(R.id.carSeat);
            fuel_type = itemView.findViewById(R.id.carFueltype);
            transmission_type = itemView.findViewById(R.id.carTransmissiontype);
            unavailable = itemView.findViewById(R.id.unavailable);
            smallpackagebtn = itemView.findViewById(R.id.smallPackage);
            mediumpackagebtn = itemView.findViewById(R.id.mediumPackage);
            highpackagebtn = itemView.findViewById(R.id.highPackage);
        }
    }
}