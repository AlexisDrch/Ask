package com.ask.ask;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.design.widget.CollapsingToolbarLayout;
import android.net.Uri;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.elmargomez.typer.Font;
import com.elmargomez.typer.Typer;

public class MainActivity extends AppCompatActivity
    implements
        HomeFragment.OnFragmentInteractionListener,
        ProfileFragment.OnFragmentInteractionListener,
        RequestsFragment.OnFragmentInteractionListener,
        OffersFragment.OnFragmentInteractionListener,
        SettingsFragment.OnFragmentInteractionListener,
        AboutFragment.OnFragmentInteractionListener {

    private String DEFAULT_USER_EMAIL = "cs947@cornell.edu";
    private String DEFAULT_USER_PASSWORD = "askisd@best";
    private Button askButton;
    private CollapsingToolbarLayout mToolbar;

    private CardView card;
    private Toolbar tb;

    private DrawerLayout navigationDrawerLayout;
    private DrawerLayout navigationDrawerHeader;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //trigger login activity
        Intent loginIntent = new Intent(this.getApplicationContext(), LoginActivity.class);
        loginIntent.putExtra("mEmail", DEFAULT_USER_EMAIL);
        loginIntent.putExtra("mPassword", DEFAULT_USER_PASSWORD);
        startActivity(loginIntent);

        //navigation bar "button"/slide
        Toolbar navigationToolbar = findViewById(R.id.toolbarid);
        setSupportActionBar(navigationToolbar);

        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_navigation_menu);

        //for navigation bar
        navigationDrawerLayout = findViewById(R.id.navigation_drawer_layout);

        navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        // set item as selected to persist highlight
                        menuItem.setChecked(true);
                        // close drawer when item is tapped
                        setTitle(menuItem.getTitle());
                        navigationDrawerLayout.closeDrawers();
                        return true;
                    }
                });

        //select the home fragment to display requests
        try {
            toggleMainFragment((Fragment) HomeFragment.class.newInstance());
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        //creating Ask button and the intent
        askButton = (Button) findViewById(R.id.askBtn);
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

        askButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), NewRequestActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * Opens the navigation bar drawer when pressed or when left to right slide is made.
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                navigationDrawerLayout.openDrawer(GravityCompat.START); //for animation
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Determines the menuItem that was selected and goes to the corresponding fragments.
     * @param menuItem
     */
    private void selectDrawerItem(MenuItem menuItem) {
        Fragment requestedFragment = null;
        Class requestedFragmentClass = null;
        switch (menuItem.getItemId()) {
            case R.id.fragment_home:
                Log.d("selectDrawerItem", "2a");
                requestedFragmentClass = HomeFragment.class;
                break;
            case R.id.fragment_profile:
                Log.d("selectDrawerItem", "2b");
                requestedFragmentClass = ProfileFragment.class;
                break;
            case R.id.fragment_requests:
                Log.d("selectDrawerItem", "2c");
                requestedFragmentClass = RequestsFragment.class;
                break;
            case R.id.fragment_offers:
                Log.d("selectDrawerItem", "2d");
                requestedFragmentClass = OffersFragment.class;
                break;
            case R.id.fragment_settings:
                Log.d("selectDrawerItem", "2f");
                requestedFragmentClass = SettingsFragment.class;
                break;
            case R.id.fragment_about:
                Log.d("selectDrawerItem", "2g");
                requestedFragmentClass = AboutFragment.class;
                break;
            default:
                Log.d("selectDrawerItem", "2g");
        }

        //make sure a valid fragment was selected, else just return
        try {
            requestedFragment = (Fragment) requestedFragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        toggleMainFragment(requestedFragment);
        // set item as selected to persist highlight
        menuItem.setChecked(true);
        // close drawer when item is tapped
        setTitle(menuItem.getTitle());
        navigationDrawerLayout.closeDrawers();
    }

    public void toggleMainFragment(Fragment requestedFragment){
        //replace existing fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_frame_layout, requestedFragment);
        fragmentTransaction.addToBackStack(null); //TODO: press back to go to home immediately like Gmail
        fragmentTransaction.commit();
    };
    /**
     *
     */
    @Override
    public void onFragmentInteraction(Uri uri){
        //this is used to communicate with other fragments. Figure out how and if necessary
        //send data I think
    }

}