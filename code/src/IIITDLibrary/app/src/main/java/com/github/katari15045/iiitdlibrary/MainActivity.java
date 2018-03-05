package com.github.katari15045.iiitdlibrary;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    static Fragment currentFragment = null;
    private static Context context = null;
    private NavDrawer navDrawer = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("SAK", "MainActivity");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        BottomNavBar.addBottomNavBar();
        navDrawer = new NavDrawer(this);
        if(currentFragment == null){
            Log.d("SAK", "cur_frag = null");
            displayHome();
        }
        Log.d("SAK", "MainActivity ends");
    }

    private void displayHome(){
        HomeFragment homeFragment = new HomeFragment();
        currentFragment = homeFragment;
        replaceFragment(homeFragment);
    }

    @Override
    public void onBackPressed() {
        if (navDrawer.isDrawerOpen()) {
            navDrawer.closeNavDrawer();
        } else {
            super.onBackPressed();
        }
    }

    void replaceFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.activity_main_fragment_container, fragment);
        transaction.commit();
    }

    static Context getContext(){
        return  context;
    }
}