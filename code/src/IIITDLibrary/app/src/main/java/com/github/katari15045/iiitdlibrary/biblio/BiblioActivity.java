package com.github.katari15045.iiitdlibrary.biblio;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.github.katari15045.iiitdlibrary.R;
import com.github.katari15045.iiitdlibrary.misc.Universal;

public class BiblioActivity extends AppCompatActivity {

    private String debugTag = null;
    public static String biblioNumber = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_biblio);
        debugTag = getResources().getString(R.string.debug_tag);
        Log.d(debugTag, "BiblioActivity::onCreate()");
        Intent intent = getIntent();
        biblioNumber = intent.getStringExtra("biblioNumber");
        DrawerLayout drawerLayout = findViewById(R.id.activity_biblio_drawer_layout);
        Universal.initNavDrawer(this, drawerLayout);
        Universal.initBottomNavView(this);
        Universal.initStatusBar(this);
        BiblioDataFetcher biblioDataFetcher = new BiblioDataFetcher(this);
        biblioDataFetcher.execute();
    }
}
