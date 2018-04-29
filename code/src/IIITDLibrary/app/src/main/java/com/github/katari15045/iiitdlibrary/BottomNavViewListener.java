package com.github.katari15045.iiitdlibrary;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

/**
 * Created by Saketh Katari on 29-04-2018.
 */

public class BottomNavViewListener implements BottomNavigationView.OnNavigationItemSelectedListener {

    private Context context = null;

    public BottomNavViewListener(Context context){
        this.context = context;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Log.d("Mars", "BottomNavViewListener::clicked!");
        if(item.getItemId() == R.id.menu_bottom_nav_bar_home){
            Toast.makeText(context, "home", Toast.LENGTH_SHORT).show();
            return true;
        }else if(item.getItemId() == R.id.menu_bottom_nav_bar_search){
            Toast.makeText(context, "search", Toast.LENGTH_SHORT).show();
            return true;
        }else if(item.getItemId() == R.id.menu_bottom_nav_bar_login){
            Toast.makeText(context, "login", Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }
}
