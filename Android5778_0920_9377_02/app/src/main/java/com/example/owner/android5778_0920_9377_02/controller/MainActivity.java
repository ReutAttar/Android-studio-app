package com.example.owner.android5778_0920_9377_02.controller;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.owner.android5778_0920_9377_02.R;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame,new WelcomeFragment()).commit();

        startService(new Intent(this,MyIntentService.class));

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override

    /**
     * creating all the options for the Navigation drawer
     */

    public boolean onNavigationItemSelected(MenuItem item) {
        /** Handle navigation view item clicks here.
         nav_info - open the information fragment that present the company details and ways to connect us
         nav_parking- open the fragment that shows the branches list and enables to choose branch and then rent a car
         nav_available_cars- shows all the available cars from all branches
         nav_my_cars= shows the cars that the user rent, enables to close order
         */
        int id = item.getItemId();

        if (id == R.id.nav_info) {
            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame,new InformationFragment()).addToBackStack("InformationFragment").commit();
        } else if (id == R.id.nav_parking) {
            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame,new BranchFilterFragment()).addToBackStack("BranchFilterFragment").commit();
        } else if (id == R.id.nav_available_cars) {
            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame,new AvailableCarsFragment()).addToBackStack("AvailableCarsFragment").commit();
        } else if (id == R.id.nav_my_cars) {
            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new MyCarFragment()).addToBackStack("MyCarFragment").commit();
        }
        else if (id == R.id.nav_exit) {

            /**
             ask the user if he want to exit the application
             */
            new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_menu_close_clear_cancel)
                    .setTitle("Closing App")
                    .setMessage("Are you sure you want to close this app?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            exitApp();
                        }

                    })
                    .setNegativeButton("No", null)
                    .show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    private void exitApp()
    {
        this.finish();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        startActivity(intent);
    }
}
