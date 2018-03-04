package com.example.mayank.library;

import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationBar);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                changeIcon(item.getItemId());
                return true;
            }
        });

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
        getMenuInflater().inflate(R.menu.menu_options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId())
        {
            case(R.id.action_about):
                break;
            case(R.id.action_resources):
                break;
            case(R.id.action_occupancy):
                break;
            case(R.id.action_services):
                break;
            case(R.id.action_giving):
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }


}
