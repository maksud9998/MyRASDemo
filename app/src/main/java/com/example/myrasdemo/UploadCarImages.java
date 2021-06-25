package com.example.myrasdemo;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class UploadCarImages extends AppCompatActivity {
    ImageView car_front,car_back,car_right,car_left,car_speedometer;
    FloatingActionButton capture_car_front, capture_car_back,capture_car_right,capture_car_left,capture_car_speedometer;
    Button continuebtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_car_images);
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
        capture_car_front.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(UploadCarImages.this,"Capture Photo In Landscape Mode", Toast.LENGTH_SHORT).show();
                ImagePicker.with(UploadCarImages.this).cameraOnly().crop(16f,9f).compress(1024).maxResultSize(1080, 1080).start(1);
            }
        });
        capture_car_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(UploadCarImages.this,"Capture Photo In Landscape Mode", Toast.LENGTH_SHORT).show();
                ImagePicker.with(UploadCarImages.this).cameraOnly().crop(16f,9f).compress(1024).maxResultSize(1080, 1080).start(2);
            }
        });
        capture_car_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(UploadCarImages.this,"Capture Photo In Landscape Mode", Toast.LENGTH_SHORT).show();
                ImagePicker.with(UploadCarImages.this).cameraOnly().crop(16f,9f).compress(1024).maxResultSize(1080, 1080).start(3);
            }
        });
        capture_car_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(UploadCarImages.this,"Capture Photo In Landscape Mode", Toast.LENGTH_SHORT).show();
                ImagePicker.with(UploadCarImages.this).cameraOnly().crop(16f,9f).compress(1024).maxResultSize(1080, 1080).start(4);
            }
        });
        capture_car_speedometer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(UploadCarImages.this,"Capture Photo In Landscape Mode", Toast.LENGTH_SHORT).show();
                ImagePicker.with(UploadCarImages.this).cameraOnly().crop(16f,9f).compress(1024).maxResultSize(1080, 1080).start(5);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1)
        {
            Uri uri = data.getData();
            car_front.setImageURI(uri);
        }
        else if (requestCode==2)
        {
            Uri uri = data.getData();
            car_back.setImageURI(uri);
        }
        else if (requestCode==3)
        {
            Uri uri = data.getData();
            car_right.setImageURI(uri);
        }
        else if (requestCode==4)
        {
            Uri uri = data.getData();
            car_left.setImageURI(uri);
        }
        else if (requestCode==5)
        {
            Uri uri = data.getData();
            car_speedometer.setImageURI(uri);
        }
    }
}