package com.ask.ask;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MatchConfirmationActivity extends AppCompatActivity {

    private TextView textViewInformation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_confirmation);

        textViewInformation = (TextView) findViewById(R.id.textViewInformation);

        Offer newOffer = (Offer) getIntent().getSerializableExtra("Offer");

        if (newOffer != null) {
            // create a confirmation string with request information
            String confirmationString = "Your offer is confirmed.\n"+
                    newOffer.toDescriptiveString()+"\n"+
                    "Cheers from the @Ask team.";

            textViewInformation.setText(confirmationString);
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
