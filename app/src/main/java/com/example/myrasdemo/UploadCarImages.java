package com.example.myrasdemo;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
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
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class UploadCarImages extends AppCompatActivity {
    ImageView car_front,car_back,car_right,car_left,car_speedometer;
    FloatingActionButton capture_car_front, capture_car_back,capture_car_right,capture_car_left,capture_car_speedometer;
    Button continuebtn;
    TextView bookingid_dropdown;
    ArrayList<String> arrayList;
    Dialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_car_images);
        bookingid_dropdown = findViewById(R.id.booking_id_dropdown);
        arrayList = new ArrayList<>();
        arrayList.add("1");
        arrayList.add("2");
        arrayList.add("3");
        arrayList.add("4");
        arrayList.add("5");
        arrayList.add("6");
        arrayList.add("7");
        arrayList.add("8");
        arrayList.add("9");
        arrayList.add("10");
        bookingid_dropdown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new Dialog(UploadCarImages.this);
                dialog.setContentView(R.layout.dialog_searchable_spinner);
                dialog.getWindow().setLayout(650,800);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
                EditText editText = dialog.findViewById(R.id.edit_text);
                ListView listView  = dialog.findViewById(R.id.list_view);
                ArrayAdapter<String> adapter = new ArrayAdapter<>(UploadCarImages.this, android.R.layout.simple_expandable_list_item_1,arrayList);
                listView.setAdapter(adapter);
                editText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        adapter.getFilter().filter(s);
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        bookingid_dropdown.setText(adapter.getItem(position));
                        dialog.dismiss();
                    }
                });
            }
        });
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