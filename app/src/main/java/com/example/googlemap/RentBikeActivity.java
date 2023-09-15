package com.example.googlemap;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
public class RentBikeActivity extends AppCompatActivity {
    private ImageView imageView, imageView2, imageView3;
    private Button button1, button2, button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rent_bike);

        imageView = findViewById(R.id.imageView);
        button1 = findViewById(R.id.button1);

        // Setting click listener for the button
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageView.setImageResource(R.drawable.trek_marlin5gen2_volmgf_m_0w);
                Intent intent = new Intent(RentBikeActivity.this, Rent_Bike_1_Activity.class);
                startActivity(intent);

            }
        });

        // Bike 2
        imageView2 = findViewById(R.id.imageView2);
        button2 = findViewById(R.id.button2);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageView2.setImageResource(R.drawable._1321_71_epic_ht_carb_rktred_hero_1200x800);
                Intent intent = new Intent(RentBikeActivity.this, Rent_Bike_1_Activity.class);
                startActivity(intent);
            }
        });

        // Bike 3
        imageView3 = findViewById(R.id.imageView3);
        button3 = findViewById(R.id.button3);

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageView3.setImageResource(R.drawable._90106_1915243_6);
                Intent intent = new Intent(RentBikeActivity.this, Rent_Bike_1_Activity.class);
                startActivity(intent);
            }
        });
    }
}