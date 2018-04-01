package com.ask.ask;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;

/**
 * Created by alexander on 4/1/2018.
 */

public class AcceptOfferConfirmationActivity extends AppCompatActivity {

    private TextView textViewRequest;
    private TextView textViewProvider;
    private EditText editTextMessage;
    private Button buttonAcceptOffer;

    private String request_id;
    private String provider_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accept_offer_confirmation);

        request_id = getIntent().getStringExtra("request_id");
        provider_id = getIntent().getStringExtra("provider_id");

        textViewRequest = (TextView) findViewById(R.id.textViewRequest);
        textViewProvider = (TextView)findViewById(R.id.textViewProvider);

        textViewRequest.setText("Request Id: " + request_id);
        textViewProvider.setText("Provider's Id: " + provider_id);

        buttonAcceptOffer = (Button) findViewById(R.id.buttonAcceptOffer);
        buttonAcceptOffer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendOffer();
            }
        });

    }

    private void sendOffer() {
        final String url = "https://ask-capa.herokuapp.com/api/offers/accept/" + request_id;

        POSTData postData = new POSTData();
        postData.postAcceptOffer(url, request_id, provider_id, "", getApplicationContext(), new VolleyCallback() {
            @Override
            public void onSuccess(JSONArray jsonArray) {
                Toast.makeText(getApplicationContext(), "Offer Accepted.", Toast.LENGTH_SHORT).show();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(2000);
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }

            @Override
            public void onFailure() {
                Toast.makeText(getApplicationContext(), "Error Accepting Offer.", Toast.LENGTH_SHORT).show();
                // handle failure on accepting offer
            }

        });

    }

}