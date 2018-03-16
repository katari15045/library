package com.github.katari15045.iiitdlibrary.Static.Resources;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

import com.github.katari15045.iiitdlibrary.Main.NavDrawer;
import com.github.katari15045.iiitdlibrary.Misc.Global;
import com.github.katari15045.iiitdlibrary.R;
import com.github.katari15045.iiitdlibrary.Static.Resources.EResource.EResourcesFragment;

public class ResourcesFragment extends Fragment{

    private View view = null;
    private static String title = null;
    private ScrollView scrollView = null;

    public ResourcesFragment(){
        title = ((AppCompatActivity) Global.context).getResources().getString
                (R.string.nav_drawer_resources_title);
    }

    @Override
    public void onResume() {
        Log.d("SAK", "ResourceFragment::onResume()");
        super.onResume();
        NavDrawer.hideItem(1);
        Global.changeActionBarTitle(title);
    }

    // Storing & Retrieving Scroll position : https://stackoverflow.com/a/29208325/8279892
    @Override
    public void onSaveInstanceState(Bundle outState) {
        Log.d("SAK", "ResourceFragment::onSaveInstanceState()");
        super.onSaveInstanceState(outState);
        float[] scrollPos = {scrollView.getScrollX(), scrollView.getScrollY()};
        outState.putFloatArray("SCROLL_POS", scrollPos);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Log.d("SAK", "ResourceFragment::onActivityCreated()");
        super.onActivityCreated(savedInstanceState);
        if(savedInstanceState == null){
            return;
        }
        final float[] scrollPos = savedInstanceState.getFloatArray("SCROLL_POS");
        if(scrollPos != null){
            scrollView.post(new Runnable() {
                @Override
                public void run() {
                    scrollView.scrollTo((int)scrollPos[0], (int)scrollPos[1]);
                }
            });
        }
    }

    @Override
    public void onPause() {
        Log.d("SAK", "ResourceFragment::onPause()");
        super.onPause();
        NavDrawer.showItem(1);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("SAK", "ResourceFragment::onCreateView()");
        view = inflater.inflate(R.layout.fragment_resources, null);
        scrollView = view.findViewById(R.id.fragment_resources_scroll_view);
        setListeners();
        return  view;
    }

    private void setListeners(){
        CardView cardViewEResources = view.findViewById(R.id.fragment_resource_cardview_e_resources);
        cardViewEResources.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("SAK", "Clicked Resources::EResources");
                EResourcesFragment eResourcesFragment = new EResourcesFragment();
                Global.replaceFragment(eResourcesFragment);
            }
        });
    }

    public static String getTitle(){
        return title;
    }
}