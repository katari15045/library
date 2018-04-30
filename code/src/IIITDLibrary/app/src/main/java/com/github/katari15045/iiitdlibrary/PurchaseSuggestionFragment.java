package com.github.katari15045.iiitdlibrary;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
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
import java.util.List;


public class PurchaseSuggestionFragment extends Fragment {

    private LinearLayout linearLayout;
    private TextView pendingSuggestionTextView;
    private ArrayList<PurchaseUtil> mSuggestionUtilList;
    private static int SUGGESTION_REQUEST_CODE = 200;
    private View view;
    private PurchaseAdapter suggestionAdapter;

    private ListView purchaseSuggestionListView;

    public PurchaseSuggestionFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_purchase_suggestion, container, false);
        mSuggestionUtilList = new ArrayList<>();
        pendingSuggestionTextView = (TextView) view.findViewById(R.id.suggestion_no_purchase);
        if(mSuggestionUtilList.size() != 0)
        {
            pendingSuggestionTextView.setVisibility(View.GONE);
        }

        mSuggestionUtilList = new ArrayList<>();

        purchaseSuggestionListView = (ListView) view.findViewById(R.id.purchaseSuggestionListView);
        suggestionAdapter = new PurchaseAdapter(getActivity(), mSuggestionUtilList);
        purchaseSuggestionListView.setAdapter(suggestionAdapter);

        setListViewHeightBasedOnItems(purchaseSuggestionListView);


        Button newSuggestionButton = (Button) view.findViewById(R.id.suggestion_new_purchase);
        newSuggestionButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent new_Suggestion_Intent = new Intent(getActivity(), PurchaseSuggestionActivity.class);
                startActivityForResult(new_Suggestion_Intent, SUGGESTION_REQUEST_CODE);
            }
        });

        return view;
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


        Log.i(getActivity()+"", "got it ------------- 99");
        if(requestCode == 200)
        {

            Log.i(getActivity()+"", "got it ------------- 55");
            Log.i(getActivity()+"", "got it ------------- 55+++++++++ " + resultCode + "  " + requestCode + data.getStringExtra("newSuggestion"));
            if(resultCode == 200)
            {
                Log.i(getActivity()+"", "got it ------------- 00");

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
