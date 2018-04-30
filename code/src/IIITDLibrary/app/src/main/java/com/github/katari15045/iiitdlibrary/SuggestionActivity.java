package com.github.katari15045.iiitdlibrary;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.github.katari15045.iiitdlibrary.Adapter.PurchaseAdapter;
import com.github.katari15045.iiitdlibrary.Util.PurchaseUtil;

import java.util.ArrayList;

public class SuggestionActivity extends AppCompatActivity {

    private LinearLayout linearLayout;
    private TextView pendingSuggestionTextView;
    private ArrayList<PurchaseUtil> mSuggestionUtilList;
    private static int SUGGESTION_REQUEST_CODE = 200;

    private PurchaseAdapter suggestionAdapter;

    private ListView purchaseSuggestionListView;
    private Toolbar mActionBarToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggestion);

        mActionBarToolbar = (Toolbar) findViewById(R.id.fineToolbar);
        setSupportActionBar(mActionBarToolbar);
        getSupportActionBar().setTitle("  Suggestion");

        getSupportActionBar().setHomeButtonEnabled(true);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_lightbulb_outline_white_36dp);
        mActionBarToolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));

        mSuggestionUtilList = new ArrayList<>();
        pendingSuggestionTextView = (TextView) findViewById(R.id.suggestion_no_purchase);
        if(mSuggestionUtilList.size() != 0)
        {
            pendingSuggestionTextView.setVisibility(View.GONE);
        }

        mSuggestionUtilList = new ArrayList<>();

        purchaseSuggestionListView = (ListView) findViewById(R.id.purchaseSuggestionListView);
        suggestionAdapter = new PurchaseAdapter(SuggestionActivity.this, mSuggestionUtilList);
        purchaseSuggestionListView.setAdapter(suggestionAdapter);

        setListViewHeightBasedOnItems(purchaseSuggestionListView);


        Button newSuggestionButton = (Button) findViewById(R.id.suggestion_new_purchase);
        newSuggestionButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent new_Suggestion_Intent = new Intent(SuggestionActivity.this, PurchaseSuggestionActivity.class);
                startActivityForResult(new_Suggestion_Intent, SUGGESTION_REQUEST_CODE);
            }
        });

    }

    public static boolean setListViewHeightBasedOnItems(ListView listView) {

        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter != null) {

            int numberOfItems = listAdapter.getCount();

            // Get total height of all items.
            int totalItemsHeight = 0;
            for (int itemPos = 0; itemPos < numberOfItems; itemPos++) {
                View item = listAdapter.getView(itemPos, null, listView);
                float px = 500 * (listView.getResources().getDisplayMetrics().density);
                item.measure(View.MeasureSpec.makeMeasureSpec((int)px, View.MeasureSpec.AT_MOST), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
                totalItemsHeight += item.getMeasuredHeight();
            }

            // Get total height of all item dividers.
            int totalDividersHeight = listView.getDividerHeight() *
                    (numberOfItems - 1);
            // Get padding
            int totalPadding = listView.getPaddingTop() + listView.getPaddingBottom();

            // Set list height.
            ViewGroup.LayoutParams params = listView.getLayoutParams();
            params.height = totalItemsHeight + totalDividersHeight + totalPadding;
            listView.setLayoutParams(params);
            listView.requestLayout();
            return true;

        } else {
            return false;
        }

    }




    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        Log.i(SuggestionActivity.this +"", "got it ------------- 99");
        if(requestCode == 200)
        {

            Log.i(SuggestionActivity.this+"", "got it ------------- 55");
            //Log.i(SuggestionActivity.this+"", "got it ------------- 55+++++++++ " + resultCode + "  " + requestCode + data.getStringExtra("newSuggestion"));
            if(resultCode == 200)
            {
                Log.i(SuggestionActivity.this+"", "got it ------------- 00");

                Bundle bundle = data.getExtras();
                PurchaseUtil thumbs = (PurchaseUtil) bundle.getSerializable("value");
                if(thumbs != null) {
                    mSuggestionUtilList.add(thumbs);
                }
                suggestionAdapter.notifyDataSetChanged();
                setListViewHeightBasedOnItems(purchaseSuggestionListView);
                if(mSuggestionUtilList.size() != 0) {
                    pendingSuggestionTextView.setVisibility(View.GONE);
                }
            }
        }
    }
}
