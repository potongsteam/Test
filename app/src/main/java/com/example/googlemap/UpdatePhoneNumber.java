package com.example.googlemap;

import android.database.DatabaseUtils;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UpdatePhoneNumber extends AppCompatActivity {

    DBHandler myDB; // Database handler
    TextView oldPhone; // TextView to display the current phone number
    EditText newPhone; // EditText to input the new phone number
    Button updatePhone; // Button to trigger phone number update
    String phone; // Store the phone number retrieved from the intent

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_phone_number);

        // Initialize the database handler
        myDB = new DBHandler(UpdatePhoneNumber.this);

        // Find views in the layout by their IDs
        oldPhone = (TextView) findViewById(R.id.currentPhone);
        newPhone = (EditText) findViewById(R.id.newPhone);
        updatePhone = (Button) findViewById(R.id.updateButton);

        // Retrieve the phone number from the intent
        phone = getIntent().getStringExtra("phone");

        // Set the new phone number EditText to display the retrieved phone number
        newPhone.setText(phone);

        // Set a click listener for the update button
        updatePhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Call the updatePhone method in the database handler to update the phone number
                myDB.updatePhone(phone, newPhone.getText().toString());

                // Display a toast message to indicate that the phone number has been updated
                Toast.makeText(UpdatePhoneNumber.this, "Phone Number Updated!",
                        Toast.LENGTH_SHORT).show();

                // Update the TextView to display the new phone number
                oldPhone.setText(newPhone.getText().toString());
            }
        });
    }
}
