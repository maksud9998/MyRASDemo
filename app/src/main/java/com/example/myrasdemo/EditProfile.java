package com.example.myrasdemo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import static com.example.myrasdemo.Login.loginemail;

public class EditProfile extends AppCompatActivity {
    TextView verify_link;
    EditText phone_no1, phone_no2, last_name, first_name, password, address, area, city, state,pincode, license_no;
    Button updatebtn;
    ImageView profile_image;
    FloatingActionButton capture_profile_image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        verify_link = findViewById(R.id.verify_link);
        phone_no1 = findViewById(R.id.Prof_MobileNumber);
        phone_no2 = findViewById(R.id.Prof_MobileNumber2);
        last_name = findViewById(R.id.Prof_LastName);
        first_name = findViewById(R.id.Prof_FirstName);
        password = findViewById(R.id.Password);
        address = findViewById(R.id.Address);
        area = findViewById(R.id.area);
        city = findViewById(R.id.city);
        state = findViewById(R.id.state);
        pincode = findViewById(R.id.pincode);
        license_no = findViewById(R.id.Licence_number);
        profile_image = findViewById(R.id.Profile_Image);

        capture_profile_image = findViewById(R.id.capture_profile_image);
        capture_profile_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.with(EditProfile.this).crop().compress(1024).maxResultSize(1080, 1080).start(3);
            }
        });

        verify_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(EditProfile.this,UploadDocs.class);
                startActivity(i);
            }
        });

        updatebtn = findViewById(R.id.submit);
        updatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strphone_no1, strphone_no2, strdob, strlast_name, strfirst_name, stremail, strpassword, straddress, strarea, strcity, strstate, strpincode, strlicense_no;
                strphone_no1 = phone_no1.getText().toString();
                strphone_no2 = phone_no2.getText().toString();
                strlast_name = last_name.getText().toString();
                strfirst_name = first_name.getText().toString();
                stremail = loginemail;
                strpassword = password.getText().toString();
                straddress = address.getText().toString();
                strarea = area.getText().toString();
                strcity = city.getText().toString();
                strstate = state.getText().toString();
                strpincode = pincode.getText().toString();
                strlicense_no = license_no.getText().toString();
                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        String[] field = new String[12];
                        field[0] = "first_name";
                        field[1] = "last_name";
                        field[2] = "email";
                        field[3] = "phone_no1";
                        field[4] = "phone_no2";
                        field[5] = "address";
                        field[6] = "area";
                        field[7] = "city";
                        field[8] = "state";
                        field[9] = "pincode";
                        field[10] = "license_no";
                        field[11] = "password";
                        //Creating array for data
                        String[] data = new String[12];
                        data[0] = strfirst_name;
                        data[1] = strlast_name;
                        data[2] = stremail;
                        data[3] = strphone_no1;
                        data[4] = strphone_no2;
                        data[5] = straddress;
                        data[6] = strarea;
                        data[7] = strcity;
                        data[8] = strstate;
                        data[9] = strpincode;
                        data[10] = strlicense_no;
                        data[11] = strpassword;
                        //Repelace The IP Address In The Following url With Your PC IP Address
                        //Find Your PC IP Address By Writing ipconfig In CMD
                        PutData putData = new PutData("http://192.168.1.223/MySQL/UpdateUserDetails.php", "POST", field, data);
                        if (putData.startPut()) {
                            if (putData.onComplete()) {
                                String result = putData.getResult();
                                if (result.equals("Profile Updated"))
                                {
                                    Toast.makeText(EditProfile.this,"Profile Successfully Updated",Toast.LENGTH_SHORT).show();
                                    Intent i = new Intent(EditProfile.this,MainActivity.class);
                                    startActivity(i);
                                    finish();
                                }
                                else
                                {
                                    Toast.makeText(EditProfile.this,result,Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    }
                });
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Uri uri = data.getData();
        profile_image.setImageURI(uri);
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
                String stremail;
                stremail = loginemail;
                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        String[] field = new String[1];
                        field[0] = "email";
                        //Creating array for data
                        String[] data = new String[1];
                        data[0] = stremail;
                        //Repelace The IP Address In The Following url With Your PC IP Address
                        //Find Your PC IP Address By Writing ipconfig In CMD
                        PutData putData = new PutData("http://192.168.1.223/MySQL/DeleteUser.php", "POST", field, data);
                        if (putData.startPut()) {
                            if (putData.onComplete()) {
                                String result = putData.getResult();
                                if (result.equals("Profile Deleted"))
                                {
                                    Toast.makeText(activity.getApplicationContext(), "Profile Deactivated Successfully",Toast.LENGTH_SHORT).show();
                                    Intent i = new Intent(EditProfile.this,Login.class);
                                    startActivity(i);
                                    finish();
                                }
                                else
                                {
                                    Toast.makeText(EditProfile.this,result,Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    }
                });
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