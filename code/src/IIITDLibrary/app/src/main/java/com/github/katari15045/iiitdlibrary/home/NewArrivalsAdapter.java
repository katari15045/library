package com.github.katari15045.iiitdlibrary.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.github.katari15045.iiitdlibrary.R;
import com.github.katari15045.iiitdlibrary.biblio.BiblioActivity;
import com.github.katari15045.iiitdlibrary.startup.NewArrivalCard;

import java.util.ArrayList;

/**
 * Created by Saketh Katari on 23-02-2018.
 */

// Adapter for the recycler view that holds new arrivals
public class NewArrivalsAdapter extends RecyclerView.Adapter<NewArrivalViewHolder>{
    private Context context = null;
    private ArrayList<NewArrivalCard> database = null;

    public NewArrivalsAdapter(Context context, ArrayList<NewArrivalCard> database){
        this.context = context;
        this.database = database;
    }

    // // Returns a view holder that holds an item of recycler view
    @Override
    public NewArrivalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.card_hrzntl_slider, parent, false);
        return new NewArrivalViewHolder(view, context);
    }

    // Sets Backend resources for all the elements in an item of recycler view
    @Override
    public void onBindViewHolder(NewArrivalViewHolder holder, int position) {
        NewArrivalCard card = database.get(position);
        holder.imageView.setImageBitmap(card.getImage());
        holder.imageView.setOnClickListener(new NewArrivalListener(context, card));
    }

    @Override
    public int getItemCount() {
        return database.size();
    }
}

// View Holder that holds an item of recycler view
class NewArrivalViewHolder extends RecyclerView.ViewHolder{
    private View view = null;
    private Context context = null;
    ImageView imageView = null;

    public NewArrivalViewHolder(View view, Context context){
        super(view);
        this.view = view;
        this.context = context;
        imageView = (ImageView) view.findViewById(R.id.card_hrzntl_slider_image_view);
    }
}

// Listener for a click on an item in recycler view
class NewArrivalListener implements View.OnClickListener{
    private Context context = null;
    private NewArrivalCard card = null;

    public NewArrivalListener(Context context, NewArrivalCard card){
        this.context = context;
        this.card = card;
    }

    public void onClick(View view){
        Log.d("SAK", "Clicked NewArrivals::Book");
        Intent intent = new Intent(context, BiblioActivity.class);
        intent.putExtra("biblioNumber", card.getBiblionumber());
        context.startActivity(intent);
    }
}