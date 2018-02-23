package com.github.katari15045.recyclercumcardviewhorizontal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Card> database = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.main_activity_recycler_view);
        fillDatabase();
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(new MyAdapter(this, database));
    }

    private void fillDatabase(){
        database = new ArrayList<>(10);
        int count = 1;
        while(count <= 10){
            Card card = new Card();
            card.setImage(R.drawable.linux_robert_love);
            database.add(card);
            count = count + 1;
        }
    }
}
