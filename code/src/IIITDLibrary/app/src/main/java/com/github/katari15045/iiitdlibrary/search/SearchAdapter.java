package com.github.katari15045.iiitdlibrary.search;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.github.katari15045.iiitdlibrary.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;

/**
 * Created by Saketh Katari on 27-04-2018.
 */

public class SearchAdapter extends RecyclerView.Adapter<SearchViewHolder>{

    private Context context = null;
    private View view = null;
    private ArrayList<SearchResult> db = null;

    public SearchAdapter(Context context, HashSet<SearchResult> dbHashset){
        this.context = context;
        prepareDb(dbHashset);
    }

    @Override
    public SearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.search_result_card, parent, false);
        return new SearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SearchViewHolder holder, int position) {
        SearchResult searchResult = db.get(position);
        holder.textViewTitle.setText(handleString(searchResult.getTitle()));
        holder.textViewAuthor.setText(handleString(searchResult.getAuthor()));
    }

    @Override
    public int getItemCount() {
        return db.size();
    }

    private String handleString(String inp){
        int maxLen = context.getResources().getInteger(R.integer.max_str_len);
        if(inp.length() > maxLen){
            StringBuilder sb = new StringBuilder();
            sb.append(inp.substring(0, maxLen-3)).append("...");
            return sb.toString();
        }
        return inp;
    }

    private void prepareDb(HashSet<SearchResult> dbHashSet){
        db = new ArrayList<>(dbHashSet.size());
        Iterator<SearchResult> iterator = dbHashSet.iterator();
        while(iterator.hasNext()){
            db.add(iterator.next());
        }
    }
}

class SearchViewHolder extends RecyclerView.ViewHolder{

    TextView textViewTitle = null;
    TextView textViewAuthor = null;
    CardView cardView = null;

    public SearchViewHolder(View view){
        super(view);
        textViewTitle = view.findViewById(R.id.search_result_card_textview_title);
        textViewAuthor = view.findViewById(R.id.search_result_card_textview_author);
        cardView = view.findViewById(R.id.search_result_card_cardview);
        cardView.setOnClickListener(new SearchResultListener(view));
    }
}

class SearchResultListener implements View.OnClickListener{

    private View view = null;

    public SearchResultListener(View view){
        this.view = view;
    }

    @Override
    public void onClick(View view) {
        Toast.makeText(view.getContext(), "Coming soon...", Toast.LENGTH_SHORT).show();
    }
}
