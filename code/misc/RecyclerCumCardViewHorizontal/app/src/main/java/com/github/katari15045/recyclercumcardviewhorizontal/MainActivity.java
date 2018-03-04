package com.github.katari15045.recyclercumcardviewhorizontal;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Card> database = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        removeActionBar();
        if(NewArrivalsFetcher.getCards() == null){
            Log.d("SAK", "filling DB...");
            fillDatabase(10);
        }else{
            Log.d("SAK", "Processing screen rotation...");
            handleScreenRotation();
        }
    }

    private void handleScreenRotation(){
        RecyclerView recyclerView = findViewById(R.id.main_activity_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(new MyAdapter(this, NewArrivalsFetcher.getCards()));
    }

    private void removeActionBar(){
        getSupportActionBar().hide();
    }

    private void fillDatabase(int totalBooks){
        NewArrivalsFetcher newArrivalsFetcher = new NewArrivalsFetcher(this, totalBooks,
                new PostDBFillingTask(this));
        newArrivalsFetcher.execute();
    }
}

class PostDBFillingTask extends AsyncTask<Void, Void, Void>{

    private Context context = null;
    private AppCompatActivity activity = null;

    PostDBFillingTask(Context context){
        this.context = context;
        this.activity = (AppCompatActivity) context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        RecyclerView recyclerView = activity.findViewById(R.id.main_activity_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(context,
                LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(new MyAdapter(context, NewArrivalsFetcher.getCards()));
    }
}