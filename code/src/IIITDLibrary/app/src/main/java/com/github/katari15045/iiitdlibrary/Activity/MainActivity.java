package com.github.katari15045.iiitdlibrary.Activity;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.github.katari15045.iiitdlibrary.Gui.BottomNavBar;
import com.github.katari15045.iiitdlibrary.Fragment.HomeFragment;
import com.github.katari15045.iiitdlibrary.Gui.NavDrawer;
import com.github.katari15045.iiitdlibrary.Helper.Global;
import com.github.katari15045.iiitdlibrary.R;

// Adds Bottom Navigation Bar, Navigation Drawer and displays Home Fragment
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("SAK", "MainActivity::onCreate()");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setGlobalVars();
        BottomNavBar.addBottomNavBar();
        // Don't replace the home fragment with Home Fragment which is of no use
        if(Global.currentFragment == null){
            displayHome();
        }
    }

    private void setGlobalVars(){
        Global.context = this;
        Global.fragmentManager = getSupportFragmentManager();
        Global.navDrawer = new NavDrawer(this);
        Global.actionBar = getSupportActionBar();
        Global.tabLayout = findViewById(R.id.activity_main_tab_layout);
    }

    // Displays the Home fragment by replacing the current fragment
    private void displayHome(){
        HomeFragment homeFragment = new HomeFragment();
        Global.currentFragment = homeFragment;
        replaceFragment(homeFragment);
    }

    // If Navigation drawer is opened then close it on a back button press
    @Override
    public void onBackPressed() {
        Log.d("SAK", "MainActivity::BackButton");
        if (Global.navDrawer.isDrawerOpen()) {
            Global.navDrawer.closeNavDrawer();
            return;
        }
        if(Global.currentFragment.getClass() == HomeFragment.class){
            Log.d("SAK", "AlertDialog::Exit");
        } else{
            MainActivity.replaceFragment(new HomeFragment());
        }
    }

    public static void replaceFragment(Fragment fragment){
        FragmentTransaction transaction = Global.fragmentManager.beginTransaction();
        transaction.replace(R.id.activity_main_fragment_container, fragment);
        transaction.commit();
    }
}