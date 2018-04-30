package com.github.katari15045.iiitdlibrary.misc;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import com.github.katari15045.iiitdlibrary.BottomNavViewListener;
import com.github.katari15045.iiitdlibrary.NavDrawerListener;
import com.github.katari15045.iiitdlibrary.R;
import com.github.katari15045.iiitdlibrary.login.LoginActivity;
import com.github.katari15045.iiitdlibrary.staticScreens.TimingsActivity;
import com.github.katari15045.iiitdlibrary.staticScreens.OccupancyActivity;

/**
 * Created by Saketh Katari on 29-04-2018.
 */

public class Universal {

    public static void optionsMenuInit(Context context, Menu menu){
        AppCompatActivity activity = (AppCompatActivity)context;
        activity.getMenuInflater().inflate(R.menu.options_menu, menu);
        if(context.getClass() == TimingsActivity.class){
            menu.findItem(R.id.menu_options_timings).setVisible(false);
        }else if(context.getClass() == OccupancyActivity.class){
            menu.findItem(R.id.menu_options_occupancy).setVisible(false);
        }
    }

    public static void optionsMenuListener(Context context, MenuItem item){
        Object activityClass = null;
        if(item.getItemId() == R.id.menu_options_timings){
            activityClass = TimingsActivity.class;
        }else if(item.getItemId() == R.id.menu_options_occupancy){
            activityClass = OccupancyActivity.class;
        }
        if(activityClass != null){
            Intent intent = new Intent(context, (Class<?>)activityClass);
            context.startActivity(intent);
        }
    }

    public static void initNavDrawer(Context context, DrawerLayout drawerLayout){
        AppCompatActivity activity = (AppCompatActivity) context;
        Toolbar toolbar = activity.findViewById(R.id.toolbar);
        activity.setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(activity, drawerLayout, toolbar,
                R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navView = activity.findViewById(R.id.nav_view);
        navView.setNavigationItemSelectedListener(new NavDrawerListener(context, drawerLayout));
        if(LoginActivity.loggedIn){
            postLoginNavDrawer(context);
        }
    }

    public static void initBottomNavView(Context context){
        AppCompatActivity activity = (AppCompatActivity)context;
        BottomNavigationView bottomNavigationView = activity.findViewById(R.id.bottom_nav_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavViewListener(context));
        if(LoginActivity.loggedIn){
            postLoginBotNavView(context);
        }
    }

    public static void initStatusBar(Context context){
        AppCompatActivity activity = (AppCompatActivity) context;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(activity.getResources().getColor(R.color.colorPrimaryDark));
        }
    }

    public static void postLogin(Context context){
       postLoginNavDrawer(context);
       postLoginBotNavView(context);
    }

    public static void postLoginNavDrawer(Context context){
        AppCompatActivity activity = (AppCompatActivity)context;
        NavigationView navView = activity.findViewById(R.id.nav_view);
        navView.getMenu().findItem(R.id.menu_nav_drawer_login).setTitle(
                activity.getString(R.string.logout));
    }

    public static void postLoginBotNavView(Context context){
        AppCompatActivity activity = (AppCompatActivity)context;
        BottomNavigationView bottomNavView = activity.findViewById(R.id.bottom_nav_view);
        bottomNavView.getMenu().findItem(R.id.menu_bottom_nav_bar_login).setTitle(
                activity.getString(R.string.profile));
    }
}
