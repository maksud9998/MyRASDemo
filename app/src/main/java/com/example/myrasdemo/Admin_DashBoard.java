package com.example.myrasdemo;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;


import de.hdodenhof.circleimageview.CircleImageView;

public class Admin_DashBoard extends AppCompatActivity {
    CardView ManageUserCardview,ManageCarCardview,ManageviewBooking,ManageRegister;
    CircleImageView admin_profile_image;
    TextView full_name;
    private String str_first_name, str_last_name, str_full_name, str_profile_image, str_phone_no1, str_phone_no2, str_email, str_password, str_licence_no, str_address_proof_no, str_address, str_area, str_city, str_state, str_pincode, str_utype;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dash_board);
        ManageUserCardview=findViewById(R.id.Manage_User_card);
        ManageCarCardview=findViewById(R.id.Manage_Car_Card);
        ManageviewBooking=findViewById(R.id.Manage_View_Booking);
        ManageRegister=findViewById(R.id.Manage_Reg);
        full_name = findViewById(R.id.admin_name);
        admin_profile_image = findViewById(R.id.admin_profile_image);
        Intent i = getIntent();
        str_first_name = i.getStringExtra("first_name");
        str_last_name = i.getStringExtra("last_name");
        str_full_name = str_first_name + " "+ str_last_name;
        full_name.setText(str_full_name);
        str_profile_image = i.getStringExtra("profile_image");
        Picasso.get().load(str_profile_image).into(admin_profile_image);
        str_phone_no1 = i.getStringExtra("phoneno1");
        str_phone_no2 = i.getStringExtra("phoneno2");
        str_email = i.getStringExtra("email");
        str_password = i.getStringExtra("password");
        str_licence_no = i.getStringExtra("licence_no");
        str_address_proof_no = i.getStringExtra("address_proof_no");
        str_address = i.getStringExtra("address");
        str_area = i.getStringExtra("area");
        str_city = i.getStringExtra("city");
        str_state = i.getStringExtra("state");
        str_pincode = i.getStringExtra("pincode");
        str_utype = i.getStringExtra("utype");
        ManageUserCardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Intent_Usercard = new Intent(Admin_DashBoard.this,UserList.class);
                startActivity(Intent_Usercard);

            }
        });
        ManageCarCardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Intent_Carcard = new Intent(Admin_DashBoard.this,Add_Car.class);
                startActivity(Intent_Carcard);

            }
        });
        ManageviewBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Intent_Bookingcard = new Intent(Admin_DashBoard.this,PreviousBooking.class);
                Intent_Bookingcard.putExtra("utype",str_utype);
                startActivity(Intent_Bookingcard);

            }
        });
        ManageRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Intent_Regcard = new Intent(Admin_DashBoard.this,Register.class);
                startActivity(Intent_Regcard);

            }
        });

    }

    public void onClickEditProfile(View view) {
        Intent i = new Intent(Admin_DashBoard.this,EditProfile.class);
        i.putExtra("first_name",str_first_name);
        i.putExtra("last_name",str_last_name);
        i.putExtra("profile_image",str_profile_image);
        i.putExtra("phoneno1",str_phone_no1);
        i.putExtra("phoneno2",str_phone_no2);
        i.putExtra("email",str_email);
        i.putExtra("password",str_password);
        i.putExtra("licence_no",str_licence_no);
        i.putExtra("address_proof_no",str_address_proof_no);
        i.putExtra("address",str_address);
        i.putExtra("area",str_area);
        i.putExtra("city",str_city);
        i.putExtra("state",str_state);
        i.putExtra("pincode",str_pincode);
        startActivity(i);
    }

    public void onClickLogout(View view) {
        logout(this);
    }

    private void logout(Activity activity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("Log Out");
        builder.setMessage("Are You Sure You Want To Log Out?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                SharedPreferences sp = getSharedPreferences("credentials",MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.clear();
                editor.apply();
                Intent i = new Intent(Admin_DashBoard.this,Login.class);
                startActivity(i);
                finish();
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