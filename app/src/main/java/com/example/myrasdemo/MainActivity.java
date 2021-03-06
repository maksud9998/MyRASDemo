package com.example.myrasdemo;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    CircleImageView profile_image;
    TextView startDate, startTime, endDate, endTime, full_name;
    RelativeLayout startLayout, endLayout;
    Button findcarbtn;
    RecyclerView carlistrecyclerView;
    DatabaseReference referenceCar;
    CarListAdapter carListAdapter;
    ArrayList<CarHelperClass> list;
    private static int timeOut=500;
    ProgressDialog progressDialog;
    private String str_first_name, str_last_name, str_full_name, str_profile_image, str_phone_no1, str_phone_no2, str_email, str_password, str_licence_no, str_address_proof_no, str_address, str_area, str_city, str_state, str_pincode, str_start_date, str_start_time, str_end_date, str_end_time, str_utype;
    boolean startCheck = false,endCheck=false;
    private static MainActivity instance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startDate = findViewById(R.id.startdate);
        startTime = findViewById(R.id.starttime);
        endDate = findViewById(R.id.enddate);
        endTime = findViewById(R.id.endtime);
        startLayout = findViewById(R.id.startLayout);
        endLayout = findViewById(R.id.endLayout);
        findcarbtn = findViewById(R.id.findcarbtn);
        drawerLayout = findViewById(R.id.drawer_layout);
        full_name = findViewById(R.id.user_name);
        profile_image = findViewById(R.id.profile_image);
        carlistrecyclerView = findViewById(R.id.carlistrecyclerView);
        instance = this;
        Intent i = getIntent();
        str_phone_no1 = i.getStringExtra("phoneno1");
        str_first_name = i.getStringExtra("first_name");
        str_last_name = i.getStringExtra("last_name");
        str_full_name = str_first_name + " "+ str_last_name;
        full_name.setText(str_full_name);
        str_profile_image = i.getStringExtra("profile_image");
        Picasso.get().load(str_profile_image).into(profile_image);
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

        startLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startCheck = true;
                startDateTimeDialog();
            }
        });
        endLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                endCheck = true;
                endDateTimeDialog();
            }
        });
        findcarbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!startCheck || !endCheck)
                {
                    Toast.makeText(MainActivity.this,"Please Schedule Your Pickup And Drop Timings",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    showCarList();
                }
            }
        });
    }

    public static MainActivity getInstance()
    {
        return instance;
    }

    private void startDateTimeDialog() {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR,year);
                calendar.set(Calendar.MONTH,month);
                calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        calendar.set(Calendar.HOUR_OF_DAY,hourOfDay);
                        calendar.set(Calendar.MINUTE,minute);
                        SimpleDateFormat startDateFormat = new SimpleDateFormat("MMMM  dd  yyyy");
                        startDate.setText(startDateFormat.format(calendar.getTime()));
                        str_start_date = startDateFormat.format(calendar.getTime());
                        SimpleDateFormat starttimeFormat = new SimpleDateFormat("EEEE hh:mm aa");
                        startTime.setText(starttimeFormat.format(calendar.getTime()));
                        str_start_time = starttimeFormat.format(calendar.getTime());
                    }
                };
                new TimePickerDialog(MainActivity.this,timeSetListener,calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),false).show();
            }
        };
        new DatePickerDialog(MainActivity.this,dateSetListener,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    private void endDateTimeDialog() {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR,year);
                calendar.set(Calendar.MONTH,month);
                calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);

                TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        calendar.set(Calendar.HOUR_OF_DAY,hourOfDay);
                        calendar.set(Calendar.MINUTE,minute);
                        SimpleDateFormat endDateFormat = new SimpleDateFormat("MMMM  dd  yyyy");
                        endDate.setText(endDateFormat.format(calendar.getTime()));
                        str_end_date = endDateFormat.format(calendar.getTime());
                        SimpleDateFormat endtimeFormat = new SimpleDateFormat("EEEE hh:mm aa");
                        endTime.setText(endtimeFormat.format(calendar.getTime()));
                        str_end_time = endtimeFormat.format(calendar.getTime());
                    }
                };
                new TimePickerDialog(MainActivity.this,timeSetListener,calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),false).show();
            }
        };
        new DatePickerDialog(MainActivity.this,dateSetListener,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    public void showCarList() {
        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                referenceCar = FirebaseDatabase.getInstance().getReference().child("car_M");
                carlistrecyclerView.setHasFixedSize(true);
                carlistrecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                list = new ArrayList<>();
                carListAdapter = new CarListAdapter(MainActivity.this,list,getApplicationContext(),str_start_date,str_start_time,str_end_date,str_end_time, str_phone_no1, str_utype);
                carlistrecyclerView.setAdapter(carListAdapter);
                referenceCar.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            CarHelperClass carHelperClass = dataSnapshot.getValue(CarHelperClass.class);
                            list.add(carHelperClass);
                        }
                        carListAdapter.notifyDataSetChanged();
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
                progressDialog.dismiss();
            }
        },timeOut);
    }

    public static void openDrawer(DrawerLayout drawerLayout)
    {
        drawerLayout.openDrawer(GravityCompat.START);
    }
    public static void closeDrawer(DrawerLayout drawerLayout)
    {
        if (drawerLayout.isDrawerOpen(GravityCompat.START))
        {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    public static void exit(Activity activity)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("Exit");
        builder.setMessage("Are You Sure You Want To Exit?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                activity.finishAffinity();
                System.exit(0);
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

    @Override
    protected void onPause()
    {
        super.onPause();
        closeDrawer(drawerLayout);
    }

    public void onClickMenu(View view) {
        openDrawer(drawerLayout);
    }

    public void onClickHome(View view) {
        recreate();
    }

    public void onClickMyTrip(View view) {
        Intent i = new Intent(MainActivity.this,PreviousBooking.class);
        i.putExtra("utype",str_utype);
        startActivity(i);
    }

    public void onClickDocuments(View view) {
        Intent i = new Intent(MainActivity.this,UploadDocs.class);
        i.putExtra("phoneno1",str_phone_no1);
        startActivity(i);
    }

    public void onClickPrivacyPolicy(View view) {
        Intent i = new Intent(MainActivity.this,PrivacyPolicies.class);
        startActivity(i);
    }

    public void onClickDeactivate(View view) {
        deactivate(this);
    }

    public void deactivate(Activity activity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("Deactivate Account");
        builder.setMessage("Are You Sure You Want To Deactivate Your Account?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                SharedPreferences sp = getSharedPreferences("credentials",MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.clear();
                editor.apply();
                Task<Void> referenceUser;
                referenceUser = FirebaseDatabase.getInstance().getReference().child("user_M").child(str_phone_no1).child("status").setValue("Deactivated");
                Toast.makeText(activity.getApplicationContext(), "Profile Deactivated Successfully",Toast.LENGTH_SHORT).show();
                Intent i = new Intent(MainActivity.this,Login.class);
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

    public void onClickContactUs(View view) {
        Intent i = new Intent(MainActivity.this,ContactUs.class);
        startActivity(i);
    }

    public void onClickExit(View view) {
        exit(this);
    }

    public void onClickEditProfile(View view) {
        Intent i = new Intent(MainActivity.this,EditProfile.class);
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
                Intent i = new Intent(MainActivity.this,Login.class);
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