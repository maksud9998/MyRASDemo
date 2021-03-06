package com.example.myrasdemo;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class EditProfile extends AppCompatActivity {
    TextView verify_link;
    EditText phone_no1, phone_no2, email, last_name, first_name, password, address, area, city, state,pincode, license_no, address_proof_no;
    Button updatebtn;
    CircleImageView profile_image;
    FloatingActionButton capture_profile_image;
    ActivityResultLauncher<String> launcher;
    FirebaseStorage storage;
    String encryptedPassword = "", decryptedPassword = "";
    private DatabaseReference reference = FirebaseDatabase.getInstance().getReference("user_M");
    String str_first_name, str_last_name, str_profile_image, str_phone_no1, str_phone_no2, str_email, str_password, str_licence_no, str_address_proof_no, str_address, str_area, str_city, str_state, str_pincode;
    ProgressDialog progressDialog;
    Integer key=1;
    Character ch;
    private static int timeOut=500;
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
        address_proof_no = findViewById(R.id.address_proof_no);
        profile_image = findViewById(R.id.Profile_Image);
        capture_profile_image = findViewById(R.id.capture_profile_image);
        Intent intent = getIntent();
        str_first_name = intent.getStringExtra("first_name");
        str_last_name = intent.getStringExtra("last_name");
        str_profile_image = intent.getStringExtra("profile_image");
        str_phone_no1 = intent.getStringExtra("phoneno1");
        str_phone_no2 = intent.getStringExtra("phoneno2");
        str_email = intent.getStringExtra("email");
        str_password = intent.getStringExtra("password");
        str_licence_no = intent.getStringExtra("licence_no");
        str_address_proof_no = intent.getStringExtra("address_proof_no");
        str_address = intent.getStringExtra("address");
        str_area = intent.getStringExtra("area");
        str_city = intent.getStringExtra("city");
        str_state = intent.getStringExtra("state");
        str_pincode = intent.getStringExtra("pincode");
        first_name.setText(str_first_name);
        last_name.setText(str_last_name);
        phone_no1.setText(str_phone_no1);
        phone_no2.setText(str_phone_no2);
        email.setText(str_email);
        password.setText(str_password);
        license_no.setText(str_licence_no);
        address_proof_no.setText(str_address_proof_no);
        address.setText(str_address);
        area.setText(str_area);
        city.setText(str_city);
        state.setText(str_state);
        pincode.setText(str_pincode);
        storage = FirebaseStorage.getInstance();
        reference.child(str_phone_no1).child("profile_image").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String image = snapshot.getValue(String.class);
                Picasso.get().load(image).into(profile_image);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        launcher = registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
            @Override
            public void onActivityResult(Uri uri) {
                profile_image.setImageURI(uri);
                final StorageReference reference_strg = storage.getReference().child(str_phone_no1).child("profile_image");
                reference_strg.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        reference_strg.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                reference.child(str_phone_no1).child("profile_image").setValue(uri.toString()).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        progressDialog.dismiss();
                                    }
                                });
                            }
                        });
                    }
                });
            }
        });

        capture_profile_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launcher.launch("image/*");
                progressDialog = new ProgressDialog(EditProfile.this);
                progressDialog.show();
                progressDialog.setContentView(R.layout.progress_dialog);
                progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            }
        });

        verify_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(EditProfile.this,UploadDocs.class);
                i.putExtra("phoneno1",str_phone_no1);
                startActivity(i);
            }
        });

        updatebtn = findViewById(R.id.submit);
        updatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog = new ProgressDialog(EditProfile.this);
                progressDialog.show();
                progressDialog.setContentView(R.layout.progress_dialog);
                progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        String str_Newpassword = password.getText().toString();
                        for(Integer i = 0; i < str_Newpassword.length(); ++i){
                            ch = str_Newpassword.charAt(i);
                            if(ch >= 'a' && ch <= 'z'){
                                ch = (char)(ch + key);
                                if(ch > 'z'){
                                    ch = (char)(ch - 'z' + 'a' - 1);
                                }
                                encryptedPassword += ch;
                            }
                            else if(ch >= 'A' && ch <= 'Z'){
                                ch = (char)(ch + key);
                                if(ch > 'Z'){
                                    ch = (char)(ch - 'Z' + 'A' - 1);
                                }
                                encryptedPassword += ch;
                            }
                            else {
                                encryptedPassword += ch;
                            }
                        }

                        reference.child(str_phone_no1).child("first_name").setValue(first_name.getText().toString());
                        reference.child(str_phone_no1).child("last_name").setValue(last_name.getText().toString());
                        reference.child(str_phone_no1).child("phoneno1").setValue(phone_no1.getText().toString());
                        reference.child(str_phone_no1).child("phoneno2").setValue(phone_no2.getText().toString());
                        reference.child(str_phone_no1).child("email").setValue(email.getText().toString());
                        reference.child(str_phone_no1).child("password").setValue(encryptedPassword);
                        reference.child(str_phone_no1).child("licence_no").setValue(license_no.getText().toString());
                        reference.child(str_phone_no1).child("address_proof_no").setValue(address_proof_no.getText().toString());
                        reference.child(str_phone_no1).child("address").setValue(address.getText().toString());
                        reference.child(str_phone_no1).child("area").setValue(area.getText().toString());
                        reference.child(str_phone_no1).child("city").setValue(city.getText().toString());
                        reference.child(str_phone_no1).child("state").setValue(state.getText().toString());
                        reference.child(str_phone_no1).child("pincode").setValue(pincode.getText().toString());
                        Query checkUser = reference.orderByChild("phoneno1").equalTo(str_phone_no1);
                        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if (snapshot.exists())
                                {
                                    String str_password = snapshot.child(str_phone_no1).child("password").getValue(String.class);
                                    String str_first_name = snapshot.child(str_phone_no1).child("first_name").getValue(String.class);
                                    String str_last_name = snapshot.child(str_phone_no1).child("last_name").getValue(String.class);
                                    String str_profile_image = snapshot.child(str_phone_no1).child("profile_image").getValue(String.class);
                                    String str_phone_no2 = snapshot.child(str_phone_no1).child("phoneno2").getValue(String.class);
                                    String str_email = snapshot.child(str_phone_no1).child("email").getValue(String.class);
                                    String str_licence_no = snapshot.child(str_phone_no1).child("licence_no").getValue(String.class);
                                    String str_address_proof_no = snapshot.child(str_phone_no1).child("address_proof_no").getValue(String.class);
                                    String str_address = snapshot.child(str_phone_no1).child("address").getValue(String.class);
                                    String str_area = snapshot.child(str_phone_no1).child("area").getValue(String.class);
                                    String str_city = snapshot.child(str_phone_no1).child("city").getValue(String.class);
                                    String str_state = snapshot.child(str_phone_no1).child("state").getValue(String.class);
                                    String str_pincode = snapshot.child(str_phone_no1).child("pincode").getValue(String.class);
                                    Toast.makeText(EditProfile.this,"Profile Successfully Updated",Toast.LENGTH_SHORT).show();
                                    Intent i = new Intent(EditProfile.this,MainActivity.class);
                                    i.putExtra("first_name",str_first_name);
                                    i.putExtra("last_name",str_last_name);
                                    i.putExtra("profile_image",str_profile_image);
                                    i.putExtra("phoneno1",str_phone_no1);
                                    i.putExtra("phoneno2",str_phone_no2);
                                    i.putExtra("email",str_email);
                                    for (Integer iD = 0; iD < str_password.length(); ++iD) {
                                        ch = str_password.charAt(iD);
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
                                    i.putExtra("password",decryptedPassword);
                                    i.putExtra("licence_no",str_licence_no);
                                    i.putExtra("address_proof_no",str_address_proof_no);
                                    i.putExtra("address",str_address);
                                    i.putExtra("area",str_area);
                                    i.putExtra("city",str_city);
                                    i.putExtra("state",str_state);
                                    i.putExtra("pincode",str_pincode);
                                    startActivity(i);
                                    finish();
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                            }
                        });
                    }
                },timeOut);
            }
        });
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
                Task<Void> reference;
                reference = FirebaseDatabase.getInstance().getReference().child("user_M").child(str_phone_no1).setValue(null);
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