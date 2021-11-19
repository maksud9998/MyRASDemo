package com.example.myrasdemo;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class UploadCarImages extends AppCompatActivity {
    ImageView car_front,car_back,car_right,car_left,car_speedometer;
    FloatingActionButton capture_car_front, capture_car_back,capture_car_right,capture_car_left,capture_car_speedometer;
    ActivityResultLauncher<String> launcher1, launcher2, launcher3, launcher4, launcher5;
    String str_booking_id;
    FirebaseStorage storage;
    ProgressDialog progressDialog;
    private static int timeOut=500;
    private DatabaseReference reference = FirebaseDatabase.getInstance().getReference("booking_M");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_car_images);
        Intent i = getIntent();
        str_booking_id = i.getStringExtra("booking_id");
        car_front = findViewById(R.id.car_front);
        car_back = findViewById(R.id.car_back);
        car_right = findViewById(R.id.car_right);
        car_left = findViewById(R.id.car_left);
        car_speedometer = findViewById(R.id.car_speedometer);
        capture_car_front = findViewById(R.id.capture_car_front);
        capture_car_back = findViewById(R.id.capture_car_back);
        capture_car_right = findViewById(R.id.capture_car_right);
        capture_car_left = findViewById(R.id.capture_car_left);
        capture_car_speedometer = findViewById(R.id.capture_car_speedometer);
        if (MainActivity.getInstance().startCheck || MainActivity.getInstance().endCheck)
        {
            MainActivity.getInstance().showCarList();
        }
        progressBar();
        storage = FirebaseStorage.getInstance();
        reference.child(str_booking_id).child("car_front_image").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String image = snapshot.getValue(String.class);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Picasso.get().load(image).into(car_front);
                    }
                },timeOut);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        launcher1 = registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
            @Override
            public void onActivityResult(Uri uri) {
                car_front.setImageURI(uri);
                final StorageReference reference_strg = storage.getReference().child(str_booking_id).child("car_front_image");
                reference_strg.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        reference_strg.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                reference.child(str_booking_id).child("car_front_image").setValue(uri.toString()).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        progressDialog.dismiss();
                                        Toast.makeText(UploadCarImages.this,"Car Front Side Image Uploaded", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        });
                    }
                });
            }
        });

        reference.child(str_booking_id).child("car_back_image").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String image = snapshot.getValue(String.class);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Picasso.get().load(image).into(car_back);
                    }
                },timeOut);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        launcher2 = registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
            @Override
            public void onActivityResult(Uri uri) {
                car_back.setImageURI(uri);
                final StorageReference reference_strg = storage.getReference().child(str_booking_id).child("car_back_image");
                reference_strg.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        reference_strg.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                reference.child(str_booking_id).child("car_back_image").setValue(uri.toString()).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        progressDialog.dismiss();
                                        Toast.makeText(UploadCarImages.this,"Car Back Side Image Uploaded", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        });
                    }
                });
            }
        });

        reference.child(str_booking_id).child("car_right_image").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String image = snapshot.getValue(String.class);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Picasso.get().load(image).into(car_right);
                    }
                },timeOut);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        launcher3 = registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
            @Override
            public void onActivityResult(Uri uri) {
                car_right.setImageURI(uri);
                final StorageReference reference_strg = storage.getReference().child(str_booking_id).child("car_right_image");
                reference_strg.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        reference_strg.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                reference.child(str_booking_id).child("car_right_image").setValue(uri.toString()).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        progressDialog.dismiss();
                                        Toast.makeText(UploadCarImages.this,"Car Right Side Image Uploaded", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        });
                    }
                });
            }
        });

        reference.child(str_booking_id).child("car_left_image").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String image = snapshot.getValue(String.class);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Picasso.get().load(image).into(car_left);
                    }
                },timeOut);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        launcher4 = registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
            @Override
            public void onActivityResult(Uri uri) {
                car_left.setImageURI(uri);
                final StorageReference reference_strg = storage.getReference().child(str_booking_id).child("car_left_image");
                reference_strg.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        reference_strg.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                reference.child(str_booking_id).child("car_left_image").setValue(uri.toString()).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        progressDialog.dismiss();
                                        Toast.makeText(UploadCarImages.this,"Car Left Side Image Uploaded", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        });
                    }
                });
            }
        });

        reference.child(str_booking_id).child("car_speedometer_image").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String image = snapshot.getValue(String.class);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Picasso.get().load(image).into(car_speedometer);
                        progressDialog.dismiss();
                    }
                },timeOut);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        launcher5 = registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
            @Override
            public void onActivityResult(Uri uri) {
                car_speedometer.setImageURI(uri);
                final StorageReference reference_strg = storage.getReference().child(str_booking_id).child("car_speedometer_image");
                reference_strg.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        reference_strg.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                reference.child(str_booking_id).child("car_speedometer_image").setValue(uri.toString()).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        progressDialog.dismiss();
                                        Task<Void> referenceBooking;
                                        referenceBooking = FirebaseDatabase.getInstance().getReference().child("booking_M").child(str_booking_id).child("status").setValue("Trip Started");
                                        PreviousBooking.getInstance().content();
                                        Toast.makeText(UploadCarImages.this,"Car Speedometer Image Uploaded", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        });
                    }
                });
            }
        });

        capture_car_front.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launcher1.launch("image/*");
                progressBar();

//                Toast.makeText(UploadCarImages.this,"Capture Photo In Landscape Mode", Toast.LENGTH_SHORT).show();
//                ImagePicker.with(UploadCarImages.this).cameraOnly().crop(16f,9f).compress(1024).maxResultSize(1080, 1080).start(1);
            }
        });
        capture_car_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launcher2.launch("image/*");
                progressBar();

//                Toast.makeText(UploadCarImages.this,"Capture Photo In Landscape Mode", Toast.LENGTH_SHORT).show();
//                ImagePicker.with(UploadCarImages.this).cameraOnly().crop(16f,9f).compress(1024).maxResultSize(1080, 1080).start(2);
            }
        });
        capture_car_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launcher3.launch("image/*");
                progressBar();

//                Toast.makeText(UploadCarImages.this,"Capture Photo In Landscape Mode", Toast.LENGTH_SHORT).show();
//                ImagePicker.with(UploadCarImages.this).cameraOnly().crop(16f,9f).compress(1024).maxResultSize(1080, 1080).start(3);
            }
        });
        capture_car_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launcher4.launch("image/*");
                progressBar();

//                Toast.makeText(UploadCarImages.this,"Capture Photo In Landscape Mode", Toast.LENGTH_SHORT).show();
//                ImagePicker.with(UploadCarImages.this).cameraOnly().crop(16f,9f).compress(1024).maxResultSize(1080, 1080).start(4);
            }
        });
        capture_car_speedometer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launcher5.launch("image/*");
                progressBar();

//                Toast.makeText(UploadCarImages.this,"Capture Photo In Landscape Mode", Toast.LENGTH_SHORT).show();
//                ImagePicker.with(UploadCarImages.this).cameraOnly().crop(16f,9f).compress(1024).maxResultSize(1080, 1080).start(5);
            }
        });
    }

    private void progressBar() {
        progressDialog = new ProgressDialog(UploadCarImages.this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
    }
}