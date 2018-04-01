package com.ask.ask;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.ask.ask.Utils.DownloadImageTask;

public class ProfileActivity extends AppCompatActivity {

    TextView profileName, profileDescription;
    ImageView profileIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        User newUser = (User) getIntent().getSerializableExtra("profileUser");

        profileName = (TextView) findViewById(R.id.pA_profileName);
        profileDescription = (TextView) findViewById(R.id.pA_profileDescription);
        profileIcon = (ImageView) findViewById(R.id.pA_profilePic);

        String name = newUser.getName() + " " + newUser.getSurname();
        String description = newUser.getDescription();

        profileName.setText(name);
        profileDescription.setText(description);

        new DownloadImageTask((ImageView) profileIcon)
                .execute(newUser.getPpicture_url());



    }
}
