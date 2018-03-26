package com.ask.ask;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by alexander on 2/28/2018.
 */

public class RequestConfirmationActivity extends AppCompatActivity {

    private TextView textViewConfirmation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_confirmation);

        textViewConfirmation = findViewById(R.id.textViewConfirmation);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String itemName = extras.getString("itemName");
            String beginDate = extras.getString("beginDate");
            String endDate = extras.getString("endDate");
            double price = extras.getDouble("price");
            String description = extras.getString("description");

            textViewConfirmation.setText(itemName + " \n " + beginDate + " - " + endDate + "\n$" + price + " per day\n" + description);
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(4000);
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
}
