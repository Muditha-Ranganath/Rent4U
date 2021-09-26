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

import java.util.HashMap;
import java.util.Map;

public class EditCourseActivity extends AppCompatActivity {

    private TextInputEditText vehicleNameEdt, vehiclePriceEdt, vehicleSuitedForEdt, vehicleImgEdt, vehicleLinkEdt, vehicleDescEdt;
    private Button updateVehicleBtn,deleteVehicleBtn;
    private ProgressBar loadingPB;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private String vehicleID;
    private CourseRVModal courseRVModal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_course);
        firebaseDatabase = FirebaseDatabase.getInstance();
        vehicleNameEdt = findViewById(R.id.idEdtVehicleName);
        vehiclePriceEdt = findViewById(R.id.idEdtVehiclePrice);
        vehicleSuitedForEdt = findViewById(R.id.idEdtVehicleSuitableFor);
        vehicleImgEdt = findViewById(R.id.idEdtVehicleImageLink);
        vehicleLinkEdt = findViewById(R.id.idEdtVehicleLink);
        vehicleDescEdt = findViewById(R.id.idEdtVehicleDescription);
        updateVehicleBtn = findViewById(R.id.idBtnUpdateVehicle);
        deleteVehicleBtn = findViewById(R.id.idBtnDeleteVehicle);
        loadingPB = findViewById(R.id.idPBLoading);
        courseRVModal = getIntent().getParcelableExtra("vehicle");
        if(courseRVModal!=null){
            vehicleNameEdt.setText(courseRVModal.getVehicleName());
            vehiclePriceEdt.setText(courseRVModal.getVehiclePrice());
            vehicleSuitedForEdt.setText(courseRVModal.getBestSuitedFor());
            vehicleImgEdt.setText(courseRVModal.getVehicleImg());
            vehicleLinkEdt.setText(courseRVModal.getVehicleLink());
            vehicleDescEdt.setText(courseRVModal.getVehicleDescription());
            vehicleID = courseRVModal.getVehicleID();
        }

        databaseReference = firebaseDatabase.getReference("Vehicles").child(vehicleID);
        updateVehicleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadingPB.setVisibility(View.VISIBLE);
                String vehicleName = vehicleNameEdt.getText().toString();
                String vehiclePrice = vehiclePriceEdt.getText().toString();
                String suitedFor = vehicleSuitedForEdt.getText().toString();
                String vehicleImg = vehicleImgEdt.getText().toString();
                String vehicleLink = vehicleLinkEdt.getText().toString();
                String vehicleDesc = vehicleDescEdt.getText().toString();

                Map<String,Object> map = new HashMap<>();
                map.put("vehicleName",vehicleName);
                map.put("vehicleDescription",vehicleDesc);
                map.put("vehiclePrice",vehiclePrice);
                map.put("bestSuitedFor",suitedFor);
                map.put("vehicleImg",vehicleImg);
                map.put("vehicleLink",vehicleLink);
                map.put("vehicleID",vehicleID);

                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        loadingPB.setVisibility(View.GONE);
                        databaseReference.updateChildren(map);
                        Toast.makeText(EditCourseActivity.this, "Vehicle Updated..", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(EditCourseActivity.this, MainActivity.class));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(EditCourseActivity.this, "Fail to update vehicle..", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        deleteVehicleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteVehicle();
            }
        });
    }

    private void deleteVehicle(){
        databaseReference.removeValue();
        Toast.makeText(this, "Vehicle Deleted..", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(EditCourseActivity.this, MainActivity.class));
    }
}