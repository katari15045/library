package com.github.katari15045.iiitdlibrary.home;

import android.os.Build;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.github.katari15045.iiitdlibrary.R;
import com.github.katari15045.iiitdlibrary.misc.Universal;
import com.github.katari15045.iiitdlibrary.startup.NewArrivalsFetcher;
import com.github.katari15045.iiitdlibrary.startup.SplashScreenActivity;

public class HomeActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout = null;
    private String debugTag = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        debugTag = getResources().getString(R.string.debug_tag);
        Log.d(debugTag, "HomeActivity::onCreate()");
        captureViews();
        Universal.initNavDrawer(this, drawerLayout);
        Universal.initBottomNavView(this);
        Universal.initStatusBar(this);
        addRecyclerViewForNewArrivals();
    }

    @Override
    public void onBackPressed() {
        Log.d(debugTag, "HomeActivity::BackButton");
        if(Build.VERSION.SDK_INT >= 16){
            SplashScreenActivity.hasStarted = false;
            finishAffinity();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        getSupportActionBar().setTitle(R.string.app_name);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Universal.optionsMenuInit(this, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Universal.optionsMenuListener(this, item);
        return true;
    }

    private void addRecyclerViewForNewArrivals(){
        RecyclerView recyclerView = findViewById(R.id.activity_home_recycler_view_new_arrivals);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(new NewArrivalsAdapter(this, NewArrivalsFetcher.getCards()));
    }


    private void captureViews(){
        drawerLayout = findViewById(R.id.activity_home_drawer_layout);
    }
}