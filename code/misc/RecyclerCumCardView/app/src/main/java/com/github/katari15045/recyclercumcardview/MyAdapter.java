package com.github.katari15045.recyclercumcardview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Saketh Katari on 22-02-2018.
 */

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    private Context context = null;
    private ArrayList<Card> database = null;

    public MyAdapter(Context context, ArrayList<Card> database){
        this.context = context;
        this.database = database;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.card, parent, false);
        return new MyViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Card card = database.get(position);
        holder.textViewTitle.setText(card.getTitle());
        holder.textViewAuthors.setText(card.getAuthor());
        holder.textViewCheckouts.setText(card.getCheckouts());
        holder.textViewHoldings.setText(card.getHoldings());
        holder.imageBook.setImageResource(card.getImage());
    }

    @Override
    public int getItemCount() {
        return database.size();
    }
}

class MyViewHolder extends RecyclerView.ViewHolder {

    private View view = null;
    private Context context = null;
    TextView textViewTitle = null;
    TextView textViewAuthors = null;
    TextView textViewCheckouts = null;
    TextView textViewHoldings = null;
    ImageView imageBook = null;

    public MyViewHolder(View view, Context context) {
        super(view);
        this.view = view;
        this.context = context;
        textViewTitle = (TextView) view.findViewById(R.id.card_text_view_title);
        textViewAuthors = (TextView) view.findViewById(R.id.card_text_view_author_names);
        textViewCheckouts = (TextView) view.findViewById(R.id.card_text_view_checkout_count);
        textViewHoldings = (TextView) view.findViewById(R.id.card_text_view_holdings_count);
        imageBook = (ImageView) view.findViewById(R.id.card_image_view_book);
        view.setOnClickListener(new MyListener(context));
    }
}

class MyListener implements View.OnClickListener{

    private Context context = null;

    public MyListener(Context context){
        this.context = context;
    }

    public void onClick(View view){
        Toast.makeText(context, "Coming soon...", Toast.LENGTH_SHORT).show();
    }
}