package com.example.myrasdemo;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnSuccessListener;
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

public class UploadDocs extends AppCompatActivity {
    ImageView licence_front,licence_back,address_proof_front,address_proof_back;
    FloatingActionButton capture_licence_front, capture_licence_back,capture_address_proof_front,capture_address_proof_back;
    ActivityResultLauncher<String> launcher1, launcher2, launcher3, launcher4;
    String str_phoneon1;
    FirebaseStorage storage;
    private DatabaseReference reference = FirebaseDatabase.getInstance().getReference("user_M");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_docs);
        Intent i = getIntent();
        str_phoneon1 = i.getStringExtra("phoneno1");
        licence_front = findViewById(R.id.licence_front);
        licence_back = findViewById(R.id.licence_back);
        address_proof_front = findViewById(R.id.address_proof_front);
        address_proof_back = findViewById(R.id.address_proof_back);
        capture_licence_front = findViewById(R.id.capture_licence_front);
        capture_licence_back = findViewById(R.id.capture_licence_back);
        capture_address_proof_front = findViewById(R.id.capture_address_proof_front);
        capture_address_proof_back = findViewById(R.id.capture_address_proof_back);
        storage = FirebaseStorage.getInstance();
        reference.child(str_phoneon1).child("licence_front_image").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String image = snapshot.getValue(String.class);
                Picasso.get().load(image).into(licence_front);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        launcher1 = registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
            @Override
            public void onActivityResult(Uri uri) {
                licence_front.setImageURI(uri);
                final StorageReference reference_strg = storage.getReference().child(str_phoneon1).child("licence_front_image");
                reference_strg.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        reference_strg.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                reference.child(str_phoneon1).child("licence_front_image").setValue(uri.toString()).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(UploadDocs.this,"Licence Front Image Uploaded", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        });
                    }
                });
            }
        });
        reference.child(str_phoneon1).child("licence_back_image").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String image = snapshot.getValue(String.class);
                Picasso.get().load(image).into(licence_back);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        launcher2 = registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
            @Override
            public void onActivityResult(Uri uri) {
                licence_back.setImageURI(uri);
                final StorageReference reference_strg = storage.getReference().child(str_phoneon1).child("licence_back_image");
                reference_strg.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        reference_strg.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                reference.child(str_phoneon1).child("licence_back_image").setValue(uri.toString()).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(UploadDocs.this,"Licence Back Image Uploaded", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        });
                    }
                });
            }
        });
        reference.child(str_phoneon1).child("address_proof_front_image").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String image = snapshot.getValue(String.class);
                Picasso.get().load(image).into(address_proof_front);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        launcher3 = registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
            @Override
            public void onActivityResult(Uri uri) {
                address_proof_front.setImageURI(uri);
                final StorageReference reference_strg = storage.getReference().child(str_phoneon1).child("address_proof_front_image");
                reference_strg.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        reference_strg.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                reference.child(str_phoneon1).child("address_proof_front_image").setValue(uri.toString()).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(UploadDocs.this,"Address Proof Front Image Uploaded", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        });
                    }
                });
            }
        });
        reference.child(str_phoneon1).child("address_proof_back_image").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String image = snapshot.getValue(String.class);
                Picasso.get().load(image).into(address_proof_back);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        launcher4 = registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
            @Override
            public void onActivityResult(Uri uri) {
                address_proof_back.setImageURI(uri);
                final StorageReference reference_strg = storage.getReference().child(str_phoneon1).child("address_proof_back_image");
                reference_strg.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        reference_strg.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                reference.child(str_phoneon1).child("address_proof_back_image").setValue(uri.toString()).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(UploadDocs.this,"Address Proof Back Image Uploaded", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        });
                    }
                });
            }
        });

        capture_licence_front.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launcher1.launch("image/*");
            }
        });
        capture_licence_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launcher2.launch("image/*");
            }
        });
        capture_address_proof_front.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launcher3.launch("image/*");
            }
        });
        capture_address_proof_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launcher4.launch("image/*");
            }
        });
    }
}