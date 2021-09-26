package com.example.rent4u;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddCourseActivity extends AppCompatActivity {

    private TextInputEditText vehicleNameEdt, vehiclePriceEdt, vehicleSuitedForEdt, vehicleImgEdt, vehicleLinkEdt, vehicleDescEdt;
    private Button addVehicleBtn;
    private ProgressBar loadingPB;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private String vehicleID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);
        vehicleNameEdt = findViewById(R.id.idEdtVehicleName);
        vehiclePriceEdt = findViewById(R.id.idEdtVehiclePrice);
        vehicleSuitedForEdt = findViewById(R.id.idEdtVehicleSuitableFor);
        vehicleImgEdt = findViewById(R.id.idEdtVehicleImageLink);
        vehicleLinkEdt = findViewById(R.id.idEdtVehicleLink);
        vehicleDescEdt = findViewById(R.id.idEdtVehicleDescription);
        addVehicleBtn = findViewById(R.id.idBtnAddVehicle);
        loadingPB = findViewById(R.id.idPBLoading);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Vehicles");

        addVehicleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadingPB.setVisibility(View.VISIBLE);
                String vehicleName = vehicleNameEdt.getText().toString();
                String vehiclePrice = vehiclePriceEdt.getText().toString();
                String suitedFor = vehicleSuitedForEdt.getText().toString();
                String vehicleImg = vehicleImgEdt.getText().toString();
                String vehicleLink = vehicleLinkEdt.getText().toString();
                String vehicleDesc = vehicleDescEdt.getText().toString();
                vehicleID = vehicleName;
                CourseRVModal courseRVModal = new CourseRVModal(vehicleName,vehicleDesc,vehiclePrice,suitedFor,vehicleImg,vehicleLink,vehicleID);

                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        loadingPB.setVisibility(View.GONE);
                        databaseReference.child(vehicleID).setValue(courseRVModal);
                        Toast.makeText(AddCourseActivity.this, "Vehicle Added..", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(AddCourseActivity.this, MainActivity.class));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(AddCourseActivity.this,"Error is" +error.toString(), Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });
    }
}