package com.example.myrasdemo;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class Add_Car extends AppCompatActivity {

    ImageView car_image;
    EditText car_name, car_no_plate, body_type, seat_capacity, fuel_type, transmission_type, rent_price;
    Button addCarBtn;
    FirebaseDatabase rootNode;
    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("car_M");;
    FirebaseStorage storage;
    ProgressDialog progressDialog;
    ActivityResultLauncher<String> launcher;
    FloatingActionButton car_image_fltbtn;
    Boolean check = false;
    private String str_body_type, str_car_name, str_car_no_plate, str_fuel_type, str_is_active, str_rent_price, str_seat_capacity, str_status, str_transmission_type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car);
        car_image = findViewById(R.id.Addcar);
        car_name = findViewById(R.id.Add_Name);
        car_no_plate = findViewById(R.id.Add_CarNumberPlate);
        body_type = findViewById(R.id.Add_Bodytype);
        seat_capacity = findViewById(R.id.Add_CarSeatCap);
        fuel_type = findViewById(R.id.Add_FuelType);
        addCarBtn = findViewById(R.id.Add_Car_btn);
        transmission_type = findViewById(R.id.Add_TransmissionType);
        rent_price = findViewById(R.id.Add_Rent);
        storage = FirebaseStorage.getInstance();
        launcher = registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
            @Override
            public void onActivityResult(Uri uri) {
                car_image.setImageURI(uri);
                final StorageReference reference_strg = storage.getReference().child(str_car_no_plate).child("car_image");
                reference_strg.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        reference_strg.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                reference.child(str_car_no_plate).child("car_image").setValue(uri.toString()).addOnSuccessListener(new OnSuccessListener<Void>() {
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
        car_image_fltbtn = findViewById(R.id.capture_car_image);
        car_image_fltbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check =true;
                str_car_no_plate = car_no_plate.getText().toString();
                if (str_car_no_plate.equals(""))
                {
                    Toast.makeText(Add_Car.this, "Enter Car Number First", Toast.LENGTH_SHORT).show();
                }
                else
                {launcher.launch("image/*");
                    progressDialog = new ProgressDialog(Add_Car.this);
                    progressDialog.show();
                    progressDialog.setContentView(R.layout.progress_dialog);
                    progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                }
                }

        });

        addCarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (check)
                {
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference().child("car_M");
                str_car_name = car_name.getText().toString();
                str_car_no_plate = car_no_plate.getText().toString();
                str_seat_capacity = seat_capacity.getText().toString();
                str_fuel_type = fuel_type.getText().toString();
                str_transmission_type = transmission_type.getText().toString();
                str_rent_price = rent_price.getText().toString();
                str_body_type = body_type.getText().toString();
                str_is_active = "Active";
                str_status = "Available";
                AddCarHelperClass addCarHelperClass = new AddCarHelperClass(str_body_type, str_car_name, str_car_no_plate, str_fuel_type, str_is_active, str_rent_price, str_seat_capacity, str_status, str_transmission_type);
                reference.child(str_car_no_plate).setValue(addCarHelperClass);
                Toast.makeText(Add_Car.this, "Car Uploaded Successfully", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(Add_Car.this, "Upload Car Image", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}