package com.example.googlemap;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import android.app.NotificationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

public class Notifications extends AppCompatActivity {

    private Switch sw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notifications);

        sw = findViewById(R.id.onOff);

        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (sw.isChecked()) {
                    // Notifications are turned on
                    // You can perform actions like enabling notifications here
                    Toast.makeText(Notifications.this, "Notifications turned on", Toast.LENGTH_SHORT).show();
                } else {
                    // Notifications are turned off
                    // You can perform actions like disabling notifications here
                    Toast.makeText(Notifications.this, "Notifications turned off", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}