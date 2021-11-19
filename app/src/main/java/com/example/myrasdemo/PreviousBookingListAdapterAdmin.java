package com.example.myrasdemo;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PreviousBookingListAdapterAdmin extends RecyclerView.Adapter<PreviousBookingListAdapterAdmin.MyViewHolder> {

    Context context;
    ArrayList<PreviousBookingHelperClass> list;

    public PreviousBookingListAdapterAdmin(Context context, ArrayList<PreviousBookingHelperClass> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public PreviousBookingListAdapterAdmin.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.booking_list_admin,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PreviousBookingListAdapterAdmin.MyViewHolder holder, int position) {

        PreviousBookingHelperClass previousBookingHelperClass = list.get(position);
        Picasso.get().load(previousBookingHelperClass.getCar_image()).into(holder.car_image);
        holder.car_name.setText(previousBookingHelperClass.getCar_name());
        holder.car_no_plate.setText(previousBookingHelperClass.getCar_no_plate());
        holder.phone_no1.setText(previousBookingHelperClass.getPhone_no1());
        holder.start_date.setText(previousBookingHelperClass.getStart_date());
        holder.start_time.setText(previousBookingHelperClass.getStart_time());
        holder.end_date.setText(previousBookingHelperClass.getEnd_date());
        holder.end_time.setText(previousBookingHelperClass.getEnd_time());
        holder.kms.setText(previousBookingHelperClass.getKms()+" KMs");
        holder.status.setText(previousBookingHelperClass.getStatus());
        holder.total_fair_price.setText(previousBookingHelperClass.getTrip_fare_price());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView car_name, car_no_plate, phone_no1, start_date, start_time, end_date, end_time, kms, status, total_fair_price;
        ImageView car_image;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            car_image = itemView.findViewById(R.id.carImage);
            car_name = itemView.findViewById(R.id.carName);
            car_no_plate = itemView.findViewById(R.id.carNotxt);
            phone_no1 = itemView.findViewById(R.id.phone_no1);
            start_date = itemView.findViewById(R.id.start_date_txt);
            start_time = itemView.findViewById(R.id.start_time_txt);
            end_date = itemView.findViewById(R.id.end_date_txt);
            end_time = itemView.findViewById(R.id.end_time_txt);
            kms = itemView.findViewById(R.id.kms_txt);
            status = itemView.findViewById(R.id.status);
            total_fair_price = itemView.findViewById(R.id.total);
        }
    }
}
