package com.github.katari15045.iiitdlibrary.biblio;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.github.katari15045.iiitdlibrary.R;
import com.github.katari15045.iiitdlibrary.misc.Universal;

public class BiblioActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout = null;
    private ViewPager viewPager = null;
    private TabLayout tabLayout = null;
    private BiblioFragAdapter fragAdapter = null;
    private String debugTag = null;
    public static String biblioNumber = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_biblio);
        debug();
        captureIntent();
        captureViews();
        initViews();
        addFrags();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getSupportActionBar().setTitle(R.string.book);
    }

    private void addFrags(){
        fragAdapter = new BiblioFragAdapter(getSupportFragmentManager());
        fragAdapter.addFragment(new BiblioOverviewFragment(), getResources().getString
                (R.string.overview));
        fragAdapter.addFragment(new BiblioCopiesFragment(), getResources().getString
                (R.string.copies));
        viewPager.setAdapter(fragAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void initViews(){
        Universal.initNavDrawer(this, drawerLayout);
        Universal.initBottomNavView(this);
        Universal.initStatusBar(this);
    }

    private void debug(){
        debugTag = getResources().getString(R.string.debug_tag);
        Log.d(debugTag, "BiblioActivity::onCreate()");
    }

    private void captureIntent(){
        Intent intent = getIntent();
        biblioNumber = intent.getStringExtra("biblioNumber");
    }

    private void captureViews(){
        drawerLayout = findViewById(R.id.activity_biblio_drawer_layout);
        viewPager = findViewById(R.id.activity_biblio_view_pager);
        tabLayout = findViewById(R.id.activity_biblio_tab_layout);
    }
}
