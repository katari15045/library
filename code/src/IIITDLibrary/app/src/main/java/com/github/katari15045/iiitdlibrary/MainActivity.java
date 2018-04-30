package com.github.katari15045.iiitdlibrary;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.github.katari15045.iiitdlibrary.misc.Universal;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout = null;
    private String debugTag = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        debugTag = getResources().getString(R.string.debug_tag);
        Log.d(debugTag, "MainActivity::onCreate()");
        captureViews();
        Universal.initNavDrawer(this, drawerLayout);
        Universal.initBottomNavView(this);
        Universal.initStatusBar(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getSupportActionBar().setTitle(R.string.app_name);
    }

    private void captureViews(){
        drawerLayout = findViewById(R.id.activity_main_drawer_layout);
    }
}