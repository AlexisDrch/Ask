package com.ask.ask;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ask.ask.Utils.DownloadImageTask;

/**
 * Created by alexander on 4/7/2018.
 */

public class OfferAcceptedActivity extends AppCompatActivity {

    private TextView textViewItemName;
    private ImageView imageViewItemImage;
    private TextView textViewRequesterName;
    private ImageView imageViewRequesterImage;
    private TextView textViewProviderName;
    private ImageView imageViewProviderImage;
    private ImageView imageViewButtonGoToMessaging;
    private ImageView imageViewButtonGoToHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer_accepted);

        textViewItemName = (TextView) findViewById(R.id.textViewItemName);
        imageViewItemImage = (ImageView) findViewById(R.id.imageViewItemImage);
        textViewRequesterName = (TextView) findViewById(R.id.textViewRequesterName);
        imageViewRequesterImage = (ImageView) findViewById(R.id.imageViewRequesterImage);
        textViewProviderName = (TextView) findViewById(R.id.textViewProviderName);
        imageViewProviderImage = (ImageView) findViewById(R.id.imageViewProviderImage);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        textViewItemName.setText(bundle.get("itemName").toString());
        imageViewItemImage.setImageResource(Integer.parseInt(bundle.get("itemImage").toString()));
        textViewRequesterName.setText(bundle.get("requesterName").toString());
        new DownloadImageTask((ImageView) imageViewRequesterImage).execute(bundle.get("requesterImage").toString());
        textViewProviderName.setText(bundle.get("providerName").toString());
        new DownloadImageTask((ImageView) imageViewProviderImage).execute(bundle.get("providerImage").toString());

        imageViewButtonGoToHome = (ImageView) findViewById(R.id.imageViewButtonGoToHome);
        imageViewButtonGoToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(v.getContext(), MainActivity.class);
                v.getContext().startActivity(intent);
            }
        });

        imageViewButtonGoToMessaging = (ImageView) findViewById(R.id.imageViewButtonGoToMessaging);
        imageViewButtonGoToMessaging.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(v.getContext(), MessagingActivity.class);
                v.getContext().startActivity(intent);
            }
        });

    }

}