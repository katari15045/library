package com.github.katari15045.recyclercumcardview;

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
        removeActionBar();
        fillCards();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.main_activity_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MyAdapter(this, database));
    }

    private void removeActionBar(){
        getSupportActionBar().hide();
    }

    private void fillCards(){
        database = new ArrayList<>(128);
        int count = 1;
        while(count <= 128){
            Card card = new Card();
            card.setTitle(getResources().getString(R.string.book_title));
            card.setAuthor(getResources().getString(R.string.book_author_names));
            card.setCheckouts(String.valueOf(8));
            card.setHoldings(String.valueOf(2));
            card.setImage(R.drawable.three_easy_pieces);
            database.add(card);
            count = count + 1;
        }
    }
}
