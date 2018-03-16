package com.github.katari15045.iiitdlibrary.Main;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.github.katari15045.iiitdlibrary.Home.HomeFragment;
import com.github.katari15045.iiitdlibrary.Login.LoginFragment;
import com.github.katari15045.iiitdlibrary.Misc.Global;
import com.github.katari15045.iiitdlibrary.R;

/**
 * Created by Saketh Katari on 05-03-2018.
 */

// The Bottom Navigation Bar that contains Home, Search & Login/Profile icons
public class BottomNavBar {

    private static Context context = null;
    private static AppCompatActivity activity = null;
    private static BottomNavigationView bottomNavigationBar = null;

    // Sets the listener when an item of Bottom Navigation bar is Clicked
    public static void addBottomNavBar(){
        context = Global.context;
        activity = (AppCompatActivity)context;
        bottomNavigationBar = activity.findViewById(R.id.activity_main_bottom_nav_view);
        bottomNavigationBar.setOnNavigationItemSelectedListener(
                new BottomNavBarListener(context));
        Log.d("SAK", "bottom_nav_bar added");
    }

    public static BottomNavigationView getBottomNavBar(){
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
        Fragment fragment = null;
        if(item.getItemId() == R.id.menu_bottom_nav_bar_home){
            Log.d("SAK", "BottomNavBar::Home");
            if(Global.currentFragment.getClass() != HomeFragment.class){
                fragment = new HomeFragment();
            }
        }else if(item.getItemId() == R.id.menu_bottom_nav_bar_search){
            Log.d("SAK", "BottomNavBar::Search");
            Toast.makeText(context, "Search", Toast.LENGTH_SHORT).show();
        }else if(item.getItemId() == R.id.menu_bottom_nav_bar_login){
            if(item.getTitle().equals(context.getResources().getString(
                    R.string.bottom_nav_bar_title_login))){
                Log.d("SAK", "BottomNavBar::Login");
                if(Global.currentFragment.getClass() != LoginFragment.class){
                    fragment = new LoginFragment();
                }
            }
        }
        if(fragment != null){
            MainActivity.replaceFragment(fragment);
            Global.currentFragment = fragment;
        }
        return true;
    }
}

