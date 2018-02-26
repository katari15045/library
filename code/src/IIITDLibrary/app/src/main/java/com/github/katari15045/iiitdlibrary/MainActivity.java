package com.github.katari15045.iiitdlibrary;

import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener  {

    private DrawerLayout drawerLayout = null;
    private Toolbar toolbar = null;
    private NavigationView navigationView = null;
    private ActionBarDrawerToggle actionBarDrawerToggle = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        captureViews();
        initNavDrawer();
        displayHome();
    }

    private void hideItemInNavDrawer(int itemId){
        navigationView.getMenu().getItem(itemId).setVisible(false);
    }

    private boolean isDrawerOpen(){
        return  drawerLayout.isDrawerOpen(GravityCompat.START);
    }

    private void closeNavDrawer(){
        drawerLayout.closeDrawer(GravityCompat.START);
    }

    private  void openNavDrawer(){
        drawerLayout.openDrawer(GravityCompat.START);
    }

    private void unlockNavDrawer(){
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
        actionBarDrawerToggle.onDrawerStateChanged(DrawerLayout.LOCK_MODE_UNLOCKED);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        actionBarDrawerToggle.syncState();
    }

    private void lockNavDrawer(){
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        actionBarDrawerToggle.onDrawerStateChanged(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(false);
        actionBarDrawerToggle.syncState();
    }

    private void displayHome(){
        replaceFragment(new HomeFragment());
    }

    private void captureViews(){
        drawerLayout = (DrawerLayout) findViewById(R.id.activity_main_drawer_layout);
        toolbar = (Toolbar) findViewById(R.id.activity_main_toolbar);
        navigationView = (NavigationView) findViewById(R.id.activity_main_nav_view);
    }

    private void initNavDrawer(){
        setSupportActionBar(toolbar);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this,
                drawerLayout, toolbar, R.string.nav_drawer_open, R.string.nav_drawer_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        if (isDrawerOpen()) {
            closeNavDrawer();
        } else {
            super.onBackPressed();
        }
    }

    public boolean onNavigationItemSelected(MenuItem item){
        Fragment fragment = null;
        if(item.getItemId() == R.id.menu_nav_drawer_login){
            fragment = new LoginFragment();
            hideItemInNavDrawer(0);
            lockNavDrawer();
        }else{
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        replaceFragment(fragment);
        return true;
    }

    private void replaceFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.activity_main_fragment_container, fragment);
        transaction.commit();
    }
}
