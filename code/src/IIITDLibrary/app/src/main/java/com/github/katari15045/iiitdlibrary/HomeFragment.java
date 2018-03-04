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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.fragment_home, container, false);
        if(NewArrivalsFetcher.getCards() == null){
            Log.d("SAK", "filling DB...");
            fillDatabase(10);
        }else{
            Log.d("SAK", "Processing screen rotation...");
            handleScreenRotation();
        }
        return view;
    }

    private void handleScreenRotation(){
        RecyclerView recyclerView = view.findViewById(R.id.fragment_home_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext(),
                LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(new HrzntlSliderAdapter(view.getContext(), NewArrivalsFetcher.getCards()));
    }

    private void fillDatabase(int totalBooks){
        NewArrivalsFetcher newArrivalsFetcher = new NewArrivalsFetcher(view.getContext(),
                totalBooks, new PostDBFillingTask(view.getContext()));
        newArrivalsFetcher.execute();
    }
}

class PostDBFillingTask extends AsyncTask<Void, Void, Void> {

    private Context context = null;
    private AppCompatActivity activity = null;

    PostDBFillingTask(Context context){
        this.context = context;
        this.activity = (AppCompatActivity) context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        RecyclerView recyclerView = activity.findViewById(R.id.fragment_home_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(context,
                LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(new HrzntlSliderAdapter(context, NewArrivalsFetcher.getCards()));
    }
}
