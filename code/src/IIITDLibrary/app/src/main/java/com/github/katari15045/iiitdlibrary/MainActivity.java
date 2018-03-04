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

    private NavDrawer navDrawer = null;
    private BottomNavigationView bottomNavigationBar = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navDrawer = new NavDrawer(this);
        addBottomNavBar();
        displayHome();
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
}

class BottomNavBarListener implements BottomNavigationView.OnNavigationItemSelectedListener{

    private Context context = null;

    BottomNavBarListener(Context context){
        this.context = context;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.menu_bottom_nav_bar_home){
            Toast.makeText(context, "Home", Toast.LENGTH_SHORT).show();
        }else if(item.getItemId() == R.id.menu_bottom_nav_bar_search){
            Toast.makeText(context, "Search", Toast.LENGTH_SHORT).show();
        }else if(item.getItemId() == R.id.menu_bottom_nav_bar_login){
            Toast.makeText(context, "Login", Toast.LENGTH_SHORT).show();
        }
        return false;
    }
}