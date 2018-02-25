package com.github.katari15045.search;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by Saketh Katari on 25-02-2018.
 */

public class SearchActivity extends AppCompatActivity{

    static EditText editTextSearch = null;
    private ImageView imageViewSearch = null;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_search);
        hideActionBar();
        editTextSearch = (EditText) findViewById(R.id.search_edit_text_search);
        imageViewSearch = (ImageView) findViewById(R.id.search_image_view_search_icon);
        imageViewSearch.setOnClickListener(new SearchListener());
    }

    private void hideActionBar(){
        getSupportActionBar().hide();
    }
}

class SearchListener implements View.OnClickListener{

    public void onClick(View view){
        Toast.makeText(MainActivity.context, SearchActivity.editTextSearch.getText(), Toast.LENGTH_LONG).show();
    }
}
