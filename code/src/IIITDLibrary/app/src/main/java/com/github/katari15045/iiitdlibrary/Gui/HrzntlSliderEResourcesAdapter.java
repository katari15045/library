package com.github.katari15045.iiitdlibrary.Gui;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.github.katari15045.iiitdlibrary.Helper.EResourceCard;
import com.github.katari15045.iiitdlibrary.R;

import java.util.ArrayList;

/**
 * Created by Saketh Katari on 05-03-2018.
 */

// Adapter for the recycler view that holds E-Resources
public class HrzntlSliderEResourcesAdapter extends RecyclerView.Adapter<EResourceViewHolder>{
    private Context context = null;
    private ArrayList<EResourceCard> database = null;

    public HrzntlSliderEResourcesAdapter(Context context, ArrayList<EResourceCard> database){
        this.context = context;
        this.database = database;
    }

    // Returns a view holder that holds an item of recycler view
    @Override
    public EResourceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.card_hrzntl_slider, parent, false);
        return new EResourceViewHolder(view, context);
    }

    // Sets Backend resources for all the elements in an item of recycler view
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

// View Holder that holds an item of recycler view
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

// Listener for a click on an item in recycler view
class EResourceListener implements View.OnClickListener{
    private Context context = null;
    private EResourceCard card = null;

    public EResourceListener(Context context, EResourceCard card){
        this.context = context;
        this.card = card;
    }

    // Opens the url on the default browser
    public void onClick(View view){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(card.getUrlString()));
        context.startActivity(intent);
    }
}
