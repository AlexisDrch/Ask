package com.ask.ask;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.net.Uri;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ask.ask.Utils.DownloadImageTask;
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

    private String DEFAULT_USER_EMAIL = "pulakazad28@gmail.com";
    private String DEFAULT_USER_PASSWORD = "logs4daze";

    //toolbars
    private CollapsingToolbarLayout mToolbar;

    // side menu views
    private DrawerLayout navigationDrawerLayout;
    private NavigationView navigationView;
    private ImageView sideMenuUserImage;
    private TextView sideMenuUserNameSurname;
    private TextView sideMenuUserEmail;
    private Boolean NewRequestFragmentIsSet = false;

    // others
    private CardView card;
    private Toolbar tb;

<<<<<<< HEAD
    private Button askButton;

=======
>>>>>>> 53ff6afd8555b86ae658b8a0929a513069009db3
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //trigger login activity
        if (!LocalData.currentUserIsLoggedin){
            Intent loginIntent = new Intent(this.getApplicationContext(), LoginActivity.class);
            loginIntent.putExtra("mEmail", DEFAULT_USER_EMAIL);
            loginIntent.putExtra("mPassword", DEFAULT_USER_PASSWORD);
            startActivity(loginIntent);
        }

        // side-menu general views
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

                // event handler on side menu
        navigationDrawerLayout.addDrawerListener(
                new DrawerLayout.DrawerListener() {

                    @Override
                    public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {
                        //side menu header with current user info when menu clicked
                        sideMenuUserImage = findViewById(R.id.sideMenuUserImage);
                        sideMenuUserNameSurname = (TextView) findViewById(R.id.sideMenuUserNameSurname);
                        sideMenuUserEmail = (TextView) findViewById(R.id.sideMenuUserEmail);

                        new DownloadImageTask((ImageView) sideMenuUserImage)
                                .execute(LocalData.getCurrentUserInstance().getPpicture_url());
                        sideMenuUserNameSurname.setText(LocalData.getCurrentUserInstance().getName() +
                                LocalData.getCurrentUserInstance().getSurname());
                        sideMenuUserEmail.setText(LocalData.getCurrentUserInstance().getEmail());
                    }

                    @Override
                    public void onDrawerOpened(@NonNull View drawerView) {

                    }

                    @Override
                    public void onDrawerClosed(@NonNull View drawerView) {

                    }

                    @Override
                    public void onDrawerStateChanged(int newState) {

                    }
                }
        );


        //navigation bar "button"/slide
        Toolbar navigationToolbar = findViewById(R.id.toolbarid);
        setSupportActionBar(navigationToolbar);

        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_navigation_menu);

        //select the home fragment to display requests
        try {
            toggleMainFragment((Fragment) HomeFragment.class.newInstance());
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }


        //creating collapsing toolbar and adding title
        mToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsingToolbar);
        mToolbar.setTitle(getTitle());
        mToolbar.setExpandedTitleTextAppearance(R.style.expandingToolbar);
        mToolbar.setCollapsedTitleTextAppearance(R.style.collapsingToolbar);


        //changing title font
        Typeface font = Typer.set(this).getFont(Font.ROBOTO_THIN);
        mToolbar.setExpandedTitleTypeface(font);
        mToolbar.setCollapsedTitleTypeface(font);
        // display new request fragment

        // add focus on spinner when toolbar dragged to ask new request
        mToolbar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                try {
                    if (!NewRequestFragmentIsSet) {
                        toggleNewRequestFragment((Fragment) NewRequestFragment.class.newInstance());
                        NewRequestFragmentIsSet = true;
                    }
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                return false;
            }
        });

        //creating the card
        card = (CardView) findViewById(R.id.requestCard);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
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
                //side menu header with current user info when menu clicked
                sideMenuUserImage = findViewById(R.id.sideMenuUserImage);
                sideMenuUserNameSurname = (TextView) findViewById(R.id.sideMenuUserNameSurname);
                sideMenuUserEmail = (TextView) findViewById(R.id.sideMenuUserEmail);

                new DownloadImageTask((ImageView) sideMenuUserImage)
                        .execute(LocalData.getCurrentUserInstance().getPpicture_url());
                sideMenuUserNameSurname.setText(LocalData.getCurrentUserInstance().getName() +
                        LocalData.getCurrentUserInstance().getSurname());
                sideMenuUserEmail.setText(LocalData.getCurrentUserInstance().getEmail());
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
    }

<<<<<<< HEAD
    public void toggleNewRequestFragment(Fragment requestedFragment){
        //replace existing fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.ask_frame_layout, requestedFragment);
        fragmentTransaction.addToBackStack(null); //TODO: press back to go to home immediately like Gmail
        fragmentTransaction.commit();
    }

=======
>>>>>>> 53ff6afd8555b86ae658b8a0929a513069009db3
    /**
     *
     */
    @Override
    public void onFragmentInteraction(Uri uri){
        //this is used to communicate with other fragments. Figure out how and if necessary
        //send data I think
    }

}