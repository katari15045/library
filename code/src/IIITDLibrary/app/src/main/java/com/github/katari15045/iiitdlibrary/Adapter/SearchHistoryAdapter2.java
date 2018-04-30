package com.github.katari15045.iiitdlibrary.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.katari15045.iiitdlibrary.R;
import com.github.katari15045.iiitdlibrary.Util.SearchHistoryUtil;

import java.util.List;

/**
 * Created by Mayank on 3/11/2018.
 */

public class SearchHistoryAdapter2 extends RecyclerView.Adapter<SearchHistoryAdapter2.MyViewHolder> {

    private Context mContext;
    private List<SearchHistoryUtil> searchList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public CheckBox checkBox;
        public TextView dateTextView;
        public TextView timeTextView;
        public TextView searchTextView;
        public TextView resultTextView;

        public MyViewHolder(View view) {
            super(view);
            checkBox = (CheckBox) view.findViewById(R.id.checkbox_listView_history_search);
            dateTextView = (TextView) view.findViewById(R.id.search_history_date_text_view);
            timeTextView = (TextView) view.findViewById(R.id.search_history_time_text_view);
            searchTextView = (TextView) view.findViewById(R.id.search_history_text_view);
            resultTextView = (TextView) view.findViewById(R.id.search_history_result_text_view);
        }
    }


    public SearchHistoryAdapter2(Context mContext, List<SearchHistoryUtil> albumList) {
        this.mContext = mContext;
        this.searchList = albumList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.listview_layout_search_history, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        SearchHistoryUtil search = searchList.get(position);

        holder.checkBox.setChecked(search.getChecked());
        holder.checkBox.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Log.i(mContext+"", "hhhhhhh - " + holder.checkBox.isChecked());
                holder.checkBox.setChecked(holder.checkBox.isChecked());
            }
        });
        holder.dateTextView.setText(search.getDate());
        holder.timeTextView.setText(search.getTime());
        holder.searchTextView.setText(search.getSearch());
        holder.resultTextView.setText(search.getResult()+"");


    }

    @Override
    public int getItemCount() {
        return searchList.size();
    }
}