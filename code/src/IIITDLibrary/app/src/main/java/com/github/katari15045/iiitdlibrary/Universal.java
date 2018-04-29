package com.github.katari15045.iiitdlibrary;

import android.content.Context;
import android.os.Build;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by Saketh Katari on 29-04-2018.
 */

public class Universal {

    public static void initNavDrawer(Context context, DrawerLayout drawerLayout){
        AppCompatActivity activity = (AppCompatActivity) context;
        Toolbar toolbar = activity.findViewById(R.id.toolbar);
        activity.setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(activity, drawerLayout, toolbar,
                R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navView = activity.findViewById(R.id.nav_view);
        navView.setNavigationItemSelectedListener(new NavDrawerListener(activity));
    }

    public static void initBottomNavView(Context context){
        AppCompatActivity activity = (AppCompatActivity)context;
        BottomNavigationView bottomNavigationView = activity.findViewById(R.id.bottom_nav_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavViewListener(context));
    }

    public static void initStatusBar(Context context){
        AppCompatActivity activity = (AppCompatActivity) context;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(activity.getResources().getColor(R.color.colorPrimaryDark));
        }
    }
}
