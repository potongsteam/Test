package com.example.googlemap;

import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.FirebaseDatabase;
import android.os.Bundle;
import android.content.Intent;
import android.os.Bundle;
import com.google.firebase.database.DatabaseReference;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
public class Rent_Bike_1_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rent_bike1);
// Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://tidal-heading-398318-default-rtdb.asia-southeast1.firebasedatabase.app/");

// Get a DatabaseReference for the "bike" node
        DatabaseReference bikeRef = database.getReference("bike");

// Use the DatabaseReference to set the "status" to false
        bikeRef.child("status").setValue(false);

    }
}