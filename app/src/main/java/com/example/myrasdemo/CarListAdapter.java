package com.example.myrasdemo;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class CarListAdapter extends RecyclerView.Adapter<CarListAdapter.ViewHolder> {
    Integer intcar_rent_price;
    LayoutInflater inflater;
    List<Car> cars;
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView car_name,seat_capacity, fuel_type;
        ImageView car_image;
        Button smallPackage, mediumPackage, highPackage;
        public ViewHolder(View view) {
            super(view);
            car_name = view.findViewById(R.id.carName);
            seat_capacity = view.findViewById(R.id.carSeat);
            fuel_type = view.findViewById(R.id.carFueltype);
            car_image = view.findViewById(R.id.carImage);
            smallPackage = view.findViewById(R.id.smallPackage);
            mediumPackage = view.findViewById(R.id.mediumPackage);
            highPackage = view.findViewById(R.id.highPackage);
        }
    }

    public CarListAdapter(Context context,List<Car> cars)
    {
        this.inflater = LayoutInflater.from(context);
        this.cars = cars;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = inflater.inflate(R.layout.cars_list, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        viewHolder.car_name.setText(cars.get(position).getCar_name());
        viewHolder.seat_capacity.setText(cars.get(position).getSeat_capacity());
        viewHolder.fuel_type.setText(cars.get(position).getFuel_type());
        Glide.with(viewHolder.car_name.getContext()).load("http://192.168.1.223/MySQL/Images/"+cars.get(position).getCar_image()).into(viewHolder.car_image);
        intcar_rent_price = cars.get(position).getRent_price();
        intcar_rent_price = intcar_rent_price*150;
        viewHolder.smallPackage.setText(intcar_rent_price.toString());
        intcar_rent_price = cars.get(position).getRent_price();
        intcar_rent_price = intcar_rent_price*350;
        viewHolder.mediumPackage.setText(intcar_rent_price.toString());
        intcar_rent_price = cars.get(position).getRent_price();
        intcar_rent_price = intcar_rent_price*600;
        viewHolder.highPackage.setText(intcar_rent_price.toString());
    }

    @Override
    public int getItemCount() {
        return cars.size();

    }
}