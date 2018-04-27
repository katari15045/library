package com.github.katari15045.iiitdlibrary.search;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.github.katari15045.iiitdlibrary.R;

public class SearchActivity extends AppCompatActivity {

    static EditText editTextQuery = null;
    private ImageView imageViewSearch = null;
    private ImageView imageViewBack = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        getSupportActionBar().hide();
        collectViews();
        imageViewSearch.setOnClickListener(new SearchListener(this));
        imageViewBack.setOnClickListener(new SearchBackIconListener(this));
    }

    private void collectViews() {
        editTextQuery = findViewById(R.id.activity_search_edit_text_search);
        imageViewSearch = findViewById(R.id.activity_search_image_view_search_icon);
        imageViewBack = findViewById(R.id.activity_search_image_view_back_icon);
    }
}

class SearchListener implements View.OnClickListener{

    private Context context = null;

    public SearchListener(Context context){
        this.context = context;
    }

    @Override
    public void onClick(View view) {
        Toast.makeText(context, SearchActivity.editTextQuery.getText().toString(), Toast.LENGTH_SHORT).show();
    }
}

class SearchBackIconListener implements View.OnClickListener{

    private Context context = null;

    public SearchBackIconListener(Context context){
        this.context = context;
    }

    @Override
    public void onClick(View view) {
        ((AppCompatActivity)context).finish();
    }
}