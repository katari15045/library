package com.github.katari15045.iiitdlibrary.Gui;

import android.content.Context;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.github.katari15045.iiitdlibrary.Activity.MainActivity;
import com.github.katari15045.iiitdlibrary.Fragment.AboutFragment;
import com.github.katari15045.iiitdlibrary.Fragment.ResourcesFragment;
import com.github.katari15045.iiitdlibrary.R;
import com.github.katari15045.iiitdlibrary.ServicesFragment;

/**
 * Created by Saketh Katari on 26-02-2018.
 */

// The Navigation Drawer - three lines
public class NavDrawer implements NavigationView.OnNavigationItemSelectedListener{

    private Context context = null;
    private AppCompatActivity appCompatActivity = null;
    private DrawerLayout drawerLayout = null;
    private static NavigationView navigationView = null;
    private ActionBarDrawerToggle actionBarDrawerToggle = null;
    private Toolbar toolbar = null;

    // Initializes the Navigation drawer
    public NavDrawer(Context context){
        this.context = context;
        appCompatActivity = ((AppCompatActivity) context);
        captureViews();
        initNavDrawer();
    }

    // Listener for a click on a navigation drawer item
    @Override
    public boolean onNavigationItemSelected(MenuItem item){
        Fragment fragment = null;
        if(item.getItemId() == R.id.menu_nav_drawer_about){
            Log.d("SAK", "Clicked NavDrawer::About");
            fragment = new AboutFragment();
        }else if(item.getItemId() == R.id.menu_nav_drawer_resources){
            Log.d("SAK", "Clicked NavDrawer::Resources");
            fragment = new ResourcesFragment();
        }else if(item.getItemId() == R.id.menu_nav_drawer_services){
            Log.d("SAK", "Clicked NavDrawer::Services");
            fragment = new ServicesFragment();
        }
        closeNavDrawer();
        MainActivity.replaceFragment(fragment);
        MainActivity.currentFragment = fragment;
        return true;
    }

    private void captureViews(){
        drawerLayout = appCompatActivity.findViewById(R.id.activity_main_drawer_layout);
        toolbar = appCompatActivity.findViewById(R.id.activity_main_toolbar);
        navigationView = appCompatActivity.findViewById(R.id.activity_main_nav_view);
    }

    private void initNavDrawer(){
        appCompatActivity.setSupportActionBar(toolbar);
        actionBarDrawerToggle = new ActionBarDrawerToggle(appCompatActivity,
                drawerLayout, toolbar, R.string.nav_drawer_open, R.string.nav_drawer_close){
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                drawerView.bringToFront();
                drawerView.requestLayout();
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    public static void hideItem(int itemId){
        navigationView.getMenu().getItem(itemId).setVisible(false);
    }

    public static void showItem(int itemId){
        navigationView.getMenu().getItem(itemId).setVisible(true);
    }

    public boolean isDrawerOpen(){
        return  drawerLayout.isDrawerOpen(GravityCompat.START);
    }

    public void closeNavDrawer(){
        drawerLayout.closeDrawer(GravityCompat.START);
    }

    public void openNavDrawer(){
        drawerLayout.openDrawer(GravityCompat.START);
    }

    public void unlockNavDrawer(){
        changeNavDrawerState(DrawerLayout.LOCK_MODE_UNLOCKED, true);
    }

    public void lockNavDrawer(){
        changeNavDrawerState(DrawerLayout.LOCK_MODE_LOCKED_CLOSED, false);
    }

    private void changeNavDrawerState(int mode, boolean indicatorEnabled){
        drawerLayout.setDrawerLockMode(mode);
        actionBarDrawerToggle.onDrawerStateChanged(mode);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(indicatorEnabled);
        actionBarDrawerToggle.syncState();
    }
}
