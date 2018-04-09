package com.ask.ask;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.ask.ask.Utils.DownloadImageTask;

public class ProfileActivity extends AppCompatActivity {

    TextView profileName, profileDescription;
    ImageView profileIcon;
    RatingBar ratingBarUserRating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //Pulls in the user from the recyclerViewAdapter
        User newUser = (User) getIntent().getSerializableExtra("profileUser");

        //updates the profile name from the user
        profileName = (TextView) findViewById(R.id.pA_profileName);
        profileDescription = (TextView) findViewById(R.id.pA_profileDescription);
        profileIcon = (ImageView) findViewById(R.id.pA_profilePic);
        ratingBarUserRating = (RatingBar) findViewById(R.id.ratingBarUserRating);

        String name = newUser.getName() + " " + newUser.getSurname();
        String description = newUser.getDescription();
        String temp_rating = ""+ newUser.getUser_id();

        profileName.setText(name);
        profileDescription.setText(description);
        float rating = Float.parseFloat(temp_rating);

        ratingBarUserRating.setRating(3 + rating % 2); //% ratingBarUserRating.getNumStars());

        //redownloads image from url
        //TODO: make this more efficient in that it only creates the image once
        new DownloadImageTask((ImageView) profileIcon).execute(newUser.getPpicture_url());

    }

}