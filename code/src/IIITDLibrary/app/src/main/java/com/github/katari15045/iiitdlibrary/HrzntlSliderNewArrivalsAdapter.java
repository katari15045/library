package com.github.katari15045.iiitdlibrary;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Saketh Katari on 23-02-2018.
 */

public class HrzntlSliderNewArrivalsAdapter extends RecyclerView.Adapter<NewArrivalViewHolder>{
    private Context context = null;
    private ArrayList<NewArrivalCard> database = null;

    public HrzntlSliderNewArrivalsAdapter(Context context, ArrayList<NewArrivalCard> database){
        this.context = context;
        this.database = database;
    }

    @Override
    public NewArrivalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.card_hrzntl_slider, parent, false);
        return new NewArrivalViewHolder(view, context);
    }

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

class NewArrivalListener implements View.OnClickListener{
    private Context context = null;
    private NewArrivalCard card = null;

    public NewArrivalListener(Context context, NewArrivalCard card){
        this.context = context;
        this.card = card;
    }

    public void onClick(View view){
        Toast.makeText(context, "biblionumber : " + card.getBiblionumber(), Toast.LENGTH_SHORT).show();
    }
}