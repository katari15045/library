package com.github.katari15045.iiitdlibrary;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        captureViews();
        Universal.initNavDrawer(this, drawerLayout);
        Universal.initStatusCumNavBar(this);
    }

    private void captureViews(){
        drawerLayout = findViewById(R.id.activity_main_drawer_layout);
    }
}