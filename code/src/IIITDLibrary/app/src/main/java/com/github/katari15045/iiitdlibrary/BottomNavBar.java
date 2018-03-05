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

public class BottomNavBar {

    private static Context context = null;
    private static AppCompatActivity activity = null;
    private static BottomNavigationView bottomNavigationBar = null;


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
        if(item.getItemId() == R.id.menu_bottom_nav_bar_home){
            Log.d("SAK", "Home");
            if(MainActivity.currentFragment.getClass() != HomeFragment.class){
                Log.d("SAK", "cur_frag != home_frag");
                HomeFragment homeFragment = new HomeFragment();
                replaceFragment(homeFragment);
                MainActivity.currentFragment = homeFragment;
            }
        }else if(item.getItemId() == R.id.menu_bottom_nav_bar_search){
            Toast.makeText(context, "Search", Toast.LENGTH_SHORT).show();
        }else if(item.getItemId() == R.id.menu_bottom_nav_bar_login){
            Log.d("SAK", "Login");
            if(item.getTitle().equals(context.getResources().getString(
                    R.string.bottom_nav_bar_title_login))){
                Log.d("SAK", "Clicked on Login");
                if(MainActivity.currentFragment.getClass() != LoginFragment.class){
                    Log.d("SAK", "cur_frag != login_frag");
                    LoginFragment loginFragment = new LoginFragment();
                    replaceFragment(loginFragment);
                    MainActivity.currentFragment = loginFragment;
                }
            }
        }
        return false;
    }

    void replaceFragment(Fragment fragment){
        FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.activity_main_fragment_container, fragment);
        transaction.commit();
    }
}

