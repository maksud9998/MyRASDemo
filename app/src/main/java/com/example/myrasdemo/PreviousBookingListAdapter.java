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

public class PreviousBookingListAdapter extends RecyclerView.Adapter<PreviousBookingListAdapter.MyViewHolder> {

    Context context;
    ArrayList<PreviousBookingHelperClass> list;

    public PreviousBookingListAdapter(Context context, ArrayList<PreviousBookingHelperClass> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public PreviousBookingListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.booking_list,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PreviousBookingListAdapter.MyViewHolder holder, int position) {

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
        String booking_id = previousBookingHelperClass.getBooking_id();
        String car_no_plate =previousBookingHelperClass.getCar_no_plate();
        String status = previousBookingHelperClass.getStatus();
        String phone_no1 = previousBookingHelperClass.getPhone_no1();
        holder.upload_car_photos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (status.equals("Cancelled"))
                {
                    Toast.makeText(context, "This Booking Is Already Cancelled", Toast.LENGTH_SHORT).show();
                }
                else if(status.equals("Trip Ended"))
                {
                    Toast.makeText(context, "This Booking Is Already Ended", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    PreviousBooking.getInstance().content();
                    Intent intent = new Intent(context,UploadCarImages.class);
                    intent.putExtra("booking_id",booking_id);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            }
        });
        holder.cancel_booking_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (status.equals("Cancelled"))
                {
                    Toast.makeText(context, "This Booking Is Already Cancelled", Toast.LENGTH_SHORT).show();
                }
                else if(status.equals("Trip Ended"))
                {
                    Toast.makeText(context, "This Booking Is Already Ended", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("Cancel Booking");
                    builder.setMessage("Are You Sure You Want To Cancel This Booking?");
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            list.clear();
                            Task<Void> referenceBooking,referenceCarStatus;
                            referenceBooking = FirebaseDatabase.getInstance().getReference().child("booking_M").child(booking_id).child("status").setValue("Cancelled");
                            referenceCarStatus = FirebaseDatabase.getInstance().getReference().child("car_M").child(car_no_plate).child("status").setValue("Available");
                            Toast.makeText(context.getApplicationContext(), "Booking Cancelled",Toast.LENGTH_SHORT).show();
                            PreviousBooking.getInstance().content();
                        }
                    });
                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which)
                        {
                            dialog.dismiss();
                        }
                    });
                    builder.show();
                }
            }
        });
        holder.endTripbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (status.equals("Cancelled"))
                {
                    Toast.makeText(context, "This Booking Is Already Cancelled", Toast.LENGTH_SHORT).show();
                }
                else if(status.equals("Trip Ended"))
                {
                    Toast.makeText(context, "This Booking Is Already Ended", Toast.LENGTH_SHORT).show();
                }
                else if (status.equals("Booked"))
                {
                    list.clear();
                    Task<Void> referenceBooking,referenceCarStatus;
                    referenceBooking = FirebaseDatabase.getInstance().getReference().child("booking_M").child(booking_id).child("status").setValue("Cancelled");
                    referenceCarStatus = FirebaseDatabase.getInstance().getReference().child("car_M").child(car_no_plate).child("status").setValue("Available");
                    Toast.makeText(context.getApplicationContext(), "Trip Ended",Toast.LENGTH_SHORT).show();
                    PreviousBooking.getInstance().content();
                }
                else
                {
                    list.clear();
                    Task<Void> referenceBooking,referenceCarStatus;
                    referenceBooking = FirebaseDatabase.getInstance().getReference().child("booking_M").child(booking_id).child("status").setValue("Trip Ended");
                    referenceCarStatus = FirebaseDatabase.getInstance().getReference().child("car_M").child(car_no_plate).child("status").setValue("Available");
                    Toast.makeText(context.getApplicationContext(), "Trip Ended",Toast.LENGTH_SHORT).show();
                    PreviousBooking.getInstance().content();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView car_name, car_no_plate, phone_no1, start_date, start_time, end_date, end_time, kms, status, total_fair_price;
        ImageView car_image;
        Button upload_car_photos, cancel_booking_btn, endTripbtn;

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
            upload_car_photos = itemView.findViewById(R.id.uploadCarPhotosbtn);
            cancel_booking_btn = itemView.findViewById(R.id.cancelbookingbtn);
            endTripbtn = itemView.findViewById(R.id.endTripbtn);
        }
    }
}
