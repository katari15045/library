package com.github.katari15045.iiitdlibrary.Home;


import android.content.res.Resources;
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
import android.widget.ScrollView;

import com.github.katari15045.iiitdlibrary.Misc.Global;
import com.github.katari15045.iiitdlibrary.R;

import java.util.ArrayList;

// The Home Fragment that contains new arrivals & E-Resources
public class HomeFragment extends Fragment {

    private View view = null;
    private AppCompatActivity activity = null;
    private ArrayList<EResourceCard> eResourceCards = null;
    private static String title = null;
    private ScrollView scrollView = null;

    public HomeFragment(){
        title = Global.context.getResources().getString(R.string.home_fragment_title);
    }

    // Storing & Retrieving Scroll position : https://stackoverflow.com/a/29208325/8279892
    @Override
    public void onSaveInstanceState(Bundle outState) {
        Log.d("SAK", "HomeFragment::onSaveInstanceState()");
        super.onSaveInstanceState(outState);
        float[] scrollPos = {scrollView.getScrollX(), scrollView.getScrollY()};
        outState.putFloatArray("SCROLL_POS", scrollPos);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Log.d("SAK", "HomeFragment::onActivityCreated()");
        super.onActivityCreated(savedInstanceState);
        if(savedInstanceState == null){
            return;
        }
        final float[] scrollPos = savedInstanceState.getFloatArray("SCROLL_POS");
        if(scrollPos == null){
            return;
        }
        scrollView.post(new Runnable() {
            @Override
            public void run() {
                scrollView.scrollTo((int)scrollPos[0], (int)scrollPos[1]);
            }
        });
    }

    @Override
    public void onResume() {
        Log.d("SAK", "HomeFragment::onResume()");
        super.onResume();
        Global.changeActionBarTitle(title);
    }

    // Adds New-Nrrivals & E-Resources to the Home Fragment
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("SAK", "HomeFragment::onCreateView()");
        this.view = inflater.inflate(R.layout.fragment_home, container, false);
        this.activity = (AppCompatActivity)view.getContext();
        scrollView = view.findViewById(R.id.fragment_home_scroll_view);
        fillEResources();
        addRecyclerViewForNewArrivals();
        addRecyclerViewForEResources();
        return view;
    }

    private void addRecyclerViewForNewArrivals(){
        RecyclerView recyclerView = view.findViewById(R.id.fragment_home_recycler_view_new_arrivals);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext(),
                LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(new HrzntlSliderNewArrivalsAdapter(view.getContext(),
                NewArrivalsFetcher.getCards()));
    }

    private void addRecyclerViewForEResources(){
        RecyclerView recyclerView = view.findViewById(R.id.fragment_home_recycler_view_e_resources);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext(),
                LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(new HrzntlSliderEResourcesAdapter(view.getContext(), eResourceCards));
    }

    public static String getTitle(){
        return  title;
    }

    // Prepares a list of E-Resources
    private void fillEResources(){
        int totalEResources = Integer.valueOf(view.getResources().getString(
                R.string.home_fragment_total_e_resources));
        Resources resources = view.getResources();
        eResourceCards = new ArrayList<EResourceCard>(totalEResources);
        EResourceCard eResourceCard1 = new EResourceCard(R.drawable.e_resource_acm,
                resources.getString(R.string.e_resource_url_acm));
        EResourceCard eResourceCard2 = new EResourceCard(R.drawable.e_resource_acs,
                resources.getString(R.string.e_resource_url_acs));
        EResourceCard eResourceCard3 = new EResourceCard(R.drawable.e_resource_elsevier,
                resources.getString(R.string.e_resource_url_elsevier));
        EResourceCard eResourceCard4 = new EResourceCard(R.drawable.e_resource_epw,
                resources.getString(R.string.e_resource_url_epw));
        EResourceCard eResourceCard5 = new EResourceCard(R.drawable.e_resource_ieee,
                resources.getString(R.string.e_resource_url_ieee));
        EResourceCard eResourceCard6 = new EResourceCard(R.drawable.e_resource_jstor,
                resources.getString(R.string.e_resource_url_jstor));
        EResourceCard eResourceCard7 = new EResourceCard(R.drawable.e_resource_nature,
                resources.getString(R.string.e_resource_url_nature));
        EResourceCard eResourceCard8 = new EResourceCard(R.drawable.e_resource_now,
                resources.getString(R.string.e_resource_url_now));
        EResourceCard eResourceCard9 = new EResourceCard(R.drawable.e_resource_saa,
                resources.getString(R.string.e_resource_url_saa));
        EResourceCard eResourceCard10 = new EResourceCard(R.drawable.e_resource_siam,
                resources.getString(R.string.e_resource_url_siam));
        EResourceCard eResourceCard11 = new EResourceCard(R.drawable.e_resource_springer_cse,
                resources.getString(R.string.e_resource_url_springer_cse));
        EResourceCard eResourceCard12 = new EResourceCard(R.drawable.e_resource_springer_link,
                resources.getString(R.string.e_resource_url_springer_link));
        EResourceCard eResourceCard13 = new EResourceCard(R.drawable.e_resource_tfo,
                resources.getString(R.string.e_resource_url_tfo));
        EResourceCard eResourceCard14 = new EResourceCard(R.drawable.e_resource_urkund,
                resources.getString(R.string.e_resource_url_urkund));
        EResourceCard eResourceCard15 = new EResourceCard(R.drawable.e_resource_wbl,
                resources.getString(R.string.e_resource_url_wbl));
        EResourceCard eResourceCard16 = new EResourceCard(R.drawable.e_resource_wbw,
                resources.getString(R.string.e_resource_url_wbw));
        eResourceCards.add(eResourceCard1);
        eResourceCards.add(eResourceCard2);
        eResourceCards.add(eResourceCard3);
        eResourceCards.add(eResourceCard4);
        eResourceCards.add(eResourceCard5);
        eResourceCards.add(eResourceCard6);
        eResourceCards.add(eResourceCard7);
        eResourceCards.add(eResourceCard8);
        eResourceCards.add(eResourceCard9);
        eResourceCards.add(eResourceCard10);
        eResourceCards.add(eResourceCard11);
        eResourceCards.add(eResourceCard12);
        eResourceCards.add(eResourceCard13);
        eResourceCards.add(eResourceCard14);
        eResourceCards.add(eResourceCard15);
        eResourceCards.add(eResourceCard16);
    }
}
