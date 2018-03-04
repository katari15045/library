package com.github.katari15045.iiitdlibrary;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    static Fragment currentFragment = null;
    private NavDrawer navDrawer = null;
    private static BottomNavigationView bottomNavigationBar = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navDrawer = new NavDrawer(this);
        addBottomNavBar();
        if(currentFragment == null){
            displayHome();
        }else{
            replaceFragment(currentFragment);
        }
    }

    private void addBottomNavBar(){
        bottomNavigationBar = findViewById(R.id.activity_main_bottom_nav_view);
        bottomNavigationBar.setOnNavigationItemSelectedListener(
                new BottomNavBarListener(this));
    }

    private void displayHome(){
        replaceFragment(new HomeFragment());
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
        if(item.getItemId() == R.id.menu_bottom_nav_bar_home){
            Toast.makeText(context, "Home", Toast.LENGTH_SHORT).show();
        }else if(item.getItemId() == R.id.menu_bottom_nav_bar_search){
            Toast.makeText(context, "Search", Toast.LENGTH_SHORT).show();
        }else if(item.getItemId() == R.id.menu_bottom_nav_bar_login){
            if(item.getTitle().equals(context.getResources().getString(
                    R.string.bottom_nav_bar_title_login))){
                LoginFragment loginFragment = new LoginFragment();
                replaceFragment(loginFragment);
                MainActivity.currentFragment = loginFragment;
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