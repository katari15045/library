package com.github.katari15045.iiitdlibrary;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

/**
 * Created by Saketh Katari on 05-03-2018.
 */

// The Bottom Navigation Bar that contains Home, Search & Login/Profile icons
public class BottomNavBar {

    private static Context context = null;
    private static AppCompatActivity activity = null;
    private static BottomNavigationView bottomNavigationBar = null;

    // Sets the listener when an item of Bottom Navigation bar is Clicked
    static void addBottomNavBar(){
        context = MainActivity.getContext();
        activity = (AppCompatActivity)context;
        bottomNavigationBar = activity.findViewById(R.id.activity_main_bottom_nav_view);
        bottomNavigationBar.setOnNavigationItemSelectedListener(
                new BottomNavBarListener(context));
        Log.d("SAK", "bot_nav_bar added");
    }

    static BottomNavigationView getBottomNavBar(){
        return bottomNavigationBar;
    }
}

// listener for a click on an item in Bottom Navigation bar
class BottomNavBarListener implements BottomNavigationView.OnNavigationItemSelectedListener{

    private Context context = null;
    private AppCompatActivity activity = null;

    BottomNavBarListener(Context context){
        this.context = context;
        this.activity = (AppCompatActivity)context;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Log.d("SAK", "cur_frag -> " + MainActivity.currentFragment.toString());
        Log.d("SAK", "Clicked on -> " + item.getTitle());
        Fragment fragment = null;
        if(item.getItemId() == R.id.menu_bottom_nav_bar_home){
            // Opens the Home Fragment
            Log.d("SAK", "Home");
            if(MainActivity.currentFragment.getClass() != HomeFragment.class){
                Log.d("SAK", "cur_frag != home_frag");
                fragment = new HomeFragment();
                MainActivity.changeActionBarTitle(HomeFragment.getTitle());
            }
        }else if(item.getItemId() == R.id.menu_bottom_nav_bar_search){
            Toast.makeText(context, "Search", Toast.LENGTH_SHORT).show();
        }else if(item.getItemId() == R.id.menu_bottom_nav_bar_login){
            // Opens the Login Fragment
            Log.d("SAK", "Login");
            if(item.getTitle().equals(context.getResources().getString(
                    R.string.bottom_nav_bar_title_login))){
                Log.d("SAK", "Clicked on Login");
                // Opens new Login Fragment if the current fragment is not a Login Fragment
                if(MainActivity.currentFragment.getClass() != LoginFragment.class){
                    Log.d("SAK", "cur_frag != login_frag");
                    fragment = new LoginFragment();
                    Log.d("SAK", "Login Fragment Initialized");
                    if(LoginFragment.getTitle() == null){
                        Log.d("SAK", "Login Fragment title is null");
                    }
                    MainActivity.changeActionBarTitle(LoginFragment.getTitle());
                    Log.d("SAK", "Action bar title changed");
                }
            }
        }
        if(fragment != null){
            MainActivity.replaceFragment(fragment);
            MainActivity.currentFragment = fragment;
        }
        return false;
    }
}

