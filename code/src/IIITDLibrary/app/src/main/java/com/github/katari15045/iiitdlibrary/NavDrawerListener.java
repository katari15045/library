package com.github.katari15045.iiitdlibrary;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

/**
 * Created by Saketh Katari on 29-04-2018.
 */

public class NavDrawerListener implements NavigationView.OnNavigationItemSelectedListener{

    private Context context = null;

    public NavDrawerListener(Context context){
        this.context = context;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Log.d("Mars", "NavDrawerListener::clicked!");
        boolean ret = false;
        if(item.getItemId() == R.id.menu_nav_drawer_login){
            Toast.makeText(context, "Login", Toast.LENGTH_SHORT).show();
            ret = true;
        }else if(item.getItemId() == R.id.menu_nav_drawer_quit){
            Toast.makeText(context, "Quit", Toast.LENGTH_SHORT).show();
            ret = true;
        }
        DrawerLayout drawerLayout = ((AppCompatActivity)context).findViewById(
                R.id.activity_main_drawer_layout);
        drawerLayout.closeDrawer(GravityCompat.START);
        return ret;
    }
}

