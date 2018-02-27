package com.github.katari15045.optionsmenu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.options_menu_about){
            Toast.makeText(this, "About - coming soon...", Toast.LENGTH_LONG).show();
        }else if(item.getItemId() == R.id.options_menu_updates){
            Toast.makeText(this, "updates - coming soon...", Toast.LENGTH_LONG).show();
        }else if(item.getItemId() == R.id.options_menu_occupancy){
            Toast.makeText(this, "Occupancy - coming soon...", Toast.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item);
    }
}
