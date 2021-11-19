package com.example.myrasdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.MyViewHolder> {

    Context context;
    ArrayList<Users> usersArrayList;

    public UserListAdapter(Context context, ArrayList<Users> usersArrayList) {
        this.context = context;
        this.usersArrayList = usersArrayList;
    }

    @NonNull
    @Override
    public UserListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.customer_list,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull UserListAdapter.MyViewHolder holder, int position) {

        Users users = usersArrayList.get(position);
        holder.first_name.setText(users.getFirst_name());
        holder.last_name.setText(users.getLast_name());
        holder.email.setText(users.getEmail());
        holder.phoneno1.setText(users.getPhoneno1());
        holder.phoneno2.setText(users.getPhoneno2());
        holder.licence_no.setText(users.getLicence_no());
        holder.address_proof_no.setText(users.getAddress_proof_no());
        Picasso.get().load(users.getProfile_image()).into(holder.profile_image);

    }

    @Override
    public int getItemCount() {
        return usersArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView profile_image;
        TextView first_name, last_name, email, phoneno1, phoneno2, licence_no, address_proof_no;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            profile_image = itemView.findViewById(R.id.UserImage);
            first_name = itemView.findViewById(R.id.cl_firtsname);
            last_name = itemView.findViewById(R.id.cl_last);
            email = itemView.findViewById(R.id.cl_Email);
            phoneno1 = itemView.findViewById(R.id.cl_mobile1);
            phoneno2 = itemView.findViewById(R.id.cl_mobile2);
            licence_no = itemView.findViewById(R.id.cl_licencenumber);
            address_proof_no = itemView.findViewById(R.id.cl_address_proof_no);
        }
    }

}
