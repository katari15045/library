package com.github.katari15045.iiitdlibrary;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Nishant Gahlawat on 04-03-2018.
 */

public class ServicesRecyclerAdapter  extends RecyclerView.Adapter<ServicesRecyclerAdapter.servicesViewHolder>  {
    private ArrayList<String> serviceNames;
    private Context context;

    serviceTextViewClickListener textViewClickListener;

    public ServicesRecyclerAdapter(ArrayList<String> serviceNames, Context context) {
        this.serviceNames = serviceNames;
        this.context = context;
    }

    public void setTextViewClickListener(serviceTextViewClickListener textViewClickListener) {
        this.textViewClickListener = textViewClickListener;
    }

    @Override
    public servicesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.services_list_item,parent,false);

        servicesViewHolder viewHolder = new servicesViewHolder(convertView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(servicesViewHolder holder, final int position) {
        holder.serviceItemTextView.setText(serviceNames.get(position));

        holder.serviceItemTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(textViewClickListener!=null){
                    textViewClickListener.onServiceTextClickListener(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return serviceNames.size();
    }

    interface serviceTextViewClickListener{
        public void onServiceTextClickListener(int position);
    }

    public class servicesViewHolder extends RecyclerView.ViewHolder{

        public TextView serviceItemTextView;

        public servicesViewHolder(View itemView) {
            super(itemView);
            serviceItemTextView = (TextView)itemView.findViewById(R.id.serviceItemTV);
        }
    }
}