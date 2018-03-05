package com.github.katari15045.iiitdlibrary;


import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.github.katari15045.iiitdlibrary.R;


public class HomeFragment extends Fragment {

    private View view = null;
    private AppCompatActivity activity = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("SAK", "cur_frag -> " + toString());
        this.view = inflater.inflate(R.layout.fragment_home, container, false);
        this.activity = (AppCompatActivity)view.getContext();
        addRecyclerViewForHrzntlSlider();
        return view;
    }

    private void addRecyclerViewForHrzntlSlider(){
        RecyclerView recyclerView = view.findViewById(R.id.fragment_home_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext(),
                LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(new HrzntlSliderAdapter(view.getContext(), NewArrivalsFetcher.getCards()));
    }

    @Override
    public String toString() {
        return "HomeFragment";
    }
}
