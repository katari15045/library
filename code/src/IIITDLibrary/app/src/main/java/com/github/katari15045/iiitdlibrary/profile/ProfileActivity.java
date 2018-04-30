package com.github.katari15045.iiitdlibrary.profile;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.github.katari15045.iiitdlibrary.R;
import com.github.katari15045.iiitdlibrary.misc.Universal;

public class ProfileActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout = null;
    private String debugTag = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        debugTag = getResources().getString(R.string.debug_tag);
        Log.d(debugTag, "ProfileActivity::onCreate()");
        captureViews();
        Universal.initNavDrawer(this, drawerLayout);
        Universal.initBottomNavView(this);
        Universal.initStatusBar(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getSupportActionBar().setTitle(R.string.profile);
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

    private void captureViews(){
        drawerLayout = findViewById(R.id.activity_profile_drawer_layout);
    }
}
