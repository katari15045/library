package com.github.katari15045.iiitdlibrary.Static.Resources.EResource;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

import com.github.katari15045.iiitdlibrary.Main.NavDrawer;
import com.github.katari15045.iiitdlibrary.Misc.Global;
import com.github.katari15045.iiitdlibrary.R;

import java.util.ArrayList;
import java.util.Arrays;

public class EResourcesFragment extends Fragment{

    private View view = null;
    private static String title = null;
    private String[] titleArray = null;
    private String[] linksArray = null;

    @Override
    public void onResume() {
        Log.d("SAK", "EResourcesFragment::onResume()");
        super.onResume();
        if(title != null){
            Global.changeActionBarTitle(title);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("SAK", "EResourcesFragment::onCreateView()");
        view = inflater.inflate(R.layout.fragment_eresources, null);
        title = view.getContext().getResources().getString
                (R.string.eresources);
        Global.changeActionBarTitle(title);
        storeData();
        setAdapter();
        return  view;
    }

    private void setAdapter(){
        RecyclerView recyclerView = view.findViewById(R.id.fragment_eresources_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext(),
                LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(new EResourcesFragRecViewAdapter(titleArray, linksArray));
    }

    private void storeData(){
        titleArray = view.getResources().getStringArray(R.array.eresources_fragment_titles);
        linksArray = view.getResources().getStringArray(R.array.eresources_fragment_links);
    }

    public static String getTitle(){
        return title;
    }
}