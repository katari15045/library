package com.github.katari15045.iiitdlibrary.Static.Resources.DailyNewsPapers;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.katari15045.iiitdlibrary.Main.NavDrawer;
import com.github.katari15045.iiitdlibrary.Misc.Global;
import com.github.katari15045.iiitdlibrary.R;
import com.github.katari15045.iiitdlibrary.Static.Resources.EResource.EResourcesFragRecViewAdapter;

public class DailyNewsPapersFragment extends Fragment {

    private View view = null;
    private static String title = null;
    private String[] titleArray = null;
    private String[] linksArray = null;

    public DailyNewsPapersFragment(){
        title = Global.context.getResources().
                getString(R.string.eresources_daily_news_papers);
    }

    @Override
    public void onResume() {
        Log.d("SAK", "DailyNewsPapersFragment::onResume()");
        super.onResume();
        ((AppCompatActivity) Global.context).getSupportActionBar().setTitle(title);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Log.d("SAK", "DailyNewsPapersFragment::onCreateView()");
        view = inflater.inflate(R.layout.fragment_daily_news_papers, null);
        storeData();
        setAdapter();
        return view;
    }

    private void setAdapter(){
        RecyclerView recyclerView = view.findViewById(R.id.fragment_daily_news_papers_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext(),
                LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(new EResourcesFragRecViewAdapter(titleArray, linksArray));
    }

    private void storeData(){
        titleArray = view.getResources().getStringArray(R.array.eresources_daily_newspaper_titles);
        linksArray = view.getResources().getStringArray(R.array.eresources_daily_newspaper_links);
    }

    public static String getTitle(){
        return title;
    }
}