package com.github.katari15045.iiitdlibrary.search;

import android.content.Context;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.github.katari15045.iiitdlibrary.R;
import com.github.katari15045.iiitdlibrary.misc.Universal;


public class SearchActivity extends AppCompatActivity {

    private ImageView imageViewSearch = null;
    private ImageView imageViewBack = null;
    public static EditText editTextQuery = null;
    private DrawerLayout drawerLayout = null;
    public static boolean resultsFetched = false;
    private static String debugTag = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        debugTag = getResources().getString(R.string.debug_tag);
        Log.d(debugTag, "SearchActivity::onCreate()");
        collectViews();
        Universal.initNavDrawer(this, drawerLayout);
        Universal.initBottomNavView(this);
        Universal.initStatusBar(this);
        imageViewSearch.setOnClickListener(new SearchListener(this));
        imageViewBack.setOnClickListener(new SearchBackIconListener(this));
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(SearchActivity.resultsFetched){
            addRecyclerView(this);
        }
        getSupportActionBar().setTitle(R.string.search);
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

    public static void addRecyclerView(Context context){
        if(SearchThread.getResults().size() == 0){
            Log.d(debugTag, "SearchActivity::No results found!");
            Toast.makeText(context, context.getResources().getString(R.string.no_results),
                    Toast.LENGTH_SHORT).show();
            return;
        }
        SearchActivity searchActivity = (SearchActivity)context;
        RecyclerView recyclerView = searchActivity.findViewById(R.id.activity_search_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(new SearchAdapter(context, SearchThread.getResults()));
    }

    private void collectViews() {
        editTextQuery = findViewById(R.id.activity_search_edit_text_search);
        imageViewSearch = findViewById(R.id.activity_search_image_view_search_icon);
        imageViewBack = findViewById(R.id.activity_search_image_view_back_icon);
        drawerLayout = findViewById(R.id.activity_search_drawer_layout);
    }
}

class SearchListener implements View.OnClickListener{

    private Context context = null;

    public SearchListener(Context context){
        this.context = context;
    }

    @Override
    public void onClick(View view) {
        String query = SearchActivity.editTextQuery.getText().toString();
        SearchThread searchThread = new SearchThread(context, query);
        searchThread.execute();
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