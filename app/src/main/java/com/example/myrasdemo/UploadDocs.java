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

public class UploadDocs extends AppCompatActivity {
    ImageView licence_front,licence_back,address_proof_front,address_proof_back;
    FloatingActionButton capture_licence_front, capture_licence_back,capture_address_proof_front,capture_address_proof_back;
    Button continuebtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_docs);
        licence_front = findViewById(R.id.licence_front);
        licence_back = findViewById(R.id.licence_back);
        address_proof_front = findViewById(R.id.address_proof_front);
        address_proof_back = findViewById(R.id.address_proof_back);
        capture_licence_front = findViewById(R.id.capture_licence_front);
        capture_licence_back = findViewById(R.id.capture_licence_back);
        capture_address_proof_front = findViewById(R.id.capture_address_proof_front);
        capture_address_proof_back = findViewById(R.id.capture_address_proof_back);

        capture_licence_front.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(UploadDocs.this,"Capture Photo In Landscape Mode", Toast.LENGTH_SHORT).show();
                ImagePicker.with(UploadDocs.this).crop(16f,9f).compress(1024).maxResultSize(1080, 1080).start(1);
            }
        });
        capture_licence_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(UploadDocs.this,"Capture Photo In Landscape Mode", Toast.LENGTH_SHORT).show();
                ImagePicker.with(UploadDocs.this).crop(16f,9f).compress(1024).maxResultSize(1080, 1080).start(2);
            }
        });
        capture_address_proof_front.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(UploadDocs.this,"Capture Photo In Landscape Mode", Toast.LENGTH_SHORT).show();
                ImagePicker.with(UploadDocs.this).crop(16f,9f).compress(1024).maxResultSize(1080, 1080).start(3);
            }
        });
        capture_address_proof_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(UploadDocs.this,"Capture Photo In Landscape Mode", Toast.LENGTH_SHORT).show();
                ImagePicker.with(UploadDocs.this).crop(16f,9f).compress(1024).maxResultSize(1080, 1080).start(4);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1)
        {
            Uri uri = data.getData();
            licence_front.setImageURI(uri);
        }
        else if (requestCode==2)
        {
            Uri uri = data.getData();
            licence_back.setImageURI(uri);
        }
        else if (requestCode==3)
        {
            Uri uri = data.getData();
            address_proof_front.setImageURI(uri);
        }
        else if (requestCode==4)
        {
            Uri uri = data.getData();
            address_proof_back.setImageURI(uri);
        }
    }
}