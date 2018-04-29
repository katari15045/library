package com.github.katari15045.iiitdlibrary;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

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
        if(item.getItemId() == R.id.menu_bottom_nav_bar_home){
            Toast.makeText(context, "home", Toast.LENGTH_SHORT).show();
            return true;
        }else if(item.getItemId() == R.id.menu_bottom_nav_bar_search){
            activityClass = SearchActivity.class;
            Log.d(debugTab, "clicked on Search");
        }else if(item.getItemId() == R.id.menu_bottom_nav_bar_login){
            Toast.makeText(context, "login", Toast.LENGTH_SHORT).show();
            return true;
        }
        if(activityClass != null){
            Intent intent = new Intent(context, (Class<?>) activityClass);
            context.startActivity(intent);
        }
        return false;
    }
}
