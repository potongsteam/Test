package com.example.googlemap;

import android.app.NotificationManager;
import android.content.DialogInterface;
import android.content.Intent;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Settings extends AppCompatActivity {

    private ImageButton change;
    private ImageButton update;
    private ImageButton pay;
    private ImageButton help;
    private ImageButton notify;
    private ImageButton logout;
    private ImageView background;
    private ImageView profilePic;
    private TextView userName;
    private TextView email;
    private TextView ChangePasswordText;
    private TextView upPhoneNumber;
    private TextView PaymentText;
    private TextView notificationText;
    private TextView aboutText;
    private TextView LogText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        // Initialize the ImageButtons by finding their corresponding views in the layout
        change = findViewById(R.id.change);
        update = findViewById(R.id.update);
        pay = findViewById(R.id.pay);
        help = findViewById(R.id.help);
        logout = findViewById(R.id.logout);
        notify = findViewById(R.id.notify);
        background = findViewById(R.id.background);
        profilePic = findViewById(R.id.profilepic);
        userName = findViewById(R.id.username);
        email = findViewById(R.id.email);
        ChangePasswordText = findViewById(R.id.textView7);
        upPhoneNumber = findViewById(R.id.textView8);
        PaymentText = findViewById(R.id.textView9);
        notificationText = findViewById(R.id.textView3);
        aboutText = findViewById(R.id.textView10);
        LogText = findViewById(R.id.textView4);

        background.setImageResource(R.drawable.top_background);
        profilePic.setImageResource(R.drawable.profile);

        // Set click listeners for the ImageButtons
        change.setImageResource(R.drawable.resetpassword);
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Finish the current activity and start the ChangePassword activity
                Intent i = new Intent(Settings.this, ChangePassword.class);
                startActivity(i);
            }
        });

        update.setImageResource(R.drawable.telephone);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Finish the current activity and start the UpdatePhoneNumber activity
                Intent u = new Intent(Settings.this, UpdatePhoneNumber.class);
                startActivity(u);
            }
        });

        pay.setImageResource(R.drawable.ewallet);
        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Finish the current activity and start the Payment activity
                Intent p = new Intent(Settings.this, Payment.class);
                startActivity(p);
            }
        });
        notify.setImageResource(R.drawable.notificationbell);
        notify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Finish the current activity and start the Notifications activity
                Intent n = new Intent(Settings.this, Notifications.class);
                startActivity(n);
            }
        });

        help.setImageResource(R.drawable.managementservice);
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    // Open a raw resource file named "help.txt" and read its content
                    InputStream input = getResources().openRawResource(R.raw.help);
                    InputStreamReader inputreader = new InputStreamReader(input);
                    BufferedReader read = new BufferedReader(inputreader);

                    String line;
                    StringBuilder text = new StringBuilder();

                    while ((line = read.readLine()) != null) {
                        text.append(line).append('\n');
                    }

                    read.close();
                    inputreader.close();
                    input.close();

                    String fileContent = text.toString();

                    // Display the file content in an AlertDialog
                    showFileContentDialog(fileContent);

                } catch (IOException e) {
                    e.printStackTrace();
                    // Handle the IOException, e.g., show an error message or log it
                    // You may want to display an error message to the user.
                }
            }
        });


        logout.setImageResource(R.drawable.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Finish the current activity and start an unspecified activity (you should specify which one)
                Intent a = new Intent(Settings.this, Login.class);
                startActivity(a);
            }

            // Define a method to show a logout confirmation dialog
            private void logoutMenu(Settings setting) {
                AlertDialog.Builder builder = new AlertDialog.Builder(setting);
                builder.setTitle("Logout");
                builder.setMessage("Are you sure you want to logout?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Finish the current activity to perform the logout
                        finish();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Dismiss the dialog if "No" is clicked
                        dialogInterface.dismiss();
                    }
                });
                builder.show();
            }
        });



    }

    private void showFileContentDialog(String content) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("About & Help");
        builder.setMessage(content);
        builder.setPositiveButton("OK", null);
        builder.show();
    }
}
