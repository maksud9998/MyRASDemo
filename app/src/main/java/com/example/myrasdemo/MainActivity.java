package com.example.myrasdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    TextView startDate, startTime,startAM_PM, endDate, endDay, endTime,endAM_PM;
    RelativeLayout startLayout, endLayout;
    Button findcarbtn;
    RecyclerView carlistrecyclerView;
    List<Car> cars;
    private static  String JSON_URL = "http://192.168.1.222/LoginRegister/CarDetails.php";
    CarListAdapter carListAdapter;
    boolean startCheck = false,endCheck=false;
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
        carlistrecyclerView = findViewById(R.id.carlistrecyclerView);
        cars = new ArrayList<>();
        carlistrecyclerView.setLayoutManager(new LinearLayoutManager(this));
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
                        SimpleDateFormat starttimeFormat = new SimpleDateFormat("EEEE HH:mm");
                        startTime.setText(starttimeFormat.format(calendar.getTime()));
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
                        SimpleDateFormat endtimeFormat = new SimpleDateFormat("EEEE HH:mm");
                        endTime.setText(endtimeFormat.format(calendar.getTime()));
                    }
                };
                new TimePickerDialog(MainActivity.this,timeSetListener,calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),false).show();
            }
        };
        new DatePickerDialog(MainActivity.this,dateSetListener,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    private void showCarList() {
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,JSON_URL,null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i <= response.length(); i++)
                {
                    try {
                        JSONObject carObject = response.getJSONObject(i);
                        Car car = new Car();
                        car.setCar_name(carObject.getString("car_name").toString());
                        car.setSeat_capacity(carObject.getString("seat_capacity").toString());
                        car.setRent_price(carObject.getInt("rent_price"));
                        car.setCar_image(carObject.getString("car_image").toString());

                        cars.add(car);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                carlistrecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                carListAdapter = new CarListAdapter(MainActivity.this,cars);
                carlistrecyclerView.setAdapter(carListAdapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("tag","onErrorResponse: " + error.getMessage());
            }
        });
        queue.add(jsonArrayRequest);
    }

    public void onClickSmallPackage(View view) {
        Intent i = new Intent(MainActivity.this,BookingDetails.class);
        startActivity(i);
    }

    public void onClickMediumPackage(View view) {
        Intent i = new Intent(MainActivity.this,BookingDetails.class);
        startActivity(i);
    }

    public void onClickHighPackage(View view) {
        Intent i = new Intent(MainActivity.this,BookingDetails.class);
        startActivity(i);
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
        startActivity(i);
    }

    public void onClickDocuments(View view) {
        Intent i = new Intent(MainActivity.this,UploadDocs.class);
        startActivity(i);
    }

    public void onClickPrivacyPolicy(View view) {
        Intent i = new Intent(MainActivity.this,PrivacyPolicies.class);
        startActivity(i);
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
        startActivity(i);
    }

    public void onClickCarImages(View view) {
        Intent i = new Intent(MainActivity.this,UploadCarImages.class);
        startActivity(i);
    }

    public void onClickLogout(View view) {
        finish();
    }
}