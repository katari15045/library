package com.github.katari15045.iiitdlibrary;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.github.katari15045.iiitdlibrary.home.HomeActivity;
import com.github.katari15045.iiitdlibrary.login.LoginActivity;
import com.github.katari15045.iiitdlibrary.startup.SplashScreenActivity;

/**
 * Created by Saketh Katari on 29-04-2018.
 */

public class NavDrawerListener implements NavigationView.OnNavigationItemSelectedListener{

    private Context context = null;
    private DrawerLayout drawerLayout = null;
    private String debugTag = null;

    public NavDrawerListener(Context context, DrawerLayout drawerLayout){
        this.context = context;
        this.drawerLayout = drawerLayout;
        debugTag = context.getResources().getString(R.string.debug_tag);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Log.d(debugTag, "NavDrawerListener::clicked!");
        Object activityClass = null;
        boolean ret = false;
        if(item.getItemId() == R.id.menu_nav_drawer_login){
            Log.d(debugTag, "Clicked on Login");
            if(LoginActivity.loggedIn){
                activityClass = HomeActivity.class;
                LoginActivity.loggedIn = false;
                Toast.makeText(context, context.getString(R.string.logged_out), Toast.LENGTH_SHORT)
                        .show();
            }else{
                activityClass = LoginActivity.class;
            }
            ret = true;
        }else if(item.getItemId() == R.id.menu_nav_drawer_quit){
            Log.d(debugTag, "Clicked on Quit");
            if(Build.VERSION.SDK_INT >= 16){
                SplashScreenActivity.hasStarted = false;
                ((AppCompatActivity)context).finishAffinity();
            }
            ret = true;
        }else if(item.getItemId() == R.id.menu_nav_drawer_discharge){
            activityClass = AskForDischargeActivity.class;
            ret = true;
        } else if(item.getItemId() == R.id.menu_nav_drawer_suggestion){
            activityClass = SuggestionActivity.class;
            ret = true;
        }else if(item.getItemId() == R.id.menu_nav_drawer_message){
            activityClass = MessagingActivity.class;
            ret = true;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        if(activityClass != null){
            Intent intent = new Intent(context, (Class<?>)activityClass);
            context.startActivity(intent);
        }
        return ret;
    }
}

