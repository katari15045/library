package com.github.katari15045.iiitdlibrary.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.katari15045.iiitdlibrary.R;
import com.github.katari15045.iiitdlibrary.Util.ReadingHistoryUtil;

import java.util.List;

/**
 * Created by Mayank on 3/11/2018.
 */


public class ReadingHistoryAdapter extends RecyclerView.Adapter<ReadingHistoryAdapter.MyViewHolder> {

    private Context mContext;
    private List<ReadingHistoryUtil> readingList;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView titleTextView;
        public TextView itemtypeTextView;
        public TextView dateTextView;

        public MyViewHolder(View view) {
            super(view);

            dateTextView = (TextView) view.findViewById(R.id.reading_history_date);
            titleTextView = (TextView) view.findViewById(R.id.reading_history_title);
            itemtypeTextView = (TextView) view.findViewById(R.id.reading_history_itemtype);
        }
    }


    public ReadingHistoryAdapter(Context mContext, List<ReadingHistoryUtil> readingList) {
        this.mContext = mContext;
        this.readingList = readingList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.listview_layout_reading_history, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        ReadingHistoryUtil reading = readingList.get(position);

        Log.i(mContext+"", "222221111111111111 ----------- " + (holder.itemtypeTextView == null));
        holder.itemtypeTextView.setText(reading.getItemtype());
        holder.titleTextView.setText(reading.getTitle());
        holder.dateTextView.setText(reading.getDate());


    }

    @Override
    public int getItemCount() {
        return readingList.size();
    }
}