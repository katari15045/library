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
 * Created by Saketh Katari on 05-03-2018.
 */

public class HrzntlSliderEResourcesAdapter extends RecyclerView.Adapter<EResourceViewHolder>{
    private Context context = null;
    private ArrayList<EResourceCard> database = null;

    public HrzntlSliderEResourcesAdapter(Context context, ArrayList<EResourceCard> database){
        this.context = context;
        this.database = database;
    }

    @Override
    public EResourceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.card_hrzntl_slider, parent, false);
        return new EResourceViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(EResourceViewHolder holder, int position) {
        EResourceCard card = database.get(position);
        holder.imageView.setImageResource(card.getImage());
        holder.imageView.setOnClickListener(new EResourceListener(context, card));
    }

    @Override
    public int getItemCount() {
        return database.size();
    }
}

class EResourceViewHolder extends RecyclerView.ViewHolder{
    private View view = null;
    private Context context = null;
    ImageView imageView = null;

    public EResourceViewHolder(View view, Context context){
        super(view);
        this.view = view;
        this.context = context;
        imageView = (ImageView) view.findViewById(R.id.card_hrzntl_slider_image_view);
    }
}

class EResourceListener implements View.OnClickListener{
    private Context context = null;
    private EResourceCard card = null;

    public EResourceListener(Context context, EResourceCard card){
        this.context = context;
        this.card = card;
    }

    public void onClick(View view){
        Toast.makeText(context,  card.getUrlString(), Toast.LENGTH_SHORT).show();
    }
}
