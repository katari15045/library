package com.github.katari15045.iiitdlibrary;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Spinner;

import com.github.katari15045.iiitdlibrary.misc.Universal;


public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.profile_activity_toolbar);
        toolbar.setTitle("Profile");
        setSupportActionBar(toolbar);

        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

       /* bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationBar);
        bottomNavigationView.setSelectedItemId(R.id.action_profile);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                changeIcon(item.getItemId());
                return true;
            }
        });
*/
        DrawerLayout drawerLayout = findViewById(R.id.activity_main_drawer_layout);
        Universal.initNavDrawer(this, drawerLayout);
        Universal.initBottomNavView(this);
        Universal.initStatusBar(this);
        int[] tabId = new int[3];
        tabId[0] = R.drawable.ic_assignment_white_36dp;
        tabId[1] = R.drawable.ic_update_white_36dp;
        tabId[2] = R.drawable.ic_history_white_36dp;

        //setting tab layout up
        viewPager = (ViewPager) findViewById(R.id.profile_activity_viewpager);

        ProfileViewpagerAdapter adapter = new ProfileViewpagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new SummaryFragment());
        adapter.addFragment(new UpdateFragment());
        adapter.addFragment(new HistoryFragment());

        viewPager.setAdapter(adapter);

        tabLayout = (TabLayout) findViewById(R.id.profile_activity_tabs);

        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(tabId[0]);
        tabLayout.getTabAt(1).setIcon(tabId[1]);
        tabLayout.getTabAt(2).setIcon(tabId[2]);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
    }

    private void changeIcon(int item_id)
    {
        if(item_id == R.id.action_home)
        {
            bottomNavigationView.getMenu().getItem(0).setIcon(R.drawable.ic_home_black_36dp);
            bottomNavigationView.getMenu().getItem(1).setIcon(R.drawable.ic_search_white_36dp);
            bottomNavigationView.getMenu().getItem(2).setIcon(R.drawable.ic_person_white_36dp);
            Log.i("MainActivity.this", "1 == " + "home");
        }
        else if(item_id == R.id.action_search)
        {
            bottomNavigationView.getMenu().getItem(0).setIcon(R.drawable.ic_home_white_36dp);
            bottomNavigationView.getMenu().getItem(1).setIcon(R.drawable.ic_search_black_36dp);
            bottomNavigationView.getMenu().getItem(2).setIcon(R.drawable.ic_person_white_36dp);
            Log.i("MainActivity.this", "1 == " + "search");
        }
        else
        {
            bottomNavigationView.getMenu().getItem(0).setIcon(R.drawable.ic_home_white_36dp);
            bottomNavigationView.getMenu().getItem(1).setIcon(R.drawable.ic_search_white_36dp);
            bottomNavigationView.getMenu().getItem(2).setIcon(R.drawable.ic_person_black_36dp);
            Log.i("MainActivity.this", "1 == " + "profile");
        }
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
