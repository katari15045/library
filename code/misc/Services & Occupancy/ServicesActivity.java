package com.github.katari15045.iiitdlibrary;

import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class ServicesActivity extends AppCompatActivity implements ServicesRecyclerAdapter.serviceTextViewClickListener {
    RecyclerView servicesRV;
    ServicesRecyclerAdapter servicesRecyclerAdapter;
    ArrayList<String> serviceNames;
    ArrayList<String> serviceDespcritions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        servicesRV = (RecyclerView)findViewById(R.id.servicesRecyclerView);

        serviceNames = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.serviceNames)));
        serviceDespcritions = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.servicesDescriptions)));

        servicesRecyclerAdapter = new ServicesRecyclerAdapter(serviceNames,this);
        servicesRecyclerAdapter.setTextViewClickListener(this);

        servicesRV.setAdapter(servicesRecyclerAdapter);
        servicesRV.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_services, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onServiceTextClickListener(int position) {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.service_custom_dialog);

        TextView titleTextView = (TextView)dialog.findViewById(R.id.customDialogTitleTV);
        titleTextView.setText(serviceNames.get(position));

        TextView descTextView = (TextView)dialog.findViewById(R.id.customDialogDescriptionTV);
        descTextView.setText(serviceDespcritions.get(position));
        descTextView.setMovementMethod(LinkMovementMethod.getInstance());

        dialog.show();
    }
}