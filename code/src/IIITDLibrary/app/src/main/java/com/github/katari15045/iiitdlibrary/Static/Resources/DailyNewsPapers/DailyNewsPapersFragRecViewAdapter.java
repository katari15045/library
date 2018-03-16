package com.github.katari15045.iiitdlibrary.Static.Resources.DailyNewsPapers;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.github.katari15045.iiitdlibrary.Misc.Global;
import com.github.katari15045.iiitdlibrary.R;


/**
 * Created by Saketh Katari on 16-03-2018.
 */

public class DailyNewsPapersFragRecViewAdapter extends  RecyclerView.Adapter<DailyNewsPapersFragCardViewHolder> {

    private String[] titleArray = null;
    private String[] linksArray = null;

    public DailyNewsPapersFragRecViewAdapter(String[] titleArray, String[] linksArray){
        this.titleArray = titleArray;
        this.linksArray = linksArray;
    }

    // // Returns a view holder that holds an item of recycler view
    @Override
    public DailyNewsPapersFragCardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(Global.context);
        View view = inflater.inflate(R.layout.daily_newspapers_fragment_card, parent, false);
        return new DailyNewsPapersFragCardViewHolder(view);
    }

    // Sets Backend resources for all the elements in an item of recycler view
    @Override
    public void onBindViewHolder(DailyNewsPapersFragCardViewHolder holder, int position) {
        holder.textView.setText(titleArray[position]);
        holder.cardView.setOnClickListener(new DailyNewsPapersFragCardListener(titleArray[position],
                linksArray[position]));
    }

    @Override
    public int getItemCount() {
        return titleArray.length;
    }
}

class DailyNewsPapersFragCardViewHolder extends RecyclerView.ViewHolder{

    TextView textView = null;
    CardView cardView = null;

    public DailyNewsPapersFragCardViewHolder(View view){
        super(view);
        textView =view.findViewById(R.id.daily_newspapers_fragment_card_textview);
        cardView = view.findViewById(R.id.daily_newspapers_fragment_card_cardview);
    }
}

class DailyNewsPapersFragCardListener implements View.OnClickListener{

    private String title = null;
    private String link = null;

    public DailyNewsPapersFragCardListener(String title, String links){
        this.title = title;
        this.link = links;
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
        Toast.makeText(Global.context, Global.context.getResources().getString(R.string.please_wait),
                Toast.LENGTH_SHORT).show();
        Global.context.startActivity(intent);
    }
}