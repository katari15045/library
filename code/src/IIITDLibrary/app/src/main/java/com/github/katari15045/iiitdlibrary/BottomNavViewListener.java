package com.github.katari15045.iiitdlibrary;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.github.katari15045.iiitdlibrary.login.LoginActivity;
import com.github.katari15045.iiitdlibrary.profile.ProfileActivity;
import com.github.katari15045.iiitdlibrary.search.SearchActivity;

/**
 * Created by Saketh Katari on 29-04-2018.
 */

public class BottomNavViewListener implements BottomNavigationView.OnNavigationItemSelectedListener {

    private Context context = null;
    private String debugTab = null;

    public BottomNavViewListener(Context context){
        this.context = context;
        debugTab = context.getResources().getString(R.string.debug_tag);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Log.d(debugTab, "BottomNavViewListener::clicked!");
        Object activityClass = null;
        boolean retVal = false;
        if(item.getItemId() == R.id.menu_bottom_nav_bar_home){
            Log.d(debugTab, "clicked on Home");
            activityClass = MainActivity.class;
            retVal = true;
        }else if(item.getItemId() == R.id.menu_bottom_nav_bar_search){
            Log.d(debugTab, "clicked on Search");
            activityClass = SearchActivity.class;
            retVal = true;
        }else if(item.getItemId() == R.id.menu_bottom_nav_bar_login){
            Log.d(debugTab, "clicked on Login");
            if(LoginActivity.loggedIn){
                activityClass = ProfileActivity.class;
            }else{
                activityClass = LoginActivity.class;
            }
            retVal = true;
        }
        if(activityClass != null){
            Intent intent = new Intent(context, (Class<?>) activityClass);
            context.startActivity(intent);
        }
        return retVal;
    }
}
