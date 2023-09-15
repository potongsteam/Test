package com.example.googlemap;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;

import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Payment extends AppCompatActivity {

    private static final String clientID = "Paste your client id here";
    // creating variables for edit text on below line.
    private EditText amountEdt;
    // creating variable for button on below line.
    private Button payBtn;
    // on below line creating a variable to store request code for paypal sdk
    public static final int PAYPAL_REQUEST_CODE = 123;
    // on below line creating a Paypal Configuration Object
    private static PayPalConfiguration config = new PayPalConfiguration()
            // Start with mock environment.  When ready,
            // switch to sandbox (ENVIRONMENT_SANDBOX)
            // or live (ENVIRONMENT_PRODUCTION)
            .environment(PayPalConfiguration.ENVIRONMENT_SANDBOX)
            // on below line we are passing a client id.
            .clientId(clientID);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment);

        amountEdt = findViewById(R.id.idEdtAmount);
        // initializing variables for button on below line.
        payBtn = findViewById(R.id.idBtnMakePayment);

        payBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (amountEdt.getText().toString().isEmpty()) {
                    // if both name and age is empty we are displaying a toast message on below line.
                    Toast.makeText(getApplicationContext(), "Please enter amout to be paid", Toast.LENGTH_SHORT).show();
                } else {
                    makePayment(amountEdt.getText().toString());
                }

            }
        });

    }

    private void makePayment(String amount) {
        // Creating a paypal payment on below line.
        PayPalPayment payment = new PayPalPayment(new BigDecimal(String.valueOf(amount)), "RM", "Fees",
                PayPalPayment.PAYMENT_INTENT_SALE);
        // Creating Paypal Payment activity intent on below line
        Intent intent = new Intent(this, PaymentActivity.class);
        //putting the paypal configuration to the intent from the configuration we have created above.
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);
        // Putting paypal payment to the intent on below line.
        intent.putExtra(PaymentActivity.EXTRA_PAYMENT, payment);
        // Starting the intent activity for result
        // the request code will be used on the method onActivityResult
        startActivityForResult(intent, PAYPAL_REQUEST_CODE);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // If the result is from paypal request code
        if (requestCode == PAYPAL_REQUEST_CODE) {
            // If the result is OK i.e. user has not canceled the payment
            if (resultCode == Activity.RESULT_OK) {
                // Getting the payment confirmation
                PaymentConfirmation confirm = data.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);
                // if confirmation is not null
                if (confirm != null) {
                    try {
                        // Getting the payment details
                        String paymentDetails = confirm.toJSONObject().toString(4);
                        // on below line we are extracting json response and displaying it in a text view.
                        JSONObject payObj = new JSONObject(paymentDetails);
                        String payID = payObj.getJSONObject("response").getString("id");
                        String state = payObj.getJSONObject("response").getString("state");
                        // on below line displaying a toast message with the payment status
                        Toast.makeText(this, "Payment : " + state + " with payment id is : " + payID, Toast.LENGTH_SHORT).show();
                    } catch (JSONException e) {
                        // handling json exception on below line
                        Log.e("Error", "an extremely unlikely failure occurred: ", e);
                    }
                }
            } else if (resultCode == Activity.RESULT_CANCELED) {
                // on below line we are displaying a toast message as user cancelled the payment.
                Toast.makeText(this, "User cancelled the payment..", Toast.LENGTH_SHORT).show();
            } else if (resultCode == PaymentActivity.RESULT_EXTRAS_INVALID) {
                // on below line displaying toast message for invalid payment config.
                Toast.makeText(this, "Invalid payment config was submitted..", Toast.LENGTH_SHORT).show();
            }
        }
    }



    }

