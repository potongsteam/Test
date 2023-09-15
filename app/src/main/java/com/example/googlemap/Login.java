package com.example.googlemap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Replace with your actual login logic
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                if (isValidUser(username, password)) {
                    // Successful login, navigate to the next activity
                    Intent intent = new Intent(String.valueOf(Login.this));
                    startActivity(intent);
                    finish();
                } else {
                    // Invalid login, show an error message
                    Toast.makeText(Login.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    // Replace this method with your actual user validation logic
    private boolean isValidUser(String username, String password) {
        // Check the username and password against your database or authentication system
        // Return true if the user is valid, otherwise return false
        // For this example, let's assume a simple validation
        return username.equals("admin") && password.equals("1234");
    }
}