package com.ask.ask;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.elmargomez.typer.Font;
import com.elmargomez.typer.Typer;

public class MainActivity extends AppCompatActivity {


    private Button ask;
    private ListView listView1;
    private CollapsingToolbarLayout mToolbar;
    private CardView card;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        MainMenuFragment mainMenu = new MainMenuFragment();
//        getSupportFragmentManager().beginTransaction().add(R.id.container, mainMenu).commit();

        //TODO: replace the user_data, item_data, request_data arrays with actual database
        //Creating an array of users
        User user_data[] = new User[]
                {
                        new User(null, "Bob", 25, R.drawable.bob_profile,
                                "678-456-9831", "711-2880 Nulla St." +
                                "Mankato, Mississippi 96522"),

                        new User(null, "Jim", 29, R.drawable.jim_profile,
                                "770-783-2923", "606-3727 Ullamcorper. Street " +
                                "Roseville, NH 11523"),

                        new User(null, "Nancy", 26, R.drawable.nancy_profile,
                                "404-719-3817", "Ap #867-859 Sit Rd. Azusa, New York 39531"),

                        new User(null, "Karen", 29, R.drawable.karen_profile,
                                "678-820-8638", "7292 Dictum Av. San Antonio, MI 47096"),

                        new User(null, "John", 31, R.drawable.john_profile,
                                "770-293-3621", "191-103 Integer Rd. " +
                                "Corona, New Mexico 08219")
                };

        //Creating an array of items
        Item item_data[] = new Item[]
                {
                        new Item(null, "Golf Club", null,
                                5.00, null, R.drawable.item_golfclub, user_data[0]),
                        new Item(null, "Pot", null,
                                10.00, null, R.drawable.item_pot, user_data[1]),
                        new Item(null, "Sleeping Bag", null,
                                7.00, null, R.drawable.item_sleepingbag, user_data[2]),
                        new Item(null, "Surfboard", null,
                                12.00, null, R.drawable.item_surfboard, user_data[3]),
                        new Item(null, "Tent", null,
                                5.00, null, R.drawable.item_tent, user_data[4])
                };

        //Creating an array of Requests
        Request request_data[] = new Request[]
                {
                        new Request(user_data[0], item_data[0], "6/10/18", "6/12/18",
                                "I want a golf club for one weekend," +
                                        " price can be negotiable" ),
                        new Request(user_data[1], item_data[1], "7/05/18", "7/06/18",
                                "I am traveling for a day and " +
                                        "need a pot to cook some food" ),
                        new Request(user_data[2], item_data[2], "7/15/18", "7/18/18",
                                "I am camping for a couple of days and need" +
                                        " a sleeping bag"),
                        new Request(user_data[3], item_data[3], "6/12/18", "6/13/18",
                                "I want to go surfing for the day"),
                        new Request(user_data[4], item_data[4], "7/24/18", "7/26/18",
                                "I need a tent for the weekend, preferably" +
                                        " for one person")
                };



        RecyclerViewAdapter adapter = new RecyclerViewAdapter(request_data);
        RecyclerView myView =  (RecyclerView)findViewById(R.id.recyclerview);
        myView.setHasFixedSize(true);
        myView.setAdapter(adapter);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.HORIZONTAL);
        myView.setLayoutManager(llm);

//        RequestAdapter adapter = new RequestAdapter(this,
//                R.layout.listview_item_row, request_data);
//
//        listView1 = (ListView)findViewById(R.id.listView1);
//        listView1.setAdapter(adapter);
//
//
        //creating Ask button and the intent
        ask = (Button) findViewById(R.id.askBtn);
//
        //creating collapsing toolbar and adding title
        mToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsingToolbar);
        mToolbar.setTitle(getTitle());
        mToolbar.setExpandedTitleTextAppearance(R.style.expandingToolbar);
        mToolbar.setCollapsedTitleTextAppearance(R.style.collapsingToolbar);

        //changing title font
        Typeface font = Typer.set(this).getFont(Font.ROBOTO_THIN);
        mToolbar.setExpandedTitleTypeface(font);
        mToolbar.setCollapsedTitleTypeface(font);

        //creating the card
        card = (CardView) findViewById(R.id.requestCard);




        ask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), RequestActivity.class);
                startActivity(intent);
            }
        });

    }

}