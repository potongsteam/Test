package com.example.googlemap;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ChangePassword extends AppCompatActivity {

    // Declare necessary variables and UI elements
    DBHandler myDB; // Database handler
    EditText oldPass; // Input field for old password
    EditText newPass; // Input field for new password
    EditText confNew; // Input field to confirm new password
    Button change; // Button to trigger the password change

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_password); // Set the layout for this activity

        myDB = new DBHandler(this); // Initialize the database handler

        // Initialize UI elements by finding them using their IDs
        oldPass = findViewById(R.id.oldPassword);
        newPass = findViewById(R.id.newPassword);
        confNew = findViewById(R.id.confNew);
        change = findViewById(R.id.change);

        // Define a click listener for the "change" button
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    // Get the values from the input fields
                    String oldPassword = oldPass.getText().toString();
                    String newPassword = newPass.getText().toString();
                    String confirmNewPass = confNew.getText().toString();

                    // Check if the old password matches the one in the database
                    if(!myDB.checkOldPassword(oldPassword))
                    {
                        throw new Exception("Please enter the correct current password!");
                    }

                    // Check if the new password and confirmation match
                    if(!newPassword.equals(confirmNewPass))
                    {
                        throw new Exception("New Password and Confirm New Password do not match!");
                    }

                    // Update the password in the database
                    myDB.updatePassword(oldPassword, newPassword);

                    // Show a success message
                    showToast("Password Changed Successfully!");

                    // Finish the activity
                    finish();

                }catch (Exception e)
                {
                    // Handle exceptions and show error messages
                    showToast(e.getMessage());
                }

            }
        });
    }

    // Helper function to display Toast messages
    private void showToast(String message)
    {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
