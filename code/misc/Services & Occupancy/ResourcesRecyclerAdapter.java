package com.github.katari15045.iiitdlibrary;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Nishant Gahlawat on 05-03-2018.
 */

public class ResourcesRecyclerAdapter extends RecyclerView.Adapter<ResourcesRecyclerAdapter.resourceViewHolder>{

    private ArrayList<String> resourceTitles;
    private ArrayList<String> resourceLists;
    private Context context;

    public ResourcesRecyclerAdapter(ArrayList<String> resourceTitles, ArrayList<String> resourceLists, Context context) {
        this.resourceTitles = resourceTitles;
        this.resourceLists = resourceLists;
        this.context = context;
    }

    @Override
    public ResourcesRecyclerAdapter.resourceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.resources_list_item,parent,false);
        resourceViewHolder viewHolder = new resourceViewHolder(convertView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ResourcesRecyclerAdapter.resourceViewHolder holder, int position) {
        holder.resourceTitleTextView.setText(resourceTitles.get(position));
        holder.resourceListTextView.setText(resourceLists.get(position));
    }

    @Override
    public int getItemCount() {
        return resourceTitles.size();
    }

    public class resourceViewHolder extends RecyclerView.ViewHolder{

        public TextView resourceTitleTextView;
        public TextView resourceListTextView;

        public resourceViewHolder(View itemView) {
            super(itemView);
            this.resourceTitleTextView = (TextView)itemView.findViewById(R.id.resourcesTitleTV);
            this.resourceListTextView = (TextView)itemView.findViewById(R.id.resourcesListTV);
        }
    }
}
