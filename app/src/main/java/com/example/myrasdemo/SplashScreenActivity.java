package com.example.myrasdemo;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class SplashScreenActivity extends AppCompatActivity {
    private static int timeOut=2500;
    Animation topanim,bottomanim;
    ImageView splash_screen_image;
    TextView splash_screen_text;
    String encryptedPassword = "", decryptedPassword = "";
    private String str_first_name, str_last_name, str_profile_image, str_phone_no1, str_phone_no2, str_email, str_password, str_licence_no, str_address_proof_no, str_address, str_area, str_city, str_state, str_pincode, str_utype;
    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("user_M");
    Integer key=1;
    Character ch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        topanim = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottomanim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);
        splash_screen_image = findViewById(R.id.splash_screen_image);
        splash_screen_text = findViewById(R.id.splash_screen_text);
        splash_screen_image.setAnimation(topanim);
        splash_screen_text.setAnimation(bottomanim);
        if (!isConnected(SplashScreenActivity.this))
        {
            showCustomDialog(SplashScreenActivity.this);
        }
        else
        {
            checkAlreadyLogin();
        }
    }

    private void checkAlreadyLogin() {
        SharedPreferences sp = getSharedPreferences("credentials",MODE_PRIVATE);
        if (sp.contains("phoneno1"))
        {
            str_phone_no1 = sp.getString("phoneno1","");
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Query checkUser = reference.orderByChild("phoneno1").equalTo(str_phone_no1);
                    checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            str_first_name = snapshot.child(str_phone_no1).child("first_name").getValue(String.class);
                            str_last_name = snapshot.child(str_phone_no1).child("last_name").getValue(String.class);
                            str_profile_image = snapshot.child(str_phone_no1).child("profile_image").getValue(String.class);
                            str_phone_no2 = snapshot.child(str_phone_no1).child("phoneno2").getValue(String.class);
                            str_password = snapshot.child(str_phone_no1).child("password").getValue(String.class);
                            for (Integer i = 0; i < str_password.length(); ++i) {
                                ch = str_password.charAt(i);
                                if (ch >= 'a' && ch <= 'z') {
                                    ch = (char) (ch - key);

                                    if (ch < 'a') {
                                        ch = (char) (ch + 'z' - 'a' + 1);
                                    }

                                    decryptedPassword += ch;
                                } else if (ch >= 'A' && ch <= 'Z') {
                                    ch = (char) (ch - key);

                                    if (ch < 'A') {
                                        ch = (char) (ch + 'Z' - 'A' + 1);
                                    }

                                    decryptedPassword += ch;
                                } else {
                                    decryptedPassword += ch;
                                }
                            }
                            str_email = snapshot.child(str_phone_no1).child("email").getValue(String.class);
                            str_licence_no = snapshot.child(str_phone_no1).child("licence_no").getValue(String.class);
                            str_address_proof_no = snapshot.child(str_phone_no1).child("address_proof_no").getValue(String.class);
                            str_address = snapshot.child(str_phone_no1).child("address").getValue(String.class);
                            str_area = snapshot.child(str_phone_no1).child("area").getValue(String.class);
                            str_city = snapshot.child(str_phone_no1).child("city").getValue(String.class);
                            str_state = snapshot.child(str_phone_no1).child("state").getValue(String.class);
                            str_pincode = snapshot.child(str_phone_no1).child("pincode").getValue(String.class);
                            str_utype = snapshot.child(str_phone_no1).child("utype").getValue(String.class);
                            Toast.makeText(SplashScreenActivity.this,"Welcome To Rent-A-Savari "+str_first_name,Toast.LENGTH_SHORT).show();
                            String str_utype = snapshot.child(str_phone_no1).child("utype").getValue(String.class);
                            if(str_utype.equals("Admin"))
                            {
                                Intent i = new Intent(SplashScreenActivity.this,Admin_DashBoard.class);
                                i.putExtra("first_name",str_first_name);
                                i.putExtra("last_name",str_last_name);
                                i.putExtra("profile_image",str_profile_image);
                                i.putExtra("phoneno1",str_phone_no1);
                                i.putExtra("phoneno2",str_phone_no2);
                                i.putExtra("email",str_email);
                                i.putExtra("password",decryptedPassword);
                                i.putExtra("licence_no",str_licence_no);
                                i.putExtra("address_proof_no",str_address_proof_no);
                                i.putExtra("address",str_address);
                                i.putExtra("area",str_area);
                                i.putExtra("city",str_city);
                                i.putExtra("state",str_state);
                                i.putExtra("pincode",str_pincode);
                                i.putExtra("utype",str_utype);
                                startActivity(i);
                                finish();
                            }
                            else
                            {
                                Intent i = new Intent(SplashScreenActivity.this,MainActivity.class);
                                i.putExtra("first_name",str_first_name);
                                i.putExtra("last_name",str_last_name);
                                i.putExtra("profile_image",str_profile_image);
                                i.putExtra("phoneno1",str_phone_no1);
                                i.putExtra("phoneno2",str_phone_no2);
                                i.putExtra("email",str_email);
                                i.putExtra("password",decryptedPassword);
                                i.putExtra("licence_no",str_licence_no);
                                i.putExtra("address_proof_no",str_address_proof_no);
                                i.putExtra("address",str_address);
                                i.putExtra("area",str_area);
                                i.putExtra("city",str_city);
                                i.putExtra("state",str_state);
                                i.putExtra("pincode",str_pincode);
                                i.putExtra("utype",str_utype);
                                startActivity(i);
                                finish();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }, timeOut);
        }
        else
        {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(SplashScreenActivity.this,Login.class);
                    startActivity(i);
                    finish();
                }
            }, timeOut);
        }
    }

    private boolean isConnected(Activity activity) {
        ConnectivityManager connectivityManager = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifiConn = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobileConn = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if ((wifiConn != null && wifiConn.isConnected()) || (mobileConn != null && mobileConn.isConnected()))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    private void showCustomDialog(Activity activity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setMessage("Please Connect To The Internet")
                .setCancelable(false);
        builder.setPositiveButton("Connect", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
                activity.finishAffinity();
                System.exit(0);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                dialog.dismiss();
            }
        });
        builder.show();
    }
}