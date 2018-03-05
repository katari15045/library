package com.github.katari15045.iiitdlibrary;

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

/**
 * Created by Saketh Katari on 26-02-2018.
 */

public class NavDrawer implements NavigationView.OnNavigationItemSelectedListener{

    private Context context = null;
    private AppCompatActivity appCompatActivity = null;
    private DrawerLayout drawerLayout = null;
    private NavigationView navigationView = null;
    private ActionBarDrawerToggle actionBarDrawerToggle = null;
    private Toolbar toolbar = null;

    NavDrawer(Context context){
        this.context = context;
        appCompatActivity = ((AppCompatActivity) context);
        captureViews();
        initNavDrawer();
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item){
        Log.d("SAK", "Clicked Nav Drawer item");
        Fragment fragment = null;
        if(item.getItemId() == R.id.menu_nav_drawer_about){
            Log.d("SAK", "Clicked About");
            fragment = new LoginFragment();
            hideItemInNavDrawer(item.getOrder());
            //lockNavDrawer();
        }else{
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        ((MainActivity)context).replaceFragment(fragment);
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

    void hideItemInNavDrawer(int itemId){
        navigationView.getMenu().getItem(itemId).setVisible(false);
    }

    boolean isDrawerOpen(){
        return  drawerLayout.isDrawerOpen(GravityCompat.START);
    }

    void closeNavDrawer(){
        drawerLayout.closeDrawer(GravityCompat.START);
    }

    void openNavDrawer(){
        drawerLayout.openDrawer(GravityCompat.START);
    }

    void unlockNavDrawer(){
        changeNavDrawerState(DrawerLayout.LOCK_MODE_UNLOCKED, true);
    }

    void lockNavDrawer(){
        changeNavDrawerState(DrawerLayout.LOCK_MODE_LOCKED_CLOSED, false);
    }

    private void changeNavDrawerState(int mode, boolean indicatorEnabled){
        drawerLayout.setDrawerLockMode(mode);
        actionBarDrawerToggle.onDrawerStateChanged(mode);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(indicatorEnabled);
        actionBarDrawerToggle.syncState();
    }
}
