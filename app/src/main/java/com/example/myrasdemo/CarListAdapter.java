package com.example.myrasdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CarListAdapter extends RecyclerView.Adapter<CarListAdapter.MyViewHolder>{

    Context context;
    ArrayList<CarHelperClass> list;

    public CarListAdapter(Context context, ArrayList<CarHelperClass> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.cars_list,parent,false);
        return new MyViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        CarHelperClass carHelperClass = list.get(position);
        holder.car_name.setText(carHelperClass.getCar_name());
        holder.seat_capacity.setText(carHelperClass.getSeat_capacity());
        holder.fuel_type.setText(carHelperClass.getFuel_type());
        holder.transmission_type.setText(carHelperClass.getTransmission_type());
        Picasso.get().load(carHelperClass.getCar_image()).into(holder.car_image);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView car_image;
        TextView car_name, seat_capacity, fuel_type, transmission_type;
        Button smallpackagebtn, mediumpackagebtn, highpackagebtn;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            car_image = itemView.findViewById(R.id.carImage);
            car_name = itemView.findViewById(R.id.carName);
            seat_capacity = itemView.findViewById(R.id.carSeat);
            fuel_type = itemView.findViewById(R.id.carFueltype);
            transmission_type = itemView.findViewById(R.id.carTransmissiontype);
            smallpackagebtn = itemView.findViewById(R.id.smallPackage);
            mediumpackagebtn = itemView.findViewById(R.id.mediumPackage);
            highpackagebtn = itemView.findViewById(R.id.highPackage);
        }
    }
}