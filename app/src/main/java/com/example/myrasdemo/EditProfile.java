package com.example.myrasdemo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class EditProfile extends AppCompatActivity {
    TextView verify_link;
    EditText phone_no1, phone_no2, email, last_name, first_name, password, address, area, city, state,pincode, license_no;
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
        email = findViewById(R.id.Email);
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
        Intent i = getIntent();
        String str_first_name = i.getStringExtra("first_name");
        String str_last_name = i.getStringExtra("last_name");
        String str_phone_no1 = i.getStringExtra("phoneno1");
        String str_phone_no2 = i.getStringExtra("phoneno2");
        String str_email = i.getStringExtra("email");
        String str_password = i.getStringExtra("password");
        String str_licence_no = i.getStringExtra("licence_no");
        String str_address = i.getStringExtra("address");
        String str_area = i.getStringExtra("area");
        String str_city = i.getStringExtra("city");
        String str_state = i.getStringExtra("state");
        String str_pincode = i.getStringExtra("pincode");
        first_name.setText(str_first_name);
        last_name.setText(str_last_name);
        phone_no1.setText(str_phone_no1);
        phone_no2.setText(str_phone_no2);
        email.setText(str_email);
        password.setText(str_password);
        license_no.setText(str_licence_no);
        address.setText(str_address);
        area.setText(str_area);
        city.setText(str_city);
        state.setText(str_state);
        pincode.setText(str_pincode);

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
                Toast.makeText(EditProfile.this,"Profile Successfully Updated",Toast.LENGTH_SHORT).show();
                Intent i = new Intent(EditProfile.this,MainActivity.class);
                startActivity(i);
                finish();
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
                Toast.makeText(activity.getApplicationContext(), "Profile Deactivated Successfully",Toast.LENGTH_SHORT).show();
                Intent i = new Intent(EditProfile.this,Login.class);
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