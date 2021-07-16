package com.example.myrasdemo;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class CarListAdapter extends RecyclerView.Adapter<CarListAdapter.ViewHolder> {
    LayoutInflater inflater;
    List<Car> cars;
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView car_name,seat_capacity;
        ImageView car_image;
        Button smallPackage;
        public ViewHolder(View view) {
            super(view);
            car_name = view.findViewById(R.id.carName);
            seat_capacity = view.findViewById(R.id.carSeat);
            car_image = view.findViewById(R.id.carImage);
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
        Picasso.get().load(cars.get(position).getCar_image()).into(viewHolder.car_image);
    }

    @Override
    public int getItemCount() {
        return cars.size();

    }
}