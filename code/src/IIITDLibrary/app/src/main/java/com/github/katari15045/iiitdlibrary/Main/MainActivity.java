package com.github.katari15045.iiitdlibrary.Main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.github.katari15045.iiitdlibrary.Home.HomeFragment;
import com.github.katari15045.iiitdlibrary.Misc.Global;
import com.github.katari15045.iiitdlibrary.R;
import com.github.katari15045.iiitdlibrary.Static.Resources.EResource.EResourcesFragment;
import com.github.katari15045.iiitdlibrary.Static.Resources.ResourcesFragment;

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
        Global.fragmentContainer = R.id.activity_main_fragment_container;
        Global.navDrawer = new NavDrawer(this);
        Global.actionBar = getSupportActionBar();
        Global.tabLayout = findViewById(R.id.activity_main_tab_layout);
    }

    // Displays the Home fragment by replacing the current fragment
    private void displayHome(){
        HomeFragment homeFragment = new HomeFragment();
        Global.currentFragment = homeFragment;
        Global.replaceFragment(homeFragment);
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
            Toast.makeText(this, "Coming soon...", Toast.LENGTH_SHORT).show();
        } else if(Global.currentFragment.getClass() == EResourcesFragment.class){
            Global.replaceFragment(new ResourcesFragment());
        } else{
            Global.replaceFragment(new HomeFragment());
        }
    }
}