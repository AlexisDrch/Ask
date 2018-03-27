package com.ask.ask;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by alexander on 2/28/2018.
 */

public class RequestConfirmationActivity extends AppCompatActivity {

    private TextView textViewConfirmation;
    private ImageView imageViewItemIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_confirmation);

        textViewConfirmation = (TextView) findViewById(R.id.textViewConfirmation);
        imageViewItemIcon = (ImageView) findViewById(R.id.imageViewItemImage);

        // To retrieve object in second Activity
        Request newRequest = (Request) getIntent().getSerializableExtra("Request");

        if (newRequest != null) {
            String itemId = newRequest.getItem_id();
            Item requestedItem = LocalData.getHashMapItemsById().get(itemId);

            // create a confirmation string with request information
            String confirmationString = "Your request is confirmed.\n"+
                    newRequest.toDescriptiveString()+"\n"+
                    "Cheers from the @Ask team";

            // layout
            imageViewItemIcon.setImageResource(requestedItem.getIcon());
            textViewConfirmation.setText(confirmationString);
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
