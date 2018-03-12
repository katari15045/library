package com.github.katari15045.iiitdlibrary;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

// Adds Bottom Navigation Bar, Navigation Drawer and displays Home Fragment
public class MainActivity extends AppCompatActivity {

    static Fragment currentFragment = null;
    private static Context context = null;
    private static NavDrawer navDrawer = null;
    private static ActionBar actionBar = null;
    private static FragmentManager fragmentManager = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("SAK", "MainActivity");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        fragmentManager = getSupportFragmentManager();
        BottomNavBar.addBottomNavBar();
        navDrawer = new NavDrawer(this);
        actionBar = getSupportActionBar();
        // Don't replace the home fragment with Home Fragment which is of no use - screen rotation
        if(currentFragment == null){
            Log.d("SAK", "cur_frag = null");
            displayHome();
        }
        Log.d("SAK", "MainActivity ends");
    }

    static void changeActionBarTitle(String newTitle){
        actionBar.setTitle(newTitle);
    }

    // Displays the Home fragment by replacing the current fragment
    private void displayHome(){
        Log.d("SAK", "Display home starts");
        HomeFragment homeFragment = new HomeFragment();
        Log.d("SAK", "cur_frag = home_frag...");
        currentFragment = homeFragment;
        Log.d("SAK", "Replacing frag...");
        replaceFragment(homeFragment);
        Log.d("SAK", "Display home ends");
    }

    // If Navigation drawer is opened then close it on a back button press
    @Override
    public void onBackPressed() {
        if (navDrawer.isDrawerOpen()) {
            navDrawer.closeNavDrawer();
        } else {
            super.onBackPressed();
        }
    }

    static void replaceFragment(Fragment fragment){
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.activity_main_fragment_container, fragment);
        transaction.commit();
    }

    static Context getContext(){
        return  context;
    }

    static void setNavDrawer(NavDrawer navDrawer){
        MainActivity.navDrawer = navDrawer;
    }

    static NavDrawer getNavDrawer(){
        return navDrawer;
    }
}