package com.ask.ask;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ViewAllRequestsActivity extends AppCompatActivity {

    Button click;
    public static TextView data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_requests);

        click = (Button) findViewById(R.id.buttonViewAll);
        data = (TextView) findViewById(R.id.viewAllText);

        click.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                FetchRequests process = new FetchRequests();
                process.execute();

            }
        });
    }
}
