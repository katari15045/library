package com.github.katari15045.iiitdlibrary;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;

public class ResourcesActivity extends AppCompatActivity {
    RecyclerView resourcesRecyclerView;
    ResourcesRecyclerAdapter resourcesRecyclerAdapter;
    ArrayList<String> resourcesTitle;
    ArrayList<String> resourcesLists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resources);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        resourcesRecyclerView = (RecyclerView)findViewById(R.id.resourcesRecyclerView);

        resourcesTitle = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.resourceTitles)));
        resourcesLists = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.resourceLists)));

        resourcesRecyclerAdapter = new ResourcesRecyclerAdapter(resourcesTitle,resourcesLists,this);

        resourcesRecyclerView.setAdapter(resourcesRecyclerAdapter);
        resourcesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
